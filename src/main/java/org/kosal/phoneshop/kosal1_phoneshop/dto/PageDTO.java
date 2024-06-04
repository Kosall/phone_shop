package org.kosal.phoneshop.kosal1_phoneshop.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;
@Data
public class PageDTO {
	private List<?>lists;
	private PaginationDTO paginationDTO;
	public PageDTO(Page<?>page) {
		this.lists=page.getContent();
		this.paginationDTO=PaginationDTO.builder()
				.empty(page.isEmpty())
				.first(page.isFirst())
				.last(page.isLast())
				.pageSize(page.getPageable().getPageSize())
				.pageNumber(page.getPageable().getPageNumber()+1)
				.totalPages(page.getTotalPages())
				.totalElements(page.getTotalElements())
				.numberOfElements(page.getNumberOfElements())
				.build();
	}

}
