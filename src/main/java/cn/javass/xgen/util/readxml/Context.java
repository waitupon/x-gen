package cn.javass.xgen.util.readxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {
    private Document document = null;

    private static Context context = null;

    //父级节点
    private List<Element> preEles = null;

    //根据不同文件路径 缓存上下文对象
    private static Map<String,Context> mapCtx = new HashMap<String,Context>();


    private Context(Document document){
        this.document = document;
    }


    public static Context newInstance(String filePathName) throws Exception {
        Context context = mapCtx.get(filePathName);
        if(context==null){
            Document document = XmlUtil.getDocument(filePathName);
            context = new Context(document);
            mapCtx.put(filePathName,context);
        }
        context.init();
        return context;
    }

    public void init(){
        preEles = new ArrayList<Element>();
    }

    public Document getDocument() {
        return document;
    }

    public List<Element> getPreEles() {
        return preEles;
    }

    public void setPreEles(List<Element> preEles) {
        this.preEles = preEles;
    }

    public List<Element> getNowEles(Element pEle, String eleName) {
        List<Element> nowEles = new ArrayList<Element>();
        NodeList childNodes = pEle.getChildNodes();
        for (int i=0;i<childNodes.getLength();i++){
                if(childNodes.item(i) instanceof Element){
                    Element nowEle = (Element) childNodes.item(i);
                    if(nowEle.getTagName().equals(eleName)){
                        nowEles.add(nowEle);
                    }
                }
        }
        return nowEles;
    }

    public boolean judgeCondition(Element ele, String condition) {
        if(condition == null || condition.trim().length()==0){
            return true;
        }

        String[] ss = condition.split("=");
        if(ss[1]!=null && ss[1].equals(ele.getAttribute(ss[0]))){
            return true;
        }

        return false;
    }
}
