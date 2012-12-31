/********************************************
*****Information about the user for the*****
******weather zip code, the outfit manager**
***********and the events page***************
********************************************/
//the user object will be created on the user signing in
//and their information will populat this object
var user = function(zipcode, username, password, fName, lName) {
	this.username = username;
	this.zipcode = zipcode;
    //arrays for the Events and Outfits of the user to be populated by methods below
	this.events = [];
	this.outfits = [];
	this.password=password;
	this.fName = fName;
	this.lName = lName;
}

user.prototype.setWeather = function (weatherObj) {
    //this.zipcode
	//retreive weather based on zipcode
	//weatherObj(conditions, high, low, humidity, precipChance, date);
}

user.prototype.getEvents = function(){
	//go to database and retrieve events based on this.username
}

user.prototype.getOutfits = function(){
	//go to database and retrieve outfits based on this.username
}
