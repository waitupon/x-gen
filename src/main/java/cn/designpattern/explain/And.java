package cn.designpattern.explain;

public class And extends Expression{
    private Expression left,right;

    public And(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public boolean interpret(Context ctx) {
        return  left.interpret(ctx) && right.interpret(ctx);
    }

    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof And){
            return left.equals((And)obj) && right.equals((And)obj);
        }
        return false;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public String toString() {
        return "(" + left.toString() + " AND " + right.toString() + ")";
    }
}
