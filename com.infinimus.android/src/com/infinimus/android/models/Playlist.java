package com.infinimus.android.models;

import com.infinimus.android.helpers.RestClient;
import com.infinimus.android.helpers.StringUtil;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Playlist {
	public String id;
	public String type;
	public Track[] items;
	
	public void load(JsonHttpResponseHandler<Playlist> handler){
		RestClient.get("/playlists/", null, handler);
	}
}
