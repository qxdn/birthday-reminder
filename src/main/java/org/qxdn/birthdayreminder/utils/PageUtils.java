package org.qxdn.birthdayreminder.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtils {

    public static Pageable getPageable(Integer page, Integer pageSize) {
        return PageRequest.of(page, pageSize);
    }

    public static Pageable getPageable(org.qxdn.birthdayreminder.model.dto.request.PageRequest request) {
        Sort.Direction direction = Sort.Direction.valueOf(request.getDirection());
        Sort sort = Sort.by(direction, request.getOrderBy());
        return PageRequest.of(request.compute(), request.getPageSize(), sort);
    }

}
