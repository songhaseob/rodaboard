package kr.or.ddit.common.model;

public class PageVo {

	private int page;
	private int pageSize;

	private int pageNum;
	
	public PageVo() {}

	public PageVo(int page, int pageSize, int pageNum) {
		this.page = page;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}
	

	@Override
	public String toString() {
		return "PageVo [page=" + page + ", pageSize=" + pageSize + ", pageNum=" + pageNum + "]";
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
}
