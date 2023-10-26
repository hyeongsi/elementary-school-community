function allCategoryCk(ckBox){
    const isCk = ckBox.checked;

    // 전체 체크박스 체크여부 토글 적용
    const query = "input[name='boardCk']";
    const ckBoxes = document.querySelectorAll(query);
    for (let i = 0; i < ckBoxes.length; i++) {
        ckBoxes[i].checked = isCk;
    }
}

function createCategory(element){
    const inputCategoryName = prompt("생성할 카테고리의 제목을 입력하세요.");
    if(inputCategoryName == null || inputCategoryName == "")
        return;

    const boardId = element.dataset['boardid'];
    const url = "http://localhost:8088/app/admin/categoryList";
    fetch(url, {
        method: "POST",
        headers:{
            "Content-Type":"application/json",
        },
        body: JSON.stringify({
            categoryName: inputCategoryName,
            boardId: boardId,
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

function removeCategory() {
    const query = "input[name='categoryCk']:checked";
    const ckBoxes = document.querySelectorAll(query);
    if(ckBoxes.length == 0)
        return;

    const isRemove = confirm("정말로 삭제하시겠습니까?");
    if(isRemove){
        const arr = [];
        for (let i = 0; i < ckBoxes.length; i++) {
            let obj = {};
            obj.categoryId = ckBoxes[i].dataset['categoryid'];
            arr.push(obj);
        }

        const url =  "http://localhost:8088/app/admin/categoryList";
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
                return;
            })
            .catch(error => alert("삭제에 실패했습니다."));
    }

}