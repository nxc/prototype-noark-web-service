function ConvertFormToJSON(form) {

	var array = jQuery(form).serializeArray();
	var json = {};

	jQuery.each(array, function() {
		json[this.name] = this.value || '';
	});

	return json;
}

jQuery(document).on(
		'ready',
		function() {
			jQuery('form#noark').bind(
					'submit',
					function(event) {
						event.preventDefault();

						var form = this;
						var json = ConvertFormToJSON(form);

						var result = jQuery('#result');

						$.ajax({
							type : "$method",
							url : "$url",
							data : JSON.stringify(json),
							dataType : "json",
							contentType : 'application/json',

							success : function(response) {
								result.html('<a href="' + response.href
										+ '">To the object</a>');

							}

						});

						return true;
					});
		});