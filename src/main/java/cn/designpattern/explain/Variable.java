package cn.designpattern.explain;

public class Variable extends Expression{
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean interpret(Context ctx) {
        return ctx.lookup(this);
    }

    public boolean equals(Object obj) {
        if(obj instanceof Variable){
               return this.name.equals(((Variable) obj).getName());
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return this.name;
    }
}
