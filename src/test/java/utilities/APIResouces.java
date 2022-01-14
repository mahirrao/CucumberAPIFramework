package utilities;

public enum APIResouces
{
	AddPlaceAPI( "maps/api/place/add/json" ),
	DeletePlaceAPI("maps/api/place/delete/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	PutPlaceAPI("maps/api/place/update/json")	;

	private String resource;
	public String getResource()
	{
		return resource;
	}
	
	APIResouces(String resource)
	{
		this.resource = resource;
	}
	
}
