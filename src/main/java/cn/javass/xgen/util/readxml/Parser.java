package cn.javass.xgen.util.readxml;

import java.util.*;

/**
 * 将  /a/b[id=z]/c$.id$
 * 转化成需要的解释器元素
 */
public class Parser {
    /**
     *   特殊字符定义
     */

    private static final String backslash = "/";
    private static final String DOT = ".";
    private static final String DOLLAR = "$";

    private static final String  OPEN_BRACKET = "[";
    private static final String  CLOSE_BRACKET = "]";

    //保证树形顺序
    private static List<String> listEle = null;

    private Parser(){

    }


    public static ReadXmlExpression parse(String expr){
        listEle = new ArrayList<String>();
        //解析表达式，得到需要得到的解析名称，和对应的解析元素
        Map<String,ParseModel> mapPath = parsePathMap(expr);
        //根据元素对应的解析模型，转换解析器对象
        List<ReadXmlExpression> list = mapPath2Expression(mapPath);
        //按照先后顺序组成抽象语法树
        ReadXmlExpression expression = buildTree(list);
        
        return expression;
    }

    private static ReadXmlExpression buildTree(List<ReadXmlExpression> list) {
        ReadXmlExpression retRe = null;
        ReadXmlExpression preEx = null;

        for(ReadXmlExpression re : list){
            if(preEx == null){
                retRe = re;
                preEx = re;
            }else{
                if(preEx instanceof ElementExpression){
                    ElementExpression ele = (ElementExpression)preEx;
                    ele.addEle(re);

                    preEx = re;
                }else if(preEx instanceof ElementsExpression){
                    ElementsExpression ele = (ElementsExpression)preEx;
                    ele.addEle(preEx);

                    preEx = re;
                }
            }
        }

        return retRe;
    }

    private static List<ReadXmlExpression> mapPath2Expression(Map<String, ParseModel> mapPath) {
        List<ReadXmlExpression>list = new ArrayList<ReadXmlExpression>();

        for(String key : listEle){
            ParseModel model = mapPath.get(key);
            ReadXmlExpression expression = parseModel2ReadXmlExpression(model);
            list.add(expression);
        }

        return list;
    }

    private static ReadXmlExpression parseModel2ReadXmlExpression(ParseModel model) {
        ReadXmlExpression obj = null;
        if(!model.isEnd()){
                if(model.isSingleValue()){
                    obj = new ElementExpression(model.getEleName(),model.getCondition());
                }else{
                    obj = new ElementsExpression(model.getEleName(),model.getCondition());
                }
        }else {
               if(model.isPropertyValue()){
                   if(model.isSingleValue()){
                        obj = new PropertyTerminalExpression(model.getEleName());
                   }else{
                        obj = new PropertysTerminalExpression(model.getEleName());
                   }
               }else{
                   if(model.isSingleValue()){
                       obj = new ElementTerminalExpression(model.getEleName(),model.getCondition());
                   }else{
                       obj = new ElementsTerminalExpression(model.getEleName(),model.getCondition());
                   }
               }

        }

        return obj;
    }

    private static Map<String,ParseModel> parsePathMap(String expr) {
        Map<String,ParseModel> pathMap = new HashMap<String, ParseModel>();

        StringTokenizer tokenizer = new StringTokenizer(expr,"/");
        while (tokenizer.hasMoreTokens()){
                String eleName = tokenizer.nextToken();
                if(tokenizer.hasMoreTokens()){
                    //不是最后一个
                    setParsePath(eleName,false,false,pathMap);
                }else{
                    //最后一个
                    int dotIndex = eleName.indexOf(DOT);
                    if(dotIndex > 0){
                        String eleName1 = eleName.substring(0,dotIndex);
                        String propName = eleName.substring(dotIndex+1);

                        setParsePath(eleName1,false,false,pathMap);
                        setParsePath(propName,true,true,pathMap);
                    }else{
                        setParsePath(eleName,false,true,pathMap);
                    }
                }
        }
        return pathMap;
    }

    public static void setParsePath(String eleName,boolean propertyValue,boolean end,Map<String, ParseModel> mapPath){
            ParseModel model = new ParseModel();
            model.setPropertyValue(propertyValue);
            model.setEnd(end);
            model.setSingleValue( !(eleName.indexOf(DOLLAR)  >0) );

            eleName = eleName.replace(DOLLAR,"");

            int tempBegin = 0;
            int tempEnd = 0;

            if((tempBegin = eleName.indexOf(OPEN_BRACKET)) > 0){
                    tempEnd = eleName.indexOf(CLOSE_BRACKET);
                    model.setCondition(eleName.substring(tempBegin + 1,tempEnd));
                    eleName = eleName.substring(0,tempBegin);
            }

            model.setEleName(eleName);

            mapPath.put(eleName,model);

            listEle.add(eleName);
    }


}
