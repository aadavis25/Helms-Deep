/********************************************
*****Information about the weather for the***
homepage will be stored here and used by the 
********canvas boxes on the homepage*********
********************************************/

var Weather = function(conditions, high, low, precip, wind, date, day, icon) {
	this.conditions = conditions;
	/*assuming everything will be passed as strings, 
	may have to add a parser for these depending in how they're
	handled on the main page*/
	this.high = high;
	this.low = low;
	this.precip = precip;
	this.wind = wind;
	this.date = date;
	this.day = day;
	this.icon = icon;
	this.gradStart = '#fff';
	this.gradEnd = '#fff';
}

/*image()
*add a check on the conditions to add a path to the 
*appropriate condition 
*images we will add eventually
*returns false if the weather condition was not recognized
*/
Weather.prototype.setGrad = function(){
	var sunny = [/sun/i, '#ffb100', '#ffda00'];
	var cloudy = [/cloud/i, '#a7a9aa', '#cccccc'];
	var rain = [/rain/i, '#496694', '#6484ae'];
	var snow = [/snow/i, '#ffffff', '#cccccc'];
	var overcast = [/overcast/i, '#F2F2F2', '#D8D8D8' ]
	/*et cetera*/
	regExpArray = [sunny,cloudy,rain,snow, overcast/*,etc*/];
	/*set the image path to the appropriate condition*/
	length = regExpArray.length;
	var i = 0;
	for (i; i < length; i++)
		if(this.conditions.match(regExpArray[i][0]) != null){
			this.gradStart = regExpArray[i][1];
			this.gradEnd = regExpArray[i][2];
			break;
		}
}
