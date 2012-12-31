
/* Outfit *
* Models information associated with each outfit *
*/
var Outfit = function (hat, top, cover, bottom, shoes, appropweather, clean) {
    this.hat = hat;
    this.top = top;
    this.cover = cover;
    this.bottom = bottom;
    this.shoes = shoes;
    this.appropweather = appropweather;
    this.clean = true;
}

Outfits = new Array();

// hard-coded data for now
Outfits['Presentation'] = new Outfit('bun', 'collared blue shirt', 'blazer', 'slacks', 'covered heels', 'sunny', true);
Outfits['Gym'] = new Outfit('ponytail', 'white tank', 'sport jacket', 'blue basketball shorts', 'sneakers', 'sunny', true);
Outfits['Lab'] = new Outfit('goggles', 'longsleeve cotton shirt', 'lab coat', 'jeans', 'sneakers', null, true);
Outfits['Class'] = new Outfit('hair pin', 'collared red shirt', 'sweater', 'khaki pants', 'sneakers', null, true);
Outfits['Outdoor date'] = new Outfit('curls', 'dress', 'cardigan', null, 'heels', 'sunny', 'true');
Outfits['Indoor date'] = new Outfit('curls', 'dress', 'raincoat', null, 'heels', 'rainy', 'true');

/*
* Returns an array of outfits that will work for the given day
* this is going somewhere else like the script that goes with the home page
*/
Outfit.prototype.getDailyOutfits = function (weather) {
    OkayOutfits = new Array();
    var i = 0
    for (key in Outfits) {
        if (Outfits[key].appropweather == weather) {
            OkayOutfits[i] = Outfits[key];
            i++;
        }
        return false;
    }
};