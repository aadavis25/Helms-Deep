<?php
	date_default_timezone_set('America/New_York');
	
	if(isset($_GET['zip'])){
		$zip = $_GET['zip'];
	} else {
		if (!isset($_COOKIE['weatherisfun-info'])){
			header("HTTP/1.1 400 Bad Request");
			print("Cookie error," . $_COOKIE['weatherisfun-info']);
			return;
		} else{
			$ch = curl_init();
			$timeout = 5;
			curl_setopt($ch,CURLOPT_URL,"http://wwwp.cs.unc.edu/Courses/comp426-f12/aaronmd/A7/PHP/users.php/" . $_COOKIE['weatherisfun-info']);
			curl_setopt($ch,CURLOPT_RETURNTRANSFER,1);
			curl_setopt($ch,CURLOPT_CONNECTTIMEOUT,$timeout);
			$json = json_decode(curl_exec($ch),true);
			curl_close($ch);
			$zip = $json['zip'];
			$name = $json['name'];
		}
	}

	$ch = curl_init();
	curl_setopt($ch,CURLOPT_URL,"http://free.worldweatheronline.com/feed/weather.ashx?q=".$zip."&format=json&num_of_days=5&key=e48f35aa86045009121210");
	curl_setopt($ch,CURLOPT_RETURNTRANSFER,1);
	curl_setopt($ch,CURLOPT_CONNECTTIMEOUT,$timeout);
	$json = curl_exec($ch);
	if (is_null($json)){
		header("HTTP/1.1 400 Bad Request");
		print("Zipcode invalid");
	}
	curl_close($ch);
	$decodedJson = json_decode($json, TRUE);
		
	$ind = 0;
	$weatherArray = array();
	foreach ($decodedJson['data']['weather'] as $forcast){
		$cond = $forcast['weatherDesc'][0]['value'];
		$high = $forcast['tempMaxF'];
		$low = $forcast['tempMinF'];
		$precip = $forcast['precipMM'];
		$wind = $forcast['windspeedMiles'];
		$date = $forcast['date'];
		$image = $forcast['weatherIconUrl'][0]['value'];
		$dayText;
		switch ((date('w') + $ind ) % 6 ){
			case 0: $dayText = "Sunday"; break;
			case 1: $dayText = "Monday"; break;
			case 2: $dayText = "Tuesday"; break;
			case 3: $dayText = "Wednesday"; break;
			case 4: $dayText = "Thursday"; break;
			case 5: $dayText = "Friday"; break;
			case 6: $dayText = "Saturday"; break;
		}
		$forcast = array($cond, $high, $low, $precip, $wind, $date, $dayText, $image, $name);
		array_push($weatherArray, $forcast);
		
		
		$ind = $ind+1; 
	}
	$jsonWeatherArray = json_encode($weatherArray);
	echo($jsonWeatherArray);
	?>
