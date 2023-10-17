const url = "https://open.neis.go.kr/hub/schoolInfo";
const key = "d3348e90712e42a0a67f03cad20d4336";
const type = "json";


// url 받기
function getSchoolName(name){
	
	const sch = document.getElementById('ATPT_OFCDC_SC_CODE');
	const ATPT_OFCDC_SC_CODE = (sch.options[sch.selectedIndex].value);
//	console.log(ATPT_OFCDC_SC_CODE);
	const SCHUL_NM = name;
	const url = getURL(ATPT_OFCDC_SC_CODE,SCHUL_NM);
//	console.log(url);
	
	fetch(url)
		.then(response => response.json())
		.then(res => updateSC_NM(res));
	
}

function getURL(ATPT_OFCDC_SC_CODE,SCHUL_NM){
	   
    return `${url}?Key=${key}&Type=${type}&ATPT_OFCDC_SC_CODE=${ATPT_OFCDC_SC_CODE}&SCHUL_NM=${SCHUL_NM}`;
}

// 검색 결과 쓰기
function updateSC_NM(res){
	const row = res.schoolInfo[1].row;
	console.log(row);
	const table = document.querySelector(`#SCH_Name`);
	
	for (let i = 0; i < row.length; i++){
		html = `<div style="display:inline;" value="${row[i].SCHUL_NM}" id="${row[i].SD_SCHUL_CODE}" onclick="getValueSCHNM(this.id)">${row[i].SCHUL_NM}</div>`;
		html += `<div style="display:inline;>	</div>`
		html += `<div style="display:inline;">${row[i].ORG_RDNMA}</div><br>`
//		console.log(html);
		table.insertAdjacentHTML('beforeend', html);
	}
}

// 검색 버튼 클릭시 검색화면을 초기화 하고 검색 함수 호출
document.querySelector(`#sheet-search-button`).onclick = () => {
	const table = document.querySelector(`#SCH_Name`);
	console.log(table);
	while(table.firstChild)  {
	    table.removeChild(table.firstChild);
	  }
	const name = document.getElementById("SCHUL_NM").value;
	getSchoolName(name);
	console.log(name);
}

function getValueSCHNM(schCode){

	var name = document.getElementById(schCode).innerText;
	document.getElementById("ATPT_OFCDC_SC_CODE_SIGN").value = schCode;
	document.getElementById("SCHUL_NM_SIGN").value = name;
	
	modal.close();
}