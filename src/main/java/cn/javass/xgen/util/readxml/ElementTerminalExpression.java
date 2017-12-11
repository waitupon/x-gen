package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class ElementTerminalExpression extends ReadXmlExpression{

    private String eleName;

    //判断的条件
    private String condition;

    public ElementTerminalExpression(String eleName, String condition) {
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
        //1 获取到自己的元素
        //1.1  先获取父节点
        List<Element> pEles = ctx.getPreEles();

        //1.2 通过父节点找到自己的元素
        Element ele = null;

        if(pEles.size()==0){
            //根节点
            ele = ctx.getDocument().getDocumentElement();
        }else{
            ele = ctx.getNowEles(pEles.get(0),eleName).get(0);
        }

        if(!ctx.judgeCondition(ele,condition)){
            return new String[0];
        }

        String[]ss = new String[1];
        if(ele.getFirstChild()!=null){
            ss[0] = ele.getFirstChild().getNodeValue();
        }else{
            ss[0] = "";
        }

        return ss;
    }
}
