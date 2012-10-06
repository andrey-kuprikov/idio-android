package com.example.test;

import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView myImageView = (ImageView) findViewById(R.id.imgvCover);
        Bitmap bmp = BitmapFactory.decodeFile("/mnt/sdcard/test.jpg");
        myImageView.setImageBitmap(bmp);
        
        getUser();
    }
    
    public void getUser() {
        RestClient.get("users/testId", null, new JsonHttpResponseHandler<User>(User.class) {
            @Override
            public void onSuccess(User user) {
            	TextView msg = (TextView) findViewById(R.id.txtMsg);
            	msg.setText(user.login);
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
