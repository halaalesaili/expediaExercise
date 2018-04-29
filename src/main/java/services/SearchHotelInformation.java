package services;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import parsing.hotelsApiParser;

/**
 * this is used as a service to filter hotel information
 * 
 * @author Hala Al Esaili
 *
 */
@Path("/getHotels")
public class SearchHotelInformation {
	/**
	 * filters hotel information based on the passed query
	 * 
	 * @param uriInfo
	 * @return
	 * @throws IOException
	 */
	@GET
	public Response getHotels(@Context UriInfo uriInfo) throws IOException {
		String query = uriInfo.getRequestUri().getQuery();
		String hotelsInformation = new hotelsApiParser().getHotelsInformation(query);
		return Response.status(200).type(MediaType.APPLICATION_JSON).entity(hotelsInformation).build();

	}

}
