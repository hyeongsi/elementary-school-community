// AJAX 요청을 보내어 데이터 가져오기

$(document).ready(function(){
    $.ajax({
        url: "/app/getCategories", // Spring Boot 엔드포인트 URL로 변경
        type: "GET",
        dataType: "json",
        success: function(data) {
        str = '';
        	$.each(data, function(i){
        		str += '<a class="collapse-item" href="/app/notice/list?categoryId='+data[i].categoryId + '">' + data[i].categoryName +'</a>';
            	
            });
        $('#categoryList').append(str);
        }
    });
});