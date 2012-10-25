package com.infinimus.android.helpers;

import android.content.Context;

import com.loopj.android.http.*;

public class RestClient {
	//developer computer localhost is on IP 10.0.2.2
	private static final String BASE_URL = "http://10.0.2.2:3000";

	private static AsyncHttpClient client = new AsyncHttpClient();
	
	public static void initCookieStore(Context c){
		PersistentCookieStore myCookieStore = new PersistentCookieStore(c);
		client.setCookieStore(myCookieStore);
	}
	
	public static void get(String url, RequestParams params, AsyncHttpResponseHandler<?> responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params, AsyncHttpResponseHandler<?> responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void put(String url, RequestParams params, AsyncHttpResponseHandler<?> responseHandler) {
		client.put(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void delete(String url,AsyncHttpResponseHandler<?> responseHandler) {
		client.delete(getAbsoluteUrl(url), responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
}
