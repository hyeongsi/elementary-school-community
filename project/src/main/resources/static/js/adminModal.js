function initMemberEdit(ele){

    inputInit(ele);
}

// 회원 정보를 토대로 모달 입력창 초기화
function inputInit(ele){
    const id = ele[0];
    const name = ele[1];
    const email = ele[2];
    const education = ele[3];
    const school = ele[4];
    const usergrade = ele[5];
    const userclass = ele[6];

    document.querySelector("#editId").value = id;
    document.querySelector("#editName").value = name;
    document.querySelector("#editEmail").value = email;
    document.querySelector("#education").value = education;
    document.querySelector("#editGrade").options[usergrade-1].selected = true;
    document.querySelector("#editClass").options[userclass-1].selected = true;
    document.querySelector("#school").value = school;
}

// 학교 검색 초기화
function initSchoolSearch(){
    document.querySelector("#ATPT_OFCDC_SC_CODE").options[0].selected = true;
    document.querySelector("#SCHUL_NM").value = "";
}