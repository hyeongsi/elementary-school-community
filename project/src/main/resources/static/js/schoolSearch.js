const url = "https://open.neis.go.kr/hub/schoolInfo";
const key = "d3348e90712e42a0a67f03cad20d4336";
const type = "json";


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

function getText(){
	const name = document.getElementById('SCHUL_NM').value;
	document.getElementById("result").innerText = name;
	console.log(name);
}

function getURL(ATPT_OFCDC_SC_CODE,SCHUL_NM){
	   
    return `${url}?Key=${key}&Type=${type}&ATPT_OFCDC_SC_CODE=${ATPT_OFCDC_SC_CODE}&SCHUL_NM=${SCHUL_NM}`;
}

function updateSC_NM(res){
	const row = res.schoolInfo[1].row;
	console.log(row);
	const table = document.querySelector(`#SCH_Name`);
	
	for (let i = 0; i < row.length; i++){
		html = `<div style="display:inline;" value="${row[i].SD_SCHUL_CODE}">${row[i].SCHUL_NM}	</div>`;
		html += `<divstyle="display:inline;>${row[i].ORG_RDNMA}</div><br>`
//		console.log(html);
		table.insertAdjacentHTML('beforeend', html);
	}
}
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