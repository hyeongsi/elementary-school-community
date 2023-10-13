function makeTable(){
	const t_table = document.querySelector(`.time`);
	
	for (let j = 0; j < 5; j++){
	var html = `<div style="display:table-cell">`;
	
	for (let i = 0; i < 6; i++) {
		
		html += `<div class="NM${j}" id="NMT${j}_${i}"></div>`;
	}
	html += `</div>`;
    console.log(html);
    t_table.insertAdjacentHTML("beforeend", html);
	}
}

makeTable();