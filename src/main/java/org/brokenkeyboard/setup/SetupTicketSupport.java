package org.brokenkeyboard.setup;

import org.brokenkeyboard.Config;
import org.brokenkeyboard.htmlTranscripts.DiscordHtmlTranscripts;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.Timestamp;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class SetupTicketSupport extends ListenerAdapter {

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("setup")) {
            String type = event.getOption("type").getAsString();
            if (type.equals("ticketsupport")) {

                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setImage("https://media.discordapp.net/attachments/871342424981663765/1069675242865299466/support.png?width=1440&height=412");
                embedBuilder.setColor(new Color(86, 98, 248));

                EmbedBuilder embedBuilder1 = new EmbedBuilder();
                embedBuilder1.setThumbnail("https://cdn.discordapp.com/attachments/871342424981663765/1041742146576851026/Unbenannt.png");
                embedBuilder1.setTitle("🎟〣ERSTELLE EIN TICKET");
                embedBuilder1.setDescription("> Klicke auf den Button unter dieser Nachricht, um ein Ticket zu erstellen.");
                embedBuilder1.setColor(new Color(86, 98, 248));


                Button createTicket =Button.primary("createTicket", "Eröffne ein Ticket!").withEmoji(Emoji.fromFormatted("\uD83D\uDCE9"));//📩
                event.getChannel().sendMessageEmbeds(embedBuilder.build(), embedBuilder1.build()).setActionRow(createTicket).queue();
            }
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("createTicket")){
            String user = event.getUser().getId();
            event.getGuild().createTextChannel("ticket-" + event.getUser().getName().toLowerCase(), event.getGuild().getCategoryById(Config.categorySupportId))
                    .addMemberPermissionOverride(event.getMember().getIdLong(), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND, Permission.MESSAGE_ATTACH_FILES), null)
                    .addPermissionOverride(event.getGuild().getRoleById(Config.roleTeam)/*teamrolle*/, EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND, Permission.MESSAGE_ATTACH_FILES, Permission.MANAGE_CHANNEL), null)
                    .addPermissionOverride(event.getGuild().getRoleById(Config.roleEveryone)/*@everyone*/, null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .queue(textChannel -> {

                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        embedBuilder.setTitle("Support");
                        embedBuilder.setDescription("> **Hey, danke dass du ein Supportticket eröffnet hast.**\n" +
                                "> Bitte beschreibe dein Anliegen so genau wie möglich und teile uns auch ggf. "+
                                "Screenshots oder Fehlermeldungen mit. Wir werden uns so schnell wie möglich um dein Anliegen kümmern. \n" +
                                "Vielen Dank!");
                        embedBuilder.setFooter("Ping ein Teammitglied nur, wenn es nötig ist. Bitte kein Pingspam.");
                        embedBuilder.setThumbnail("https://media.discordapp.net/attachments/871342424981663765/1041742146576851026/Unbenannt.png");
                        embedBuilder.setColor(new Color(86, 98, 248));

                        Button schließen = Button.danger("closeTicket", "Schließen").withEmoji(Emoji.fromFormatted("\uD83D\uDD12"));//🔒
                        event.getGuild().getTextChannelById(textChannel.getId()).sendMessageEmbeds(embedBuilder.build()).setActionRow(schließen).setContent(event.getUser().getAsMention()).queue();
                        event.reply(event.getGuild().getTextChannelById(textChannel.getId()).getAsMention()).setEphemeral(true).queue();
                    });
        }
        if (event.getComponentId().equals("closeTicket")){
            if (event.getMember().hasPermission(Permission.MANAGE_CHANNEL)){
                //bekomme den User aus der Mention aus der 1. Nachicht
                User user = (User) event.getMessage().getMentions().getUsers().get(0);

                //Message Creation Date
                Instant instant = Instant.ofEpochMilli(event.getMessage().getTimeCreated().toInstant().toEpochMilli());
                ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
                String formattedDate = zonedDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

                EmbedBuilder chatlog = new EmbedBuilder();
                chatlog.setTitle("Ticket wurde geschlossen");
                chatlog.addField("**Ticket Name**", event.getChannel().getName(), true);
                chatlog.addField("**Eröffnet von**", user.getAsMention(), true);
                chatlog.addField("**Geschlossen von**", event.getUser().getAsMention(), true);
                chatlog.addField("**Eröffnet um**", formattedDate, true);
                chatlog.setTimestamp(OffsetDateTime.now(Clock.systemUTC()));
                chatlog.setThumbnail("https://cdn.discordapp.com/attachments/871342424981663765/1068575987014565898/tickets.png");
                chatlog.setColor(new Color(86, 98, 248));


                EmbedBuilder userDmLog = new EmbedBuilder();
                userDmLog.setTitle("Ticket wurde geschlossen");
                userDmLog.setDescription("Dein Ticket auf [DiscordServer4you](https://discord.com/channels/847424520411152425/) wurde geschlossen. \n" +
                        "Mit dieser Nachricht wurde dir ein Transcript davon gesendet!");
                userDmLog.setThumbnail("https://media.discordapp.net/attachments/871342424981663765/1072242274865516614/discordserver4youDark.png?width=676&height=676");
                chatlog.setTimestamp(OffsetDateTime.now(Clock.systemUTC()));
                userDmLog.setColor(new Color(86, 98, 248));

                File htmlTranscript = new File("transcript.html");
                DiscordHtmlTranscripts transcript = DiscordHtmlTranscripts.getInstance();
                try {
                    htmlTranscript = transcript.generateFromMessages(event.getChannel().getIterableHistory().stream().collect(Collectors.toList()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                //Sende transcript.html in den SupportLogChannel und dem User per DM
                event.getGuild().getTextChannelById(Config.channelSupportLog).sendMessageEmbeds(chatlog.build()).addFiles(FileUpload.fromData(htmlTranscript)).queue();
                user.openPrivateChannel().complete().sendMessageEmbeds(userDmLog.build()).addFiles(FileUpload.fromData(htmlTranscript)).queue();


                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("Löschung in 30 Sekunden!");
                embedBuilder.setDescription("Dieses Ticket wird in 30 Sekunden endgültig gelöscht!");
                embedBuilder.setThumbnail("https://cdn.discordapp.com/attachments/871342424981663765/1069216364100849726/trash.png");
                embedBuilder.setColor(new Color(86, 98, 248));

                event.replyEmbeds(embedBuilder.build()).queue();


                new Thread(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(30);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    event.getChannel().delete().queue();
                }).start();




                //e.getGuild().getChannelByID(e.getChannel().getID()).delete().reason(reason).queue();
            }else {
                event.reply("Nur Teammitglieder können dieses Ticket schließen!").setEphemeral(true).queue();
            }


        }
    }

}
