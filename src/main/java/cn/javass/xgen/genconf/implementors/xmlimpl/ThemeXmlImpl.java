package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.implementors.ThemeImplementor;
import cn.javass.xgen.genconf.vo.GenTypeModel;
import cn.javass.xgen.util.readxml.Context;
import cn.javass.xgen.util.readxml.Parser;
import cn.javass.xgen.util.readxml.ReadXmlExpression;

import java.util.HashMap;
import java.util.Map;

public class ThemeXmlImpl implements ThemeImplementor{

    public Map<String, GenTypeModel> getGenTypesMap(String themeId, Map<String, String> params) {
        Map<String, GenTypeModel> map = new HashMap<String, GenTypeModel>();
        Context c = this.getContext(params);

        String[]genTypeIds = parseGenTypeIds(c);
        String[]genTypeValues = parseGenTypeValues(c);

        for (int i=0;i<genTypeIds.length;i++){
            GenTypeModel model = new GenTypeModel();
            model.setId(genTypeIds[i]);
            model.setGenTypeClass(genTypeValues[i]);

            Map<String,String> paramMap = new HashMap<String,String>();
            String [] paramIds = parseGenTypeParamIds(c,model.getId());
            String [] paramValues = parseGenTypeParamValues(c,model.getId());

            for(int j=0;j<paramIds.length;j++){
                paramMap.put(paramIds[j],paramValues[j]);
            }
            model.setMapParams(paramMap);

            map.put(model.getId(),model);
        }

        return map;
    }

    public Map<String, String> getGenOutTypesMap(String themeId, Map<String, String> params) {
        Map<String, String> map = new HashMap<String, String>();

        Context c = this.getContext(params);
        String[]genOutTypeIds = parseGenOutTypeIds(c);
        String[]genOutTypeValues = parseGenOutTypeValues(c);

        for(int i=0;i<genOutTypeIds.length;i++){
            map.put(genOutTypeIds[i],genOutTypeValues[i]);
        }

        return map;
    }

    public Map<String, String> getProvidersMap(String themeId, Map<String, String> params) {
        Map<String, String> map = new HashMap<String, String>();

        Context c = this.getContext(params);
        String[]genProvideIds = parseProvideIds(c);
        String[]genProvideValues = parseProvideValues(c);

        for(int i=0;i<genProvideIds.length;i++){
            map.put(genProvideIds[i],genProvideValues[i]);
        }

        return map;
    }

    private String[] parseProvideValues(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addProviders().addSeparator().addProvider().addDollar()
                        .addDot().addType().addDollar()
                        .build()
        );
        return re.interpret(c);
    }

    private String[] parseProvideIds(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addProviders().addSeparator().addProvider().addDollar()
                        .addDot().addId().addDollar()
                        .build()
        );
        return re.interpret(c);
    }


    private String[] parseGenOutTypeValues(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addGenOutTypes().addSeparator().addGenOutType().addDollar()
                        .addDot().addType().addDollar()
                        .build()
        );
        return re.interpret(c);
    }

    private String[] parseGenOutTypeIds(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addGenOutTypes().addSeparator().addGenOutType().addDollar()
                        .addDot().addId().addDollar()
                        .build()
        );
        return re.interpret(c);
    }



    private String[] parseGenTypeParamValues(Context c, String genTypeId) {
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addGenTypes().addSeparator().addGenType().addDollar()
                        .addOpenBracket().addId().addEqual().addOtherValue(genTypeId).addCloseBracket().addSeparator()
                        .addParams().addSeparator()
                        .addParam().addDollar()
                        .build()
        );
        return re.interpret(c);
    }

    private String[] parseGenTypeParamIds(Context c, String genTypeId) {
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addGenTypes().addSeparator().addGenType().addDollar()
                        .addOpenBracket().addId().addEqual().addOtherValue(genTypeId).addCloseBracket().addSeparator()
                        .addParams().addSeparator()
                        .addParam().addDollar()
                        .addDot().addId().addDollar()
                        .build()
        );
        return re.interpret(c);
    }

    private String[] parseGenTypeValues(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(new ThemeBuilder().addTheme().addSeparator().addGenTypes().addSeparator()
                .addGenType().addDollar().addDot().addType().addDollar().build());
        return re.interpret(c);
    }

    private String[] parseGenTypeIds(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(new ThemeBuilder().addTheme().addSeparator().addGenTypes().addSeparator()
                                                        .addGenType().addDollar().addDot().addId().addDollar().build());
        return re.interpret(c);
    }



    private Context getContext(Map<String, String> params) {
        Context c = null;
        try {
            c = Context.newInstance(params.get(ExpressionEnum.location.getExpr())
                                               + ExpressionEnum.separator.getExpr()
                                               +ExpressionEnum.themeXmlName.getExpr());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }


}
