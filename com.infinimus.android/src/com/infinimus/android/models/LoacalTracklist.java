package com.infinimus.android.models;

import com.infinimus.android.helpers.FSClient;

public class LoacalTracklist extends Tracklist {

	public static void load(String path, FSClient<LoacalTracklist> task){
		task.execute(path);
	}
	
	//public void save(String path, FSClient<LoacalTracklist> task){
	//	task.execute(path);
	//}
}
