package cn.javass.xgen.util.readxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    private void init(){
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
}
