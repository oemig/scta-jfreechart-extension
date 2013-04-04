package net.oemig.scta.jfreechart;

import java.io.Serializable;

import net.oemig.scta.jfreechart.data.SctaDataset;

import org.jfree.chart.labels.AbstractXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.PublicCloneable;

public class SctaItemLabelGenerator  extends AbstractXYItemLabelGenerator implements  XYItemLabelGenerator, Cloneable, PublicCloneable, Serializable {

	private static final long serialVersionUID = -6299990286658215645L;

	@Override
	public String generateLabel(XYDataset dataset, int series, int item) {
		if (dataset instanceof SctaDataset) {
			return ((SctaDataset) dataset).getSessionName(series, item);
		}
		
		return "???";
	}

}
