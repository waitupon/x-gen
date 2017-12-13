package cn.designpattern.composite;

public class FinanceDepartment extends Company {

    public FinanceDepartment(){

    }

    public FinanceDepartment(String name){
        super(name);
    }

    public void add(Company company) {

    }

    public void remove(Company company) {

    }

    public void display(int depth) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        System.out.println(new String(sb) + this.getName() ) ;
    }
}
