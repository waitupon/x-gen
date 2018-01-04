package cn.javass.xgen.genconf.implementors;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

import java.util.List;
import java.util.Map;

public interface ModuleGenConfImplementor {

    public ModuleConfModel getBaseModuleConfModel(Map<String,String> param);

    public Map<String,List<String>> getMapNeedGenTypes(Map<String,String> param);

    public Map<String,ExtendConfModel> getMapExtends(GenConfModel gm, Map<String,String> param);
}
