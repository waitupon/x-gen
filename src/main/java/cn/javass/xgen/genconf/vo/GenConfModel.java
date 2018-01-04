package cn.javass.xgen.genconf.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public class GenConfModel {
   //用户配置项
   private List<NeedGenModel> needGens = new ArrayList<NeedGenModel>();
   //用户主题
   private List<ThemeModel> themes = new ArrayList<ThemeModel>();
   //通用变量
   private Map<String,String> mapConstants = new HashMap<String,String>();

    public List<NeedGenModel> getNeedGens() {
        return needGens;
    }

    public void setNeedGens(List<NeedGenModel> needGens) {
        this.needGens = needGens;
    }

    public List<ThemeModel> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeModel> themes) {
        this.themes = themes;
    }

    public Map<String, String> getMapConstants() {
        return mapConstants;
    }

    public void setMapConstants(Map<String, String> mapConstants) {
        this.mapConstants = mapConstants;
    }

    @Override
    public String toString() {
        return "GenConfModel{" +
                "needGens=" + needGens +
                ", themes=" + themes +
                ", mapConstants=" + mapConstants +
                '}';
    }

    public ThemeModel getThemeById(String themeId) {
        for(ThemeModel tm : this.themes){
            if(tm.getId().equals(themeId)){
                return tm;
            }
        }
        return new ThemeModel();
    }
}
