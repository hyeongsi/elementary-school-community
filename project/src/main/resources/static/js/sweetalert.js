
// 게시글 수정

$("#submiBtn").on("click", function () {

	let formData = $("#frm").serialize(); // 폼 데이터 직렬화
	
		Swal.fire({
	      title: '게시글',
	      text: "수정 하시겠습니까?",
	      icon: 'warning',
	      showCancelButton: true,
	      confirmButtonColor: '#3085d6',
	      cancelButtonColor: '#d33',
	      confirmButtonText: '승인',
	      cancelButtonText: '취소',
	      reverseButtons: false, // 버튼 순서 거꾸로
	      
	    }).then((result) => {
	    	
	    	$.ajax({
	    		url: "http://localhost:8088/app/notice/edit",
	    		type: "post",
	    		data: formData, // 직렬화한 폼 데이터를 전송
	    		success: function (data) {
	      			if (data.trim()==='YES') {
	      				
	      				if (result.isConfirmed) {
	      			        Swal.fire(
	      			          'success',
	      			          '수정이 완료되었습니다.',
	      			          'success'
	      			        ).then(function(){
	      			        	location.href="./detail?postId="+$("#postId").val();
	      			      })
	      			    }
	      				
	      			} else {
	      				
	      				Swal.fire('실패!', '빈칸이 있는지 확인해주세요.', 'error');
	      			
	      			}
	    		},error: function () {
	    			
	    			Swal.fire('에러 발생', '서버 오류가 발생했습니다.', 'error');
	      		
	    		}
	  		});
	    })
	    
		
		
});





//게시글 삭제

$("#postDelete").on("click", function () {
	var postId = $("#deletePostId").val();
	console.log("postId:", postId);
    Swal.fire({
        title: '정말로 삭제하시겠습니까?',
        text: '삭제 후에는 복구할 수 없습니다.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '삭제',
        cancelButtonText: '취소',
        reverseButtons: false,
    }).then((result) => {
        if (result.isConfirmed) {
            var postId = $("#deletePostId").val(); // 게시글 ID 가져오기
            var categoryId = $("#deleteCategoryId").val(); // 카테고리 ID 가져오기

            $.ajax({
                url: "http://localhost:8088/app/notice/delete",
                type: "get",
                data: { "postId": postId, "categoryId": categoryId }, // postId 및 categoryId 전달
                success: function (data) {
                    if (data.trim() === 'YES') {
                        Swal.fire(
                            '삭제 완료',
                            '게시글이 삭제되었습니다.',
                            'success'
                        ).then(function () {
                            location.href = "./list?categoryId=" + categoryId;
                        });
                    } else {
                        Swal.fire(
                            '오류',
                            '게시글 삭제에 실패하였습니다.',
                            'error'
                        );
                    }
                },
                error: function () {
                    Swal.fire(
                        '에러',
                        '서버와의 통신 중 오류가 발생하였습니다.',
                        'error'
                    );
                }
            });
        }
    });
});

