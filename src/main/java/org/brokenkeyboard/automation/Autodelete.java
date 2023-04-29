package org.brokenkeyboard.automation;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.brokenkeyboard.Config;

public class Autodelete extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event){

        if (event.getMessage().getChannel().getId().equals(Config.channelBotchatId)){
            if (!event.getMessage().getId().equals(Config.botChatMessageId)){
                Message message = event.getMessage();
                long waitTime = 5 * 60 * 1000; // 5 Minuten in Millisekunden
                Thread t = new Thread(new AutoDeleteTask(message, waitTime));
                t.start();
                //System.out.println("Nachricht eingegangen!");
            }
        }

    }

}
