
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

$("#delBtn").on("click", function () {

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

