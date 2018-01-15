package cn.javass.xgen.dispatch.command;

import cn.javass.xgen.dispatch.executechain.DefaultHandler;
import cn.javass.xgen.dispatch.executechain.GenHandler;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultCommand implements GenCommand {

    private ModuleConfModel moduleConfModel;

    public DefaultCommand(ModuleConfModel moduleConfModel) {
        this.moduleConfModel = moduleConfModel;
    }

    public void execute() {
        List<String> listNeedgGenTypes = new ArrayList<String>();

        for(String s : moduleConfModel.getNeedGenTypesMap().keySet()){
            listNeedgGenTypes.add(s);
        }

        Map<Integer,GenHandler> handlerMap = new HashMap<Integer,GenHandler>();
        for(int i=0;i<handlerMap.values().size();i++){
            handlerMap.put(i+1,new DefaultHandler(listNeedgGenTypes.get(i)));
        }

        GenHandler h1 = handlerMap.get(1);
        for(int i=0;i<handlerMap.values().size();i++){
            handlerMap.get(i).setSuccessor(handlerMap.get(i+1));
        }

        h1.handleRequest(moduleConfModel);
    }
}
