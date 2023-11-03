function makeTable(){
	const t_table = document.querySelector(`.time`);
	
	// 셀 테이블 생성

	const cell = 6;
	for (let j = 0; j < cell; j++){
	let html = `<div class="period">${j+1}</div>`;
	
	// 태그별 id 부여
	// NM : 클래스 열
	// NMT : 클래스 열 * 행
	const NM_id = 6;
	for (let i = 0; i < 5; i++) {
		
		html += `<div class="NM${j}" id="NMT${i}_${j}"></div>`;
	}
    t_table.insertAdjacentHTML("beforeend", html);
	}
}

makeTable();