package org.brokenkeyboard.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class PingCommandSlash extends ListenerAdapter {

    public void onSlashCommandInteraction (SlashCommandInteractionEvent event) {

        if (event.getName().equals("ping")) {
            event.reply(":ping_pong: Pong! Das hat **" + event.getMember().getJDA().getGatewayPing() + "ms** gedauert.").queue();

        }

    }
}
