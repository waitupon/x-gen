package cn.javass.xgen.genconf.implementors;

import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/4 0004.
 */
public interface GenConfImplementor {

    List<NeedGenModel> getNeedGens();

    List<ThemeModel> getThemes();

    Map<String,String> getMapConstants();
}
