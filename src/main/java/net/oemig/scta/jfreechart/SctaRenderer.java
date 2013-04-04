package net.oemig.scta.jfreechart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import net.oemig.scta.jfreechart.data.SctaDataset;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ShapeUtilities;

/**
 * A renderer that draws shapes at (x, y) coordinates and, if the dataset is an
 * instance of {@link XYZDataset}, fills the shapes with a paint that is based
 * on the z-value (the paint is obtained from a lookup table). The renderer also
 * allows for optional guidelines, horizontal and vertical lines connecting the
 * shape to the edges of the plot. <br>
 * <br>
 * The example shown here is generated by the
 * <code>XYShapeRendererDemo1.java</code> program included in the JFreeChart
 * demo collection: <br>
 * <br>
 * <img src="../../../../../images/XYShapeRendererSample.png"
 * alt="XYShapeRendererSample.png" /> <br>
 * <br>
 * This renderer has similarities to, but also differences from, the
 * {@link XYLineAndShapeRenderer}.
 * 
 * @since 1.0.11
 */
public class SctaRenderer extends XYLineAndShapeRenderer {

	/** Auto generated serial version id. */
	private static final long serialVersionUID = 8320552104211173221L;

	/** The paint scale. */
	private LookupPaintScale paintScale;

	/** A flag that controls whether or not the shape outlines are drawn. */
	private boolean drawOutlines;

	/**
	 * A flag that controls whether or not the outline paint is used (if not,
	 * the regular paint is used).
	 */
	private boolean useOutlinePaint;

	/**
	 * A flag that controls whether or not the fill paint is used (if not, the
	 * fill paint is used).
	 */
	private boolean useFillPaint;

	/** Flag indicating if guide lines should be drawn for every item. */
	private boolean guideLinesVisible;

	/** The paint used for drawing the guide lines. */
	private transient Paint guideLinePaint;

	/** The stroke used for drawing the guide lines. */
	private transient Stroke guideLineStroke;

	/**
	 * Creates a new <code>XYShapeRenderer</code> instance with default
	 * attributes.
	 */
	public SctaRenderer() {
		this.paintScale = new LookupPaintScale();
		this.paintScale.add(0.1, Color.RED);
		this.paintScale.add(0.5, Color.YELLOW);
		this.paintScale.add(1.0, Color.GREEN);

		this.useFillPaint = false;
		this.drawOutlines = false;
		this.useOutlinePaint = true;
		this.guideLinesVisible = false;
		this.guideLinePaint = Color.darkGray;
		this.guideLineStroke = new BasicStroke();
		setBaseShape(new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));
		setAutoPopulateSeriesShape(false);
	}

	/**
	 * Get the paint for a given series and item from a dataset.
	 * 
	 * @param dataset
	 *            the dataset..
	 * @param series
	 *            the series index.
	 * @param item
	 *            the item index.
	 * 
	 * @return The paint.
	 */
	protected Paint getPaint(XYDataset dataset, int series, int item) {
		Paint p = null;
		if (dataset instanceof SctaDataset) {
			double z = ((SctaDataset) dataset).getCoordinationErrorRate(series, item).doubleValue();
			p = this.paintScale.getPaint(z);
		} else {
			if (this.useFillPaint) {
				p = getItemFillPaint(series, item);
			} else {
				p = getItemPaint(series, item);
			}
		}
		return p;
	}

	@Override
	public Shape getItemShape(int series, int item) {
		if (this.getPlot().getDataset() instanceof SctaDataset) {
			double z = ((SctaDataset) this.getPlot().getDataset()).getPerformance(
					series, item).doubleValue();
			return new Ellipse2D.Double(-(20 * z / 2), -(20 * z / 2), 20 * z,
					20 * z);
		}
		return super.getItemShape(series, item);
	}

	protected void drawSecondaryPass(Graphics2D g2, XYPlot plot,
			XYDataset dataset, int pass, int series, int item,
			ValueAxis domainAxis, Rectangle2D dataArea, ValueAxis rangeAxis,
			CrosshairState crosshairState, EntityCollection entities) {

		Shape entityArea = null;

		// get the data point...
		double x1 = dataset.getXValue(series, item);
		double y1 = dataset.getYValue(series, item);
		if (Double.isNaN(y1) || Double.isNaN(x1)) {
			return;
		}

		PlotOrientation orientation = plot.getOrientation();
		RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
		RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
		double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
		double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);

		if (getItemShapeVisible(series, item)) {
			Shape shape = getItemShape(series, item);
			if (orientation == PlotOrientation.HORIZONTAL) {
				shape = ShapeUtilities.createTranslatedShape(shape, transY1,
						transX1);
			} else if (orientation == PlotOrientation.VERTICAL) {
				shape = ShapeUtilities.createTranslatedShape(shape, transX1,
						transY1);
			}
			entityArea = shape;
			if (shape.intersects(dataArea)) {
				//ACHTUNG HIER IST DIE AENDERUNG... Farbenselektion aus PaintScale
				g2.setPaint(getPaint(dataset, series, item));
                g2.fill(shape);
				if (this.drawOutlines) {
					if (getUseOutlinePaint()) {
						g2.setPaint(getItemOutlinePaint(series, item));
					} else {
						g2.setPaint(getItemPaint(series, item));
					}
					g2.setStroke(getItemOutlineStroke(series, item));
					g2.draw(shape);
				}
			}
		}

		double xx = transX1;
		double yy = transY1;
		if (orientation == PlotOrientation.HORIZONTAL) {
			xx = transY1;
			yy = transX1;
		}

		// draw the item label if there is one...
		if (isItemLabelVisible(series, item)) {
			drawItemLabel(g2, orientation, dataset, series, item, xx, yy,
					(y1 < 0.0));
		}

		int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
		int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
		updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex,
				rangeAxisIndex, transX1, transY1, orientation);

		// add an entity for the item, but only if it falls within the data
		// area...
		if (entities != null && isPointInRect(dataArea, xx, yy)) {
			addEntity(entities, entityArea, dataset, series, item, xx, yy);
		}
	}

	//

}
