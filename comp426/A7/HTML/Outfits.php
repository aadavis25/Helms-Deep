<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Weather is FUN!</title>
	<link rel="stylesheet" type="text/css" href="../CSS/styles.css" >
	<link rel="shortcut icon" href="../images/favicon.png" type="image/png" />
	<link rel="icon" href="../images/favicon.png"  />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js" type="text/javascript"></script>
    <script src="../js/jquery.js" type="text/javascript"></script>
    <script src="../js/OutfitList.js" type="text/javascript"></script>
    <script src="../js/Outfit.js" type="text/javascript"></script>
    <script src="../js/auth.js" type="text/javascript"></script>
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
		<a href="Page With CSS.php">Home</a>
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
	</div>
<?php
    if (isset($_POST['user'])){
    ?>
    Welcome, <?php echo $_POST['user']?>
	<br>
    <?php 
    }?>
    <div id="outfits-panel">
    </div>

    <form id="outfit_form">
        <label>New outfit name, hat, top, cover, bottom, shoes, and weather:
            <input type="text" name="outfitname" >
            <input type="text" name="hat" >
            <input type="text" name="top" >
            <input type="text" name="cover" >
            <input type="text" name="bottom" >
            <input type="text" name="shoes" >
            <input type="text" name="weather" >
        </label>
        <button type="submit">Add Outfit</button>
	</form>

<div id="footer">
    Elijah Batkoski - 720006899 <br>
    Aaron Davis - 720018223 <br>
    Nicole Busby - 720008364
</div>
</body>
</html>