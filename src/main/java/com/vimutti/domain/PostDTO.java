package com.vimutti.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PostDTO {
    private long postId;

    @NotBlank(message = "userId가 없음")
    private String userId;

    @NotBlank(message = "title이 없음")
    private String title;

    @NotBlank(message = "content가 없음")
    private String content;

    @NotBlank(message = "type이 없음")
    private String type;

    private Date createDate;
    private Date upadateDate;

    @NotNull
    private int isPresented;




}
