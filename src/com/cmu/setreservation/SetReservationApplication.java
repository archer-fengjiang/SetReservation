package com.cmu.setreservation;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Fengjiang.
 *         Created Nov 11, 2012.
 */
public class SetReservationApplication extends Application{
	
	private static final String TAG = SetReservationApplication.class.getSimpleName();
	Context context;
	private ActivityManager activityManager;
	private List<RunningAppProcessInfo> processList;
	
	@Override
	public void onCreate(){
		super.onCreate();
		context = getApplicationContext();
		activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	}
	
	@Override
	public void onTerminate(){
		super.onTerminate();
	}
	
	public void updateProcessList(){
		processList = activityManager.getRunningAppProcesses();
	}
	
	public List<RunningAppProcessInfo> getProcessListOnCopy(){
		return processList;
	}

}
