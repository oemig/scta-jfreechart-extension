package net.oemig.scta.jfreechart.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A {@link SctaDatasetSeries} contains one or multiple
 * {@link SctaDatasetItem} instances and corresponds to a
 * SCTA trace.
 * 
 * @author christoph.oemig
 *
 */
public class SctaDatasetSeries {
	
	//the trace name
	private String name;
	
	//the sessions
	private List<SctaDatasetItem> items;
	
	public SctaDatasetSeries(String newName){
		this.items=new ArrayList<SctaDatasetItem>();
		this.name=newName;
	}
	
	public void add(SctaDatasetItem item){
		this.items.add(item);
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public SctaDatasetItem[] getItems() {
		return items.toArray(new SctaDatasetItem[items.size()]);
	}
	public void setItems(SctaDatasetItem[] items) {
		this.items = Arrays.asList(items);
	}

}
