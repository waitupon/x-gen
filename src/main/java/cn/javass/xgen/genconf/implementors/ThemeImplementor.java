package cn.javass.xgen.genconf.implementors;

import cn.javass.xgen.genconf.vo.GenTypeModel;

import java.util.Map;

public interface ThemeImplementor {
    public Map<String, GenTypeModel> getGenTypesMap(String themeId,Map<String,String> params);

    public Map<String, String> getGenOutTypesMap(String themeId,Map<String,String> params);

    public Map<String, String> getProvidersMap(String themeId,Map<String,String> params);
}
