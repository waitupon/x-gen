package cn.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

public class ConcreteCompany extends Company{
    private List<Company> list;

    public ConcreteCompany(String name) {
        super(name);
        list = new ArrayList<Company>();
    }

    public ConcreteCompany(){
        list = new ArrayList<Company>();
    }

    public void add(Company company) {
        list.add(company);
    }

    public void remove(Company company) {
        list.remove(company);
    }

    public void display(int depth) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        System.out.println(new String(sb) + this.getName());
        for (Company c : list) {
            c.display(depth + 2);
        }
    }
}
