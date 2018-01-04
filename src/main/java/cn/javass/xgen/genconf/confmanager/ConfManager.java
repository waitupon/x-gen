package cn.javass.xgen.genconf.confmanager;

import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.implementors.ModuleGenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.genconf.vo.NeedGenModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public class ConfManager {
    private GenConfModel confModel = new GenConfModel();
    private static ConfManager manager = null;
    private Map<String,ModuleConfModel> moduleConfMap = new HashMap<String,ModuleConfModel>();


    private ConfManager(GenConfImplementor provider){
        readConf(provider);
    }

    public static ConfManager newInstance(GenConfImplementor provider){
        if(manager == null){
            manager = new ConfManager(provider);
        }
        return manager;
    }

    public  GenConfModel getGenConf(){
            return this.confModel;
    }

    public Map<String, ModuleConfModel> getModuleConfMap() {
        return moduleConfMap;
    }

    private void readConf(GenConfImplementor provider){
        readGenConf(provider);
        for(NeedGenModel ngm : provider.getNeedGens()){
            readOneModuleGenConf(ngm);
        }
    }

    private void readOneModuleGenConf(NeedGenModel ngm) {
        ModuleConfModel mcm = new ModuleConfModel();

        String providerClassName = this.confModel.getThemeById(ngm.getThemeId()).getProvidersMap().get(ngm.getProvider());

        ModuleGenConfImplementor implementor = null;

        try {
            implementor = (ModuleGenConfImplementor)Class.forName(providerClassName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mcm = implementor.getBaseModuleConfModel(ngm.getParams());
        mcm.setUseTheme(ngm.getThemeId());
        mcm.setNeedGenTypesMap(implementor.getMapNeedGenTypes(ngm.getParams()));
        mcm.setExtendConfsMap(implementor.getMapExtends(this.confModel,ngm.getParams()));

        this.moduleConfMap.put(mcm.getModuleId(),mcm);
    }

    private void readGenConf(GenConfImplementor provider) {
        getGenConf().setNeedGens(provider.getNeedGens());
        getGenConf().setThemes(provider.getThemes());
        getGenConf().setMapConstants(provider.getMapConstants());
    }
}
