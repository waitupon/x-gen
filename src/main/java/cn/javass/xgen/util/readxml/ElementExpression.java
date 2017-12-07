package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class ElementExpression extends ReadXmlExpression{

    private List<ReadXmlExpression> eles = new ArrayList<ReadXmlExpression>();

    private String eleName;

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


    public ElementExpression(String eleName, String condition) {
        this.eleName = eleName;
        this.condition = condition;
    }

    public String[] interpret(Context ctx) {
        List<Element> preEles = ctx.getPreEles();
        List<Element>nowEles = new ArrayList<Element>();

        Element ele = null;

        if(preEles.size() == 0){
            ele = ctx.getDocument().getDocumentElement();
            preEles.add(ele);
            ctx.setPreEles(preEles);
        }else{
            for(Element pEle : preEles){
                nowEles.addAll(ctx.getNowEles(pEle, eleName));
                if(nowEles.size()>0){
                    break;
                }
            }


            if(nowEles.size()>0 && ctx.judgeCondition(nowEles.get(0),condition)){
                List<Element>tempList = new ArrayList<Element>();
                tempList.add(nowEles.get(0));
                ctx.setPreEles(tempList);
            }
        }


        String[]ss = null;
        for(ReadXmlExpression tempEle : eles){
            ss = tempEle.interpret(ctx);
        }

        return ss;
    }
}
