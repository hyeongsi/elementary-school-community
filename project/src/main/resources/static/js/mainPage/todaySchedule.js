const url = "https://open.neis.go.kr/hub/SchoolSchedule";
const key = "d3348e90712e42a0a67f03cad20d4336";
const type = "json";

updateSchedule();

function updateSchedule(){
    getSchoolInfo()
        .then(schoolInfo => {
            getSchedule(schoolInfo.officeOfEducationCode, schoolInfo.schoolCode)
        });
}


// 학사일정 요청 [날짜, 시도교육청코드 (서울특별시교육청), 행정표준코드 (경기초등학교)]
function getSchedule(officeOfEducationCode, schoolCode){
    if(officeOfEducationCode == null || schoolCode == null){
        return;
    }
    const date = getToday();
    const url = getURL(officeOfEducationCode, schoolCode, date);
    fetch(url)
        .then(response => response.json())
        .then(res => scheduleProcess(res))
        .catch(error => {
            displayScheduleException();
        });
}

// 요청 url 생성
function getURL(ATPT_OFCDC_SC_CODE, SD_SCHUL_CODE, date){
    
    return `${url}?Key=${key}&Type=${type}&ATPT_OFCDC_SC_CODE=${ATPT_OFCDC_SC_CODE}&SD_SCHUL_CODE=${SD_SCHUL_CODE}&AA_YMD=${date}`;
}

function getResultCode(res){
	if(res.SchoolSchedule[0].head[1].RESULT.CODE != "INFO-000"){
		const dataInfoWrap = document.querySelector(`.dataInfoWrap`);
	    const html = `<div class="infoText">일정 없음</div>`;
	     dataInfoWrap.innerHTML = html;
	}
    return res.SchoolSchedule[0].head[1].RESULT.CODE;
}

// 학사일정 처리
function scheduleProcess(res){
    const success = filter(res);
    if(success){
        clearScheduleInfo();
        updateCalendarWithSchedule(res);
    }else{
        displayScheduleException();
    }
}

// 달력 업데이트
function updateCalendarWithSchedule(res){
    const row = res.SchoolSchedule[1].row;
        const eventDateBoardElement = document.querySelector(`.today-schedule`);

        const redColor = "#e31b20";
        if(row[0].SBTR_DD_SC_NM != "해당없음"){
            eventDateBoardElement.style.color = redColor;
        }

        const html = `<div>${row[0].EVENT_NM}</div>`;
        eventDateBoardElement.insertAdjacentHTML("beforeend", html);
    
}

// delete info
function clearScheduleInfo(){
    const dataInfoWrap = document.querySelector(`.dataInfoWrap`);
    dataInfoWrap.innerHTML = "";
}

// display exception info
function displayScheduleException(){
    const dataInfoWrap = document.querySelector(`.dataInfoWrap`);
    const html = `<div class="infoText">일정 없음</div>`;
     dataInfoWrap.innerHTML = html;
}