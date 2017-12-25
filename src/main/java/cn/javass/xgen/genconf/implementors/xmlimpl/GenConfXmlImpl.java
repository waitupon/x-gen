package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/25 0025.
 */
public class GenConfXmlImpl implements GenConfImplementor{

    public List<NeedGenModel> getNeedGens() {
        return readNeedGends();
    }

    public List<ThemeModel> getThemes() {
        return null;
    }

    public Map<String, String> getMapConstants() {
        return null;
    }


    private List<NeedGenModel> readNeedGends() {
        return null;
    }

}
