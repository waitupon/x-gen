package cn.javass.xgen.dispatch.executechain;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public class DefaultHandler extends GenHandler {
    //需要生成的标识
    private String needGenType = "";

    public DefaultHandler(String needGenType) {
        this.needGenType = needGenType;
    }


    @Override
    public void handleRequest(ModuleConfModel mcm) {
        //本职责对象要实现功能

        //交给父类，继续职责链的后续处理
        super.handleRequest(mcm);
    }
}
