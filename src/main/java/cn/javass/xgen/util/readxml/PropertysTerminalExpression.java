package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

import java.util.List;

public class PropertysTerminalExpression extends ReadXmlExpression{
    private String propertyName;

    public PropertysTerminalExpression(String propertyName) {
        this.propertyName = propertyName;
    }

    public String[] interpret(Context ctx) {
        List<Element> preEles = ctx.getPreEles();

        String[]ss = new String[preEles.size()];
        for(int i=0;i<ss.length;i++){
            ss[0] = preEles.get(i).getAttribute(propertyName);
        }

        return ss;
    }
}
