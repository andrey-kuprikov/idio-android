package com.infinimus.android.models;

import com.google.gson.Gson;
import com.infinimus.android.helpers.RestClient;
import com.infinimus.android.helpers.StringUtil;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Playlist extends Tracklist {

	public static void load(JsonHttpResponseHandler<Playlist> handler){
		RestClient.get("/playlists/", null, handler);
	}

	public static void update(final JsonHttpResponseHandler<Playlist> handler){
		RestClient.put("/playlists/", null, new JsonHttpResponseHandler<Playlist>(Playlist.class){
			public void onSuccess(Playlist p) {
				//waiting for update processing in echonest
				try{
					Thread.sleep(10000);
				}
				catch (Exception ex) {}
				RestClient.get("/playlists/", null, handler);		
			}
		});
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
