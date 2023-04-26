package org.brokenkeyboard.setup;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.brokenkeyboard.Config;

import java.awt.*;
import java.util.Random;

public class SetupVerify extends ListenerAdapter {

    public void onSlashCommandInteraction (SlashCommandInteractionEvent event) {

        if (event.getName().equals("setup")) {
            String type = event.getOption("type").getAsString();

            if (type.equals("verify")) {
                EmbedBuilder verify = new EmbedBuilder();
                verify.setThumbnail("https://media.discordapp.net/attachments/871342424981663765/1042079831367635045/verify.png?width=670&height=676");
                //builder.setImage();
                verify.setTitle("Verifizierung");
                verify.setDescription("> Klicke auf den Button unter dieser Nachricht, um dich zu verifizieren! \n" +
                        "> Wenn du dich verifiziert, stimmst du unseren Regeln \n> in " + event.getGuild().getRulesChannel().getAsMention() + " zu.");
                verify.setColor(new Color(86, 98, 248));

                event.getChannel().sendMessageEmbeds(verify.build()).setActionRow(Button.success("verify", "verifizieren")).queue();

            }

        }
    }

    public void onButtonInteraction (ButtonInteractionEvent event){
        if (event.getComponentId().equals("verify")) {

            event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.roleMember)).queue();
            /*event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1066770295022694570")).queue();//🕒│Sonstiges│🕒
            event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1066771289265684580")).queue();//💬│Level│💬
            event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1066769484792213674")).queue();//👋│Über mich│👋
            event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1066770388291432489")).queue();//🏀│Hobbys│🏀
            event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1066769118033891400")).queue();//🔴│Pings│🔴*/
            event.reply("Du wurdest erfolgreich verifiziert!").setEphemeral(true).queue();


        }

    }
}
