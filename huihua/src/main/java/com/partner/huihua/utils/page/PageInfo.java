package com.partner.huihua.utils.page;

/**
 * 用来进行分页的类
 * 
 * @author 
 *
 */
public class PageInfo {

	public static final int DEFAULT_PAGE_SIZE = 10;

	/**
	 * 分页有关的属性
	 */
	private int pageSize;
    private int currentPage;
    private int prePage;
    private int nextPage;
    private int totalPage;
    private int totalCount;
    // 是否统计总数(是否执行select count语句)
    private boolean statisTotal;
    private String sort; // 排序字段
    private String order; // 排序顺序

    public PageInfo() {
        this.currentPage = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.statisTotal = false;
    }

    /**
     * 
     * @param currentPage
     * @param pageSize
     */
    public PageInfo(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.statisTotal = false;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

	public boolean isStatisTotal() {
		return statisTotal;
	}

	public void setStatisTotal(boolean statisTotal) {
		this.statisTotal = statisTotal;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
