$('#modalBtn').click(function(e){
			e.preventDefault();
			$('#schModal').modal("show");
		});

$('#closeModal').click(function(e){
	e.preventDefault();
	$('#schModal').modal("hide");
});