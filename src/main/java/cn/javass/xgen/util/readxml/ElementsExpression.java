package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ElementsExpression extends ReadXmlExpression{
    private List<ReadXmlExpression> eles = new ArrayList<ReadXmlExpression>();

    private String eleName;

    //判断的条件
    private String condition;

    public ElementsExpression(String eleName, String condition) {
        this.eleName = eleName;
        this.condition = condition;
    }

    public List<ReadXmlExpression> getEles() {
        return eles;
    }

    public void setEles(List<ReadXmlExpression> eles) {
        this.eles = eles;
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

    public void addEle(ReadXmlExpression ele){
        this.eles.add(ele);
    }

    public void removeEle(ReadXmlExpression ele){
        this.eles.remove(ele);
    }

    public void removeAll(){
        this.eles.clear();
    }

    public String[] interpret(Context ctx) {
        List<Element> preEles = ctx.getPreEles();

        List<Element> nowEles = new ArrayList<Element>();

        for(Element pEle : preEles){
            nowEles.addAll(ctx.getNowEles(pEle, eleName));
        }

        Iterator<Element> it = nowEles.iterator();
        while (it.hasNext()){
            Element ele = it.next();
            if(!ctx.judgeCondition(ele,condition)){
                it.remove();
            }
        }
        ctx.setPreEles(nowEles);

        String ss[] = null;
        for(ReadXmlExpression expression : eles){
           ss = expression.interpret(ctx);
        }

        return ss;
    }
}
