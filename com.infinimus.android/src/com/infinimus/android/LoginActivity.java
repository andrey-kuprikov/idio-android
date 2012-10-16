package com.infinimus.android;

import com.infinimus.android.helpers.RestClient;
import com.infinimus.android.helpers.StringUtil;
import com.infinimus.android.models.Session;
import com.infinimus.android.models.User;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_login);

		TextView txtLogin = (TextView) findViewById(R.id.txtLogin);
		TextView txtPassword = (TextView) findViewById(R.id.txtPassword);
		txtLogin.setText("test");
		txtPassword.setText("test");
	}
	
	public void signIn(View view){
		TextView txtLogin = (TextView) findViewById(R.id.txtLogin);
		TextView txtPassword = (TextView) findViewById(R.id.txtPassword);
		
		RequestParams params = new RequestParams();
		params.put("login", String.valueOf(txtLogin.getText()));
		params.put("password", StringUtil.md5(String.valueOf(txtPassword.getText())));
		 
		RestClient.post("session/", params, new JsonHttpResponseHandler<Session>(Session.class){
			public void onSuccess(Session session) {
				Intent result = new Intent();
	    		result.putExtra("sessionId", session._id);
	    		setResult(RESULT_OK, result);
	    		finish();
	    	}
	    });
	}

}
