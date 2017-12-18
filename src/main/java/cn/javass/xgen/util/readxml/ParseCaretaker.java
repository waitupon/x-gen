package cn.javass.xgen.util.readxml;

public class ParseCaretaker {
    private static ParseCaretaker taker = new ParseCaretaker();
    private ParseMemento memento;

    private ParseCaretaker(){

    }


    public static ParseCaretaker newInstance(){
        return taker;
    }

    public void saveMemento(ParseMemento memento){
            this.memento = memento;
    }

    public ParseMemento retriveMemento(){
        return this.memento;
    }

}
