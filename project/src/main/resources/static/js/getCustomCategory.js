// AJAX 요청을 보내어 데이터 가져오기

var ajaxRequest = false;
$(document).ready(function(){
	if (ajaxRequest ) {
		console.log(ajaxRequest);
		return;
	  }
	
	ajaxRequest = true;
	 $.ajax({
        url: "/app/getCustomCategories",
        type: "GET",
        dataType: "json",
        success: function(data) {
        	console.log(ajaxRequest);
        	setTimeout(function(){ajaxRequest = false;}, 1000);
        str = '';
        	$.each(data, function(i){
        		$('#board-category'+i).remove();
        		str = '<a class="collapse-item" id="board-category'+ i  +'"' +' href="/app/notice/list?categoryId='+data[i].categoryId + '">' + data[i].categoryName +'</a>';
        		 $('#custom-board-list'+data[i].boardId).append(str);
            });
        	console.log("반복");
        }
    });
});