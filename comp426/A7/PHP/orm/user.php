<?php

class user{
	private $uid;
	private $login;
	private $name;
	private $password;
	private $zip;
	private $events = array();
	private $outfits = array();

	public static function create($login, $name, $password, $zip) {
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("INSERT INTO users VALUES (0, '" . $name . "', '" . $login . "', '" . $password . "', '" . "weatherisfun-" . $login . "', '" . md5('weatherisfun-' . $login . $password) . "', " . intval($zip) . ")");
		if ($return){
			$insert_uid = $db->insert_id;
			return new user($insert_uid, $login, $name, $password, $zip);
		}

		return null;
	}

	public static function getuser($uid){
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("SELECT * FROM users WHERE uid = " . $uid);
		if($return){
			if($return->num_rows == 0){
				return null;
			}
			$user_by_uid = $return->fetch_array();
			return new user(intval($user_by_uid['uid']), $user_by_uid['login'], $user_by_uid['name'], $user_by_uid['pass'], intval($user_by_uid['zip']));
		}
		return null;
	}
	

	public static function getuserByZip($zip){
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("SELECT uid FROM users WHERE zip = " . intval($zip));
		if($return){
			if($return->num_rows == 0){
				return null;
			}
			$return_array =  array(); 
			$user_by_zip = $return->fetch_array();
			foreach($user_by_zip as $user)
				$return_array[]   = intval($user['uid']);
			return $return_array;
		}
		return null;
	}

	public static function fetchEvents($uid){
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("SELECT * FROM events WHERE uid = " . $uid);
		if($return){
			if($return->num_rows == 0){
				return null;
			} 
			$user_events = $return->fetch_array();
			foreach($user_events as $event)
				array_push($this->$events, $event['id']);
		}
		return null;
	}

	public static function fetchOutifts($uid){
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("SELECT * FROM outfits WHERE uid = " . $uid);
		if($return){
			if($return->num_rows == 0){
				return null;
			} 
			$user_outfits = $return->fetch_array();
			foreach($user_outfits as $outfit)
				array_push($this->$outfits, $outfit['id']);
		}
		return null;
	}

	public static function validate($login){
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$result = $db->query("SELECT md5, salt, uid FROM users WHERE login = '" . $login . "'");
		if($result){
			if($result->num_rows == 0){
				return null;
			} 
			$return = array();
			$user = $result->fetch_array();
			$return['md5'] = $user['md5'];
			$return['salt'] = $user['salt'];
			$return['uid'] = $user['uid'];
			return $return;
		}
		return null;
	}

	private function __construct($uid, $login, $name, $password, $zip) {
		$this->uid = $uid;
		$this->login = $login;
		$this->name = $name;
		$this->password = $password;
		$this->zip = $zip;
		//fetchEvents($uid);
		//fetchOutfits($uid);
	}

	public function getJSON() {
	  $json = array();
	  $json['uid'] = $this->uid;
	  $json['login'] = $this->login;
	  $json['name'] = $this->name;
	  $json['password'] = $this->password;
	  $json['zip'] = $this->zip;
	  $json['events'] = json_encode($this->events);
	  $json['outfits'] = json_encode($this->outfits);
	  return $json;
	}

	public function getAll() {
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("SELECT * FROM users");
		if($return){
			if($return->num_rows == 0){
				return null;
			}
			
			for ($i=0; $i<$return->num_rows; $i++){
				$row = $return->fetch_row();
				$users[] = $row[0];
			}
			return $users;
		}
		return null;
	}

	public function getUID() {
		return $this->uid;
	}

	public function getZip() {
		return $this->zip;
	}

	public function getLogin() {
		return $this->login;
	}


	public function updateZip($uid, $newzip){
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("UPDATE users SET zip = " . $newzip . " WHERE uid = " . $uid);
		return $return;
	}
	public function updatePass($uid, $newpass){
		$db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
		$return = $db->query("UPDATE users SET pass = '" . $newpass . "' WHERE uid = " . $uid);
		return $return;
	}

	public function delete() {
	  $db = new mysqli("classroom.cs.unc.edu", "aaronmd", "bobobocheez77!", "comp4261db");
	  $return = $db->query("delete from users where uid = " . $this->uid);
	  return $return;
	}
}

?>