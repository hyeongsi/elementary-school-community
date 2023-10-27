//#################### 템플릿 ############################
    const signUpButton = document.getElementById('signUp');
    const signInButton = document.getElementById('signIn');
    const container = document.getElementById('container');

    signUpButton.addEventListener('click', () => {
    		container.classList.add("right-panel-active");
    });

    signInButton.addEventListener('click', () => {
    		container.classList.remove("right-panel-active");
    });
    
    
$(document).ready(function(){
    	
    	$("#login").click(function(){ //로그인 버튼 누를시 실행되는 함수
    		
    		$.ajax({
    			url:"http://localhost:8088/app/login.html",
    			type:"post",
    			data:{ id:$("#id").val(), pwd:$("#pwd").val() }, //id란과 pwd에 입력된 Data 서버로 전달
    			//백엔드에서 넘겨주는 데이터가 dto라는 객체이므로 json으로 받음
    			success:function(json){
    			//	alert('success');
    			//	alert(JSON.stringify(json));
    				if(json==""){
    					alert("id나 비밀번호를 확인하세요.")
    					$("#id").val("");
    					$("#pwd").val("")
    				}else{
    					sessionStorage.setItem("login",JSON.stringify(json)); //JSON.stringify(json)
    					alert(json.name+"님 환영합니다.")
    					location.href="./"
    				}
    				
    			},
    			error:function(){
    				alert('error');
    			}
    			
    		});
    		
    		
    	});
    	
    	
    });


    // #################### 회원가입 ############################

    $(document).ready(function() {
    	
    	$("#idcheckBtn").click(function() {
    		$.ajax({
    			url:"http://localhost:8088/app/getId",
    			type:"post",
    			data:{"id":$("#accountid").val() },
    			success:function( msg ){
    				//alert('success')
    				//alert(msg);
    				if(msg.trim()=='OK'){
    					$("#idcheck").html("이 아이디는 사용 가능합니다.");
    					$("#accountid").val( $("#accountid").val());
    				}else{
    					$("#idcheck").html("이 아이디는 사용중입니다.");
    					$("#accountid").val("");//삭제
    					$("#accountid").focus(); //다시 입력하라고 커서가 뜬다.
    				}	
    			},
    			error:function(){
    				alert('error');
    			}
    		});
    		
    	});
    	
    	$("#account").on("click",function(){
    		
    		let params = $("#frm").serialize();
    		let input = $("#accountemail").val();
			let email_format = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
			
			// 정규식을 이용한 이메일 형식 체크 로직
			if (email_format.test(input)) {
				$.ajax({
					url:"http://localhost:8088/app/addmember",
					type:"post",
					//data:{id:$("#id").val(), pwd:$("#pwd").val(), name:$("name").val(), email:$("email").val() }, //id로 보낸것
					data:params,//name으로 보낸것
					success:function(data){			
						//alert('success')
						if(data=="YES"){
							alert('가입성공');
							container.classList.remove("right-panel-active");
						}else{
							alert('가입실패');
						}
					},
					error:function(){
						alert('error');
					}

				});
    		
				} else {
					alert("잘못된 이메일 형식입니다");
					alert('가입실패');
					$("#accountemail").val("");//삭제
					$("#accountemail").focus(); // 커서 포커싱
				}
    	});
    	
    	
    	
    	
    	
    	
    	
    	/*
    	
    	//#################### 정규식을 이용한 이메일 형식 체크 ############################

    	let input = document.getElementById("accountemail").value
    	let email_format = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

    	if (email_format.test(input)) {
    	    alert("이메일 입력 성공!")
    	} else {
    	    alert("잘못된 이메일 형식입니다");
    	    document.getElementById("accountemail").value = "";
    	    inputform.em.focus();
    	}
    	
    	*/
    
    });
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    