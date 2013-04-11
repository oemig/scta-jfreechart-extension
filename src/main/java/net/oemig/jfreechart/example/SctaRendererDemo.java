package net.oemig.jfreechart.example;

import net.oemig.scta.jfreechart.SctaItemLabelGenerator;
import net.oemig.scta.jfreechart.SctaRenderer;
import net.oemig.scta.jfreechart.SctaToolTipGenerator;
import net.oemig.scta.jfreechart.data.SctaDataset;
import net.oemig.scta.jfreechart.data.SctaDatasetBuilder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration of the {@link XYLineAndShapeRenderer} class.
 */
public class SctaRendererDemo extends ApplicationFrame {

	private static final long serialVersionUID = -6586841681409528443L;

	/**
	 * Constructs the demo application.
	 * 
	 * @param title
	 *            the frame title.
	 */
	public SctaRendererDemo(final String title) {

		super(title);
		XYDataset dataset = createSCTADataset("tracy");
		JFreeChart chart = ChartFactory.createXYLineChart(title, "Quickness","Successrate",
				dataset, PlotOrientation.VERTICAL, true, false, false);
		XYLineAndShapeRenderer renderer = new SctaRenderer();

		// background image
//		URL picURL = getClass().getResource("test.jpg");
//		try {
//			Image image = ImageIO.read(picURL);
//			chart.getPlot().setBackgroundImage(image);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.getDomainAxis().setAutoRange(false);
		plot.getDomainAxis().setRange(new Range(0, 100));
		plot.getRangeAxis().setAutoRange(false);
		plot.getRangeAxis().setRange(new Range(0, 100));

		renderer.setBaseItemLabelGenerator(new SctaItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseToolTipGenerator(new SctaToolTipGenerator());
		
		renderer.setSeriesVisibleInLegend(0, false);
		renderer.setSeriesVisibleInLegend(1, false);

		chart.getXYPlot().setRenderer(renderer);
		
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
		setContentPane(chartPanel);

	}

	private SctaDataset createSCTADataset(String traceName) {
//		 DefaultSCTADataset d=DefaultSCTADataset.getInstance();
//		 SCTADatasetSeries s=new SCTADatasetSeries("Trace");
//		
//		 s.add(new SCTADatasetItem("A0", 88.0, 68.0, 0.5, 2.0));
//		 s.add(new SCTADatasetItem("A1", 44.0, 50.0, 1.0, 1.0));
//		 s.add(new SCTADatasetItem("A2", 10.0, 11.0, 1.0, 1.0));
//		 d.addSeries(s);
		return SctaDatasetBuilder.of(traceName).
				addItemToSeries(traceName, "A0", 88.0, 68.0, 0.5, 2.0).
				addItemToSeries(traceName, "A1", 44.0, 50.0, 1.0, 1.0).
				addItemToSeries(traceName, "A2", 10.0, 11.0, 1.0, 1.0).
				addEmptySeries("d").
				addItemToSeries("d", "B1", 49.0, 20.0, 1.0, 1.0).
				addItemToSeries("d", "B2", 13.0, 31.0, 1.0, 1.0).
				build();
//		return null;
	}

	// ****************************************************************************
	// * JFREECHART DEVELOPER GUIDE *
	// * The JFreeChart Developer Guide, written by David Gilbert, is available
	// *
	// * to purchase from Object Refinery Limited: *
	// * *
	// * http://www.object-refinery.com/jfreechart/guide.html *
	// * *
	// * Sales are used to provide funding for the JFreeChart project - please *
	// * support us so that we can continue developing free software. *
	// ****************************************************************************

	/**
	 * Starting point for the demonstration application.
	 * 
	 * @param args
	 *            ignored.
	 */
	public static void main(final String[] args) {

		final SctaRendererDemo demo = new SctaRendererDemo("SCTARenderer Demo");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}
