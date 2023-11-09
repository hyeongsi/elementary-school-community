const tUrl = "https://open.neis.go.kr/hub/elsTimetable";
const tKey = "d3348e90712e42a0a67f03cad20d4336";
const tType = "json";

updateTimeTable();

function updateTimeTable(){
    getSchoolInfo()
        .then(schoolInfo => {
        	getTimeTable(schoolInfo.officeOfEducationCode, schoolInfo.schoolCode, schoolInfo.usergrade, schoolInfo.userclass)
        });
}
// 학사일정 요청 [날짜, 시도교육청코드 (서울특별시교육청), 행정표준코드 (경기초등학교)]
function getTimeTable(officeOfEducationCode, schoolCode, usergrade, userclass){
    if(officeOfEducationCode == null || schoolCode == null){
        return;
    }
    const date = getToday();
    const tUrl = getTURL(officeOfEducationCode, schoolCode, date , usergrade, userclass);

    fetch(tUrl)
        .then(response => response.json())
        .then(res => timeScheduleProcess(res))
        .catch(error => {
        	const dataInfoWrap = document.querySelector(`.dataInfoWrap-class`);
    	    const html = `<div class="infoText">일정 없음</div>`;
    	    dataInfoWrap.innerHTML = html;
            displayScheduleException();
        });
}

// 요청 url 생성
function getTURL(ATPT_OFCDC_SC_CODE, SD_SCHUL_CODE,ALL_TI_YMD,GRADE,CLASS_NM){
    
	return `${tUrl}?Key=${key}&Type=${type}&ATPT_OFCDC_SC_CODE=${ATPT_OFCDC_SC_CODE}&SD_SCHUL_CODE=${SD_SCHUL_CODE}&ALL_TI_YMD=${ALL_TI_YMD}&GRADE=${GRADE}&CLASS_NM=${CLASS_NM}`;
}

function getTimeResultCode(res){
    return res.elsTimetable[0].head[1].RESULT.CODE;
}


// 학사일정 처리
function timeScheduleProcess(res){
    const success = timeFilter(res);
    if(success){
        updateTimeSchedule(res);
    }else{
        //displayScheduleException();
    }
}

function timeFilter(res){
    const result = getTimeResultCode(res);

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

// 달력 업데이트
function updateTimeSchedule(res){
    const row = res.elsTimetable[1].row;
    let str = "";
    let html = `<table class="CNTNT">`;
    let tTable = document.querySelector(`.today-time-table`);
    for (let i = 0; i < row.length; i++) {
           html += `<tr><td>${i+1}교시</td><td>${row[i].ITRT_CNTNT}</td></tr>`;
            	str += row[i].ITRT_CNTNT + "</br>";
            tTable.innerHTML= `<style>
    .CNTNT{
    text-align: center;
       font-weight: 400;
       font-size: medium;
    }
    </style><p class="CNTNT">${str}</p>`;
            tTable.innerHTML = `<style>
            	.CNTNT {
            	width: 100%;
            	height: 100%;
            	table-layout: fixed;
            	
            	border: 1px solid #bcbcbc;
            	}
            	.CNTNT tr td {
            	border: 1px solid #bcbcbc;
            	}
                .CNTNT{
                text-align: center;
                   font-weight: 400;
                   font-size: medium;
                }
                </style>` + html + `</table>`;
            //tTable.insertAdjacentHTML("beforeend", `<div>${row[i].ITRT_CNTNT}</div>`);
        }
    
}

//display exception info
function displayScheduleException(){
    const dataInfoWrap = document.querySelector(`.dataInfoWrap`);
    const html = `<div class="infoText">해당하는 데이터가 없습니다.</div>`;
    dataInfoWrap.innerHTML = html;
}
