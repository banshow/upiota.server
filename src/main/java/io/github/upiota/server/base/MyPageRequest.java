package io.github.upiota.server.base;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class MyPageRequest implements Pageable{

	private int pageNumber;
	private int pageSize;
	
	
	
	public MyPageRequest() {
		
	}

	public MyPageRequest(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public long getOffset() {
		return 0;
	}

	@Override
	public Sort getSort() {
		return null;
	}

	@Override
	public Pageable next() {
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		return null;
	}

	@Override
	public Pageable first() {
		return new MyPageRequest(1,10);
	}

	@Override
	public boolean hasPrevious() {
		return pageNumber > 0;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
