
jQuery.noConflict();  

function onSelectScheduledEvent () {
	eventDialog.show();
	
	var title = jQuery("span.fc-event-title");
	var date = jQuery("span.fc-event-time");

	var titleDialog = jQuery("#titleDialog");
	titleDialog.html(title.html());
	var dateDialog = jQuery("#dateDialog");
	dateDialog.html(date.html());
	
	return null;
}
