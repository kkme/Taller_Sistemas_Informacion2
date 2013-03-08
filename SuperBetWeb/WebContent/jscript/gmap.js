
var currentMarker = null;
var markerArray = [];

function handlePointClick(event) {

	if (currentMarker != null) {
		currentMarker.setMap(null);
		currentMarker = null;
	}

	currentMarker = new google.maps.Marker({
		position:new google.maps.LatLng(event.latLng.lat(), event.latLng.lng())
	});
					
	map.addOverlay(currentMarker);

	currentMarker.setTitle("Evento nuevo");
	
}



					