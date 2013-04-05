package net.oemig.scta.jfreechart;

import net.oemig.scta.jfreechart.data.SctaDataset;

import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;

public class SctaToolTipGenerator implements XYToolTipGenerator{

	@Override
	public String generateToolTip(XYDataset dataset, int series, int item) {
		if (dataset instanceof SctaDataset) {
			return ((SctaDataset) dataset).getSessionName(series, item);
		}
		
		return "???";

	}

}
