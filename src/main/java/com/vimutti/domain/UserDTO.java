package com.vimutti.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
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

    @Builder
    public UserDTO(String userId, String title, String content, String type){
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public UserDTO changeTitle(String title){
        return UserDTO.builder()
                .title(title)
                .content(this.content)
                .build();
    }

}
