package org.brokenkeyboard.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.concurrent.TimeUnit;

public class ViewCommandSlash extends ListenerAdapter {

    public void onSlashCommandInteraction (SlashCommandInteractionEvent event){
        if (event.getName().equals("view")){
            Member member = event.getOption("user").getAsMember();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setThumbnail(member.getEffectiveAvatarUrl());
            embedBuilder.setTitle("Nutzerinfos zu " + member.getEffectiveName());
            //embedBuilder.setDescription("adhgiadhgadhfhgijfad jajfdu hjadujfhj uadh");
            embedBuilder.addField("ID des Users", member.getId(), true);

            Button kickButton = Button.secondary("kick" + member.getId(), "Mitglied kicken");
            Button banButton = Button.danger("ban" + member.getId(), "Mitglied bannen");
            Button profileButton = Button.link(member.getEffectiveAvatarUrl(), "Profilbild abrufen");

            event.replyEmbeds(embedBuilder.build()).setActionRow(kickButton, banButton, profileButton).queue();



        }

    }

    public void onButtonInteraction (ButtonInteractionEvent ereignis) {

        if (ereignis.getButton().getId().startsWith("kick")) {

            if (ereignis.getMember().getPermissions().contains(Permission.KICK_MEMBERS)) {

                String nutzerID = ereignis.getButton().getId().substring(4);

                ereignis.getGuild().kick(User.fromId(nutzerID)).queue();

                ereignis.reply("Bestrafung erfolgreich!").queue();

            } else {

                ereignis.reply("Dir fehlen die Rechte hierzu!").setEphemeral(true).queue();

            }

        } else if (ereignis.getButton().getId().startsWith("ban")) {

            if (ereignis.getMember().getPermissions().contains(Permission.BAN_MEMBERS)) {

                String nutzerID = ereignis.getButton().getId().substring(3);

                ereignis.getGuild().ban(User.fromId(nutzerID), 7, TimeUnit.DAYS).reason("Bann eines Admins").queue();

                ereignis.reply("Bestrafung erfolgreich!").queue();

            } else {

                ereignis.reply("Dir fehlen die Rechte hierzu!").queue();

            }

        }

    }
}
