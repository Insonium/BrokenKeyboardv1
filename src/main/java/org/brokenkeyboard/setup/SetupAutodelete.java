package org.brokenkeyboard.setup;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class SetupAutodelete extends ListenerAdapter {

    public void onSlashCommandInteraction (SlashCommandInteractionEvent event) {

        if (event.getName().equals("setup")) {
            String type = event.getOption("type").getAsString();

            if (type.equals("autodelte")) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setImage("https://cdn.discordapp.com/attachments/871342424981663765/1069659455488266382/commands.png");
                embedBuilder.setColor(new Color(86, 98, 248));

                EmbedBuilder embedBuilder1 = new EmbedBuilder();
                embedBuilder1.setTitle(":wrench: Bot Commands");
                embedBuilder1.setDescription("> Hier kannst du alle verfügbaren Befehle nutzen. \n" +
                        "> Bitte beachte, dass die Nutzung der Bot-Befehle ausschließlich in diesem Channel erlaubt ist, um die Übersichtlichkeit des gesamten Servers zu gewährleisten.");
                embedBuilder1.setThumbnail("https://media.discordapp.net/attachments/871342424981663765/1069677019287277568/laptop.png?width=809&height=676");
                embedBuilder1.setColor(new Color(86, 98, 248));

                EmbedBuilder embedBuilder2 = new EmbedBuilder();
                embedBuilder2.setTitle(":warning:ㅤAlle Nachrichten werden innerhalb von 5 Minuten gelöscht!");
                embedBuilder2.setColor(new Color(86, 98, 248));

                event.getChannel().sendMessageEmbeds(embedBuilder.build(), embedBuilder1.build(), embedBuilder2.build()).queue();
            }
        }
    }
}
