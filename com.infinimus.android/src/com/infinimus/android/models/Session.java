package com.infinimus.android.models;

import com.infinimus.android.helpers.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Parcel;
import android.os.Parcelable;

public class Session implements Parcelable {
	public String sessionId;
	public String userId;

	
	public Session() {}
	
	public void post(String login, String passwordHash, JsonHttpResponseHandler<Session> handler){
		RequestParams params = new RequestParams();
		params.put("login", login);
		params.put("password", passwordHash);
		 
		RestClient.post("/session/", params, handler);
	}
	
	
	
	
	public Session(Parcel in){
		String[] arr = new String[2];
		in.readStringArray(arr);
		sessionId = arr[0];
		userId = arr[1];
	}
	
    // 99.9% of the time you can just ignore this
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
        out.writeStringArray(new String[] {sessionId, userId});
        
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Session> CREATOR = new Parcelable.Creator<Session>() {
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        public Session[] newArray(int size) {
            return new Session[size];
        }
    };
}