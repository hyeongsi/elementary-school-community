



let loginSession = sessionStorage.getItem("login");
let loginJson = JSON.parse(loginSession);

var sessionId = String(loginJson.id);


// 회원정보 조회
$.ajax({
	url:"http://localhost:8088/app/mypage/memberProfile.html",
	type:"get",
	data:{ "id":sessionId },
	success:function(data){
		//alert('success');
		//alert(JSON.stringify(str));
		
		$("#id").text(data.id);
		$("#email").text(data.email);
		$("#name").text(data.name);
		$("#education").text(data.education);
		$("#school").text(data.school);
		
	},
	error:function(){
		alert('error');
	}
});

// 회원정보 수정
$("#bbsupdate").click(function () {
	$.ajax({
		url:"/updateMem",
		type:"get",
		data:{ "id":jsonId, "pwd":$("#pwd").val(), "name":$("#name").val(), "email":$("#email").val(), "education":$("#education").val(), "school":$("#school").val() },
		success:function(msg){
			if(msg == "OK"){
				alert('정상적으로 수정되었습니다');
				location.href = "memberProfile.html";
			}else{
				alert('수정되지 않았습니다');
			}			
		},
		error:function(){
			alert('error');
		}	
	});
	
// 회원정보 삭제
	$("#bbsdelete").click(function () {	
		$.ajax({
			url:"/deleteMem",
			type:"get",
			data:{ "id":jsonId },
			success:function(msg){
				if(msg == "OK"){
					alert("삭제되었습니다");
					location.href = "memberProfile.html";
				}else{
					alert("삭제되지 않았습니다");
				}
			},
			error:function(){
				alert('error');
			}
		})
	});