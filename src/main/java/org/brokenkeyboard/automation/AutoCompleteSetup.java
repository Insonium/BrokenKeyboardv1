package org.brokenkeyboard.automation;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutoCompleteSetup extends ListenerAdapter {
    private String[] words = new String[]{"reactionroles", "verify", "rules", "ticketsupport", "autodelte"/*, "playercounter"*/};
    //private String[] spiele = new String[]{"wort_erraten", "sonstiges"};


    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        if (event.getName().equals("setup") && event.getFocusedOption().getName().equals("type")) {
            List<Command.Choice> options = Stream.of(words)
                    .filter(word -> word.startsWith(event.getFocusedOption().getValue())) // only display words that start with the user's current input
                    .map(word -> new Command.Choice(word, word)) // map the words to choices
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }
        /*if (event.getName().equals("game") && event.getFocusedOption().getName().equals("spiel")) {
            List<Command.Choice> options = Stream.of(spiele)
                    .filter(word -> word.startsWith(event.getFocusedOption().getValue())) // only display words that start with the user's current input
                    .map(word -> new Command.Choice(word, word)) // map the words to choices
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }*/

    }
}