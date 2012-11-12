package com.cmu.setreservation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Fengjiang.
 *         Created Nov 12, 2012.
 */
public class GetPlotActivity extends Activity{
	private static final String TAG = GetPlotActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getplot);
		Intent mIntent = getIntent();
		int pid = mIntent.getExtras().getInt("PID");
		Log.d(TAG, "PID:"+ pid);
	}
}