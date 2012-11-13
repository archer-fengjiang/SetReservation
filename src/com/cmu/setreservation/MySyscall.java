package com.cmu.setreservation;

import java.util.LinkedList;
import java.util.List;

import android.util.Log;

/**
 * This class provide static functions for making system calls
 * 1. SetProcessBudget
 * 2. CancelBudget
 * 3. WaitUntilNextPeriod
 * 
 * @author Fengjiang.
 *         Created Nov 10, 2012.
 */
public class MySyscall {
	
	private static final String TAG = MySyscall.class.getSimpleName();
	
	static public native int GetProcessComputePlotPoint(int pid);
	
	static {
		System.loadLibrary("ndk1");
	}
	
	
	
	static public void SetProcessBudget(int pid, int t, int budget){
		//
	}
	
	static public void CancelBudget(int pid){
		//
	}
	
	static public void WaitUntilNextPeriod(int pid){
		//
	}
	
	static public double[] getProcessPlots(int pid, int count){
		double[] plotArr = new double[count];
		for(int i = 0; i < count; i++){
			double d = GetProcessComputePlotPoint(pid);
			Log.d(TAG, "point:" + d);
			plotArr[i] = d;
			try {
				Thread.sleep(10);
			} catch (InterruptedException exception) {
				// TODO Auto-generated catch-block stub.
				exception.printStackTrace();
			}
		}
		return plotArr;
	}
}
