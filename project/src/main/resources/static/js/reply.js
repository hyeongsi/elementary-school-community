$(document).ready(function () {
	$(".reply").bind('click',function () {
       	$('.reply-input').remove();
       	$('.edit-input').remove();
       	console.log($(this).attr('id').substring(8));
       	var HTML = `<form class="reply-input" action="../addComment" method="post">
													<input type="hidden" name="postId" id="postIdInput" value="`+document.querySelector(".postId-input").value+ `">
       												<input type="hidden" name="parentCommentId" id="parentCommentId" value="`+ $(this).attr('id').substr(8) +`">
													<textarea name="comment" rows="3" cols="100%" placeholder="댓글을 입력해주세요."></textarea>
													<button type="submit" class="common_input_btn btn btn-outline-primary" tabindex="0" id="customButton">등록</button>
													<!-- <a href="javascript:void(0);" class="common_input_btn" tabindex="0" id="customButton">등록</a>
												 -->
												</form>`;
       	document.querySelector("#comment-input-box"+$(this).attr('id').substring(8)).insertAdjacentHTML("afterend", HTML);
       	
       	
    });
});

