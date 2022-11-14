package me.georgikunev.Commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.georgikunev.CmdBot.CmdBot;
import me.georgikunev.CmdBot.ExecArgs;
import me.georgikunev.lavaplayer.GuildMusicManager;
import me.georgikunev.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class CmdSkip implements CmdBot {
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

        if (audioPlayer.getPlayingTrack() == null){
            executeArgs.getTextChannel().sendMessage("There is no track playing currently.").complete();
            return;
        }
        if (guildMusicManager.trackScheduler.queue.isEmpty()){
            executeArgs.getTextChannel().sendMessage("No more tracks in queue.").complete();
            return;
        }
        guildMusicManager.trackScheduler.nextTrack();
        executeArgs.getTextChannel().sendMessage("Track skipped.").complete();
    }

    @Override
    public String getName() {
        return "skip";
    }

    @Override
    public String helpMessage() {
        return "Skips current track";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
