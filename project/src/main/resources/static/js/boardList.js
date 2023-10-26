function allBoardCk(ckBox){
    const isCk = ckBox.checked;
    
    // 전체 체크박스 체크여부 토글 적용
    const query = "input[name='boardCk']";
    const ckBoxes = document.querySelectorAll(query);
    for (let i = 0; i < ckBoxes.length; i++) {
        ckBoxes[i].checked = isCk;
    }
}

function createBoard(){
    const input = prompt("생성할 게시판의 제목을 입력하세요.");
    if(input == null || input == "")
        return;

    const url = "http://localhost:8088/app/admin/boardList";
    fetch(url, {
        method: "POST",
        headers:{
            "Content-Type":"application/json",
        },
        body: JSON.stringify({
            boardName: input,
        }),
    })
        .then(response => response.json())
        .then(result => {
            if(result == 1){
                alert("생성에 성공했습니다.")
                window.location.reload();
                return;
            }
            alert("생성에 실패했습니다.")
            return;
        })
        .catch(error => alert("생성에 실패했습니다."));
}

function removeBoard() {
    const query = "input[name='boardCk']:checked";
    const ckBoxes = document.querySelectorAll(query);
    if(ckBoxes.length == 0)
        return;

    const isRemove = confirm("정말로 삭제하시겠습니까?");
    if(isRemove){
        const arr = [];
        for (let i = 0; i < ckBoxes.length; i++) {
            let obj = {};
            obj.boardId = ckBoxes[i].dataset['boardid'];
            arr.push(obj);
        }

        const url =  "http://localhost:8088/app/admin/boardList";
        fetch(url, {
            method: "DELETE",
            headers:{
                "Content-Type":"application/json",
            },
            body: JSON.stringify(arr),
        })
            .then(response => response.json())
            .then(result => {
                if(result >= 1){
                    alert("성공적으로 삭제되었습니다.")
                    window.location.reload();
                    return;
                }
                alert("삭제에 실패했습니다.")
            })
            .catch(error => alert("삭제에 실패했습니다."));
    }

}