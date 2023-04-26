package org.brokenkeyboard.automation;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.brokenkeyboard.Config;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class WillkommensNachricht extends ListenerAdapter {

    public void onGuildMemberJoin (GuildMemberJoinEvent event) {

        String[] messages = new String[] {
                "Willkommen an Bord "+event.getUser().getAsMention()+"["+event.getUser().getName()+"]! Wir freuen uns darauf, dich besser kennenzulernen. Bei Fragen steht dir <#1030775534587756554> zur Verfügung.",
                "Herzlich willkommen "+event.getUser().getAsMention()+"["+event.getUser().getName()+"] Unser Server ist froh, dich bei uns zu haben. Brauchst du Hilfe, steht dir <#1030775534587756554> immer zur Verfügung.",
                "Schön, dass du hier bist "+event.getUser().getAsMention()+"["+event.getUser().getName()+"] Solltest du Fragen haben, zögere nicht, ein Ticket in <#1030775534587756554> zu erstellen. Wir stehen dir gerne zur Seite.",
                "Hallo "+event.getUser().getAsMention()+"["+event.getUser().getName()+"] Willkommen in unserer Community. Falls du Probleme mit dem Bot hast, kannst du einen Beitrag in <#1071065309064200332> erstellen und ich werde mein Bestes tun, um dir zu helfen.",
                "Hi "+event.getUser().getAsMention()+"["+event.getUser().getName()+"] Schön, dass du Teil unserer Gemeinschaft wirst. Solltest du Unterstützung benötigen, kannst du jederzeit ein Ticket in <#1030775534587756554> erstellen.",
                "Willkommen "+event.getUser().getAsMention()+"["+event.getUser().getName()+"] Unser Server freut sich, dich bei uns zu haben. Für Fragen oder Probleme bezüglich des Bots steht dir <#1071065309064200332> zur Verfügung.",
                "Herzlich willkommen "+event.getUser().getAsMention()+"["+event.getUser().getName()+"] Bei Fragen kannst du gerne ein Ticket in <#1030775534587756554> erstellen. Falls du Probleme mit dem Bot hast, kannst du in <#1071065309064200332> einen Beitrag erstellen und wir werden uns um eine Lösung kümmern.",
                "Guten Tag "+event.getUser().getAsMention()+"["+event.getUser().getName()+"] Willkommen bei uns. Für Fragen steht dir <#1030775534587756554> immer zur Verfügung und falls du Probleme mit dem Bot hast, kannst du in <#1071065309064200332> einen Beitrag erstellen.",
                "Hi "+event.getUser().getAsMention()+"["+event.getUser().getName()+"] Willkommen in unserer Community. Brauchst du Hilfe, steht dir <#1030775534587756554> jederzeit zur Verfügung. Solltest du Probleme mit dem Bot haben, kannst du in <#1071065309064200332> einen Beitrag erstellen.",
                "Willkommen "+event.getUser().getAsMention()+"["+event.getUser().getName()+"] Unser Server freut sich, dich hier zu haben. Für Fragen steht dir <#1030775534587756554> zur Verfügung und falls du Probleme mit dem Bot hast, kannst du in <#1071065309064200332> einen Beitrag erstellen und wir werden uns darum kümmern.",
        };

        Random random = new Random();
        int randomIndex = random.nextInt(messages.length);
        String message = messages[randomIndex];


        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Willkommen auf Discordserver4you!");
        embedBuilder.setDescription(message);
        embedBuilder.setColor(new Color(86, 98, 248));
        embedBuilder.setThumbnail("https://media.discordapp.net/attachments/871342424981663765/1073683523443109922/wavingHand.png?width=676&height=676");

        event.getGuild().getTextChannelById(Config.chatId).sendMessageEmbeds(embedBuilder.build()).queue();



    }
}
