package com.infinimus.android.models;

import android.os.Handler;
import android.os.Message;

import com.infinimus.android.helpers.Serializator;

public class LocalTracklist extends Tracklist {

	public static void load(String path, Handler handler){		
		Serializator<LocalTracklist> s = new Serializator<LocalTracklist>(LocalTracklist.class);
		s.read(path, handler);
	}
	
	public void save(String path, Handler handler){
		Serializator<LocalTracklist> s = new Serializator<LocalTracklist>(LocalTracklist.class);
		s.write(path, this, handler);
	}
}
