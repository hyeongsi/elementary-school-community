// AJAX 요청을 보내어 데이터 가져오기

$(document).ready(function(){
    $.ajax({
        url: "/app/getBoard", // Spring Boot 엔드포인트 URL로 변경
        type: "GET",
        dataType: "json",
        async: false,
        success: function(data) {
        str = '';
        	$.each(data, function(i){
        		str += "<li class='nav-item'>" +
        	    "<script defer src='/app/js/getCustomCategory.js'></script>" +
                "<a class='nav-link collapsed' href='#' data-toggle='collapse' data-target='#collapseUserPages"+data[i].boardId+"'"+
                   "aria-expanded='true' aria-controls='collapseUserPages'>"+
                    "<i class='fas fa-fw fa-folder'></i>"+
                    "<span>"+data[i].boardName+"</span>"+
                "</a><div id='collapseUserPages"+data[i].boardId+"'"+"class='collapse' aria-labelledby='headingPages' data-parent='#accordionSidebar'>"+
                    "<div class='bg-white py-2 collapse-inner rounded' id='custom-board-list"+data[i].boardId+"'>"+
                    "</div></div></li>";
            	
            });
        $('#user-board').append(str);
        }
    });
});