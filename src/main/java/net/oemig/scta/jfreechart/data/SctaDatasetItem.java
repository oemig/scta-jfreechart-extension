package net.oemig.scta.jfreechart.data;

/**
 * A {@link SctaDatasetItem} corresponds to a SCTA session.
 * 
 * @author christoph.oemig
 *
 */
public class SctaDatasetItem {
	
	private String sessionName;
	private double responseTimeHalfLifeOfAwarenessRatio;
	private double errorRate;
	private double coordinationErrorRate;
	private double performance;
	
	/**
	 * Constructor
	 * @param name - name of the sesseion
	 * @param responseTime
	 * @param errorRate
	 * @param coordinationErrorRate
	 * @param performance
	 */
	public SctaDatasetItem(String name, double responseTime, double errorRate, double coordinationErrorRate, double performance){
		this.sessionName=name;
		this.responseTimeHalfLifeOfAwarenessRatio=responseTime;
		this.errorRate=errorRate;
		this.coordinationErrorRate=coordinationErrorRate;
		this.performance=performance;
	}
	
	
	public double getResponseTimeHalfLifeOfAwarenessRatio() {
		return responseTimeHalfLifeOfAwarenessRatio;
	}
	public void setResponseTimeHalfLifeOfAwarenessRatio(
			double responseTimeHalfLifeOfAwarenessRatio) {
		this.responseTimeHalfLifeOfAwarenessRatio = responseTimeHalfLifeOfAwarenessRatio;
	}
	public double getErrorRate() {
		return errorRate;
	}
	public void setErrorRate(double errorRate) {
		this.errorRate = errorRate;
	}
	public double getCoordinationErrorRate() {
		return coordinationErrorRate;
	}
	public void setCoordinationErrorRate(double coordinationErrorRate) {
		this.coordinationErrorRate = coordinationErrorRate;
	}
	public double getPerformance() {
		return performance;
	}
	public void setPerformance(double performance) {
		this.performance = performance;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

}
