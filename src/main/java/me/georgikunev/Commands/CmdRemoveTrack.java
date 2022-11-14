package me.georgikunev.Commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.georgikunev.CmdBot.CmdBot;
import me.georgikunev.CmdBot.ExecArgs;
import me.georgikunev.lavaplayer.GuildMusicManager;
import me.georgikunev.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class CmdRemoveTrack implements CmdBot {
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
        final GuildMusicManager guildMusicManager = PlayerManager.getINSTANCE().getMusicManager(executeArgs.getGuild());
        final AudioPlayer audioPlayer = guildMusicManager.audioPlayer;

        if (guildMusicManager.trackScheduler.queue.isEmpty()){
            executeArgs.getTextChannel().sendMessage("There are no tracks in the queue.").complete();
            return;
        }
        guildMusicManager.trackScheduler.queue.remove();
        executeArgs.getTextChannel().sendMessage("Track removed from queue").complete();

    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String helpMessage() {
        return "Removes latest track from queue";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
