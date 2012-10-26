package com.infinimus.android;

import com.infinimus.android.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		
		User.load(String.valueOf(txtLogin.getText()),
				String.valueOf(txtPassword.getText()),
				new JsonHttpResponseHandler<User>(User.class){
					public void onSuccess(User user) {
						Intent result = new Intent();
			    		result.putExtra("User", user);
			    		setResult(RESULT_OK, result);
			    		finish();
			    	}
	    	});
	}

}
