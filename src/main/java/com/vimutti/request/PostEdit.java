package com.vimutti.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostEdit {
    @NotBlank(message = "유저아이디 입력")
    private String userId;
    @NotBlank(message = "타이틀 입력")
    private String title;
    @NotBlank(message = "내용 입력")
    private String content;
    @NotBlank(message = "타입 입력")
    private String type;


    @Builder
    public PostEdit(String userId, String title, String content, String type){
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.type = type;
    }


}
