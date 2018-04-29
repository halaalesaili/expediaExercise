package parsing;

import java.text.SimpleDateFormat;
import java.util.Date;

import beans.Hotel;
import beans.HotelInfo;

/**
 * used to filter which hotels are to be displayed,any error in search query
 * (like wrong date format, or searching for string instead of number) will be
 * ignored
 * 
 * @author Hala Al Esaili
 *
 */
public class HotelInformationFiltering {
	private String destination;
	private String tripDate;
	private String stay;
	private String guestRating;
	private String totalRatig;
	private String starRating;
	private String price;
	private boolean isMinTripDate;
	private boolean isMinGuestRating;
	private boolean isMinTotalRatig;
	private boolean isMinStarRating;
	private boolean isMinPrice;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}

	public String getStay() {
		return stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	public String getGuestRating() {
		return guestRating;
	}

	public void setGuestRating(String guestRating) {
		this.guestRating = guestRating;
	}

	public String getTotalRatig() {
		return totalRatig;
	}

	public void setTotalRatig(String totalRatig) {
		this.totalRatig = totalRatig;
	}

	public String getStarRating() {
		return starRating;
	}

	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public boolean isMinTripDate() {
		return isMinTripDate;
	}

	public void setMinTripDate(boolean isMinTripDate) {
		this.isMinTripDate = isMinTripDate;
	}

	public boolean isMinGuestRating() {
		return isMinGuestRating;
	}

	public void setMinGuestRating(boolean isMinGuestRating) {
		this.isMinGuestRating = isMinGuestRating;
	}

	public boolean isMinTotalRatig() {
		return isMinTotalRatig;
	}

	public void setMinTotalRatig(boolean isMinTotalRatig) {
		this.isMinTotalRatig = isMinTotalRatig;
	}

	public boolean isMinStarRating() {
		return isMinStarRating;
	}

	public void setMinStarRating(boolean isMinStarRating) {
		this.isMinStarRating = isMinStarRating;
	}

	public boolean isMinPrice() {
		return isMinPrice;
	}

	public void setMinPrice(boolean isMinPrice) {
		this.isMinPrice = isMinPrice;
	}

	/**
	 * checks if anything needs to be filtered based on the search query information
	 * 
	 * @return
	 */
	public boolean shouldFilter() {
		return shouldFilter(destination) || shouldFilter(tripDate) || shouldFilter(stay) || shouldFilter(guestRating)
				|| shouldFilter(totalRatig) || shouldFilter(starRating) || shouldFilter(price);
	}

	/**
	 * checks if value is eligible to filtering
	 * 
	 * @param value
	 * @return
	 */
	private boolean shouldFilter(String value) {
		return !(value == null || value.isEmpty());
	}

	/**
	 * filters hotel information if filtering is needed
	 * 
	 * @param hotel
	 * @return
	 */
	public boolean matchesSearchQuery(Hotel hotel) {
		return matchDestination(hotel) && matchPrice(hotel) && matchGuestRating(hotel) && matchStarRating(hotel)
				&& matchTotalRating(hotel) && matchStay(hotel) && matchTripStartDate(hotel);
	}

	/**
	 * filters destination for hotel information if filtering is needed, destination
	 * will be matched against (hotelLongDestination,destination
	 * regionID,destination LongName, hotelStreetAdress, hotelDestinationRegionID)
	 * 
	 * @param hotel
	 * @return
	 */
	private boolean matchDestination(Hotel hotel) {
		try {
			if (!shouldFilter(destination)) {
				return true;
			}
			HotelInfo hotelIfo = hotel.getHotelInfo();
			String compare = destination.toLowerCase();
			return (hotel.getDestination().getLongName().toLowerCase().contains(compare)
					|| hotelIfo.getHotelLongDestination().toLowerCase().contains(compare)
					|| hotel.getDestination().getRegionID().toLowerCase().contains(compare)
					|| hotelIfo.getHotelDestinationRegionID().toLowerCase().contains(compare)
					|| hotelIfo.getHotelStreetAddress().toLowerCase().contains(compare));
		} catch (Exception e) {
			e.printStackTrace();
			// ignore
		}
		return true;
	}

	/**
	 * filters total price for hotel information if filtering is needed
	 * 
	 * @param hotel
	 * @return
	 */
	private boolean matchPrice(Hotel hotel) {
		try {
			if (!shouldFilter(price)) {
				return true;
			}
			if (isMinPrice) {
				return hotel.getHotelPricingInfo().getTotalPriceValue() >= Double.valueOf(price);
			}
			return hotel.getHotelPricingInfo().getTotalPriceValue() <= Double.valueOf(price);
		} catch (Exception e) {
			e.printStackTrace();
			// ignore
		}
		return true;
	}

	/**
	 * filters quest rating for hotel information if filtering is needed
	 * 
	 * @param hotel
	 * @return
	 */
	private boolean matchGuestRating(Hotel hotel) {
		try {
			if (!shouldFilter(guestRating)) {
				return true;
			}
			if (isMinGuestRating) {
				return hotel.getHotelInfo().getHotelGuestReviewRating() >= Double.valueOf(guestRating);
			}
			return hotel.getHotelInfo().getHotelGuestReviewRating() <= Double.valueOf(guestRating);
		} catch (Exception e) {
			e.printStackTrace();
			// ignore
		}
		return true;
	}

	/**
	 * filters total review rating for hotel information if filtering is needed
	 * 
	 * @param hotel
	 * @return
	 */
	private boolean matchTotalRating(Hotel hotel) {
		try {
			if (!shouldFilter(totalRatig)) {
				return true;
			}
			if (isMinTotalRatig) {
				return hotel.getHotelInfo().getHotelReviewTotal() >= Double.valueOf(totalRatig);
			}
			return hotel.getHotelInfo().getHotelReviewTotal() <= Double.valueOf(totalRatig);
		} catch (Exception e) {
			e.printStackTrace();
			// ignore
		}
		return true;
	}

	/**
	 * filters star rating for hotel information if filtering is needed
	 * 
	 * @param hotel
	 * @return
	 */
	private boolean matchStarRating(Hotel hotel) {
		try {
			if (!shouldFilter(starRating)) {
				return true;
			}
			if (isMinStarRating) {
				return Double.valueOf(hotel.getHotelInfo().getHotelStarRating()) >= Double.valueOf(starRating);
			}
			return Double.valueOf(hotel.getHotelInfo().getHotelStarRating()) <= Double.valueOf(starRating);
		} catch (Exception e) {
			e.printStackTrace();
			// ignore
		}
		return true;
	}

	/**
	 * filters stay period for hotel information if filtering is needed
	 * 
	 * @param hotel
	 * @return
	 */
	private boolean matchStay(Hotel hotel) {
		try {
			if (!shouldFilter(stay)) {
				return true;
			}
			return hotel.getOfferDateRange().getLengthOfStay() == Integer.valueOf(stay);
		} catch (Exception e) {
			e.printStackTrace();
			// ignore
		}
		return true;
	}

	/**
	 * filters start date for hotel information if filtering is needed
	 * 
	 * @param hotel
	 * @return
	 */
	private boolean matchTripStartDate(Hotel hotel) {
		try {
			if (!shouldFilter(tripDate)) {
				return true;
			}
			int[] startDateArray = hotel.getOfferDateRange().getTravelStartDate();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = dateFormatter.parse(startDateArray[0] + "-" + startDateArray[1] + "-" + startDateArray[2]);
			Date searchDate = dateFormatter.parse(tripDate);
			if (isMinTripDate) {
				return searchDate.equals(startDate) || searchDate.before(startDate);
			}
			return searchDate.equals(startDate) || searchDate.after(startDate);
		} catch (Exception e) {
			e.printStackTrace();
			// ignore
		}
		return true;
	}

}