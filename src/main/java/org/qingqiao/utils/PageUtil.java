package org.qingqiao.utils;

/**
 * @author Administrator
 */
public class PageUtil {
    //当前页
    private Integer currentPage;
    //起始页
    private Integer startPage;
    //上一页
    private Integer prevPage;
    //下一页
    private Integer nextPage;
    //首页
    private Integer homePage = 1;
    //尾页
    private Integer lastPage;
    //分页单位
    private Integer pageCount;
    //数据数量
    private Integer count;

    public PageUtil(String currentPage, Integer count, Integer pageCount) {
        if (currentPage == null || "".equals(currentPage)){
            this.currentPage = 1;
        }else {
            this.currentPage = Integer.valueOf(currentPage);
        }
        this.pageCount = pageCount;
        this.count = count;
        initStartPage();
        initLastPage();
        initPrevPage();
        initNextPage();
    }

    private void initStartPage() {
        this.startPage = (this.currentPage - 1) * this.pageCount;
    }

    private void initNextPage() {
        this.nextPage = this.currentPage + 1 >= this.lastPage ? this.lastPage : this.currentPage + 1;

    }

    private void initPrevPage() {
        this.prevPage = this.currentPage - 1 == 0 ? 1 : this.currentPage - 1;
    }

    private void initLastPage() {
        this.lastPage = this.count % this.pageCount == 0 ? this.count/this.pageCount : this.count/this.pageCount+1;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPrevPage() {
        return prevPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public Integer getCount() {
        return count;
    }
}
