package com.cmu.setreservation;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static String TAG = MainActivity.class.getSimpleName();

	private final Context context = this;
	private TextView processLine;
	private SetReservationApplication setReservationApp;
	private StringBuffer buffer = new StringBuffer();

	// Buttons
	private Button refreshButton;
	private Button setBudgetButton;
	private Button getPlotButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setReservationApp =(SetReservationApplication) this.getApplication();

		// Fine my view
		this.processLine = (TextView) findViewById(R.id.processLine);

		// Fine refresh button
		this.refreshButton = (Button) findViewById(R.id.refreshButton);
		this.refreshButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				setReservationApp.updateProcessList();
				List<RunningAppProcessInfo> processList = setReservationApp.getProcessListOnCopy();
				if(processList != null){
					buffer.setLength(0);
					for(RunningAppProcessInfo info : processList){
						buffer.append(info.processName + "\t" + info.pid + "\n");
					}
					processLine.setText(buffer.toString());
				}
			}
		});

		// Find setBudget button
		this.setBudgetButton = (Button) findViewById(R.id.setBudgetButton);
		this.setBudgetButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// get prompts.xml view
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.setbudget_prompts, null);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);

				final EditText pidText = (EditText) promptsView.findViewById(R.id.editTextPID);
				final EditText CText = (EditText) promptsView.findViewById(R.id.editC);
				final EditText TText = (EditText) promptsView.findViewById(R.id.editT);
				final EditText PText = (EditText) promptsView.findViewById(R.id.editP);
				
				// set dialog message
				alertDialogBuilder.setCancelable(false);
				alertDialogBuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// get user input and set it to result
						// edit text
						Log.d(TAG, "PID:"+pidText.getText()+"\tC:"+CText.getText()+"\tT:"+TText.getText()+"\tP:"+PText.getText());
					}
				});
				alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
			}
		});
		
		// fine getPlot button
		this.getPlotButton = (Button) findViewById(R.id.getPlotButton);
		this.getPlotButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(final View v) {
				// get prompts.xml view
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.getplot_prompts, null);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);

				final EditText pidText = (EditText) promptsView.findViewById(R.id.editTextGetplotPID);
				
				// set dialog message
				alertDialogBuilder.setCancelable(false);
				alertDialogBuilder.setPositiveButton("Get", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// get user input and set it to result
						// edit text
						int pid = -1;
						try{
							pid = Integer.parseInt(pidText.getText().toString());
						}catch(NumberFormatException e){}
						if(pid != -1){
							Log.d(TAG, "PID:"+pid);
//							Intent mIntent = new Intent(v.getContext(), GetPlotActivity.class);
//							Bundle b = new Bundle();
//							b.putInt("PID", pid);
//							mIntent.putExtras(b);
//							startActivityForResult(mIntent, 0);
							PlotChart chart = new PlotChart();
							Intent mIntent = chart.execute(v.getContext(), pid, 10000);
							startActivityForResult(mIntent, 0);
						}
					}
				});
				alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
			}
			
		});
	}

	@Override
	public void onResume(){
		super.onResume();
		// Get data from process List
		setReservationApp.updateProcessList();
		List<RunningAppProcessInfo> processList = setReservationApp.getProcessListOnCopy();
		if(processList != null){
			buffer.setLength(0);
			for(RunningAppProcessInfo info : processList){
				buffer.append(info.processName + "\t" + info.pid + "\n");
			}
			processLine.setText(buffer.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
