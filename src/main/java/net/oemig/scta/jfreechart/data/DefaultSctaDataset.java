package net.oemig.scta.jfreechart.data;

import java.util.ArrayList;
import java.util.List;

import org.jfree.data.xy.AbstractXYDataset;

/**
 * A {@link DefaultSctaDataset} instance implements the 
 * {@link SctaDataset} interface and contains one or multiple
 * {@link SctaDatasetSeries} instances which in turn contain one
 * or multiple {@link SctaDatasetItem}.
 * <p>
 * Speaking in SCTA concepts a {@link DefaultSctaDataset} may contain
 * one or multiple traces (=={@link SctaDatasetSeries} instances)
 * 
 * @author christoph.oemig
 *
 */
public final class DefaultSctaDataset extends AbstractXYDataset implements
		SctaDataset {

	private static final long serialVersionUID = -5728241401997708083L;
	private List<SctaDatasetSeries> seriesList;
	private List<String> seriesKeys;

	public static DefaultSctaDataset getInstance() {
		return new DefaultSctaDataset();
	}

	//private constructor
	private DefaultSctaDataset() {
		this.seriesKeys = new ArrayList<String>();
		this.seriesList = new ArrayList<SctaDatasetSeries>();
	}

	public void addSeries(SctaDatasetSeries series) {
		this.seriesList.add(series);
		this.seriesKeys.add(series.getName());
	}

	@Override
	public int getItemCount(int series) {
		if ((series < 0) || (series >= getSeriesCount())) {
			throw new IllegalArgumentException("Series index out of bounds");
		}
		return this.seriesList.get(series).getItems().length;
	}

	@Override
	public Number getY(int series, int item) {
		return getErrorRate(series, item);
	}

	@Override
	public Number getPerformance(int series, int item) {
		return new Double(
				this.seriesList.get(series).getItems()[item].getPerformance());
	}

	@Override
	public Number getCoordinationErrorRate(int series, int item) {
		return new Double(
				this.seriesList.get(series).getItems()[item]
						.getCoordinationErrorRate());
	}

	@Override
	public int getSeriesCount() {
		return this.seriesList.size();
	}

	@Override
	public Comparable<String> getSeriesKey(int series) {
		if ((series < 0) || (series >= getSeriesCount())) {
			throw new IllegalArgumentException("Series index out of bounds");
		}
		return this.seriesList.get(series).getName();
	}

	@Override
	public Number getX(int series, int item) {
		return getResponseTimeHalfLifeOfAwarenessRatio(series, item);
	}

	@Override
	public String getSessionName(int series, int item) {
		return this.seriesList.get(series).getItems()[item].getSessionName();
	}

	@Override
	public Number getResponseTimeHalfLifeOfAwarenessRatio(int series, int item) {
		return new Double(
				this.seriesList.get(series).getItems()[item]
						.getResponseTimeHalfLifeOfAwarenessRatio());
	}

	@Override
	public Number getErrorRate(int series, int item) {
		return new Double(
				this.seriesList.get(series).getItems()[item].getErrorRate());
	}

}
