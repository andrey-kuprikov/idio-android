package com.infinimus.android.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import android.os.AsyncTask;

public class FSClient <T> extends AsyncTask <String, Void, T> {
    private final Class<T> resultType; 
    
    public FSClient(Class<T> resultType){
    	this.resultType = resultType;
    }

	@Override
	protected T doInBackground(String... paths) {
		T result = null;
		
		//Get the text file
		File file = new File(paths[0]);

		//Read text from file
		StringBuilder data = new StringBuilder();

		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;

		    while ((line = br.readLine()) != null) {
		    	data.append(line);
		    	//data.append('\n');
		    }
		    br.close();
		}
		catch (IOException e) {
		    return null;
		}		
		
		Gson g = new Gson();
		result = g.fromJson(data.toString(), resultType);
		return result;
	}

	@Override
	protected void onPostExecute(T result) {}
}
