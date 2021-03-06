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
	static public native int SetProcessBudget(int pid, int budget_sec, 
			int budget_nsec, int period_sec, int period_nsec, int rtprio);
	static public native void CancelBudget(int pid);
	static public native void WaitUntilNextPeriod(int pid);
	
	static {
		System.loadLibrary("ndk1");
	}

	static public double[] getProcessPlots(int pid, int count, int millisec){
		double[] plotArr = new double[count];
		for(int i = 0; i < count; i++){
			double d = GetProcessComputePlotPoint(pid);
			Log.d(TAG, "point:" + d);
			plotArr[i] = d;
			try {
				Thread.sleep(millisec);
			} catch (InterruptedException exception) {
				// TODO Auto-generated catch-block stub.
				exception.printStackTrace();
			}
		}
		return plotArr;
	}
}
