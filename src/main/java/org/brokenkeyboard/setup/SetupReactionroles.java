package org.brokenkeyboard.setup;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectInteraction;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.brokenkeyboard.Config;

import java.awt.*;

public class SetupReactionroles extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("setup")) {
            String type = event.getOption("type").getAsString();

            if (type.equals("reactionroles")) {
                EmbedBuilder image = new EmbedBuilder();
                image.setImage("https://media.discordapp.net/attachments/871342424981663765/1069659455215648869/rollenvergabe.png?width=1440&height=412");
                image.setColor(new Color(86, 98, 248));
                event.getChannel().sendMessageEmbeds(image.build()).queue();

                EmbedBuilder info = new EmbedBuilder();
                info.setThumbnail("https://media.discordapp.net/attachments/871342424981663765/1041742146576851026/Unbenannt.png?width=717&height=632");
                info.setTitle("Hier kannst du deine Rollen auswählen!");
                info.addField("Ich möchte meine Rollen ändern, was nun?", "> Dein Alter kannst du einfach so ändern, sobald du eine neue Rolle auswählst, werden dir automatisch alle anderen altersbezogenen Rollen entfernt! \n" +
                        "> Du kannst dir aber auch alle Rollen entfernen lassen, sodass du deine Rollen neu auswählen kannst.", false);
                //info.addField("", "Bei allen anderen Rollen kannst du auf den roten Button klicken, dieser entfernt dir ALLE Rollen, anschließend kannst du deine Rollen neu auswählen!", false);
                //info.addField("Bugfixes", "Falls du einen Fehler bemerkst oder etwas nicht ordnungsgemäß funktioniert, kannst du mir dies gerne per DM (<@682264027723989073>) oder per Ticket (<#1030775534587756554>) mitteilen. Ich werde dann mein bestes geben, den Fehler zu korrigieren :)", false);
                info.addField("Na los, worauf wartest du denn noch?", "Worauf wartest du denn noch? Wähle jetzt gleich deine Rollen aus!", false);
                info.setColor(new Color(86, 98, 248));

                event.getChannel().sendMessageEmbeds(info.build()).queue();


                EmbedBuilder builder = new EmbedBuilder();
                builder.setThumbnail("https://cdn.discordapp.com/attachments/871342424981663765/1069215389646606336/kalender.png");
                builder.setTitle("Wähle dein Alter aus!");
                builder.setDescription("> Klicke auf die Buttons unter dieser Nachricht, um deine Rolle auszuwählen!");
                builder.setColor(new Color(86, 98, 248));
                Button buttonAlter1 = Button.secondary("buttonAlter1315", " 13-15").withEmoji(Emoji.fromFormatted("1️⃣"));
                Button buttonAlter2 = Button.secondary("buttonAlter1618", " 16-18").withEmoji(Emoji.fromFormatted("2️⃣"));
                Button buttonAlter3 = Button.secondary("buttonAlter18+", " 18+").withEmoji(Emoji.fromFormatted("3️⃣"));


                event.getChannel().sendMessageEmbeds(builder.build()).setActionRow(buttonAlter1, buttonAlter2, buttonAlter3).queue();


                EmbedBuilder builder1 = new EmbedBuilder();
                builder1.setThumbnail("https://cdn.discordapp.com/attachments/871342424981663765/1069213926287491163/kontroller.png");
                builder1.setTitle("Wähle deine Hobbys aus!");
                builder1.setDescription("> Klicke auf das Menü unter dieser Nachricht, um deine Rollen auszuwählen!");
                builder1.setColor(new Color(86, 98, 248));

                /*SelectMenu menu1 = SelectMenu.create("hobbys")
                        .setPlaceholder("Wähle deine Hobbys!")
                        .setRequiredRange(1, 5)
                        .addOption("Gaming", "gaming", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("\uD83D\uDD79"))
                        .addOption("Wissenschaft", "wissenschaft", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("⚗"))
                        .addOption("Sport", "sport", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("\uD83E\uDD3F"))
                        .addOption("Kunst", "kunst", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("\uD83C\uDFA8"))
                        .addOption("IT", "IT", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("💻"))
                        .build();
                 */

                Button buttonHobby1 = Button.secondary("buttonHobbyGaming", " Gaming").withEmoji(Emoji.fromFormatted("🕹"));//\uD83D\uDD79
                Button buttonHobby2 = Button.secondary("buttonHobbyWissenschaft", " Wissenschaft").withEmoji(Emoji.fromFormatted("🧪"));//⚗
                Button buttonHobby3 = Button.secondary("buttonHobbySport", " Sport").withEmoji(Emoji.fromFormatted("👟"));//\uD83E\uDD3F
                Button buttonHobby4 = Button.secondary("buttonHobbyKunst", " Kunst").withEmoji(Emoji.fromFormatted("🎨"));//\uD83C\uDFA8
                Button buttonHobby5 = Button.secondary("buttonHobbyIt", " IT").withEmoji(Emoji.fromFormatted("💻"));//\uD83D\uDCBB

                event.getChannel().sendMessageEmbeds(builder1.build()).setActionRow(buttonHobby1, buttonHobby2, buttonHobby3, buttonHobby4, buttonHobby5).queue();



                EmbedBuilder builder2 = new EmbedBuilder();
                builder2.setThumbnail("https://cdn.discordapp.com/attachments/871342424981663765/1041745483011457145/one-g86a400986_1280.png");
                builder2.setTitle("Wähle deine Pings aus!");
                builder2.setDescription("> Klicke auf das Menü unter dieser Nachricht, um deine Rollen auszuwählen!");
                builder2.setColor(new Color(86, 98, 248));


                /*
                SelectMenu menu2 = SelectMenu.create("pings")
                        .setPlaceholder("Wähle deine Pings!")
                        .setRequiredRange(0, 2)
                        .addOption("servernews", "Servernews", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("\uD83D\uDCF0"))
                        .addOption("umfragen", "Umfragen", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("❓"))
                        .build();
                 */

                Button buttonPings1 = Button.secondary("buttonPingsServernews", " Servernews").withEmoji(Emoji.fromFormatted("📰"));//\uD83D\uDCF0
                Button buttonPings2 = Button.secondary("buttonPingsUmfragen", " Umfragen").withEmoji(Emoji.fromFormatted("❓"));//\uD83D\u2753

                event.getChannel().sendMessageEmbeds(builder2.build()).setActionRow(buttonPings1, buttonPings2).queue();




                EmbedBuilder delte = new EmbedBuilder();
                delte.setThumbnail("https://cdn.discordapp.com/attachments/871342424981663765/1069216364100849726/trash.png");
                delte.setTitle("Lösche deine Auswahl!");
                delte.setDescription("> Klicke auf den Button unter dieser Nachricht, um deine Rolle zurückzusetzen! ");
                delte.setColor(new Color(86, 98, 248));

                event.getChannel().sendMessageEmbeds(delte.build()).setActionRow(Button.danger("buttonDelete", " Alle Rollen entfernen").withEmoji(Emoji.fromFormatted("\uD83D\uDDD1"))).queue();


            }


        }
    }
    public void onButtonInteraction (ButtonInteractionEvent event){
        if (event.getComponentId().equals("buttonAlter1315")) {
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.role1618)).queue();
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.role18)).queue();
            event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.role1315)).queue();
            event.deferEdit().queue();
        }
        if (event.getComponentId().equals("buttonAlter1618")) {
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.role1315)).queue();
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.role18)).queue();
            event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.role1618)).queue();
            event.deferEdit().queue();
        }
        if (event.getComponentId().equals("buttonAlter18+")) {
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.role1315)).queue();
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.role1618)).queue();
            event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.role18)).queue();
            event.deferEdit().queue();
        }



        if (event.getComponentId().equals("buttonHobbyGaming")) {event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.roleGaming)).queue();event.deferEdit().queue();}
        if (event.getComponentId().equals("buttonHobbyWissenschaft")) {event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.roleWissenschaft)).queue();event.deferEdit().queue();}
        if (event.getComponentId().equals("buttonHobbySport")) {event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.roleSport)).queue();event.deferEdit().queue();}
        if (event.getComponentId().equals("buttonHobbyKunst")) {event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.roleKunst)).queue();event.deferEdit().queue();}
        if (event.getComponentId().equals("buttonHobbyIt")) {event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.roleIt)).queue();event.deferEdit().queue();}


        if (event.getComponentId().equals("buttonPingsServernews")) {event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.roleServernews)).queue();event.deferEdit().queue();}
        if (event.getComponentId().equals("buttonPingsUmfragen")) {event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(Config.roleUmfragen)).queue();event.deferEdit().queue();}



        if (event.getComponentId().equals("buttonDelete")) {
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.role18)).queue();//18+
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.role1618)).queue();//16-18
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.role1315)).queue();//13-15
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.roleIt)).queue();//IT
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.roleSport)).queue();//Sport
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.roleGaming)).queue();//Gaming
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.roleKunst)).queue();//Kunst
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.roleWissenschaft)).queue();//Wissenschaft
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.roleServernews)).queue();//Servernews
            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(Config.roleUmfragen)).queue();//Umfragen
            event.reply("Deine Rollen wurden erfolgreich entfernt!").setEphemeral(true).queue();
        }
    }
}