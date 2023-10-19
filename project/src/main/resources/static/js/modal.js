$('#modalBtn').click(function(e){
			e.preventDefault();
			console.log("실행");
			$('#schModal').modal("show");
		});

$('#closeModal').click(function(e){
	e.preventDefault();
	$('#schModal').modal("hide");
});