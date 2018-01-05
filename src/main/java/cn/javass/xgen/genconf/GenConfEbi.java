package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public interface GenConfEbi {
    /**
     * 获取x-gen核心框架运行所需要的配置数据model
     * @return
     */
    public GenConfModel getGenConf();

    /**
     * 获取需要生成的所以模块的配置
     * @return
     */
    public Map<String,ModuleConfModel>getMapModuleConf();
}
