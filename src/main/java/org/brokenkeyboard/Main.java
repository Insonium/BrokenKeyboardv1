package org.brokenkeyboard;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

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
}