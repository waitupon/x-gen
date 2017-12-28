package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.implementors.ThemeImplementor;
import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;
import cn.javass.xgen.util.readxml.Context;
import cn.javass.xgen.util.readxml.Parser;
import cn.javass.xgen.util.readxml.ReadXmlExpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/25 0025.
 */
public class GenConfXmlImpl implements GenConfImplementor{

    public List<NeedGenModel> getNeedGens() {
        return readNeedGends();
    }

    public List<ThemeModel> getThemes() {
        return readThemes();
    }


    public Map<String, String> getMapConstants() {
        return readMapConstants();
    }

    private Map<String,String> readMapConstants() {
            Map<String,String> map = new HashMap<String,String>();
            Context c = this.getContext();

            String [] ids = parseConstantIds(c);
            String [] values = parseConstantValues(c);

            for(int i=0;i<ids.length;i++){
                map.put(ids[i], values[i]);
            }
            return map;
    }

    private String[] parseConstantValues(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(
                new GenConfBuilder().addGenConf().addSeparator().addConstants().addSeparator()
                        .addConstant().addDollar()
                        .build()
        );
        return re.interpret(c);
    }

    private String[] parseConstantIds(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(
                new GenConfBuilder().addGenConf().addSeparator().addConstants().addSeparator()
                        .addConstant().addDollar()
                        .addDot().addId().addDollar()
                        .build()
        );
        return re.interpret(c);
    }


    private List<ThemeModel> readThemes() {
        List<ThemeModel>retList = new ArrayList<ThemeModel>();
        Context c = getContext();

        String[]ids = parseThemeIds(c);
        String[]locations = parseThemeLocations(c);

        for(int i=0;i<ids.length;i++){
            ThemeModel model = new ThemeModel();

            model.setId(ids[i]);
            model.setLocation(locations[i]);

            ThemeImplementor implementor = new ThemeXmlImpl();

            Map<String,String> param = new HashMap<String, String>();
            param.put(ExpressionEnum.location.getExpr(),locations[i]);

            model.setGenTypesMap(implementor.getGenTypesMap(ids[i],param));
            model.setGenOutTypesMap(implementor.getGenOutTypesMap(ids[i],param));
            model.setProvidersMap(implementor.getProvidersMap(ids[i],param));

            retList.add(model);
        }

        return retList;
    }

    private String[] parseThemeLocations(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addThemes().addSeparator()
                .addTheme().addDollar().build());
        return re.interpret(c);
    }

    private String[] parseThemeIds(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addThemes().addSeparator()
                                            .addTheme().addDollar().addDot().addId().addDollar().build());
        return re.interpret(c);
    }


    private List<NeedGenModel> readNeedGends() {
        List<NeedGenModel> retList = new ArrayList<NeedGenModel>();
        Context c = getContext();

        String[]needGenIds = parseNeedGenIds(c);
        String[]needGenProviders = parseNeedGenProviders(c);
        String[]needGenThemes = parseNeedGenThemes(c);

        for (int i=0; i<needGenIds.length;i++){
            NeedGenModel model = new NeedGenModel();
            model.setId(needGenIds[i]);
            model.setProvider(needGenProviders[i]);
            model.setThemeId(needGenThemes[i]);

            Map<String,String> params = new HashMap<String,String>();

            String[]paramIds = parseParamIds(c,needGenIds[i]);
            String[]paramValues = parseParamValues(c,needGenIds[i]);

            for(int j=0;j<paramIds.length;j++){
                params.put(paramIds[j],paramValues[j]);
            }
            model.setParams(params);

            retList.add(model);
        }


        return retList;
    }

    private String[] parseParamValues(Context c, String needGenId) {
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
                .addNeedGen().addDollar().addOpenBracket().addId().addEqual().addOtherValue(needGenId).addCloseBracket()
                .addSeparator().addParams().addDollar().addSeparator().addParam().addDollar()
                .build());
        return re.interpret(c);
    }

    private String[] parseParamIds(Context c, String needGenId) {
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
                .addNeedGen().addDollar().addOpenBracket().addId().addEqual().addOtherValue(needGenId).addCloseBracket()
                .addSeparator().addParams().addDollar().addSeparator().addParam().addDollar()
                .addDot().addId().addDollar()
                .build());
        return re.interpret(c);
    }

    private String[] parseNeedGenThemes(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
                .addNeedGen().addDollar().addDot().addThemeId().addDollar().build());
        return re.interpret(c);
    }

    private String[] parseNeedGenProviders(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
                .addNeedGen().addDollar().addDot().addProvider().addDollar().build());
        return re.interpret(c);
    }

    private String[] parseNeedGenIds(Context c) {
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
                                            .addNeedGen().addDollar().addDot().addId().build());
        return re.interpret(c);
    }

    private Context getContext() {
        Context c = null;
        try {
            c = Context.newInstance(new GenConfBuilder().addXmlFilePre().addSeparator().addGenConf().addDot().addXml().build() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

}
