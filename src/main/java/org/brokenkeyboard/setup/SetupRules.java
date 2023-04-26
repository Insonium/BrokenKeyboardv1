package org.brokenkeyboard.setup;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class SetupRules extends ListenerAdapter {

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("setup")) {
            String type = event.getOption("type").getAsString();

            if (type.equals("rules")) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setImage("https://media.discordapp.net/attachments/871342424981663765/1069675242412326982/regelwerk.png?width=1440&height=412");
                embedBuilder.setColor(new Color(86, 98, 248));

                EmbedBuilder embedBuilder1 = new EmbedBuilder();
                embedBuilder1.setDescription("**§1. Behandlung anderer Mitglieder:**\nBehandle alle Mitglieder des Servers respektvoll und höflich.\n\n" +
                        "**§2. Inhalte:** Verbreite keine unangemessenen Inhalte.\n\n**§3. Spamming:** Spamme nicht im Chat oder in anderen Kanälen.\n\n" +
                        "**§4. Werbung:** Werbung für andere Communitys oder Produkte ist nur in <#1030775393898201139> erlaubt.\n\n" +
                        "**§5. Sprache:** Benutze eine anständige Sprache und vermeide Beleidigungen oder rassistische Äußerungen.\n\n" +
                        "**§6. Verstöße:** Verstöße gegen die Regeln können zu Konsequenzen führen, wie zum Beispiel eine Verwarnung oder ein Ausschluss aus dem Server.");
                embedBuilder1.setColor(new Color(86, 98, 248));

                EmbedBuilder embedBuilder2 = new EmbedBuilder();
                embedBuilder2.setAuthor("Discord", null, "https://images-ext-1.discordapp.net/external/z8SRx5cqxVXFpPqCqY3yuNfTFCrRUY0QldGjlt5NoR8/https/images-ext-2.discordapp.net/external/2dZVVL6feMSM7lxfFkKVW__LToSOzmToSEmocJV5vcA/https/cdn.discordapp.com/embed/avatars/0.png");
                embedBuilder2.setDescription("Beachte auch die Discord [Terms of Service](https://discord.com/terms).");
                embedBuilder2.setColor(new Color(86, 98, 248));

                event.getChannel().sendMessageEmbeds(embedBuilder.build(), embedBuilder1.build(), embedBuilder2.build()).queue();
            }
        }
    }
}
