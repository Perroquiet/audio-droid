package com.audio_droid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.spoledge.aacdecoder.AACPlayer;


public class MainActivity extends Activity {
	private static String logtag = "MainActivity";
	MediaPlayer mplayer;
	AACPlayer aacPlayer;
	String urlpath;
	boolean is_connect = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Button buttonStart = (Button)findViewById(R.id.buttonConnect);        
	     buttonStart.setOnClickListener(startListener); // Register the onClick listener with the implementation above
	       
	     Button buttonStop = (Button)findViewById(R.id.buttonDisconnect);        
	     buttonStop.setOnClickListener(stopListener); // Register the onClick listener with the implementation above
	     
*/		
		mplayer = new MediaPlayer();
		//aacPlayer = new AACPlayer();
		//urlpath = "rtsp://v2.cache7.c.youtube.com/CjYLENy73wIaLQnIH7D0dZO9IhMYDSANFEIJbXYtZ29vZ2xlSARSBXdhdGNoYIaU5_fj_qyZUQw=/0/0/0/video.3gp";
		
		Toast toast = Toast.makeText(getApplicationContext(), "URL: " + urlpath, Toast.LENGTH_LONG);
	 	toast.show();
		setContentView(R.layout.activity_main);
	}
	
	/*//Create an anonymous implementation of OnClickListener
    private OnClickListener startListener = new OnClickListener() {
        public void onClick(View v) {
          Log.d(logtag,"onClick() called - start button");              
          Toast.makeText(MainActivity.this, "The Connect button was clicked.", Toast.LENGTH_LONG).show();
          Log.d(logtag,"onClick() ended - start button");
        }
    };
     
    // Create an anonymous implementation of OnClickListener
    private OnClickListener stopListener = new OnClickListener() {
        public void onClick(View v) {
         Log.d(logtag,"onClick() called - stop button"); 
         Toast.makeText(MainActivity.this, "The Disconnect button was clicked.", Toast.LENGTH_LONG).show();
          Log.d(logtag,"onClick() ended - stop button");
        } 
    };*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	 public void playmusic(View view){
		 Context context = getApplicationContext();
		 CharSequence text = "Playing";
		 int duration = Toast.LENGTH_SHORT;
		 Toast toast = Toast.makeText(context, text + " " + urlpath, duration);
		 toast.show();
		 try {
		 mplayer.setDataSource(urlpath);
		 //AssetFileDescriptor afd = getAssets().openFd("Wake Up.mp3");
		 //mplayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
		 mplayer.prepare();
		 mplayer.start();
		 }
		 catch(Exception e){
		 }
		// aacPlayer.playAsync(urlpath);		 
	 }
	 
	 public void connect(View view){
		    final Button connectButton = (Button) view;
		    if(!is_connect){
		    AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("audio-droid");
			alert.setMessage("IP Address");

			// Set an EditText view to get user input 
			final EditText input = new EditText(this);
			alert.setView(input);

			alert.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  urlpath = input.getText().toString();
			  connectButton.setText("Disconnect");
			  Toast con = Toast.makeText(getApplicationContext(), "Connected to: " + urlpath, Toast.LENGTH_LONG);
			  con.show();
			  // Do something with value!
			  }
			});

			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
			    // Canceled.
			  }
			});
			is_connect = true;
			alert.show();
		    }
		    
		    else{
		    	AlertDialog.Builder alert = new AlertDialog.Builder(this);
		    	alert.setTitle("audio-droid");
				alert.setMessage("Do you really want to disconnect?");

				alert.setPositiveButton("Disconnect", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

			      is_connect = false;
			      connectButton.setText("Connect");
				  urlpath = null;
				  Toast disco = Toast.makeText(getApplicationContext(), "You have been disconnected", Toast.LENGTH_LONG);
				  disco.show();
				  }
				});

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
				  }
				});
				alert.show();
		    }
		 }
	 }
	 /*@Override
	 protected void onStart() {//activity is started and visible to the user
	  Log.d(logtag,"onStart() called");
	  super.onStart();  
	 }
	 @Override
	 protected void onResume() {//activity was resumed and is visible again
	  Log.d(logtag,"onResume() called");
	  super.onResume();
	   
	 }
	 @Override
	 protected void onPause() { //device goes to sleep or another activity appears
	  Log.d(logtag,"onPause() called");//another activity is currently running (or user has pressed Home)
	  super.onPause();
	   
	 }
	 @Override
	 protected void onStop() { //the activity is not visible anymore
	  Log.d(logtag,"onStop() called");
	  super.onStop();
	   
	 }
	 @Override
	 protected void onDestroy() {//android has killed this activity
	   Log.d(logtag,"onDestroy() called");
	   super.onDestroy();
	 }*/


