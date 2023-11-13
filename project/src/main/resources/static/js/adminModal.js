// 회원 정보를 토대로 모달 입력창 초기화
function initMemberEdit(ele){
    const id = ele[0];
    const name = ele[1];
    const email = ele[2];
    const education = ele[3];
    const school = ele[4];
    const educationCode = ele[5];
    const schoolCode = ele[6];
    const usergrade = ele[7];
    const userclass = ele[8];

    document.querySelector("#editId").value = id;
    document.querySelector("#editName").value = name;
    document.querySelector("#editEmail").value = email;
    document.querySelector("#editEducation").value = education;
    document.querySelector("#editSchool").value = school;
    document.querySelector("#editEducationCode").value = educationCode;
    document.querySelector("#editSchoolCode").value = schoolCode;
    document.querySelector("#editGrade").options[usergrade-1].selected = true;
    document.querySelector("#editClass").options[userclass-1].selected = true;
}

function updateMember(){
    const formData = $("#userEditForm").serialize();

    Swal.fire({
        title: '정말로 수정 하시겠습니까?',
        text: "다시 되돌릴 수 없습니다.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '승인',
        cancelButtonText: '취소',
        reverseButtons: false, // 버튼 순서 거꾸로

    }).then(result => {

        if(result.isConfirmed){
            $.ajax({
                url: "http://localhost:8088/app/mypage/memberUpdate",
                type: "PATCH",
                data: formData, // 직렬화한 폼 데이터를 전송
                success: function (data) {
                    if (data.trim()==='YES') {
                        updateSuccess(result);
                    } else {
                        Swal.fire('실패!', '빈칸이 있는지 확인해주세요.', 'error');
                    }
                },error: function () {
                    Swal.fire('에러 발생', '서버 오류가 발생했습니다.', 'error');
                }
            });
        }

    }) // end Swal
}

function updateSuccess(result){
    Swal.fire(
        'success',
        '수정이 완료되었습니다.',
        'success'
    ).then(function(){
        location.href = location.href;
    })
}