package com.infinimus.android.helpers;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;

import com.infinimus.android.models.LocalTracklist;
import com.infinimus.android.models.Playlist;
import com.infinimus.android.models.Track;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Synchronizator {
	
	public static void Sync(LocalTracklist local, Playlist remote, final DownloadManager dm){
		for (int i = 0; i < remote.items.size(); ++i) {
			SyncOne(local, remote.items.get(i), dm);
		}
	}
	
	private static void SyncOne(LocalTracklist local, Track target, final DownloadManager dm) {
		RequestParams params = new RequestParams();
		params.put("artistName", target.artist_name);
		params.put("trackName", target.song_name);
		
		RestClient.get("/track/", params, new JsonHttpResponseHandler<Track>(Track.class){
			@Override
			public void onSuccess(Track t) {
				DownloadManager.Request req=new DownloadManager.Request(Uri.parse(t.url));

				req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
				   .setAllowedOverRoaming(false)
				   .setTitle(t.artist_name + " - " + t.song_name)
				   .setDescription("Syncing media library...")
				   .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
				                                      t.artist_name+"-"+t.song_name + ".mp3");
			
				dm.enqueue(req);
				super.onSuccess(t);
			}
		});
	}
}
