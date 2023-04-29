package org.brokenkeyboard;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.brokenkeyboard.automation.AutoCompleteSetup;
import org.brokenkeyboard.automation.Autodelete;
import org.brokenkeyboard.automation.OnReady;
import org.brokenkeyboard.automation.WillkommensNachricht;
import org.brokenkeyboard.setup.*;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static JDA bot;

    public static void main(String[] args) throws LoginException, InterruptedException {
        String status = "BrokenKeyboard";




        JDABuilder builder = JDABuilder.createDefault(Config.token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing(status));

        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.DIRECT_MESSAGE_TYPING, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MEMBERS);

        /*EnumSet<CacheFlag> enumSet = EnumSet.of(CacheFlag.ONLINE_STATUS, CacheFlag.CLIENT_STATUS, CacheFlag.EMOJI, CacheFlag.VOICE_STATE);
        bauplan.enableCache(enumSet);*/



        builder.addEventListeners(new Main()); //OnReady
        //builder.addEventListeners(new OnReady());// Gibt noch Nullpointexeption Error

        builder.addEventListeners(new AutoCompleteSetup()); //Automation
        builder.addEventListeners(new WillkommensNachricht());
        builder.addEventListeners(new Autodelete());

        builder.addEventListeners(new SetupAutodelete()); //Setup
        builder.addEventListeners(new SetupRules());
        builder.addEventListeners(new SetupVerify());
        builder.addEventListeners(new SetupTicketSupport());
        builder.addEventListeners(new SetupReactionroles());






        bot = builder.build();
        System.out.println(" _____  _                       _  _____                          _  _                     \n" +
                "|  __ \\(_)                     | |/ ____|                        | || |                    \n" +
                "| |  | |_ ___  ___ ___  _ __ __| | (___   ___ _ ____   _____ _ __| || |_ _   _  ___  _   _ \n" +
                "| |  | | / __|/ __/ _ \\| '__/ _` |\\___ \\ / _ \\ '__\\ \\ / / _ \\ '__|__   _| | | |/ _ \\| | | |\n" +
                "| |__| | \\__ \\ (_| (_) | | | (_| |____) |  __/ |   \\ V /  __/ |     | | | |_| | (_) | |_| |\n" +
                "|_____/|_|___/\\___\\___/|_|  \\__,_|_____/ \\___|_|    \\_/ \\___|_|     |_|  \\__, |\\___/ \\__,_|\n" +
                "                                                                          __/ |            \n" +
                "                                                                         |___/             \n");
        System.out.println("Der Bot ist nun online!");
    }



    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("Commands aktiviert");
            event.getJDA().getGuilds().get(1).updateCommands().addCommands(
                    Commands.slash("setup", "Sendet dir Reaktionroles, Ticketembeds und vieles mehr!")
                            .addOption(OptionType.STRING, "type", "Wähle aus, was gesendet werden soll!", true, true)
            ).queue();
    }
}