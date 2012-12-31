/********************************************
******Information about each event that*****
will go into our calendar page will be stored
here where users can find information about 
**************each event*********************
********************************************/

var Event = function(name, date, time, zipcode, location, username) {
	this.name = name;
	this.date = date;
	this.time = time;
	this.zipcode = zipcode;
	this.location = location;
	this.username = username;
}

Event.prototype.findOutfit() 
{
	/*Queuries Outfit Manager for outfit that would go with given weather from given zipcode, date, time, and location
	*/

}

Event.prototype.getWeather()
{
	/*Gets weather from WeatherBox for event
	*/
}

