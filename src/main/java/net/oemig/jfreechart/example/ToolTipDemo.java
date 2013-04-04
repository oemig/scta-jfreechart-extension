package net.oemig.jfreechart.example;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.xy.DefaultXYDataset;

public class ToolTipDemo {
   public static void main(String[] args) {
      DefaultXYDataset set = new DefaultXYDataset();
      set.addSeries("Values",new double[][]{{1,2,3.25,4,5},{10,15,8,37,23}});      
      JFreeChart chart = ChartFactory.createXYLineChart(
         "Tool Tips","x","y",
         set,PlotOrientation.VERTICAL,true, true, false);
      XYShapeRenderer r = new XYShapeRenderer();
      chart.getXYPlot().setRenderer(r);
      //XYItemRenderer r = chart.getXYPlot().getRenderer();
      r.setToolTipGenerator(new StandardXYToolTipGenerator());
      r.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
      r.setBaseItemLabelsVisible(true);
      JFrame frame = new JFrame("Test");
      frame.setContentPane(new ChartPanel(chart));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}