$(document).ready(function () {
    load_outfits();

    $('#outfit_form').on('submit', function (e) {
            add_outfit();
            e.preventDefault();
    });   

});


/*adds new outfit to the panel on the page
*/
var add_outfit = function () {
    var outfits_panel = $('#outfits-panel')
    var new_outfit_entry = $('<div></div>');
    new_outfit_entry.append($('#outfit_form input[name="outfitname"]'+ " ").val());
    new_outfit_entry.append($('#outfit_form input[name="hat"]' + " ").val());
    new_outfit_entry.append($('#outfit_form input[name="top"]' + " ").val());
    new_outfit_entry.append($('#outfit_form input[name="cover"]' + " ").val());
    new_outfit_entry.append($('#outfit_form input[name="bottom"]' + " ").val());
    new_outfit_entry.append($('#outfit_form input[name="shoes"]' + " ").val());
    new_outfit_entry.append($('#outfit_form input[name="weather"]' + " ").val());
    outfits_panel.append(new_outfit_entry);
}

var load_outfits = function () {
    var outfits_panel = $('#outfits-panel')
    outfits_panel.empty();

    for (key in Outfits)
    {
        var new_outfit_entry = $('<div></div>');
        new_outfit_entry.append(Outfits[key].hat + " ");
        new_outfit_entry.append(Outfits[key].top + " ");
        new_outfit_entry.append(Outfits[key].cover + " ");
        new_outfit_entry.append(Outfits[key].bottom + " ");
        new_outfit_entry.append(Outfits[key].shoes + " ");
        new_outfit_entry.append(Outfits[key].appropweather);
        outfits_panel.append(new_outfit_entry);
    }
}