package parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import beans.Hotel;
import beans.HotelsAPI;

/**
 * parses expedia API, filters search result, and create search result as json
 * 
 * @author Hala Al Esaili
 *
 */
public class hotelsApiParser {
	private static final String HOTELS_API = "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel";

	/**
	 * reads expedia API json response, parses the search query and return
	 * appropriate result with the same format as expedia API
	 * 
	 * @param query
	 * @return
	 * @throws IOException
	 */
	public String getHotelsInformation(String query) throws IOException {
		URL url = new URL(HOTELS_API);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();
		String resultsJson;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream) request.getContent()))) {
			resultsJson = reader.lines().collect(Collectors.joining(""));
		}
		HotelInformationFiltering searchInformation = parseSearchQuery(query);
		return filterResult(resultsJson, searchInformation);
	}

	/**
	 * filters API JSON based on search information, if no filtering is applicable
	 * then result is returned as is, if filtering is required then hotels that
	 * don't match the filter are removed from the response then the response is
	 * parsed to JSON again to be consumed by the service
	 * 
	 * @param resultsJson
	 * @param searchInformation
	 * @return
	 */
	private String filterResult(String resultsJson, HotelInformationFiltering searchInformation) {
		if (!searchInformation.shouldFilter()) {
			return resultsJson;
		}
		Gson gson = new Gson();
		HotelsAPI hotelApi = gson.fromJson(resultsJson, HotelsAPI.class);
		List<Hotel> hotelsToRemove = new ArrayList<Hotel>();
		for (Hotel hotel : hotelApi.getOffers().getHotel()) {
			if (!searchInformation.matchesSearchQuery(hotel)) {
				hotelsToRemove.add(hotel);
			}
		}
		hotelApi.getOffers().getHotel().removeAll(hotelsToRemove);
		return gson.toJson(hotelApi);
	}

	/**
	 * convert search query string to information used for filtering the API result
	 * the seach query is assumed to be: [destination]&[start
	 * date:min/max]&[stay]&[guest rating:min/max]&[total rating:min/max]&[star
	 * rating:min/max]&[price:min/max]&
	 * 
	 * @param query
	 * @return
	 */
	private HotelInformationFiltering parseSearchQuery(String query) {
		HotelInformationFiltering searchInformation = new HotelInformationFiltering();
		if (query == null || query.isEmpty()) {
			return searchInformation;
		}
		String searchQueryArray[] = query.split("&");
		String value = searchQueryArray[0];
		searchInformation.setDestination(value.replace("destination=", "").trim());
		value = searchQueryArray[2];
		searchInformation.setStay(value.replace("stay=", "").trim());
		value = searchQueryArray[1];
		value = value.replace("tripDate=", "");
		String[] tripDateInfo = value.split(":");
		if (tripDateInfo[0].trim().length() > 0) {
			searchInformation.setTripDate(tripDateInfo[0].trim());
			searchInformation.setMinTripDate("min".equals(tripDateInfo[1]));
		}
		value = searchQueryArray[3];
		value = value.replace("guestRating=", "");
		String[] ratigInfo = value.split(":");
		if (ratigInfo[0].trim().length() > 0) {
			searchInformation.setGuestRating(ratigInfo[0].trim());
			searchInformation.setMinGuestRating("min".equals(ratigInfo[1]));
		}
		value = searchQueryArray[4];
		value = value.replace("totalRating=", "");
		ratigInfo = value.split(":");
		if (ratigInfo[0].trim().length() > 0) {
			searchInformation.setTotalRatig(ratigInfo[0].trim());
			searchInformation.setMinTotalRatig("min".equals(ratigInfo[1]));
		}
		value = searchQueryArray[5];
		value = value.replace("starRating=", "");
		ratigInfo = value.split(":");
		if (ratigInfo[0].trim().length() > 0) {
			searchInformation.setStarRating(ratigInfo[0].trim());
			searchInformation.setMinStarRating("min".equals(ratigInfo[1]));
		}

		value = searchQueryArray[6];
		value = value.replace("price=", "");
		String[] priceInfo = value.split(":");
		if (priceInfo[0].trim().length() > 0) {
			searchInformation.setPrice(priceInfo[0].trim());
			searchInformation.setMinPrice("min".equals(priceInfo[1]));
		}
		return searchInformation;
	}
}
