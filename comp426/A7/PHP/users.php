<?php
require_once('orm/user.php');
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
  if (is_null($_SERVER['PATH_INFO'])) {
     if (isset($_GET['zip'])){
      $zip = $_GET['zip'];
      $users = user::getuserByZip($zip);
      if (is_null($users)) {
        header("HTTP/1.1 400 Bad Request");
        print("No user with that zip");
      exit();
      }

      $userIDs = array();
      foreach($users as $userid){
        print(json_encode(user::getuser($userid)->getJSON()));
      }
      
      header("Content-type: application/json");
      //print(json_encode($userIDs));
      exit();
    } else {
    $users = array();
		$users = user::getAll();

		if (is_null($users)) {
      	header("HTTP/1.1 400 Bad Request");
     	print("Unknown Error");
     	exit();
    	}

    
      $userinfo = array();
    	foreach($users as $userid){
    		print(json_encode(user::getuser($userid)->getJSON()));
    	}
    	header("Content-type: application/json");
    	//print(json_encode($userinfo));
    	exit();
   
    
    }
  }else{
		$userID = intval(substr($_SERVER['PATH_INFO'],1));
		$user = user::getuser($userID);
		if(is_null($user)){
			header("HTTP/1.1 404 Not Found");
      		print("User id specified not found or non-existant");
      		exit();
		}
		if (is_null($_GET['delete'])) {
      		header("Content-type: application/json");
      		print(json_encode($user->getJSON()));
      		exit();
    	} 
      else {
      		$user->delete();
      		header("Content-type: application/json");
      		print(json_encode(true));
      		exit();
    	}
	}


} else if($_SERVER['REQUEST_METHOD'] == 'POST'){
	if(is_null($_SERVER['PATH_INFO'])){
		$login = $_POST['login'];
		$name = $_POST['name']; 
		$password = $_POST['password'];
		$zip = $_POST['zip'];

    if(((is_null($name) and is_null($zip)) and is_null($password)) and isset($login)) {
      $user = user::validate($login);
      if(is_null($user)){
        header("HTTP/1.1 400 Bad Request");
        print("User doesn't exist");
      }
      header("Content-type: application/json");
      print(json_encode($user));
      exit();
    }

		if (strlen($zip) != 5 ) {
      		header("HTTP/1.1 400 Bad Request");
      		print("Zipcode is invalid");
      		exit();
   	}
    if (strlen($login) > 15 ) {
          header("HTTP/1.1 400 Bad Request");
          print("Username is too long");
          exit();
    }
    if (strlen($password) > 21 ) {
          header("HTTP/1.1 400 Bad Request");
          print("Password is too long");
          exit();
    }
    if (strlen($name) <= 0) {
          header("HTTP/1.1 400 Bad Request");
          print("Name is unspecified");
          exit();
    }

   	$user = user::create($login, $name, $password, intval($zip));
   	if (is_null($user)) {
     		header("HTTP/1.1 400 Bad Request");
     		print("User creation failed at database");
     		exit();
   	}
   	header("Content-type: application/json");
   	print(json_encode($user->getJSON()));
   	exit();
    } else {
    	$userid = intval(substr($_SERVER['PATH_INFO'],1));
     $user = user::getuser($userid);
    	if(is_null($user)){
    		header("HTTP/1.1 404 Not Found");
      		print("Specified user id not found or non-existant");
      		exit();
    	}
      if (isset($_POST['zip'])){
    	$zip = $_POST['zip'];
    	if (strlen($zip) < 5) {
      		header("HTTP/1.1 400 Bad Request");
      		print("Zipcode is invalid");
      		exit();
      	}
      	user::updateZip($userid, $zip);
      	print(json_encode(user::getuser($userid)->getJSON()));
    	exit();
    }
    else if (isset($_POST['password'])){
      $pass = $_POST['password'];
        user::updatePass($userid, $pass);
        print(json_encode(user::getuser($userid)->getJSON()));
      exit();
    }
  }
}
header("HTTP/1.1 400 Bad Request");
print("URL did not match any known action.");

?>