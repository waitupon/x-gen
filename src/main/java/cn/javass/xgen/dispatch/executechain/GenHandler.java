package cn.javass.xgen.dispatch.executechain;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public abstract class GenHandler {

    //持有后继的职责对象
    protected GenHandler successor;

    public void setSuccessor(GenHandler successor) {
        this.successor = successor;
    }

    public void handleRequest(ModuleConfModel mcm){
        //如果不属于自己的职责范畴，那就给后继的职责对象
        if(this.successor!=null){
            this.successor.handleRequest(mcm);
        }
    }
}
