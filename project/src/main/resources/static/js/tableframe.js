function makeTable(){
	const t_table = document.querySelector(`.time`);
	
	// 셀 테이블 생성
	const cell = 5;
	for (let j = 0; j < cell; j++){
	var html = `<div style="display:table-cell">`;
	
	// 태그별 id 부여
	const NM_id = 6;
	for (let i = 0; i < 6; i++) {
		
		html += `<div class="NM${j}" id="NMT${j}_${i}"></div>`;
	}
	html += `</div>`;
    t_table.insertAdjacentHTML("beforeend", html);
	}
}

makeTable();