package cn.designpattern.explain;

public class Not extends Expression{
    private Expression exp;

    public Not(Expression exp) {
        this.exp = exp;
    }

    public boolean interpret(Context ctx) {
        return !exp.interpret(ctx);
    }

    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof Not){
            return exp.equals(((Not)obj).exp);
        }
        return false;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public String toString() {
        return "(Not " + exp.toString() + ")";
    }
}
