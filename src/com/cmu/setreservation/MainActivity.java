package com.cmu.setreservation;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static String TAG = MainActivity.class.getSimpleName();

	private TextView processLine;
	private SetReservationApplication setReservationApp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setReservationApp =(SetReservationApplication) this.getApplication();
        // Fine my view
        processLine = (TextView) findViewById(R.id.processLine);
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	
    	// Get data from process List
    	setReservationApp.updateProcessList();
    	List<RunningAppProcessInfo> processList = setReservationApp.getProcessListOnCopy();
    	if(processList != null){
    		for(RunningAppProcessInfo info : processList){
    			processLine.append(info.processName + "\t" + info.pid + "\n");
    		}
    	}else{
    		Log.d(TAG, "processLine is null!");
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
