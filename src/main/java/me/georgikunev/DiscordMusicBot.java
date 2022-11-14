package me.georgikunev;

import me.georgikunev.CmdBot.JDACommands;
import me.georgikunev.Commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class DiscordMusicBot {
    public static final GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES,GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.MESSAGE_CONTENT};


    public static void main(String[] args) throws LoginException {

        JDACommands jdaCommands = new JDACommands("!");
        jdaCommands.registerCommand(new CmdPlay());
        jdaCommands.registerCommand(new CmdSkip());
        jdaCommands.registerCommand(new CmdPause());
        jdaCommands.registerCommand(new CmdResume());
        jdaCommands.registerCommand(new CmdClear());
        jdaCommands.registerCommand(new CmdHelp());
        jdaCommands.registerCommand(new CmdRemoveTrack());


        JDA bot = JDABuilder.create("token", Arrays.asList(INTENTS))
                .enableCache(CacheFlag.VOICE_STATE)
                .setActivity(Activity.watching("me"))
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(jdaCommands)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
    }
}
