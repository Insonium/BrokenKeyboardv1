package org.brokenkeyboard.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ClearCommandSlash extends ListenerAdapter {
    public void onSlashCommandInteraction (SlashCommandInteractionEvent event){

        if (event.getName().equals("clear")){
            int anzahl = event.getOption("anzahl").getAsInt();
            if (anzahl == 0){
                event.reply("Du musst eine positive Zahl angeben!");
            }

            List<Message> messages = event.getChannel().getHistory().retrievePast(anzahl).complete();
            //event.getChannel().purgeMessages(messages.toArray(new Message[0]))
            event.getChannel().purgeMessages(messages).clear();

            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Nachrichten wurden gelöscht!");

            if (anzahl == 1){
                builder.setDescription("Insgesamt wurde " + anzahl + " Nachricht gelöscht!");
            }else {
                builder.setDescription("Insgesamt wurden " + anzahl + " Nachrichten gelöscht!");
            }



            builder.setThumbnail("https://media.discordapp.net/attachments/871342424981663765/1042137951167057990/mull.png?width=567&height=675");
            builder.setColor(new Color(86, 98, 248));




            event.replyEmbeds(builder.build()).setEphemeral(true).queue(m -> m.deleteOriginal().queueAfter(5, TimeUnit.SECONDS));


        }

    }
}
