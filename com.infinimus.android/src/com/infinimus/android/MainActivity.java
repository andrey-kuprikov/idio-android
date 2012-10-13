package com.infinimus.android;


import com.infinimus.android.R;
import com.infinimus.android.helpers.RestClient;
import com.infinimus.android.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

//this is controller class of MVC pattern
public class MainActivity extends Activity implements OnSeekBarChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Bitmap bmp = BitmapFactory.decodeFile("/mnt/sdcard/test.jpg");
        setImg(bmp);
        SeekBar bar = (SeekBar) findViewById(R.id.seekTime);
        bar.setOnSeekBarChangeListener(this);
        
        getUser();
    }
    
    public void banTrack(View view) {
    	log("method banTrack not implemented");
    }
    
    public void likeTrack(View view) {
    	log("method likeTrack not implemented");
    }
    
    public void shareTrack(View view) {
    	log("method shareTrack not implemented");
    }
    
    public void previousTrack(View view) {
    	log("method previousTrack not implemented");
    }
    
    private boolean isPlaying = false;
    public void playPauseTrack(View view) {
    	if (isPlaying)
    		pauseTrack(view);
    	else
    		playTrack(view);
    }
    public void playTrack(View view) {
    	log("method playTrack not implemented");
    	isPlaying = true;
    	Button btn = (Button) findViewById(R.id.btnPlay);
		btn.setText(R.string.btn_pause);
    }
    public void pauseTrack(View view) {
    	log("method pauseTrack not implemented");
    	isPlaying = false;
    	Button btn = (Button) findViewById(R.id.btnPlay);
		btn.setText(R.string.btn_play);
    }
    
    public void nextTrack(View view) {
    	log("method nextTrack not implemented");
    }
    
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
    	log("method onProgressChanged not implemented");
    }

    public void onStartTrackingTouch(SeekBar seekBar)
    {
    	log("method onStartTrackingTouch not implemented");
    }

    public void onStopTrackingTouch(SeekBar seekBar)
    {
    	log("method onStopTrackingTouch not implemented");
    }
    
    
    public void getUser() {
        RestClient.get("users/testId", null, new JsonHttpResponseHandler<User>(User.class) {
            @Override
            public void onSuccess(User user) {
            	log(user.login);
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void log(String msg){
    	TextView view = (TextView) findViewById(R.id.txtMsg);
    	view.setText(msg);
    }
    
    private void setImg(Bitmap bmp){
        ImageView myImageView = (ImageView) findViewById(R.id.imgvCover);
        myImageView.setImageBitmap(bmp);
    }
}
