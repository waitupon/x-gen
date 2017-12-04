package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.implementors.GenConfImplementor;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public class GenConfFactory {
    private GenConfFactory(){}

    public static GenConfEbi createGenConfEbi(GenConfImplementor provider){
        return  GenConfEbo.newInstance(provider);
    }
}
