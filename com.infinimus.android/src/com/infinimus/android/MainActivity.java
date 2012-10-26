package com.infinimus.android;


import com.infinimus.android.R;
import com.infinimus.android.helpers.RestClient;
import com.infinimus.android.models.Playlist;
import com.infinimus.android.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

//this is controller class of MVC pattern
public class MainActivity extends Activity implements OnSeekBarChangeListener {
	private User _user = null;
	public User getUser(){
		return _user;
	}
	public void setUser(User user){
		_user = user; 
	}

	public Playlist localTracks;
	public Playlist actionStat;
	public Playlist playQueue;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	init();
    	
    	if (getUser() == null){
    		showLoginActivity();
    	}
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Bitmap bmp = BitmapFactory.decodeFile("/mnt/sdcard/test.jpg");
        setImg(bmp);
        SeekBar bar = (SeekBar) findViewById(R.id.seekTime);
        bar.setOnSeekBarChangeListener(this);
    }
    
    public void init(){
    	RestClient.initCookieStore(this);
    }
    
    //Fires after authentication
    public void onAuthenticated()
    {
    	Playlist.load(new JsonHttpResponseHandler<Playlist>(Playlist.class){
			public void onSuccess(Playlist p) {
				log(String.valueOf(p.items.length) + p.id);
	    	}
    	});
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
    
        
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void showLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        //intent.setData(Uri.parse(YOUR_AUTHENTICATION_ENDPOINT));
        startActivityForResult(intent, 0);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
        // Start the WebViewActivity to handle the authentication.
        case R.id.login:
        	showLoginActivity();
        	return true;
        // Exit.
        case R.id.exit:
        	finish();
        	return true;
      }
      return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch (requestCode) {
    		case 0:
    			if (resultCode != RESULT_OK || data == null) {
    				return;
    			}
    			// Get the token.
    			setUser((User)data.getParcelableExtra("User"));
    			onAuthenticated();
    			log(getUser().login + ":" + getUser().name + ":" + getUser().sessionId + ":" + getUser()._id);
    			return;
    	}
    	super.onActivityResult(requestCode, resultCode, data);
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
