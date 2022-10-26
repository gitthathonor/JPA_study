package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.User;

public class CommentReqDto {
    @Getter
    @Setter
    public static class CommentSaveReqDto {
        private String content;
        private Long boardId; // 클라이언트한테서 받아야 함
        private SessionUser sessionUser; // 서비스 로직
        // Board도 필요한데?
    }
}
