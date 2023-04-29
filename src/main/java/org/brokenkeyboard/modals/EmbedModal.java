package org.brokenkeyboard.modals;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

import java.time.Clock;
import java.time.OffsetDateTime;

public class EmbedModal extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("embed")) {
            TextInput titleInput = TextInput.create("title", "Titel", TextInputStyle.SHORT)
                    .setPlaceholder("Titel, den das Embed haben soll. Leer lassen, wenn du keinen Titel setzen möchtest!")
                    .setRequired(false)
                    .build();

            TextInput descriptionInput = TextInput.create("description", "Beschreibung", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Beschreibung, die das Embed haben soll. Leer lassen, wenn du keine Beschreibung setzen möchtest!")
                    .setRequired(false)
                    .setMaxLength(1000)
                    .build();


            Modal modal = Modal.create("createEmbed", "Erstelle ein Embed!")
                    .addActionRows(ActionRow.of(titleInput), ActionRow.of(descriptionInput))
                    .build();

            event.replyModal(modal).queue();

        }
    }


    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {


        if (event.getModalId().equals("createEmbed")) {
            String title = event.getValue("title").getAsString();
            String description = event.getValue("description").getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder();
            if (!title.equals("")){
                embedBuilder.setTitle(title);
            }
            if (!description.equals("")){
                embedBuilder.setDescription(description);
            }
            embedBuilder.setFooter(event.getMember().getId());


            embedBuilder.setAuthor(event.getMember().getEffectiveName(), "https://www.discordapp.com/users/" + event.getMember().getId(), event.getMember().getEffectiveAvatarUrl());
            embedBuilder.setTimestamp(OffsetDateTime.now(Clock.systemUTC()));
            event.replyEmbeds(embedBuilder.build()).queue();


        }
    }
}
