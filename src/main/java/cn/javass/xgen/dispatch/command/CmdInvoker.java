package cn.javass.xgen.dispatch.command;

public class CmdInvoker {

    private GenCommand cmd = null;

    public void setCmd(GenCommand cmd){
        this.cmd = cmd;
    }

    /**
     *
     */
    public void doCommand(){
        this.cmd.execute();
    }
}
