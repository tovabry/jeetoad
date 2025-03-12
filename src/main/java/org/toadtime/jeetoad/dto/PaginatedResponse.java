package org.toadtime.jeetoad.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PaginatedResponse<T> {
    private List<T> data;
    private long totalItems;
    private int totalPages;
    private int currentPage;
    private int pageSize;

    public PaginatedResponse(List<T> data, long totalItems, int totalPages, int currentPage, int pageSize) {
        this.data = data;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

}
