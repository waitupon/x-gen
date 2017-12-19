package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

public class PropertyTerminalExpression extends ReadXmlExpression{
    private String propertyName;

    public PropertyTerminalExpression(String propertyName) {
        this.propertyName = propertyName;
    }

    public String[] interpret(Context ctx) {
        Element element = ctx.getPreEles().get(0);

        String[]ss = new String[1];
        ss[0] = element.getAttribute(propertyName);
        return ss;
    }

    @Override
    public Object clone(){
        Object obj = null;
        try{
            obj = super.clone();
        }catch(Exception err){
            err.printStackTrace();
        }

        return obj;
    }
}
