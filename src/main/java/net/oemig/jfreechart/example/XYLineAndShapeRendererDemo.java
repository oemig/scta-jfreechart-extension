package net.oemig.jfreechart.example;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import net.oemig.scta.jfreechart.SctaItemLabelGenerator;
import net.oemig.scta.jfreechart.SctaRenderer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration of the {@link XYLineAndShapeRenderer} class.
 */
public class XYLineAndShapeRendererDemo extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYLineAndShapeRendererDemo(final String title) {

        super(title);
        XYDataset dataset = createXYZDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
            title,
            "X",
            "Y",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            false,
            false
        );
//        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new SctaRenderer();
        
        //background image
      URL picURL = getClass().getResource("4i.gif");
      try {
			Image image=ImageIO.read(picURL);
	        chart.getPlot().setBackgroundImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        renderer.setBaseItemLabelGenerator(new SctaItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        
        chart.getXYPlot().setRenderer(renderer);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);

    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return A dataset.
     */
    private XYDataset createXYDataset() {
        XYSeries series1 = new XYSeries("Series 1");
        series1.add(1.0, 3.3);
        series1.add(2.0, 4.4);
        series1.add(3.0, 1.7);
//        XYSeries series2 = new XYSeries("Series 2");
//        series2.add(1.0, 7.3);
//        series2.add(2.0, 6.8);
//        series2.add(3.0, 9.6);
//        series2.add(4.0, 5.6);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
//        dataset.addSeries(series2);
        return dataset;
    }
    private XYZDataset createXYZDataset() {
    	DefaultXYZDataset d = new DefaultXYZDataset();
        double[] x1 = new double[] {1.0, 2.0, 3.0};
        double[] y1 = new double[] {4.0, 5.0, 6.0};
        double[] z1 = new double[] {0.1, 0.5, 1.0};
        double[][] data1 = new double[][] {x1, y1, z1};
        d.addSeries("S1", data1);
//        double[] x2 = new double[] {1.0, 2.0, 3.0};
//        double[] y2 = new double[] {4.0, 5.0, 6.0};
//        double[] z2 = new double[] {7.0, 8.0, 9.0};
//        double[][] data2 = new double[][] {x2, y2, z2};
//        d.addSeries("S2", data2);

        return d;
    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final XYLineAndShapeRendererDemo demo = new XYLineAndShapeRendererDemo(
            "XYLineAndShapeRenderer Demo"
        );
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
