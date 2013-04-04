package net.oemig.scta.jfreechart.data;

import java.util.HashMap;
import java.util.Map;


/**
 * The {@link SctaDatasetBuilder} is a convenience class
 * for building and filling {@link SctaDataset} instances.
 * 
 * @author christoph.oemig
 *
 */
public class SctaDatasetBuilder {
	
	public static SctaDatasetBuilder of(String aTraceName, SctaDatasetItem...items){
		return new SctaDatasetBuilder().addSeries(aTraceName, items);
	}
	
	public static SctaDatasetBuilder of(String aTraceName){
		return new SctaDatasetBuilder().addEmptySeries(aTraceName);
	}

	private DefaultSctaDataset ds;
	private Map<String, SctaDatasetSeries> traceMap;
	
	
	private SctaDatasetBuilder() {
		ds=DefaultSctaDataset.getInstance();
		traceMap=new HashMap<String,SctaDatasetSeries>();
	}
	
	
	public SctaDatasetBuilder addSeries(String aTraceName,SctaDatasetItem...items){
		SctaDatasetSeries series=new SctaDatasetSeries(aTraceName);
		for(SctaDatasetItem item:items){
			series.add(item);
		}
		ds.addSeries(series);
		traceMap.put(aTraceName, series);
		return this;
	}
	
	public SctaDatasetBuilder addEmptySeries(String aTraceName){
		SctaDatasetSeries series=new SctaDatasetSeries(aTraceName);
		ds.addSeries(series);
		traceMap.put(aTraceName, series);
		
		return this;
	}
	
	public SctaDatasetBuilder addItemToSeries(String aTraceName, SctaDatasetItem item){
		traceMap.get(aTraceName).add(item);
		return this;
	}
	
	public SctaDatasetBuilder addItemToSeries(String aTraceName, String aSessionName, double responseTime, double errorRate, double coordinationErrorRate, double performance){
		return addItemToSeries(aTraceName, new SctaDatasetItem(aSessionName, responseTime, errorRate, coordinationErrorRate, performance));
	}
	
	public SctaDataset build(){
		return ds;
	}

}
