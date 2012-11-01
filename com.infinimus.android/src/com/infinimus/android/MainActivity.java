package com.infinimus.android;


import java.io.File;

import com.infinimus.android.R;
import com.infinimus.android.helpers.RestClient;
import com.infinimus.android.helpers.Serializator;
import com.infinimus.android.models.LocalTracklist;
import com.infinimus.android.models.PlayStat;
import com.infinimus.android.models.Playlist;
import com.infinimus.android.models.Track;
import com.infinimus.android.models.Tracklist;
import com.infinimus.android.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

	public LocalTracklist tracks;
	public PlayStat stat;
	public Playlist playlist;
	
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
        
        //TODO: Check if media storage ready (http://developer.android.com/guide/topics/data/data-storage.html)
        SharedPreferences settings = getPreferences(0);
        String rootPath = settings.getString("dataPath", "");
        if (rootPath == null || rootPath == ""){
        	rootPath = getExternalFilesDir(null).getAbsolutePath();
        	SharedPreferences.Editor editor = settings.edit();
        	editor.putString("dataPath", rootPath);
        	editor.commit();
        }

        String tracksFile = rootPath + "/tracks.json";
        File f = new File(tracksFile);
        if (!f.exists()){
        	tracks = new LocalTracklist();
        	Track t = new Track();
        	t.artist_id = "f";
        	t.artist_name = "madonna";
        	t.foreign_id = "sdfsdfsd";
        	t.play_count = 10;
        	t.song_name = "Sorry";
        	tracks.items.add(t);
        	tracks.save(tracksFile, new Handler(){
        		@Override
        		public void handleMessage(Message msg) {
        			log("FFF");
        			super.handleMessage(msg);
        		}
        	});
        }
        else{
	        //Loading local track list database
	        LocalTracklist.load(tracksFile, new Handler(){
				@Override
				public void handleMessage(Message msg) {
					tracks = (LocalTracklist)msg.obj;
					super.handleMessage(msg);
				}
			});
        }
    }
    
    public void init(){
    	RestClient.initCookieStore(this);
    }
    
    //Fires after authentication
    public void onAuthenticated()
    {
    	Playlist.load(new JsonHttpResponseHandler<Playlist>(Playlist.class){
			public void onSuccess(Playlist p) {
				log(String.valueOf(p.items.size()) + p.id);
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
