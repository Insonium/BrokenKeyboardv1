package org.brokenkeyboard.automation;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.brokenkeyboard.Config;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class OnReady extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Nachricht erkannt");
        event.getJDA().getGuildById(Config.guildID).getTextChannelById(Config.channelBotchatId).getIterableHistory().takeAsync(100).thenAcceptAsync(messages -> {

            for (Message message : messages) {
                long messageAge = Instant.now().toEpochMilli() - message.getTimeCreated().toInstant().toEpochMilli();

                if (messageAge >= 300000){
                    //System.out.println("älter als 5 min");
                    if (message.getChannel().getId().equals(Config.channelBotchatId)) {
                        //System.out.println("im Botchannel");
                        if (!message.getId().equals(Config.botChatMessageId)) {
                            //System.out.println("nicht das 1. Embed");
                            message.delete().queue();
                        }
                    }
                }


                /*
                long messageAge = Instant.now().toEpochMilli() - event.getMessage().getTimeCreated().toInstant().toEpochMilli();
                if (messageAge >= 300000) {
                event.getMessage().delete().queue();
        }
                 */
            }
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5*60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            event.getJDA().getGuildById(Config.guildID).getTextChannelById(Config.channelBotchatId).getIterableHistory().takeAsync(100).thenAcceptAsync(messages -> {
                for (Message message : messages) {
                    long messageAge = Instant.now().toEpochMilli() - message.getTimeCreated().toInstant().toEpochMilli();

                    if (messageAge >= 300000){
                        //System.out.println("älter als 5 min");
                        if (message.getChannel().getId().equals(Config.channelBotchatId)) {
                            //System.out.println("im Botchannel");
                            if (!message.getId().equals(Config.botChatMessageId)) {
                                //System.out.println("nicht das 1. Embed");
                                message.delete().queue();
                            }
                        }
                    }
                }
            });
        }).start();
    }
}