package beans;

/**
 *
 * @author Hala Al Esaili
 */
public class OfferDateRange {

    private int[] travelStartDate;
    private int[] travelEndDate;
    private int lengthOfStay;

    public int[] getTravelStartDate() {
        return travelStartDate;
    }

    public void setTravelStartDate(int[] travelStartDate) {
        this.travelStartDate = travelStartDate;
    }

    public int[] getTravelEndDate() {
        return travelEndDate;
    }

    public void setTravelEndDate(int[] travelEndDate) {
        this.travelEndDate = travelEndDate;
    }

    public int getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(int lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }
}
