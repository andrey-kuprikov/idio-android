package com.infinimus.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.infinimus.android.helpers.RestClient;
import com.infinimus.android.helpers.StringUtil;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class User implements Parcelable {
	public String _id;
	public String login;
	public String name;
	public String sessionId;
	
	public User(){
		
	}
	
	public void load(String login, String password, JsonHttpResponseHandler<User> handler){
		RequestParams params = new RequestParams();
		params.put("login", login);
		params.put("passwordHash", StringUtil.md5(password));
		RestClient.get("/users/", params, handler);
	}
	
	public void save(JsonHttpResponseHandler<User> handler){
		RequestParams params = new RequestParams();
		Gson g = new Gson();
		params.put(g.toJson(this));
		if (StringUtil.isNullOrEmpty(_id)){
			RestClient.post("/users/", params, handler);
		}
		else{
			RestClient.put("/users/", params, handler);
		}
	}
	
	
	
	
	
	
	public User(Parcel in){
		String[] arr = new String[4];
		in.readStringArray(arr);
		_id = arr[0];
		login = arr[1];
		name = arr[2];
		sessionId = arr[3];
	}
	
    // 99.9% of the time you can just ignore this
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
        out.writeStringArray(new String[] {_id, login, name, sessionId});
        
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };	
}
