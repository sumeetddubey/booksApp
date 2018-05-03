/**
 * 
 */
function getSearchResults(){
	console.log("ready");
	$.ajax({
        type: 'GET',
        url: 'ItunesSearch.java'
    })
    .done(function(data){
		console.log(data);
	});
};