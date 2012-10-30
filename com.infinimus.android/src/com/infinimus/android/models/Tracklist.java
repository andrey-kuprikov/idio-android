package com.infinimus.android.models;

import com.google.gson.Gson;
import com.infinimus.android.helpers.FSClient;
import com.infinimus.android.helpers.RestClient;
import com.infinimus.android.helpers.StringUtil;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Tracklist {
	public String id;
	public String type;
	public Track[] items;
	
}
