package org.brokenkeyboard.modals;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

import java.time.Clock;
import java.time.OffsetDateTime;

public class EditEmbed extends ListenerAdapter {

    String MessageID;
    String AuthorId;
    String Title;
    String Description;
    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        if (event.getName().equals("Edit Embed")) {
            Message message = event.getTarget();
            MessageID = event.getTarget().getId();
            AuthorId = "";
            Title = "";
            Description = "";

            if (!message.getEmbeds().isEmpty()) {

                AuthorId = message.getEmbeds().get(0).getFooter().getText().toString();
                if (message.getEmbeds().get(0).getTitle() != null && !message.getEmbeds().get(0).getTitle().isEmpty()) {
                    Title = message.getEmbeds().get(0).getTitle().toString();
                } else {
                    //System.out.println("Title ist leer");
                }
                if (message.getEmbeds().get(0).getDescription() != null && !message.getEmbeds().get(0).getDescription().isEmpty()) {
                    Description = message.getEmbeds().get(0).getDescription().toString();
                } else {
                    //System.out.println("Description ist leer");
                }




            }


            if (event.getMember().getId().equals(AuthorId)){

                TextInput titleInput;
                if (Title.equals(null) || Title.equals("")){// falls null
                    titleInput = TextInput.create("title", "Titel", TextInputStyle.SHORT)
                            .setPlaceholder("Titel, den das Embed haben soll. Leer lassen, wenn du keinen Titel setzen möchtest!")
                            .setRequired(false)
                            .build();
                }else {// falls not null
                    titleInput = TextInput.create("title", "Titel", TextInputStyle.SHORT)
                            .setPlaceholder("Titel, den das Embed haben soll. Leer lassen, wenn du keinen Titel setzen möchtest!")
                            .setRequired(false)
                            .setValue(Title)
                            .build();
                }


                TextInput descriptionInput;
                if (Description.equals(null) || Description.equals("")){//falls Beschreibung null ist
                    descriptionInput = TextInput.create("description", "Beschreibung", TextInputStyle.PARAGRAPH)
                            .setPlaceholder("Beschreibung, die das Embed haben soll. Leer lassen, wenn du keine Beschreibung setzen möchtest!")
                            .setRequired(false)
                            .setMaxLength(1000)
                            .build();
                }else {//falls Beschreibung Wert hat
                    descriptionInput = TextInput.create("description", "Beschreibung", TextInputStyle.PARAGRAPH)
                            .setPlaceholder("Beschreibung, die das Embed haben soll. Leer lassen, wenn du keine Beschreibung setzen möchtest!")
                            .setRequired(false)
                            .setMaxLength(1000)
                            .setValue(Description)
                            .build();
                }




                Modal modal = Modal.create("editEmbed", "Bearbeite dein Embed!")
                        .addActionRows(ActionRow.of(titleInput), ActionRow.of(descriptionInput))
                        .build();

                event.replyModal(modal).queue();
                //event.reply("Du bist der Ersteller des Embeds und darfst somit dieses bearbeiten! Funktion wird noch hinzugefügt.").queue();
            }else {
                event.reply("Du bist nicht der Ersteller des Embed und darfst es deshalb nicht bearbeiten!").setEphemeral(true).queue();
            }

            //event.getTarget().editMessage("").queue();
        }
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {


        if (event.getModalId().equals("editEmbed")) {
            String title = event.getValue("title").getAsString();
            String description = event.getValue("description").getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder();
            if (!title.equals("")){
                embedBuilder.setTitle(title);
            }
            if (!description.equals("")){
                embedBuilder.setDescription(description);
            }
            embedBuilder.setFooter(AuthorId);


            embedBuilder.setAuthor(event.getMember().getEffectiveName(), "https://www.discordapp.com/users/" + event.getMember().getId(), event.getMember().getEffectiveAvatarUrl());
            embedBuilder.setTimestamp(OffsetDateTime.now(Clock.systemUTC()));
            event.getChannel().editMessageEmbedsById(MessageID, embedBuilder.build()).queue();
            event.reply("Dein Embed wurde erfolgreich bearbeitet!").setEphemeral(true).queue();
            //event.replyEmbeds(embedBuilder.build()).queue();


        }
    }
}
