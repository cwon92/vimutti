package com.vimutti.request;

import lombok.Builder;
import lombok.Data;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Data
@Builder
public class PostSearch {

    private static final int MAX_SIZE = 1000;


    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 10;

//    @Builder
//    public PostSearch(Integer page, Integer size){
//        this.page = page;
//        this.size = size;
//    }

    public long getOffset(){
        return (long) (max(1, page) - 1) * min(size, MAX_SIZE);
    }

}
