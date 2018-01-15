package cn.javass.xgen.dispatch;

import cn.javass.xgen.dispatch.command.CmdInvoker;
import cn.javass.xgen.dispatch.command.DefaultCommand;
import cn.javass.xgen.dispatch.command.GenCommand;
import cn.javass.xgen.genconf.GenConfFactory;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

import java.util.Collection;

public class GenFacade {
    /**

     */
    private GenFacade(){}

    /**
     * @param provider
     */
    public static void generate(GenConfImplementor provider){
        for(ModuleConfModel model : CoreMediator.getNeedGenModuleConf(provider)){
            genOneModule(model);
        }
    }

    private static void genOneModule(ModuleConfModel model) {
        GenCommand cmd = new DefaultCommand(model);

        CmdInvoker invoker = new CmdInvoker();

        invoker.setCmd(cmd);

        invoker.doCommand();
    }

}
