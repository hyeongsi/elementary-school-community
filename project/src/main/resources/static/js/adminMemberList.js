function allMemberCk(ckBox){
    const isCk = ckBox.checked;
    
    // 전체 체크박스 체크여부 토글 적용
    const query = "input[name='memberCk']";
    const ckBoxes = document.querySelectorAll(query);
    for (let i = 0; i < ckBoxes.length; i++) {
        ckBoxes[i].checked = isCk;
    }
}

function removeMember() {
    const query = "input[name='memberCk']:checked";
    const ckBoxes = document.querySelectorAll(query);
    if(ckBoxes.length == 0)
        return;

    const isRemove = confirm("정말로 삭제하시겠습니까?");
    if(isRemove){
        const arr = [];
        for (let i = 0; i < ckBoxes.length; i++) {
            let obj = {};
            obj.id = ckBoxes[i].dataset['memberid'];
            arr.push(obj);
        }

        const url =  "http://localhost:8088/app/admin/memberList";
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