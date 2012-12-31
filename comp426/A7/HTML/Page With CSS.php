<!DOCTYPE html>
<!--
@author Aaron Davis
Weather webapp using http://free.worldweatheronline.com API
key e48f35aa86045009121210


-->
<html>
<head>
	<meta charset="utf-8" />
	<title>Weather is FUN!</title>
	<link rel="stylesheet" type="text/css" href="../CSS/styles.css" >
	<link rel="shortcut icon" href="../images/favicon.png" type="image/png" />
	<link rel="icon" href="../images/favicon.png"  />
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js" type="text/javascript"></script>
	<script src="../js/events.js" type="text/javascript"></script>
	<script src="../js/user.js" type="text/javascript"></script>
	<script src="../js/WeatherBox.js" type="text/javascript"></script>
	<script src="../js/populateCanvas.js" type="text/javascript"></script>
	<script src="../js/auth.js" type="text/javascript"></script>
	<script>
		var user1 = new user('27514', 'jsmith', '12345', 'John', 'Smith');
		var user2 = new user('27856', 'msmith', 'password', 'Mary', 'Smith');
		var users = [user1, user2, "taco"];
	</script>
</head>
<body>
	<div id="header">
		<h1>Weather is FUN.</h1>
		<a href="Outfits.php">Outfit Manager</a>
		<a href="Events.php">Events</a>
		<div id = "login">
		<form id="loginForm" onsubmit="authenticate(users)" method="post">
			<label for="username">Username</label>
			<input type="text" id="username" name='user'>
			<label for="password">Password</label>
			<input type="password" id="password" name='pass'>
			<input type="submit" id="submit" value = "Submit">
			<input type='hidden' id="zipcode" name="zip">
		</form>
		</div>
		<span>
		<div id ="zipdiv">
		<form id="zipform" action="Page With CSS.php" method="post">
			<label for="zipbox">Zipcode</label>
			<input id="zipbox" name="zip" type="text">
			<input type="submit" value="Go" />
		</form>
	</div>
	</div>
	<?php
	if (isset($_POST['user'])){
	?>
	Welcome, <?php echo $_POST['user']?>
	<?php 
	}?>
	<?php
	if (isset($_POST['zip'])){
	?>
	<div id="weather-info-div">
	</div>

	<?php }
	else{?>
		<div>
			Input a zipcode to begin.
		</div>
	<?php
	}
	?>

	<!--I hate fantasy football-->
<div id="footer">
    Elijah Batkoski - 720006899 <br>
    Aaron Davis - 720018223 <br>
    Nicole Busby - 720008364
</div>
</body>
<script>
	<?php if (isset($_POST['zip'])){
		 if ($_POST['zip'] == '27514'){ ?>
	var json = [["Patchy rain nearby","76","46","3.2","9","2012-10-15","Monday","http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0009_light_rain_showers.png"],["Sunny","66","44","0.0","7","2012-10-16","Tuesday","http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0001_sunny.png"],["Sunny","66","50","0.0","8","2012-10-17","Wednesday","http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0001_sunny.png"],["Partly Cloudy","66","50","0.0","15","2012-10-18","Thursday","http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0002_sunny_intervals.png"],["Sunny","66","47","0.0","14","2012-10-19","Friday","http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0001_sunny.png"]];
	<?php } if ($_POST['zip'] == 27856) {?>
	var json = [["Patchy rain nearby","73","49","4.5","9","2012-10-15","Monday","http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0009_light_rain_showers.png"],["Sunny","66","43","0.0","10","2012-10-16","Tuesday","http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0001_sunny.png"],["Snowy","66","48","0.0","6","2012-10-17","Wednesday","http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0001_sunny.png"],["Sunny","68","58","0.0","15","2012-10-18","Thursday","http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0001_sunny.png"],["Sunny","66","47","0.0","15","2012-10-19","Friday","http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0001_sunny.png"]];
	<?php }?>
//	var length = jason.length();
//	var i = 0;
	var weatherArray = new Array();
	$.each(json,function(){
		weatherArray.push(new Weather(this[0], this[1], this[2],this[3], this[4], this[5], this[6], this[7]))
	});
	//weatherArray.push(Weather(json[i][0], json[i][1], json[i][2],json[i][3], json[i][4], json[i][5], json[i][6]
	$.each(weatherArray,function(){
		 populateCanvas(this, 'weather-info-div')
	});
	<?php } ?>
</script>
</html>
