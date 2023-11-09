const url = "http://localhost:8088/app/schoolBasicInfo/getSchool";
const type = "json";

// 학교 선택 시 검색결과 지우고, 선택한 값 세팅
function selectSchool(SCHUL_NM, SD_SCHUL_CODE, ATPT_OFCDC_SC_NM, ATPT_OFCDC_SC_CODE){
    clearSearchHistory();

    // 학교 이름, 코드 세팅
    document.querySelector("#SCHUL_NM").value = SCHUL_NM;
    document.querySelector("#SD_SCHUL_CODE").value = SD_SCHUL_CODE;

    // 교육청 이름, 코드 세팅
    document.querySelector("#ATPT_OFCDC_SC_NM").value = ATPT_OFCDC_SC_NM;
    document.querySelector("#ATPT_OFCDC_SC_CODE").value = ATPT_OFCDC_SC_CODE;
}
// 학교정보 요청
function getSchool(pIndex = 1, pSize = 100){
    clearSearchHistory();

    const searchStptSelectElement = document.getElementById('SEARCH_ATPT_OFCDC_SC_CODE');
    const ATPT_OFCDC_SC_CODE = searchStptSelectElement.options[searchStptSelectElement.selectedIndex].value;
    const SCHUL_NM = document.getElementById('SEARCH_SCHUL_NM').value;
    
    const url = getURL(pIndex, pSize, ATPT_OFCDC_SC_CODE, SCHUL_NM);

    fetch(url)
        .then(response => response.json())
        .then(res => updateSearchSchoolList(res, pIndex, pSize));
}

// 검색 결과를 바탕으로 front의 학교정보 출력(업데이트)
function updateSearchSchoolList(res, pIndex, pSize){
    const listTotalCount = res.schoolInfo[0].head[0].list_total_count;
    const row = res.schoolInfo[1].row;

    // 학교명과 주소를 토대로 테이블 구성
    let htmlStr = "<table class='table table-hover mb-0' style='cursor:pointer;'>";
    for (let i = 0; i < row.length; i++){
        const SCHUL_NM = row[i].SCHUL_NM;
        const SD_SCHUL_CODE = row[i].SD_SCHUL_CODE;
        const ATPT_OFCDC_SC_NM = row[i].ATPT_OFCDC_SC_NM;
        const ATPT_OFCDC_SC_CODE = row[i].ATPT_OFCDC_SC_CODE;
        const ORG_RDNMA = row[i].ORG_RDNMA;

        htmlStr += `<tr data-target="#memberEditModal" data-toggle="modal" data-dismiss="modal" 
                        onclick="submitSchoolInfo(\'${SCHUL_NM}\', \'${SD_SCHUL_CODE}\', \'${ATPT_OFCDC_SC_NM}\', \'${ATPT_OFCDC_SC_CODE}\')">
                        <td>${SCHUL_NM}</td>
                        <td>${ORG_RDNMA}</td>
                    </tr>`;
    }
    
    // 마지막 페이지 계산
    const endPIndex = parseInt((listTotalCount - 1) / pSize) + 1;

    htmlStr += "</table>";
    // 페이지 번호 및 버튼 출력
    htmlStr += `<div class="text-center border-top pt-2" style="font-size: 20px;">
                    <span style="cursor:pointer" onclick="prevPage(${pIndex}, ${pSize}, ${endPIndex})">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-bar-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M12.5 15a.5.5 0 0 1-.5-.5v-13a.5.5 0 0 1 1 0v13a.5.5 0 0 1-.5.5ZM10 8a.5.5 0 0 1-.5.5H3.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L3.707 7.5H9.5a.5.5 0 0 1 .5.5Z"/>
                        </svg>
                    </span>
                    <span> <b>${pIndex} / ${endPIndex}</b> </span>
                    <span style="cursor:pointer" onclick="nextPage(${pIndex}, ${pSize}, ${endPIndex})">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-bar-right" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M6 8a.5.5 0 0 0 .5.5h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L12.293 7.5H6.5A.5.5 0 0 0 6 8Zm-2.5 7a.5.5 0 0 1-.5-.5v-13a.5.5 0 0 1 1 0v13a.5.5 0 0 1-.5.5Z"/>
                        </svg>
                    </span>
                </div>`;
    const searchSchoolListEle = document.querySelector("#searchSchoolList");
    searchSchoolListEle.insertAdjacentHTML('beforeend', htmlStr);
}

// 학교 검색 모달을 출력하기 전 input 초기화
function initSchoolSearch(){
    document.querySelector("#SEARCH_ATPT_OFCDC_SC_CODE").options[0].selected = true;
    document.querySelector("#SEARCH_SCHUL_NM").value = "";

    clearSearchHistory();
}

// 검색기록 초기화
function clearSearchHistory(){
    const SCH_Name = document.querySelector("#searchSchoolList");
    SCH_Name.innerHTML="";
}

// 이전 페이지 검색
function prevPage(pIndex, pSize, endPIndex){
    if(pIndex <= 1)
        return;

    getSchool(pIndex - 1, pSize);
}

// 다음 페이지 검색
function nextPage(pIndex, pSize, endPIndex){
    if(pIndex >= endPIndex)
        return;

    getSchool(pIndex + 1, pSize);
}

// 학교정보 요청 url 생성 /  페이지, 한번에 요청할 데이터 개수, 시도교육청코드, 학교이름으로 요청 url 생성
function getURL(pIndex, pSize, ATPT_OFCDC_SC_CODE, SCHUL_NM){
    return `${url}?pIndex=${pIndex}&pSize=${pSize}&ATPT_OFCDC_SC_CODE=${ATPT_OFCDC_SC_CODE}&SCHUL_NM=${SCHUL_NM}`;
}