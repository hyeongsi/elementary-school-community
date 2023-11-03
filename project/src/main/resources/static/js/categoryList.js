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
