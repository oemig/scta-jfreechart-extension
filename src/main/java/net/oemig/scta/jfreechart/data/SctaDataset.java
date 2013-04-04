package net.oemig.scta.jfreechart.data;

import org.jfree.data.xy.XYDataset;

public interface SctaDataset extends XYDataset{
	
	public Number getPerformance(int series, int item);
	
	public Number getCoordinationErrorRate(int series, int item);
	
	public Number getResponseTimeHalfLifeOfAwarenessRatio(int series, int item);
	
	public Number getErrorRate(int series, int item);
	
	public String getSessionName(int series, int item);

}
