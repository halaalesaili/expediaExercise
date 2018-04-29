package beans;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Hala Al Esaili
 */
public class Offers {

	private List<Hotel> Hotel = new ArrayList<Hotel>();

	public List<Hotel> getHotel() {
		return Hotel;
	}

	public void setHotel(List<Hotel> Hotel) {
		this.Hotel = Hotel;
	}

}
