package com.vimutti.repository;

import com.vimutti.domain.PostEntity;
import com.vimutti.request.PostSearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {

    List<PostEntity> getList(PostSearch postSearch);

}
