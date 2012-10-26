package com.infinimus.android.models;

import com.google.gson.Gson;
import com.infinimus.android.helpers.RestClient;
import com.infinimus.android.helpers.StringUtil;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Playlist {
	public String id;
	public String type;
	public Track[] items;
	
	public static void load(JsonHttpResponseHandler<Playlist> handler){
		RestClient.get("/playlists/", null, handler);
	}
	
	public void save(JsonHttpResponseHandler<Playlist> handler){
		RequestParams params = new RequestParams();
		Gson g = new Gson();
		params.put(g.toJson(this));
		if (StringUtil.isNullOrEmpty(id)){
			RestClient.post("/playlists/", params, handler);
		}
		else{
			RestClient.put("/playlists/", params, handler);
		}
	}
}
