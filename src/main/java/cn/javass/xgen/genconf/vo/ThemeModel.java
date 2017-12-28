package cn.javass.xgen.genconf.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public class ThemeModel {
    private String id;
    private String location;

    private Map<String,GenTypeModel> genTypesMap = new HashMap<String,GenTypeModel>();

    private Map<String,String> genOutTypesMap = new HashMap<String,String>();

    private Map<String,String> providersMap = new HashMap<String,String>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, GenTypeModel> getGenTypesMap() {
        return genTypesMap;
    }

    public void setGenTypesMap(Map<String, GenTypeModel> genTypesMap) {
        this.genTypesMap = genTypesMap;
    }

    public Map<String, String> getGenOutTypesMap() {
        return genOutTypesMap;
    }

    public void setGenOutTypesMap(Map<String, String> genOutTypesMap) {
        this.genOutTypesMap = genOutTypesMap;
    }

    public Map<String, String> getProvidersMap() {
        return providersMap;
    }

    public void setProvidersMap(Map<String, String> providersMap) {
        this.providersMap = providersMap;
    }

    @Override
    public String toString() {
        return "ThemeModel{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", genTypesMap=" + genTypesMap +
                ", genOutTypesMap=" + genOutTypesMap +
                ", providersMap=" + providersMap +
                '}';
    }
}
