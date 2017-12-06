package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class ElementExpression extends ReadXmlExpression{

    private List<ReadXmlExpression> eles = new ArrayList<ReadXmlExpression>();

    private String element;

    //判断的条件
    private String condition;

    public List<ReadXmlExpression> getEles() {
        return eles;
    }

    public void setEles(List<ReadXmlExpression> eles) {
        this.eles = eles;
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


    public ElementExpression(String element, String condition) {
        this.element = element;
        this.condition = condition;
    }

    public String[] interpret(Context ctx) {
        List<Element> preEles = ctx.getPreEles();

        Element ele = null;

        if(preEles.size() == 0){
            ele = ctx.getDocument().getDocumentElement();
        }

        return new String[0];
    }
}
