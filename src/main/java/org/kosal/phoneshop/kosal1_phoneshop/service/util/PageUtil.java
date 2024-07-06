package org.kosal.phoneshop.kosal1_phoneshop.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {
	int DEFAULT_PAGE_LIMIT=4;
	int DEFAULT_PAGE_NUM=1;
	String PAGE_LIMIT="_limit";
	String PAGE_NUMBER="_page";
	static Pageable getPage(int pageNumber,int pageSize) {
		if(pageNumber<DEFAULT_PAGE_NUM) {
			pageNumber=DEFAULT_PAGE_NUM;
		}
		if(pageNumber<1) {
			pageSize=DEFAULT_PAGE_LIMIT;
		}
		
		Pageable pageable= PageRequest.of(pageNumber-1, pageSize);
		
		return pageable;
	}

}
