package me.georgikunev.Commands;

import me.georgikunev.CmdBot.CmdBot;
import me.georgikunev.CmdBot.ExecArgs;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class CmdHelp implements CmdBot {
    @Override
    public void execute(ExecArgs executeArgs) {
        if (!executeArgs.getMemberVoiceState().inAudioChannel()){
            executeArgs.getTextChannel().sendMessage("You need to be in a voice channel for this command to work!").complete();
            return;
        }
        if (!executeArgs.getSelfVoiceState().inAudioChannel()){
            final AudioManager audioManager = executeArgs.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) executeArgs.getMemberVoiceState().getChannel();
            audioManager.openAudioConnection(memberChannel);
        }
        executeArgs.getTextChannel().sendMessage("Commands: !play, !resume, !pause, !clear, !skip, !remove").complete();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String helpMessage() {
        return "Shows all commands";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
