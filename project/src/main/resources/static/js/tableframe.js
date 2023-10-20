function makeTable(){
	const t_table = document.querySelector(`.time`);
	
	// 셀 테이블 생성
	
	let period = `<div style="display:table-cell">`
				+`<div class="period">1</div>`
				+`<div class="period">2</div>`
				+`<div class="period">3</div>`
				+`<div class="period">4</div>`
				+`<div class="period">5</div>`
				+`<div class="period">6</div>`
				+`</div>`;
	t_table.insertAdjacentHTML("beforeend", period);
	const cell = 5;
	for (let j = 0; j < cell; j++){
	let html = `<div style="display:table-cell">`;
	
	// 태그별 id 부여
	// NM : 클래스 열
	// NMT : 클래스 열 * 행
	const NM_id = 6;
	for (let i = 0; i < 6; i++) {
		
		html += `<div class="NM${j}" id="NMT${j}_${i}"></div>`;
	}
	html += `</div>`;
    t_table.insertAdjacentHTML("beforeend", html);
	}
}

makeTable();