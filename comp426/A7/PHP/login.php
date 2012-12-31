<?php
session_start();
function check_pass($user, $pass) {
	$ch = curl_init();
	curl_setopt($ch,CURLOPT_URL,"http://wwwp.cs.unc.edu/Courses/comp426-f12/aaronmd/A7/PHP/users.php");
	$fields_string = 'login=' . $user;
	curl_setopt($ch,CURLOPT_POST,1);
	curl_setopt($ch,CURLOPT_RETURNTRANSFER,1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $fields_string);
	$json = json_decode(curl_exec($ch),true);
	curl_close($ch);
	if(is_null($json)){
		print("User does not exist.");
		return null;
	}
	if(md5($json['salt'] . $pass) == $json['md5']){
		return $json['uid'];
	}
	return null;
}

$user = $_GET['user'];
$pass = $_GET['pass'];

$return = check_pass($user, $pass);

if (!is_null($return)) {
  header('Content-type: application/json');

  // Generate authorization cookie
  $_SESSION['user'] = $user;
  $_SESSION['authsalt'] = time();

  $auth_cookie_val = md5($_SESSION['user'] . $_SERVER['REMOTE_ADDR'] . $_SESSION['authsalt']);

  setcookie('weatherisfun', $auth_cookie_val,  time()+3000, '/Courses/comp426-f12/aaronmd', 'wwwp.cs.unc.edu');
  setcookie('weatherisfun-info', $return,  time()+3000, '/Courses/comp426-f12/aaronmd', 'wwwp.cs.unc.edu');
  
  print(json_encode(true));



} else {
  unset($_SESSION['user']);
  unset($_SESSION['authsalt']);

  header('HTTP/1.1 401 Unauthorized');
  header('Content-type: application/json');
  print(json_encode(false));
}

?>