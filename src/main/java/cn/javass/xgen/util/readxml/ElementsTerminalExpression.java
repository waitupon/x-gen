package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ElementsTerminalExpression extends ReadXmlExpression{

    private String eleName;

    //判断的条件
    private String condition;

    public ElementsTerminalExpression(String eleName, String condition) {
        this.eleName = eleName;
        this.condition = condition;
    }

    public String getEleName() {
        return eleName;
    }

    public void setEleName(String eleName) {
        this.eleName = eleName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String[] interpret(Context ctx) {

        List<Element> pEles = ctx.getPreEles();

        List<Element> nowEles = new ArrayList<Element>();
        for(Element ele : pEles){
            nowEles.addAll(ctx.getNowEles(ele,eleName));
        }

        Iterator<Element> it = nowEles.iterator();
        while(it.hasNext()){
            Element ele = it.next();
            if(!ctx.judgeCondition(ele,condition)){
                    it.remove();
            }
        }


        String[]ss = new String[nowEles.size()];

        for(int i=0;i<nowEles.size();i++){
            Element element = nowEles.get(i);
            if(element.getFirstChild()!=null){
                ss[i] = element.getFirstChild().getNodeValue();
            }else {
                ss[i] = "";
            }
        }


        return ss;
    }
}
