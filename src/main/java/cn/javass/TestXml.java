package cn.javass;

import cn.javass.xgen.genconf.GenConfEbi;
import cn.javass.xgen.genconf.GenConfFactory;
import cn.javass.xgen.genconf.implementors.xmlimpl.GenConfBuilder;
import cn.javass.xgen.genconf.implementors.xmlimpl.GenConfXmlImpl;
import cn.javass.xgen.util.readxml.*;
import org.junit.Test;


public class TestXml {

    @Test
    public void testModelLoadXml(){
        GenConfEbi genConfEbi = GenConfFactory.createGenConfEbi(new GenConfXmlImpl());
        System.out.println(genConfEbi.getGenConf());

    }

    @Test
    public void testParseXml() throws Exception {
//            ElementExpression genConf = new ElementExpression("GenConf","");
//            ElementExpression themes = new ElementExpression("Themes","");
//            ElementExpression theme = new ElementExpression("Theme","");
//            PropertyTerminalExpression id = new PropertyTerminalExpression("id");
//            genConf.addEle(themes);
//            themes.addEle(theme);
//            theme.addEle(id);
//
//            ElementExpression needGens = new ElementExpression("NeedGens","");
//            ElementExpression needGen = new ElementExpression("NeedGen","");
//            ElementExpression params = new ElementExpression("Params","");
//
//            ElementsTerminalExpression param = new ElementsTerminalExpression("Param","");
//
//            genConf.addEle(needGens);
//            needGens.addEle(needGen);
//            needGen.addEle(params);
//            params.addEle(param);
        System.out.println(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
                .addNeedGen().addDollar().addDot().addProvider().addDollar().build());
            Context ctx = Context.newInstance("xgenconfxml/GenConf.xml");


            String[] ss = Parser.parse("GenConf/NeedGens/NeedGen$.id$").interpret(ctx);
            String[] ss2 = Parser.parse("GenConf/NeedGens/NeedGen$.provider$").interpret(ctx);
           // String[] ss2 = Parser.parse("GenConf/NeedGens/NeedGen/Params/Param$[id=fileName1]").interpret(ctx);
            for (int i = 0; i < ss.length; i++) {//
                System.out.println("ss = " + ss[i]);
                System.out.println("ss2 = " + ss2[i]);
            }

    }


}
