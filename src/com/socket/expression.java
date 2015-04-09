

package com.socket;

/**
 *
 * @author Sean
 */

class command
{
    command next;
    String cmd;
    Object command;
    command()
    {
        return;
    }
    command(Object command, String cmd)
    {
        this.command = command;
        this.cmd = cmd;
    }
    command getNext()
    {
        return next;
    }
    String getcmd()
    {
        return cmd;
    }
}
class commandlist
{
    private static commandlist cmdlist = new commandlist();
    command currentCommand = new command();
    final command firstCommand = currentCommand;
    boolean badCommand = false;
    static commandlist getCommandList()
    {
        return cmdlist;
    }
    private commandlist()
    {
        currentCommand.next = new command( new kick(), "kick");
    }
    command find(String cmd)
    {
        currentCommand = firstCommand;
        while(currentCommand.getcmd() != cmd && badCommand == false )
        {
            if(currentCommand.getNext() == null)
                badCommand = false;
        }
        if(badCommand == false)
            return currentCommand;
        else
            return null;
    }
    
}
// this is the interpreter - Sean C
public class expression{
   private Message msg;
   private String cmd;
   private String arg;
   private command commandobj;
   private commandlist cmdlist = commandlist.getCommandList();
   expression(Message msg)
   {
       if(!msg.content.startsWith("#"))
           return;
       msg.content = msg.content.substring(1);
       cmd = msg.content.substring(0, msg.content.indexOf(' '));
       arg = msg.content.substring(msg.content.indexOf(' '));
       commandobj = cmdlist.find(cmd);
   }
}

class kick extends command
{
    void execute(Message msg)
    {
         String toChartxt = "You have kicked " + msg.recipient + " From the server!";
         Message toChar = new Message(toChartxt,msg.sender,msg.recipient,msg.type);
         String toVictimtxt = msg.sender + " has kicked you from the server!";
         Message toVictim = new Message(toVictimtxt, msg.recipient, msg.sender, msg.type);
    }
}
