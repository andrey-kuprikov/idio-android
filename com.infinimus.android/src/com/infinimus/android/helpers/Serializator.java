package com.infinimus.android.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class Serializator <T> {
    private final Class<T> resultType; 
    
    public Serializator(Class<T> resultType){
    	this.resultType = resultType;
    }
    
    public void read(final String path, final Handler handler){
        new Thread(new Runnable() {
            public void run() {
            	Message msg = new Message();
            	msg.obj = readInternal(path);
            	
            	handler.sendMessage(msg);
            }
        }).start();    	
    }
    
    private T readInternal(String path){
		T result = null;
		
		//Get the text file
		File file = new File(path);

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
    
    public void write(final String path, final T obj, final Handler handler){
        new Thread(new Runnable() {
            public void run() {
            	writeInternal(path, obj);
            	handler.sendEmptyMessage(0);
            }
        }).start();
    }
    
    private void writeInternal(String path, T obj){
		Gson g = new Gson();
		String json = g.toJson(obj, resultType);

		File file = new File(path);
		File filePath = new File(path.substring(0, path.lastIndexOf("/")));
	
		try {
			filePath.mkdirs();
			file.createNewFile();
		    BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		    bw.write(json);
		    bw.close();
		}
		catch (IOException e) {
			String a = e.getMessage();
			a = a + e.toString();
		}
    }
}
