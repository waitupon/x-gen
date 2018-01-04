package cn.javass.xgen.genconf.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleConfModel {
    private String moduleId;
    private String useTheme;
    private Map<String,List<String>> needGenTypesMap = new HashMap<String,List<String>>();
    private Map<String,ExtendConfModel>extendConfsMap = new HashMap<String, ExtendConfModel>();


    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getUseTheme() {
        return useTheme;
    }

    public void setUseTheme(String useTheme) {
        this.useTheme = useTheme;
    }

    public Map<String, List<String>> getNeedGenTypesMap() {
        return needGenTypesMap;
    }

    public void setNeedGenTypesMap(Map<String, List<String>> needGenTypesMap) {
        this.needGenTypesMap = needGenTypesMap;
    }

    public Map<String, ExtendConfModel> getExtendConfsMap() {
        return extendConfsMap;
    }

    public void setExtendConfsMap(Map<String, ExtendConfModel> extendConfsMap) {
        this.extendConfsMap = extendConfsMap;
    }

    @Override
    public String toString() {
        return "ModuleConfModel{" +
                "moduleId='" + moduleId + '\'' +
                ", useTheme='" + useTheme + '\'' +
                ", needGenTypesMap=" + needGenTypesMap +
                ", extendConfsMap=" + extendConfsMap +
                '}';
    }
}
