package net.oemig.jfreechart.example;
import java.awt.Color;
import java.awt.Image;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;

import javax.imageio.ImageIO;

import net.oemig.scta.jfreechart.SctaItemLabelGenerator;
import net.oemig.scta.jfreechart.SctaRenderer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYZToolTipGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYShapeRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration of the {@link XYLineAndShapeRenderer} class.
 */
public class XYShapeRendererDemo extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYShapeRendererDemo(final String title) {

        super(title);
        XYZDataset dataset = createSampleDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
            title,
            "Avg Response Time",
            "Error Rate",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
//        XYShapeRenderer renderer = new XYShapeRenderer();
        SctaRenderer renderer=new SctaRenderer();
        
        //Lower/upper bounds lets you set the max min values
        LookupPaintScale ps=new LookupPaintScale();
        ps.add(0.1,Color.BLUE);
        ps.add(0.5, Color.RED);
        ps.add(1.0, Color.GREEN);
//        renderer.setPaintScale(ps);
        
//      renderer.setPaintScale(new GrayPaintScale());
        
        
        //background image
//        URL picURL = getClass().getResource("test.jpg");
//        try {
//			Image image=ImageIO.read(picURL);
//	        chart.getPlot().setBackgroundImage(image);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

        
        
        //tooltips
        renderer.setBaseToolTipGenerator(new StandardXYZToolTipGenerator());
        
        //item labels
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        XYItemLabelGenerator generator = new SctaItemLabelGenerator();
//            new StandardXYItemLabelGenerator(
//                StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT,
//                format, format);
        renderer.setItemLabelGenerator(generator);
        renderer.setItemLabelsVisible(true);

        //wenn man die einzige series rausnimmt, verschwindet die legende unten
//        renderer.setSeriesVisibleInLegend(0, false);
        
        
//        renderer.setSeriesShape(0, new Rectangle2D.Double(0, 0, 10, 10));

        
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
    private XYZDataset createSampleDataset() {
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

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final XYShapeRendererDemo demo = new XYShapeRendererDemo(
            "XYShapeRenderer Demo"
        );
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
}
