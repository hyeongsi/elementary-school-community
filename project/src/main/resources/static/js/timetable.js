const today = new Date();

// 스쿨인포 객체 받아오기
function getSchoolInfo(today){
    const url = "/app/member/schoolInfo";

    return fetch(url)
        .then(res => res.json());
}

function updateTimeTable(today){
    getSchoolInfo()
        .then(schoolInfo => {
        	getTimeTable(today, schoolInfo.officeOfEducationCode, schoolInfo.schoolCode)
        });
}


// 월~금요일 각각 날자를 계산하여 반환
function week(num){
	const sel_day = today.getDay()-1; // 현재 요일과 금주의 월요일간의 차
	today.setDate(today.getDate() - sel_day + num);	// 금주의 월요일 날짜
	const year    = today.getFullYear();
	const month   = ('0' + (today.getMonth() +  1 )).slice(-2);
	const day     = ('0' + today.getDate()).slice(-2);
	const dt = year+month+day;
	return dt;
}

const url = "https://open.neis.go.kr/hub/elsTimetable";
const key = "d3348e90712e42a0a67f03cad20d4336";
const type = "json";

// 시간표
// officeOfEducationCode : 시도교육청코드 (서울특별시교육청)
// schoolCode : 시도교육청코드 (서울특별시교육청)
function getTimeTable(today, officeOfEducationCode, schoolCode){
	
	deleteSchedule();
	 if(officeOfEducationCode == null || schoolCode == null){
	        return;
	    }
	const GRADE = "1";	// 학년
	const CLASS_NM = "1";	// 반
	
    // const ATPT_OFCDC_SC_CODE = "S10";   // 시도교육청코드 (서울특별시교육청)
    // const SD_SCHUL_CODE = "9091055";    // 행정표준코드 (경기초등학교)

    const url = new Array();
   
    const urlCount = 5;
    for(let i = 0; i < urlCount; i++){
    	url[i] = getURL(officeOfEducationCode, schoolCode, week(i), GRADE, CLASS_NM);
    	// console.log(url[i]);
    }
    const dayOfWeek = 5;
    for(let i = 0; i < dayOfWeek; i++){
    	fetch(url[i])
    		.then(response => response.json())
    		.then(res => scheduleProcess(res,i))
    		.catch(error => {
    			displayScheduleException();
    		});
    }
    

    // https://open.neis.go.kr/hub/elsTimetable?
    // Key=d3348e90712e42a0a67f03cad20d4336
    // &Type=json
    // &ATPT_OFCDC_SC_CODE=B10
    // &SD_SCHUL_CODE=7031110
    // &ALL_TI_YMD=20231012
}


// 요청 url 생성
function getURL(ATPT_OFCDC_SC_CODE, SD_SCHUL_CODE,ALL_TI_YMD,GRADE,CLASS_NM){
   
    return `${url}?Key=${key}&Type=${type}&ATPT_OFCDC_SC_CODE=${ATPT_OFCDC_SC_CODE}&SD_SCHUL_CODE=${SD_SCHUL_CODE}&ALL_TI_YMD=${ALL_TI_YMD}&GRADE=${GRADE}&CLASS_NM=${CLASS_NM}`;
}

function getResultCode(res){
    return res.elsTimetable[0].head[1].RESULT.CODE;
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
            return false;
    }
}
// 시간표 처리
function scheduleProcess(res, num){
    const success = filter(res);
    if(success){
        clearScheduleInfo();
        updateSchedule(res, num);
    }else{
        displayScheduleException();
    }
}
// 시간표 업데이트
function updateSchedule(res,num){
    const row = res.elsTimetable[1].row;
    for (let i = 0; i < row.length; i++) {
    var t_table = document.querySelector(`#NMT${num}_${i}`);
        if(row[i].ITRT_CNTNT==null||!isNaN(row[i].ITRT_CNTNT)){
        	
        t_table.innerHTML= `<p CNTNT style="color:red;">x</p>`;	
        }else
    	t_table.innerHTML= `<p class="CNTNT">${row[i].ITRT_CNTNT}</p>`;
    }
}

function deleteSchedule(){
	let IdNM;
	for(let j = 0; j < 5; j++){
		for(let i = 0; i < 6; i++){
			let temp = IdNM= "NMT" +j+"_" +i;
			IdNM = temp; 
	
			let tableInfo = document.getElementById(IdNM);
			tableInfo.innerHTML='';
		}
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
    const html2 = `<div class="infoText">해당하는 데이터가 없습니다.</div>`;
    dataInfoWrap.innerHTML = html2;
}

document.querySelector(`.prevDay`).onclick = () => {
	today.setDate(today.getDate() - 7);
	updateTimeTable(today);
}
document.querySelector(`.nextDay`).onclick = () => {
	today.setDate(today.getDate() + 7);
	updateTimeTable(today);
}

updateTimeTable(new Date());