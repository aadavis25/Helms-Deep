function populateCanvas(weatherObj, apendee){
//	var length = objArray.length();
//	var i = 0;
//	for (i;i < length; i++;){
		$('#' + apendee).append('<div class="wb-div"><canvas class="weatherbox" id="' 
+ weatherObj['day'] + '" width="400" height="200"></canvas></div>');
		var c = document.getElementById(weatherObj['day']);
		var ctx = c.getContext("2d");
		console.log(weatherObj['gradStart']);
		weatherObj.setGrad();
		var grd = ctx.createLinearGradient(0,0,400,200);
		grd.addColorStop(0, weatherObj['gradStart']);
		grd.addColorStop(1, weatherObj['gradEnd']);
		ctx.fillStyle = grd;
		ctx.fillRect(0,0,400,200);

		ctx.font = "25px Arial";
               	ctx.fillStyle = 'black';
		ctx.fillText("Conditions", 10, 25);
               	ctx.fillText("Date", 240, 25);
               	ctx.fillText("High", 10, 90);
               	ctx.fillText("Low", 75, 90);
               	ctx.fillText("Precipitation", 160, 90);
		ctx.fillText("Wind", 10, 155);
		ctx.font = "Italic 40px Arial";
		ctx.fillText(weatherObj['day'], 150, 175);
		ctx.font = "20px Arial";
		ctx.fillText(weatherObj['conditions'], 10, 45);
		ctx.fillText(weatherObj['date'], 210, 45);
		ctx.fillText(weatherObj['high'], 15, 110);
		ctx.fillText(weatherObj['low'], 85, 110);
		ctx.fillText(weatherObj['precip'], 170, 110);
		ctx.fillText(weatherObj['wind'], 25, 175);
		
		var image = new Image();
		image.src = weatherObj['icon'];
		ctx.drawImage(image, 330, 5);
		
		
//	}
}
