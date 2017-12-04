package cn.javass.xgen.genconf.confmanager;

import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public class ConfManager {
    private GenConfModel confModel = new GenConfModel();
    private static ConfManager manager = null;


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

    private void readConf(GenConfImplementor provider){
        readGenConf(provider);
    }

    private void readGenConf(GenConfImplementor provider) {
        getGenConf().setNeedGens(provider.getNeedGens());
        getGenConf().setThemes(provider.getThemes());
        getGenConf().setMapConstants(provider.getMapConstants());
    }
}
