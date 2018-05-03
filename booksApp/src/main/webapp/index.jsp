<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books App</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">

</head>
<body>
<div class="container">
<form id="searchForm">
  <div class="form-group">
    <label for="searchInput">Search Text</label>
    <input type="text" class="form-control" id="searchInput">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

<div class="mt-1" id="results"></div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${bookResults.resultCount} == 0">
<p>an item</p>
 </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script type='text/javascript'>
    /* attach a submit handler to the form */
    $("#searchForm").submit(function(event) {

      /* stop form from submitting normally */
      event.preventDefault();

      $.ajax({
          type: 'GET',
          url: '/ItunesSearch',
          data: {text: $('#searchInput').val()},
          success: function(res){
        	  $('#results').empty()
        	  console.log(res);
        	  if(res.resultCount>0){
        		  res.results.forEach(function(result, index){
        			  var card = document.createElement('div');
        			  card.setAttribute('class', 'card mt-4'); 
        			  var image = document.createElement('img');
        			  image.setAttribute('src', result.artworkUrl100);
        			  image.setAttribute('class', 'card-img-top book-artwork');
        			  var cardBody = document.createElement('div');
        			  cardBody.setAttribute('class', 'card-body');
        			  var cardTitle = document.createElement('h5');
        			  cardTitle.setAttribute('class', 'card-title');
        			  cardTitle.innerHTML = result.trackName;
        			  var cardText = document.createElement('p');
        			  cardText.setAttribute('class', 'card-text');
        			  cardText.innerHTML=result.description;
        			  var cardHeader = document.createElement('div');
        			  cardHeader.setAttribute('class', 'card-header');
        			  
        			  cardHeader.appendChild(image);
        			  cardHeader.appendChild(cardTitle);
        			  cardBody.appendChild(cardText);
        			  card.appendChild(cardHeader);
        			  card.appendChild(cardBody);
        			  document.getElementById('results').appendChild(card);
        		  })
        		  }
          }
      })
  	});
</script>
</body>
</html>