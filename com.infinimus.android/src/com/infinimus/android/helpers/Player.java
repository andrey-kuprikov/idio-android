package com.infinimus.android.helpers;

import java.io.IOException;

import com.infinimus.android.models.Track;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class Player {
	private static MediaPlayer mediaPlayer = new MediaPlayer();
	private static boolean hasSource = false;
	
	public static void Play(Context c, Track t, String rootPath) throws IllegalStateException, IOException{
		if (mediaPlayer.isPlaying())
			mediaPlayer.stop();
		
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDataSource(c, Synchronizator.getTrackUri(t, rootPath));
		hasSource = true;
		mediaPlayer.prepare();
		mediaPlayer.start();
	}
	
	public static void Stop(){
		mediaPlayer.stop();
	}
	
	public static boolean Resume(){
		if (hasSource)
			mediaPlayer.start();
		
		return hasSource;
	}
	
	public static void Pause(){
		mediaPlayer.pause();
	}
}
