package cn.javass;

import cn.designpattern.explain.*;
import org.junit.Test;

public class TestExplain {

  @Test
  public void testAnd(){
      Variable x = new Variable("x");
      Variable y = new Variable("y");
      Context ctx = new Context();
      ctx.assign(x,false);
      ctx.assign(y,true);
      Expression exp = new Or(new And(x,y),new And(y,new Not(x)));
      System.out.println("x=" + x.interpret(ctx));
      System.out.println("exp=" + exp.interpret(ctx));
  }
}
