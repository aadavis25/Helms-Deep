<?php
require_once('orm/weatherHistory.php');
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
  if (is_null($_SERVER['PATH_INFO'])) {
     if (!isset($_GET['zip'])){
      header("HTTP/1.1 400 Bad Request");
        print("Must specify a zipcode");
     }
      $zip = $_GET['zip'];
      $weath = array();
      $weath = weatherHistory::getWeatherByZip($zip);
      if (is_null($weath)) {
        header("HTTP/1.1 400 Bad Request");
        print("No weather for that zip");
      exit();
      }
      //debugging -> print(json_encode($weath));
      foreach($weath as $weathid){
        print(json_encode(weatherHistory::getWeather($weathid)->getJSON()));
      }
      
      header("Content-type: application/json");
      exit();
} else{
  $weathid = intval(substr($_SERVER['PATH_INFO'],1));
    $weath = weatherHistory::getWeather($weathid);

    if(is_null($weath)){
      header("HTTP/1.1 404 Not Found");
          print("Weather id specified not found or non-existant");
          exit();
    }
    if (is_null($_GET['delete'])) {
          header("Content-type: application/json");
          print(json_encode($weath->getJSON()));
          exit();
      } 
      else {
          $weath->delete();
          header("Content-type: application/json");
          print(json_encode(true));
          exit();
      }
    }
      
  }
} else if($_SERVER['REQUEST_METHOD'] == 'POST'){
	if(is_null($_SERVER['PATH_INFO'])){
    $zip = $_POST['zip']; 
    $date = $_POST['date'];
    $conditions = $_POST['conditions'];
    $high = $_POST['high'];
    $low = $_POST['low'];
    $precip = $_POST['precip'];
    $wind = $_POST['wind'];
    $icon = $_POST['icon'];

		if (strlen($zip) != 5 ) {
      		header("HTTP/1.1 400 Bad Request");
      		print("Zipcode is invalid");
      		exit();
   	}

   	$weather = weatherHistory::create(intval($zip), $date, $conditions, intval($high), intval($low), intval($precip), intval($wind), $icon);
   	if (is_null($weather)) {
     		header("HTTP/1.1 400 Bad Request");
     		print("Creation failed at database");
     		exit();
   	}
   	header("Content-type: application/json");
   	print(json_encode($weather->getJSON()));
   	exit();
    } 
}
header("HTTP/1.1 400 Bad Request");
print("URL did not match any known action.");

?>