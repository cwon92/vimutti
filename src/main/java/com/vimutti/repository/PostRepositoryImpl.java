package com.vimutti.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vimutti.domain.PostEntity;
import com.vimutti.domain.QPostEntity;
import com.vimutti.request.PostSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PostEntity> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(QPostEntity.postEntity)
                .limit(postSearch.getSize())
                .offset(postSearch.getOffset())
                .orderBy(QPostEntity.postEntity.postId.desc())
                .fetch();
    }

}
