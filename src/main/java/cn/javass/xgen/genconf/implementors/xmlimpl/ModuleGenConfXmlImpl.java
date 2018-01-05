package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.implementors.ModuleGenConfImplementor;
import cn.javass.xgen.genconf.implementors.dynamicparse.ParseContext;
import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.util.readxml.Context;
import cn.javass.xgen.util.readxml.Parser;
import cn.javass.xgen.util.readxml.ReadXmlExpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleGenConfXmlImpl implements ModuleGenConfImplementor{
    public ModuleConfModel getBaseModuleConfModel(Map<String, String> param) {
        ModuleConfModel mcm = new ModuleConfModel();
        this.parseModuleId(mcm,this.getContext(param));
        return mcm;
    }

    private void parseModuleId(ModuleConfModel mcm, Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addDot().addId().build());
        String[] ss = re.interpret(c);
        mcm.setModuleId(ss[0]);
    }

    private Context getContext(Map<String, String> param) {
        Context ctx = null;
        try {
            ctx = Context.newInstance(new ModuleGenConfBuilder().addXmlFilePre().addSeparator()
                                    .addOtherValue(param.get(ExpressionEnum.fileName.getExpr())).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctx;
    }

    public Map<String, List<String>> getMapNeedGenTypes(Map<String, String> param) {
        Map<String, List<String>>map = new HashMap<String, List<String>>();

        Context ctx = this.getContext(param);
        String[]needGenTypeIds = parseNeedGenTypeIds(ctx);

        for(int i=0;i<needGenTypeIds.length;i++){
            map.put(needGenTypeIds[i],parseNeedGenOutTypes(ctx,needGenTypeIds[i]));
        }

        return map;
    }

    private List<String> parseNeedGenOutTypes(Context ctx, String needGenTypeId) {
        ctx.init();
        List<String>list = new ArrayList<String>();
        ReadXmlExpression re = Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addSeparator().addNeedGenTypes()
                .addSeparator().addNeedGenType().addDollar().addOpenBracket().addId().addEqual().addOtherValue(needGenTypeId)
                .addCloseBracket().addSeparator().addNeedGenOutType().addDollar().addDot().addId().addDollar().build());
        String[] ss = re.interpret(ctx);
        for(String s : ss){
           list.add(s);
        }

        return list;
    }

    private String[] parseNeedGenTypeIds(Context ctx) {
        ctx.init();
        ReadXmlExpression re = Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addSeparator().addNeedGenTypes()
                .addSeparator().addNeedGenType().addDollar().addDot().addId().addDollar().build());
        return re.interpret(ctx);
    }

    public Map<String, ExtendConfModel> getMapExtends(GenConfModel gm, Map<String, String> param) {
        Map<String, ExtendConfModel>map = new HashMap<String, ExtendConfModel>();
        Context ctx = this.getContext(param);

        String[] extendIds = parseExtendIds(ctx);
        String[] isSingles = parseIsSingles(ctx);
        String[] values = parseValues(ctx);

        for (int i=0;i<extendIds.length;i++){
            ExtendConfModel model = new ExtendConfModel();
            model.setId(extendIds[i]);
            model.setSingle(Boolean.parseBoolean(isSingles[i]));
            model.setValue(values[i]);

            if(!model.isSingle()){
                model.setValues(model.getValue().split(ExpressionEnum.comma.getExpr()));
            }
            map.put(model.getId(),model);
        }
        //讲变量转换为所需值
        map = new ParseContext().parse(gm, map);

        return map;
    }


    private String[] parseValues(Context ctx) {
        ctx.init();
        ReadXmlExpression re = Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addSeparator().addExtendConfs().addSeparator()
                .addExtendConf().addDollar().build());
        return re.interpret(ctx);
    }

    private String[] parseIsSingles(Context ctx) {
        ctx.init();
        ReadXmlExpression re = Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addSeparator().addExtendConfs().addSeparator()
                .addExtendConf().addDollar().addDot().addIsSingle().addDollar().build());
        return re.interpret(ctx);
    }

    private String[] parseExtendIds(Context ctx) {
        ctx.init();
        ReadXmlExpression re = Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addSeparator().addExtendConfs().addSeparator()
                .addExtendConf().addDollar().addDot().addId().addDollar().build());
        return re.interpret(ctx);
    }
}
