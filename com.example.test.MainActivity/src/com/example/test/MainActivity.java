package com.example.test;

import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView myImageView = (ImageView) findViewById(R.id.imgvCover);
        Bitmap bmp = BitmapFactory.decodeFile("/mnt/sdcard/test.jpg");
        myImageView.setImageBitmap(bmp);
        
        
    }
    
    public void getUser() {
        RestClient.get("/statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray response) {
                // Pull out the first event on the public timeline
                JSONObject firstEvent = timeline.get(0);
                String tweetText = firstEvent.getString("text");

                // Do something with the response
                System.out.println(tweetText);
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
