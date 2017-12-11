package cn.javass;

import cn.javass.xgen.util.readxml.*;

import java.io.InputStream;

public class TestXml {

    public static void main(String[] args) throws Exception {
        ElementExpression genConf = new ElementExpression("GenConf","");
        ElementExpression themes = new ElementExpression("Themes","");
        ElementExpression theme = new ElementExpression("Theme","");
        PropertyTerminalExpression id = new PropertyTerminalExpression("id");
        genConf.addEle(themes);
        themes.addEle(theme);
        theme.addEle(id);

//        ElementExpression needGens = new ElementExpression("NeedGens","");
//        ElementExpression needGen = new ElementExpression("NeedGen","");
//        ElementExpression params = new ElementExpression("Params","");
//
//        ElementTerminalExpression param = new ElementTerminalExpression("Param","");
//
//        genConf.addEle(needGens);
//        needGens.addEle(needGen);
//        needGen.addEle(params);
//        params.addEle(param);

        Context ctx = Context.newInstance("xgenconfxml/GenConf.xml");
        String[] ss = genConf.interpret(ctx);
        System.out.println(ss[0]);

    }
}
