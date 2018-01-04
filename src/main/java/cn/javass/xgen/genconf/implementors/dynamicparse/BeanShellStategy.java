package cn.javass.xgen.genconf.implementors.dynamicparse;

import bsh.EvalError;
import bsh.Interpreter;
import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;

import java.util.HashMap;
import java.util.Map;

public class BeanShellStategy implements ParseStategy{

    public String parseDynamicContent(GenConfModel gm, Map<String, ExtendConfModel> mapEcms, String expr) {
        Interpreter interpreter = new Interpreter();
        String str = "";
        try {
            interpreter.set("gm",gm);
            interpreter.set("mapEcms",mapEcms);
            interpreter.eval("retValue="+expr);
            str = interpreter.get("retValue").toString();
        } catch (EvalError evalError) {
            evalError.printStackTrace();
        }
        return str;
    }


    public static void main(String[] args) {
        Map<String,String>map = new HashMap<String, String>();
        map.put("a1","po");
        String str = "";
        Interpreter interpreter = new Interpreter();
        try {
            interpreter.set("mapEcms",map);
            String expr = "mapEcms.get(\"a1\");";
            interpreter.eval("retValue="+expr);
            str = interpreter.get("retValue").toString();
            System.out.println(str);
        } catch (EvalError evalError) {
            evalError.printStackTrace();
        }
    }
}
