const date = new Date();

updateCalendar(date);

function updateCalendar(date){
    createCalendar(date);
    getSchedule();
}

function createCalendar(date) {
    const currentYear = new Date(date).getFullYear();
    const currentMonth = new Date(date).getMonth() + 1;

    // 1일의 요일
    const firstDay = new Date(date.setDate(1)).getDay();
    // 이달의 마지막 일
    const lastDay = new Date(currentYear, currentMonth, 0).getDate();

    // 1일이 항상 일요일부터 시작하지 않기때문에
    const limitDay = firstDay + lastDay;
    // 7일 단위로 달력을 만들기 위해 7의 곱단위로 일 계산
    const nextDay = Math.ceil(limitDay / 7) * 7;

    let htmlDummy = '';

    for (let i = 0; i < firstDay; i++) {
        htmlDummy += `<div class="noColor"></div>`;
    }

    const strMonth = (String(currentMonth).length == 1) ? ("0" + currentMonth) : String(currentMonth);
    for (let i = 1; i <= lastDay; i++) {
        const loopDay = firstDay + i;
        const week = 7;

        const strDay = (String(i).length == 1) ? ("0" + i) : String(i);
        const date = String(currentYear) + strMonth + strDay;

        const saturday = 0;
        const sunday = 1;

        switch (loopDay % week){
            case saturday:
                htmlDummy += `<div class="blueFont" data-date="${date}">${i}</div>`;
                break;
            case sunday:
                htmlDummy += `<div class="redFont" data-date="${date}">${i}</div>`;
                break;
            default:
                htmlDummy += `<div data-date="${date}">${i}</div>`;
                break;
        }
    }

    for (let i = limitDay; i < nextDay; i++) {
        htmlDummy += `<div class="noColor"></div>`;
    }

    document.querySelector(`.dateBoard`).innerHTML = htmlDummy;
    const dateTitle = document.querySelector(`.dateTitle`);
    dateTitle.innerText = `${currentYear}년 ${currentMonth}월`;
    dateTitle.setAttribute("data-year",String(currentYear));
    dateTitle.setAttribute("data-month",String(currentMonth));
}

// 이전달 이동
document.querySelector(`.prevDay`).onclick = () => {
    updateCalendar(new Date(date.setMonth(date.getMonth() - 1)));
}

// 다음달 이동
document.querySelector(`.nextDay`).onclick = () => {
    updateCalendar(new Date(date.setMonth(date.getMonth() + 1)));
}