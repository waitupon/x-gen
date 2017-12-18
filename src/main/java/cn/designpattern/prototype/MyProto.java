package cn.designpattern.prototype;

import java.util.ArrayList;
import java.util.List;

public class MyProto extends Prototype{

    private String name;

    private List<String> list = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MyProto{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        MyProto obj = null;
        obj = (MyProto)super.clone();
        List cloneList = new ArrayList();
        for(String str : list){
            cloneList.add(str);
        }
        obj.setList(cloneList);
        return obj;
    }


    public static void main(String[] args) throws Exception {
        MyProto p = new MyProto();
        p.setName("tom");
        List<String> list = new ArrayList<String>();
        list.add("h1");
        list.add("h2");
        p.setList(list);

        System.out.println(p.clone());
    }
}
