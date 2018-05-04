$("#searchForm").submit(function(event) {
	event.preventDefault();
	$.ajax({
		type: 'GET',
		url: '/ItunesSearch',
		data: {text: $('#searchInput').val()},
		success: function(res){
			$('#results').empty()
			if(res.resultCount>0){
				var html="";
				res.results.forEach(function(result, index){
					html+="<div class='card mt-4'>";
					html+="<div class='card-header'>";
					html+="<img class='card-img-top book-artwork' src=" +result.artworkUrl100 +">";
					html+="<a href=" +result.trackViewUrl +" target='_blank'><h5 class='card-title mt-1'>" +result.trackName +"</h5></a>"
					html+="<div>Written by " +result.artistName +"</div>";
					html+="<div>Price: " +result.formattedPrice +"</div>";
					html+="</div>";
					html+="<div class='card-body mt-2'>";
					html+="<p class='card-text'>" +result.description +"</p>";
					html+="</div> </div>"
				})
				$('#results').html(html);
			}
		}
	})
});