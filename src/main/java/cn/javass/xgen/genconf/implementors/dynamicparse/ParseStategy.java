package cn.javass.xgen.genconf.implementors.dynamicparse;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;

import java.util.Map;

public interface ParseStategy {
    /**
     * 在模块中需要动态解析的字符串的策略接口
     * @return
     */
    public String parseDynamicContent(GenConfModel gm, Map<String,ExtendConfModel> mapEcms, String expr);
}
