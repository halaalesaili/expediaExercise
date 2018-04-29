package beans;

/**
 *
 * @author Hala Al Esaili
 */
public class HotelPricingInfo {

	private double averagePriceValue;
	private double totalPriceValue;
	private double crossOutPriceValue;
	private double originalPricePerNight;
	private String currency;
	private double percentSavings;
	private boolean drr;

	public double getAveragePriceValue() {
		return averagePriceValue;
	}

	public void setAveragePriceValue(double averagePriceValue) {
		this.averagePriceValue = averagePriceValue;
	}

	public double getTotalPriceValue() {
		return totalPriceValue;
	}

	public void setTotalPriceValue(double totalPriceValue) {
		this.totalPriceValue = totalPriceValue;
	}

	public double getCrossOutPriceValue() {
		return crossOutPriceValue;
	}

	public void setCrossOutPriceValue(double crossOutPriceValue) {
		this.crossOutPriceValue = crossOutPriceValue;
	}

	public double getOriginalPricePerNight() {
		return originalPricePerNight;
	}

	public void setOriginalPricePerNight(double originalPricePerNight) {
		this.originalPricePerNight = originalPricePerNight;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getPercentSavings() {
		return percentSavings;
	}

	public void setPercentSavings(double percentSavings) {
		this.percentSavings = percentSavings;
	}

	public boolean isDrr() {
		return drr;
	}

	public void setDrr(boolean drr) {
		this.drr = drr;
	}
}
