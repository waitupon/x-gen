package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.confmanager.ConfManager;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public class GenConfEbo implements GenConfEbi{
       private static GenConfEbo genConfEbo = null;
       private GenConfImplementor provider = null;

       private GenConfEbo(GenConfImplementor provider){
              this.provider = provider;
       }

       public static GenConfEbo newInstance(GenConfImplementor provider){
              if(genConfEbo == null){
                     if(provider == null){
                          Assert.assertNotNull(provider);
                     }
                     genConfEbo = new GenConfEbo(provider);
              }
           return genConfEbo;
       }

       public GenConfModel getGenConf() {
              return ConfManager.newInstance(provider).getGenConf();
       }

    public Map<String, ModuleConfModel> getMapModuleConf() {
        return  ConfManager.newInstance(provider).getModuleConfMap();
    }

}
