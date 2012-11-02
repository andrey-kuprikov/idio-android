package com.infinimus.android.helpers;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;

import com.infinimus.android.models.LocalTracklist;
import com.infinimus.android.models.Playlist;

public class Synchronizator {
	
	public static void Sync(LocalTracklist local, Playlist remote, DownloadManager dm){
		DownloadManager.Request req=new DownloadManager.Request(Uri.parse("http://download.fidel.ru/me.jpg"));

		req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
		   .setAllowedOverRoaming(false)
		   .setTitle("Demo")
		   .setDescription("Something useful. No, really.")
		   .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
		                                      "me.jpg");
	
		dm.enqueue(req);
	}
}
