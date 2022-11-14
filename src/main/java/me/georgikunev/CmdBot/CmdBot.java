package me.georgikunev.CmdBot;



public interface CmdBot {
    void execute(ExecArgs executeArgs);

    String getName();

    String helpMessage();

    boolean needOwner();
}
