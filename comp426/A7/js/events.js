function handle_hover(e){
	start_pos = $(this).position();
 	handle_hover_pos(e);
}

function showEventCreation() {
    var selection = $('#selection');
    selection.css("display", "none");
    var eventCreation = $('#eventcreation');
    eventCreation.css("display", "inline");
}


function showEvents() {
    var selection = $('#selection');
    selection.css("display", "none");
    var eventShow = $('#events');
    eventShow.css("display", "inline");
}





function handle_hover_pos(e) {
    var moveup = Math.floor(Math.random() * 20) - 10;
    var moveleft = Math.floor(Math.random() * 20) - 10;
    $(e.currentTarget).animate({
        top: '+=' + moveup,
        left: '+=' + moveleft
    }, 1000, function() {
        handle_hover_neg(e, moveup, moveleft);
    });

}

function handle_hover_neg(e, moveup, moveleft) {
    $(e.currentTarget).animate({
        top: '-=' + moveup,
        left: '-=' + moveleft
    }, 1000, function() {
        handle_hover_pos(e);
    });

}

var start_pos;

$(document).ready(function () {


	$('.weatherbox').hover(function(e){
        handle_hover(e);
    },
        function() {
            $(this).stop().animate( { top:start_pos.top, left:start_pos.left }, 1000);
        }
    );
	
    $('#loginForm').on('submit', function(e){
        e.stopPropagation();
        e.preventDefault();
        $.ajax('PHP/login.php',{
            type:'GET',
            data: $('#loginForm').serialize(),
            cache: false,
            success: function () {
                alert('Login Successful'); 
                $.getJSON('PHP/forcast.php',function(data){
                    var weatherArray = new Array();
                    var name;
                    if (data.length != 0){
                    $.each(data,function(){
                        weatherArray.push(new Weather(this[0], this[1], this[2],this[3], this[4], this[5], this[6], this[7]));
                        name = this[8];
                    });
                    $('#weather-info-div').empty();
                    $('#weather-info-div').append('<p class="name">Welcome ' + name + '<p>');
     
                    $.each(weatherArray,function(){
                        if (this['date'] != null)
                            populateCanvas(this, 'weather-info-div');
                    }
                    );
                    } else {
                        $('#weather-info-div').empty();
                        $('#weather-info-div').append('<p class="name">Zipcode is invalid.<p>');
                    }
                
                });
                
            },
            error: function () {
                alert('Login failed');}
        });
    });

    $('#zipform').on('submit', function(e){
        e.stopPropagation();
        e.preventDefault();
        var zip = $('#zipform').serialize();
	   $.getJSON('PHP/forcast.php?'+zip,function(data){
            if (data.length != 0){
                $('#weather-info-div').empty();
                    console.log(data);
                    var weatherArray = new Array();
                    $.each(data,function(){
                        console.log(this[0]);
                        weatherArray.push(new Weather(this[0], this[1], this[2],this[3], this[4], this[5], this[6], this[7]));
                    });
     
                    $.each(weatherArray,function(){
                        populateCanvas(this, 'weather-info-div');
                    }
                    );
                } else {
                    $('#weather-info-div').empty();
                        $('#weather-info-div').append('<p class="name">Zipcode is invalid.<p>');
                    }
                
                });
       });
       $('#userCreateForm').on('submit', function(e){
        e.stopPropagation();
        e.preventDefault();
        $.ajax('PHP/users.php',{
            type:'POST',
            data: $('#userCreateForm').serialize(),
            cache: false,
            success: function () {
                alert('User creation succesful!\nPlease log in!'); 
            },
            error: function () {
                alert('Creation failed');
            }
        });
    });


});

/*var move_object = function (e){
	var object = e.data;
	var newx = e.clientX - object.startx;
	var newy = e.clientY - object.starty;
		
	var x = object.pos.left + newx;
	var y = object.pos.top + newy;
	
	$(object).css('top', y +'px');
	$(object).css('left', x+'px');
	$(object).children('canvas').css('top', y + 'px');
	$(object).children('canvas').css('left', x + 'px');
};
var hover_in = function(e){
	var obj = e.data;
	$(obj).css("z-index", 1);
      	$(obj).animate({
            	height: "250",
            	width: "500",
               	left: "-=50",
               	top: "-=25"
            }, "medium");
        }; 
	var hover_out = function(e){
            // hover out
            var obj = e.data;
            $(obj).css("z-index", 0);
            $(obj).animate({
                height: "200",
                width: "400",
                left: "+=50",
                top: "+=25"
            }, "medium");
        };
*/
