package TestData;

import java.util.ArrayList;
import java.util.List;

import PlacesPOJO.Location;
import PlacesPOJO.PlaceDetails;

public class APIBody
{
	public PlaceDetails addPlacePayload(String name, String language, String address)
	{
		PlaceDetails addPlace = new PlaceDetails();

		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setName(name);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("https://rahulshettyacademy.com");

		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);

		addPlace.setLocation(location);

		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");

		addPlace.setTypes(types);
		return addPlace;
	}
	
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n"
				+ "    \"place_id\":\"" +place_id+ "\"\r\n"
				+ "}";
	}
}
