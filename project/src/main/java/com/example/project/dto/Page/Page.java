package com.example.project.dto.Page;

public class Page {

    private int displayUnit;    // 출력 단위
    private int curPage;        // 현재 페이지
    private int totalCnt;       // 전체 데이터 개수
    private int maxPage;        // 최대 페이지 번호
    private int startPage;      // 시작 페이지 버튼 번호 
    private int endPage;        // 마지막 페이지 버튼 번호
    private int pageBtnCnt = 5;  // 페이지 버튼 개수

    public Page(int displayUnit, int curPage, int totalCnt) {
        this.displayUnit = displayUnit;
        this.curPage = curPage;
        this.totalCnt = totalCnt;
        maxPage = calcMaxPage();
        startPage = calcStartPage();
        endPage = calcEndPage();
    }

    public int getDisplayUnit() {
        return displayUnit;
    }

    public int getCurPage() {
        return curPage;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setPageBtnCnt(int pageBtnCnt) {
        this.pageBtnCnt = pageBtnCnt;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    private int calcMaxPage(){
        if(displayUnit == 0)
            return 0;

        double totalCnt = this.totalCnt;
        double maxPage = Math.ceil(totalCnt / displayUnit);
        return (int)maxPage;
    }

    private int calcStartPage(){
        return ((curPage-1) / pageBtnCnt) * pageBtnCnt + 1;
    }

    private int calcEndPage(){
        int endPage = ((curPage-1) / pageBtnCnt) * pageBtnCnt + pageBtnCnt;
        return (maxPage > endPage) ? endPage : maxPage;
    }

    public int getStartNum(){
        return displayUnit * (curPage - 1) + 1;
    }

    public int getEndNum(){
        return displayUnit * curPage;
    }
}
