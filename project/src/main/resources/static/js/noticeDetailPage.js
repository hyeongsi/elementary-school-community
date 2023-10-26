function delCheck(postId){
  //상세화면으로 보이는 게시글을 삭제(글번호로 삭제)
  //alert(bnum); 삭제하려는 글 번호 제대로 가져오는지 체크
	console.log("성공");
  let conf = confirm("삭제하겠습니까?");
  if(conf == true){
    location.href = "/notice/delete?postId=" + postId;
  }