package com.cmu.setreservation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Fengjiang.
 *         Created Nov 12, 2012.
 */
public class PlotChart {
	
	public Intent execute(Context context, int pid, int count, int interval_millisec){
		String[] titles = new String[] { count +" plots for process " + pid + " with interval " + interval_millisec + " milli second" };
		List<double[]> x = new ArrayList<double[]>();
 	    List<double[]> values = new ArrayList<double[]>();
//	    values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,
//	        12600, 14000 });
//	    values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030, 11200, 9500, 10500,
//	        11600, 13500 });
 	    double tik = 0.0;
 	    double interval_sec = interval_millisec / 1000000.0;
 	    double[] tiks = new double[count];
 	    for(int i = 0; i < count; i++){
 	    	tiks[i] = tik;
 	    	tik+=interval_sec;
 	    }
 	    x.add(tiks);
	    values.add(MySyscall.getProcessPlots(pid, count, interval_millisec));
//	    int[] colors = new int[] { Color.BLUE, Color.CYAN };
	    int[] colors = new int[] { Color.BLUE};
	    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
	    setChartSettings(renderer, "Ploting for process" + pid, "Time", "Executed", 0.5,
	        12.5, 0, 2, Color.GRAY, Color.LTGRAY);
	    renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
//	    renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setXLabelsAlign(Align.LEFT);
	    renderer.setYLabelsAlign(Align.LEFT);
	    renderer.setPanEnabled(true, false);
	    // renderer.setZoomEnabled(false);
	    renderer.setZoomRate(1.1f);
	    renderer.setBarSpacing(0.1f);
	    return ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,
	        Type.STACKED);
	}


	private XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			CategorySeries series = new CategorySeries(titles[i]);
			double[] v = values.get(i);
			int seriesLength = v.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(v[k]);
			}
			dataset.addSeries(series.toXYSeries());
		}
		return dataset;
	}
	
	private XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    renderer.setAxisTitleTextSize(16);
	    renderer.setChartTitleTextSize(20);
	    renderer.setLabelsTextSize(15);
	    renderer.setLegendTextSize(15);
	    int length = colors.length;
	    for (int i = 0; i < length; i++) {
	      SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	      r.setColor(colors[i]);
	      renderer.addSeriesRenderer(r);
	    }
	    return renderer;
	}
	
	private void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
			String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
	}
}
