package com.vimutti.repository;

import com.vimutti.domain.PostDTO;
import com.vimutti.domain.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
