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

    private static final String BACKLASH = "/";
    private static final String DOT = ".";
    private static final String DOLLAR = "$";

    private static final String  OPEN_BRACKET = "[";
    private static final String  CLOSE_BRACKET = "]";

    //保证树形顺序
    private static List<String> listElePath = null;

    private Parser(){

    }

    private static class MementoImpl implements ParseMemento{
        private Map<String,ReadXmlExpression>mapRe = new HashMap<String, ReadXmlExpression>();

        public MementoImpl(Map<String,ReadXmlExpression>mapRe){
            this.mapRe = mapRe;
        }

        public Map<String,ReadXmlExpression> getMapRe(){
            return this.mapRe;
        }
    }

    public static ReadXmlExpression parse(String expr){
        ReadXmlExpression expression = null;
        //1：应该获取备忘录对象
        ParseMemento memento = ParseCaretaker.newInstance().retriveMemento();
        //2：从备忘录中取出数据
        Map<String,ReadXmlExpression> mapRe = null;
        if(mapRe == null){
            mapRe = new HashMap<String, ReadXmlExpression>();
        }else {
            mapRe = ((MementoImpl) memento).getMapRe();
        }
        //3：从缓存里面找到最长的相同的 string来，这部分就不用解析了
        String notParseExpr = searchMaxLongEquals(expr,mapRe);

        //4：获取剩下的需要解析的部分
        String needParseExpr = "";
        if(notParseExpr.trim().length() == 0){
            needParseExpr = expr;
        }else{
            if(notParseExpr.length() < expr.length()){
                needParseExpr = expr.substring(notParseExpr.length() + 1);
            }else{
                needParseExpr = "";
            }
        }

        //5：真正解析剩下的需要解析的 string,把两个部分的抽象语法树合并起来
        if(needParseExpr.trim().length()>0){
            expression = parse2(needParseExpr,notParseExpr,mapRe);
        }else{
            expression = mapRe.get(notParseExpr);
        }

        //6：解析完了，该重新设置 备忘录
        ParseCaretaker.newInstance().saveMemento(new MementoImpl(mapRe));

        return expression;
    }

    private static String searchMaxLongEquals(String expr, Map<String, ReadXmlExpression> mapRe) {
        boolean flag = mapRe.containsKey(expr);

        while (!flag) {
            int lastIndex = expr.lastIndexOf(BACKLASH);
            if(lastIndex >0){
                expr = expr.substring(0,lastIndex);
                flag = mapRe.containsKey(expr);
            }else{
                flag = true;
                expr = "";
            }
        }
        return expr;
    }


    public static ReadXmlExpression parse2(String needParseExpr,String notParseExpr,Map<String,ReadXmlExpression> mapRe){
        listElePath = new ArrayList<String>();
        //解析表达式，得到需要得到的解析名称，和对应的解析元素
        Map<String,ParseModel> mapPath = parseMapPath(needParseExpr);
        //根据元素对应的解析模型，转换解析器对象
        Map<String,ReadXmlExpression> mapPathAndRe = mapPath2Expression(mapPath);

        ReadXmlExpression prefixRe = mapRe.get(notParseExpr+BACKLASH);

        if(prefixRe!=null){
            prefixRe = (ReadXmlExpression) mapRe.get(notParseExpr+BACKLASH).clone();
        }
        //按照先后顺序组成抽象语法树
        ReadXmlExpression expression = buildTree(needParseExpr,prefixRe,mapPathAndRe,mapRe);
        
        return expression;
    }

    private static ReadXmlExpression buildTree(String prefixStr,ReadXmlExpression prefixRe,
                                               Map<String,ReadXmlExpression> mapPathAndRe,Map<String,ReadXmlExpression> mapRe) {
        ReadXmlExpression retRe = prefixRe;
        ReadXmlExpression preEx = getLastRE(prefixRe);

        for(String path : listElePath){
            ReadXmlExpression re = mapPathAndRe.get(path);

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

            //每次生成一个新的 抽象树对象，就应该添加到缓存里面，应该是把retRe 克隆一份
            if(prefixStr!=null && prefixStr.trim().length()>0){
                mapRe.put(prefixStr + BACKLASH + path , (ReadXmlExpression)re.clone());
            }else{
                mapRe.put(path, (ReadXmlExpression)re.clone());
            }
        }

        return retRe;
    }
    /**
     * 获取已经解析过的对象树的最后一个元素对象
     * @param prefixRe
     * @return
     */
    private static ReadXmlExpression getLastRE(ReadXmlExpression prefixRe) {

        return null;
    }

    private static Map<String,ReadXmlExpression> mapPath2Expression(Map<String, ParseModel> mapPath) {
        Map<String,ReadXmlExpression> map = new HashMap<String,ReadXmlExpression>();

        for(String key : listElePath){
            ParseModel model = mapPath.get(key);
            ReadXmlExpression expression = parseModel2ReadXmlExpression(model);
            map.put(key,expression);
        }

        return map;
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

    private static Map<String,ParseModel> parseMapPath(String expr) {
        //root/a/b/c.name
        Map<String,ParseModel> pathMap = new HashMap<String, ParseModel>();

        StringTokenizer tokenizer = new StringTokenizer(expr,BACKLASH);

        //从根开始的前缀路径
        StringBuffer buffer = new StringBuffer();

        while (tokenizer.hasMoreTokens()){
                String eleName = tokenizer.nextToken();
                if(tokenizer.hasMoreTokens()){
                    buffer.append(eleName + BACKLASH);
                    //不是最后一个
                    setParsePath(buffer,eleName,false,false,pathMap);
                }else{
                    //最后一个
                    int dotIndex = eleName.indexOf(DOT);
                    if(dotIndex > 0){
                        String eleName1 = eleName.substring(0,dotIndex);
                        String propName = eleName.substring(dotIndex+1);

                        buffer.append(eleName1 + DOT);
                        setParsePath(buffer,eleName1,false,false,pathMap);

                        buffer.append(propName);
                        setParsePath(buffer,propName,true,true,pathMap);
                    }else{
                        buffer.append(eleName);
                        setParsePath(buffer,eleName,false,true,pathMap);
                    }
                }
        }
        return pathMap;
    }

    public static void setParsePath(StringBuffer buffer,String eleName,boolean propertyValue,boolean end,Map<String, ParseModel> mapPath){
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

            mapPath.put(buffer.toString(),model);

            listElePath.add(buffer.toString());
    }


}
