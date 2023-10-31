//document.getElementById("customButton").addEventListener("click", function() {
//	const postId = new URL(window.location.href).searchParams.get("postId");
//	const addCommentUrl = "../addComment?postId=" + postId;
//
//     var form = document.createElement("form");
//     form.method = "POST";
//     form.action = addCommentUrl;
//
//     var postIdInput = document.createElement("input");
//     postIdInput.type = "hidden";
//     postIdInput.name = "postId";
//     postIdInput.value = postId;
//
//     form.appendChild(postIdInput);
//
//     document.body.appendChild(form);
//     form.submit();
//	
//	console.log("성공");
//});

    // URL에서 postId를 추출
    var urlParams = new URLSearchParams(window.location.search);
    var postId = urlParams.get("postId");

    // postId를 input 요소에 설정
    var postIdInput = document.getElementById("postIdInput");
    postIdInput.value = postId;