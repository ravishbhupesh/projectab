$(function() {
	getUsers();
	var formId = "#signup-form";
	$(formId).validate({
		errorClass : "is-invalid",
		validClass : "is-valid"
	});

	$(formId).ajaxForm({
		beforeSubmit : function() {
			return $(formId).valid();
		},
		success : function(response) {
			toastr.success("Signup completed successfully");
			grecaptcha.reset();
			$(formId).reset();
		},
		error : function(response) {
			if ( response.responseJSON){
				toastr.error(response.responseJSON.message);
			}else{
				toastr.error(response.responseText);
			}
			
			grecaptcha.reset();
		}
	});
});

