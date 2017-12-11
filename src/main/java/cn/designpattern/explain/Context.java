package cn.designpattern.explain;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private Map<Variable,Boolean> map = new HashMap<Variable,Boolean>();

    public void assign(Variable variable,Boolean b){
        map.put(variable,b);
    }

    public boolean lookup(Variable variable) {
        Boolean b = map.get(variable);
        if(b==null){
            throw new IllegalArgumentException();
        }
        return b.booleanValue();
    }
}
