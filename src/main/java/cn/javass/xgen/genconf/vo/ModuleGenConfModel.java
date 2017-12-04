package cn.javass.xgen.genconf.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public class ModuleGenConfModel {
    private Map<String,List<String>> needGenTypes = new HashMap<String, List<String>>();
    private Map<String,ExtendConfModel> extendConfs = new HashMap<String, ExtendConfModel>();

    public Map<String, List<String>> getNeedGenTypes() {
        return needGenTypes;
    }

    public void setNeedGenTypes(Map<String, List<String>> needGenTypes) {
        this.needGenTypes = needGenTypes;
    }

    public Map<String, ExtendConfModel> getExtendConfs() {

        return extendConfs;
    }

    public void setExtendConfs(Map<String, ExtendConfModel> extendConfs) {
        this.extendConfs = extendConfs;
    }
}
