<?php

class weatherHistory{
	private $wid;
	private $zip; 
	private $date;
	private $conditions;
	private $high;
	private $low;
	private $precip;
	private $wind;
	private $icon;

	public static function create($zip, $date, $conditions, $high, $low, $precip, $wind, $icon) {
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("INSERT INTO weather_history values (0, " . $zip . ", " . $date . ", '" . $conditions . "', " . $high . ", " . $low . ", " . $precip . ", " . $wind . ", '" . $icon . "')");
		if ($return){
			$insert_uid = $db->insert_uid;
			return new weatherHistory($zip, $date, $conditions, $high, $low, $precip, $wind, $icon);
		}
		return null;
	}

	public static function getWeather($wid){
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("SELECT * FROM weather_history WHERE wid = " . $wid);
		if($return){
			if($return->num_rows == 0){
				return null;
			}
			$weath_info = $return->fetch_array();
			$weather = new weatherHistory(intval($weath_info['wid']), intval($weath_info['zip']), $weath_info['date'], $weath_info['conditions'], intval($weath_info['high']), intval($weath_info['low']), intval($weath_info['precip']), intval($weath_info['wind']), $weath_info['inon']);
			return $weather;
		}
		return null;
	}

	public static function getWeatherByZip($zip){
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("SELECT wid FROM weather_history WHERE zip = " . $zip);
		if($return){
			if($return->num_rows == 0){
				return null;
			}
			$return_array =  array(); 
			$weather_by_zip = $return->fetch_array();
			foreach($weather_by_zip as $weath)
				$return_array[] = intval($weath['uid']);
			return $return_array;
		}
		return null;
	}

	private function __construct($wid, $zip, $date, $conditions, $high, $low, $precip, $wind, $icon) {
		$this->wid = $wid;
		$this->zip = $zip;
		$this->date = $date;
		$this->conditions = $conditions;
		$this->high = $high;
		$this->low = $low;
		$this->precip = $precip;
		$this->wind = $wind;
		$this->icon = $icon;
	}

	public function getJSON() {
	  $json = array();
	  $json['wid'] = $this->wid;
	  $json['zip'] = $this->zip;
	  $json['date'] = $this->date;
	  $json['conditions'] = $this->conditions;
	  $json['high'] = $this->high;
	  $json['low'] = $this->low;
	  $json['precip'] = $this->precip;
	  $json['wind'] = $this->wind;
	  $json['icon'] = $this->icon;
	  return $json;
	}
	public function delete() {
	  $db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
	  $return = $db->query("delete from weather_history where wid = " . $this->wid);
	  return $return;
	}


}