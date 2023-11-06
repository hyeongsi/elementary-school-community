// AJAX 요청을 보내어 데이터 가져오기

$(document).ready(function(){
	 $.ajax({
        url: "/app/getCustomCategories",
        type: "GET",
        dataType: "json",
        success: function(data) {
        str = '';
        	$.each(data, function(i){
        		$('#board-category'+i).remove();
        		str = '<a class="collapse-item" id="board-category'+ i  +'"' +' href="/app/notice/list?categoryId='+data[i].categoryId + '">' + data[i].categoryName +'</a>';
        		 $('#custom-board-list'+data[i].boardId).append(str);
            });
        }
    });
});