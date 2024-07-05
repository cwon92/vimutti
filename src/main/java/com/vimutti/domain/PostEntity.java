package com.vimutti.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String userId;
    private String title;

    @Lob
    private String content;

    private String type;
    private Date createDate;
    private Date updateDate;

    @ColumnDefault("1")
    private int isPresented;

    @Builder
    public PostEntity(String userId, String title, String content, String type){
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public void editPost(String title, String content) {
        this.title = title;
        this.content = content;
    }



}
