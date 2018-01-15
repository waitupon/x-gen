package cn.javass.xgen.mediator;

import cn.javass.xgen.genconf.GenConfFactory;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

import java.util.Collection;

public class CoreMediator {

    private static CoreMediator mediator = new CoreMediator();

    private CoreMediator(){

    }

    public static CoreMediator getInstance(){
        return mediator;
    }

    public static Collection<ModuleConfModel>getNeedGenModuleConf(GenConfImplementor provider){
        return GenConfFactory.createGenConfEbi(provider).getMapModuleConf().values();
    }
}
