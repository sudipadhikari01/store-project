package com.sudip.store.electronicstore.utils;

import com.sudip.store.electronicstore.dtos.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


public class PageableHelper {


    public static <U, V> PageableResponse<V> getPageableResponse(Page<U> page, Class<V> vClassless) {
        List<U> users = page.getContent();
        List<V> list = users.stream().map(user -> {
            return new ModelMapper().map(user, vClassless);
        }).collect(Collectors.toList());


        PageableResponse<V> pageableResponse = new PageableResponse<>();
        pageableResponse.setContent(list);
        pageableResponse.setPageNumber(page.getNumber());
        pageableResponse.setPageSize(page.getSize());
        pageableResponse.setTotalElements(page.getTotalElements());
        pageableResponse.setTotalPages(page.getTotalPages());
        pageableResponse.setLastPage(page.isLast());

        return pageableResponse;
    }
}
