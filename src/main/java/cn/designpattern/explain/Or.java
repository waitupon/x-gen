package cn.designpattern.explain;

public class Or extends Expression{
    private Expression left,right;

    public Or(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public boolean interpret(Context ctx) {
        return left.interpret(ctx) || right.interpret(ctx);
    }

    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof Or){
            return this.left.equals(obj) || this.right.equals(obj);
        }
        return false;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public String toString() {
        return "(" + left.toString() + " OR " + right.toString() + ")";
    }
}
