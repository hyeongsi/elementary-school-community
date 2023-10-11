const url = "https://open.neis.go.kr/hub/SchoolSchedule";
const key = "d3348e90712e42a0a67f03cad20d4336";
const type = "json";

// getSchedule();

// 학사일정 요청
function getSchedule(){
    const dataTitle = document.querySelector(`.dateTitle`);

    const year = dataTitle.dataset.year;
    let month = dataTitle.dataset.month;
    const ATPT_OFCDC_SC_CODE = "B10";   // 시도교육청코드 (서울특별시교육청)
    const SD_SCHUL_CODE = "7031110";    // 행정표준코드 (경기초등학교)

    const url = getURL(ATPT_OFCDC_SC_CODE, SD_SCHUL_CODE, year, month);

    fetch(url)
        .then(response => response.json())
        .then(res => scheduleProcess(res))
        .catch(error => {
            displayScheduleException();
            console.log("error", error)
        });

    // https://open.neis.go.kr/hub/SchoolSchedule?
    // Key=d3348e90712e42a0a67f03cad20d4336
    // &Type=json
    // &ATPT_OFCDC_SC_CODE=B10
    // &SD_SCHUL_CODE=7031110
    // &AA_FROM_YMD=20220301
    // &AA_TO_YMD=20220331
}

// 요청 url 생성
function getURL(ATPT_OFCDC_SC_CODE, SD_SCHUL_CODE, year, month){
    if(month.length == 1){
        month = "0" + String(month);
    }
    const AA_YMD = String(year) + String(month);
    return `${url}?Key=${key}&Type=${type}&ATPT_OFCDC_SC_CODE=${ATPT_OFCDC_SC_CODE}&SD_SCHUL_CODE=${SD_SCHUL_CODE}&AA_YMD=${AA_YMD}`;
}

function getResultCode(res){
    return res.SchoolSchedule[0].head[1].RESULT.CODE;
}

// 에러 여부 검사
function filter(res){
    const result = getResultCode(res);

    switch (result){
        case "INFO-000":    // 정상 처리되었습니다.
        case "INFO-100":     // (해당 자료는 단순 참고용으로만 활용하시기 바랍니다.)
            return true;
        case "INFO-300":    // 관리자에 의해 인증키 사용이 제한되었습니다.
        case "INFO-200":    // 해당하는 데이터가 없습니다.
        case "ERROR-300":   // 필수 값이 누락되어 잇습니다. 요청인자를 참고 하십시오.
        case "ERROR-290":   // 인증키가 유효하지 않습니다. 인증키가 없는 경우, 홈페이지에서 인증키를 신청하십시오.
        case "ERROR-310":   // 해당 서비스를 찾을 수 없습니다. 요청인자 중 SERVICE를 확인하십시오.
        case "ERROR-333":   // 요청위치 값의 타입이 유효하지 않습니다.요청위치 값은 정수를 입력하세요.
        case "ERROR-336":   // 데이터요청은 한번에 최대 1,000건을 넘을 수 없습니다.
        case "ERROR-337":   // 일별 트래픽 제한을 넘은 호출입니다. 오늘은 더이상 호출할 수 없습니다.
        case "ERROR-500":   // 서버 오류입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다.
        case "ERROR-600":   // 데이터베이스 연결 오류입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다.
        case "ERROR-601":   // SQL 문장 오류 입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다.
            console.log(result);
            return false;
    }
}

// 학사일정 처리
function scheduleProcess(res){
    const success = filter(res);
    if(success){
        clearScheduleInfo();
        updateSchedule(res);
    }else{
        displayScheduleException();
    }
}

// 달력 업데이트
function updateSchedule(res){
    const row = res.SchoolSchedule[1].row;

    for (let i = 0; i < row.length; i++) {
        const eventDateBoardElement = document.querySelector(`.dateBoard div[data-date="${row[i].AA_YMD}"]`);

        const html = `<div>${row[i].EVENT_NM}</div>`;
        eventDateBoardElement.insertAdjacentHTML("beforeend", html);
    }
}

// delete info
function clearScheduleInfo(){
    const dataInfoWrap = document.querySelector(`.dataInfoWrap`);
    dataInfoWrap.innerHTML = "";
}

// display exception info
function displayScheduleException(){
    const dataInfoWrap = document.querySelector(`.dataInfoWrap`);
    const html = `<div class="infoText">해당하는 데이터가 없습니다.</div>`;
    dataInfoWrap.innerHTML = html;
}