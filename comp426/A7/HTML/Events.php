<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Weather is FUN!</title>
	<script language = "JavaScript" type = "text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="../js/events.js" type= "text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="../CSS/styles.css" >
	<link rel="shortcut icon" href="../images/favicon.png" type="image/png" />
	<link rel="icon" href="../images/favicon.png"  />
	<script src="../js/auth.js" type="text/javascript"></script>
	<script>
	var user1 = new user('27514', 'jsmith', '12345', 'John', 'Smith');
		var user2 = new user('27856', 'msmith', 'password', 'Mary', 'Smith');
		var users = [user1, user2, "taco"];
		var event1 = new Event('Party', '10/23/2012', '14:00', '27514', 'Chapel Hill, NC', 'user1');
		var event2 = new Event('Football Game', '10/27/2012', '12:00', '27514', 'Chapel Hill, NC', 'user2');
		var events = ['event1', 'event2']
		</script>
	</head>
<body>
	<div id="header">
		<h1>Weather is FUN.</h1>
		<a href="Page With CSS.php">Home</a>
		<a href="Outfits.php">Outfit Manager</a>
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

	</div>
	<?php
	if (isset($_POST['user'])){
	?>
	Welcome, <?php echo $_POST['user']?>
	<br>
	<?php 
	}?>

	<div id="selection">

		<label for="search">Search for an event by Zip Code</label>
		<input type="text" id="search" name="eventsearch">
		<input type= "submit" id="showeventbutton" onclick = "showEvents()" value="GO!">
		<br>
		<div id="or">-OR-</div> 
		<button type="button" onclick = "showEventCreation()" id="showformbutton">Create an Event</button>
	</div>


<div id = "eventcreation">
		<form id="eventForm">
			<label for="name">Event Name</label>
			<input type="text" id="eventname" name='eventname'>
			<br>
			<label for="location">Event Location(Zip Code)</label>
			<input type="text" id="eventloc" name='eventloc'>
			<br>
			<label for="date">Event Date</label>
			<input type="date" id="eventdate" name='eventdate'>
			<br>
			<label for="time">Event Time</label>
			<input type="time" id="eventtime" name='eventtime'>
			<br>
			<button type="submit" id="eventcreate">Create Event!</button>
		</form>
	</div>


<div id ="events">
	<ul><div id="eventName">Party</div>
		<li>Date: 10/23/2012</li>
		<li>Time: 10:00PM</li>
		<li>Location: Chapel Hill, NC</li>
		<li>Host: Billy Bob</li>
	</ul>
	<br>
		<ul><div id="eventName">Football Game</div>
		<li>Date: 10/27/2012</li>
		<li>Time: 7:00PM</li>
		<li>Location: Chapel Hill, NC</li>
		<li>Host: KMP</li>
	</ul>



</div>





	<!--I hate fantasy football-->
<div id="footer">
    Elijah Batkoski - 720006899 <br>
    Aaron Davis - 720018223 <br>
    Nicole Busby - 720008364
</div>
<!--Not really though-->
</body>
</html>