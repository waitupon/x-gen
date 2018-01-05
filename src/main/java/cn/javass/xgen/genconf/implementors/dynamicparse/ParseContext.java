package cn.javass.xgen.genconf.implementors.dynamicparse;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;

import java.util.Map;

public class ParseContext {

    public Map<String,ExtendConfModel> parse(GenConfModel gm,Map<String,ExtendConfModel>mapEcms){

        for(String key:mapEcms.keySet()){
            ExtendConfModel ecm = mapEcms.get(key);
            ecm.setValue(this.parseOne(gm,mapEcms,ecm.getValue()));
            mapEcms.put(key,ecm);
        }

        return mapEcms;
    }

    /**
     * 将变量解析为特定值
     * @param gm
     * @param mapEcms
     * @param value
     * @return
     */
    private String parseOne(GenConfModel gm, Map<String, ExtendConfModel> mapEcms, String value) {
        if(value!=null && value.indexOf("${")>=0){
            int begin = value.indexOf("${");
            int end = begin + "${".length() + value.substring(begin + "${".length()).indexOf("}");

            String prop = value.substring(begin+"${".length(),end);

            ParseStategy ps = null;
            if(isWord(prop)){
                ps = new PropertyReplaceStrategy();
            }else{
                ps = new BeanShellStategy();
            }

            String tempStr = ps.parseDynamicContent(gm,mapEcms,prop);

            value = value.replace("${" + prop + "}",tempStr);

            value = parseOne(gm,mapEcms,value);
        }
        return value;
    }

    private boolean isWord(String s) {
        char[] cs = s.toCharArray();
        for(char c:cs){
            if(
                    (c>='a' && c<='z')
                    ||
                    (c>='A' && c<='Z')
              )
            {

            }else{
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String value = "${ab}.${gm.getMapConstants().get('projectName');}.test";
        int begin = value.indexOf("${");
        int end = begin + "${".length() + value.substring(begin + "${".length()).indexOf("}");
        System.out.println("begin=" + begin + " , end=" + end);

    }
}
