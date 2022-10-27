package site.metacoding.white.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.Comment;
import site.metacoding.white.domain.User;

public class BoardRespDto {

    @Getter
    @Setter
    public static class BoardSaveRespDto {
        private Long id;
        private String title;
        private String content;

        private UserDto user;

        @Setter
        @Getter
        public static class UserDto { // 외부로 노출시키는 것이 아니라서 그냥 DTO인지 확인만 해주자
            // 이 친구는 화면에 따라서 달라지기 때문에 뭔가 정해진 것은 없다. 다만 한 프로젝트 내에서는 규칙으로 정해두어야 한다.
            // 만약 공통적으로 사용된다고 따로 빼놨는데, 뭔가 수정사항이 발생하면, 고쳐야 될 부분이 엄청나게 늘어난다.
            // 그래서 강사님은 분리시키는 것을 좋아한다.
            private Long id;
            private String username;

            public UserDto(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }

        @Builder
        public BoardSaveRespDto(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.user = new UserDto(board.getUser());
        }

    }

    @Getter
    @Setter
    public static class BoardDetailRespDto {
        private Long id;
        private String title;
        private String content;
        private BoardUserDto user;
        private List<CommentDto> comments = new ArrayList<>();

        @Setter
        @Getter
        public static class CommentDto {
            private Long id;
            private String content;
            private CommentUserDto user;

            @Setter
            @Getter
            public static class CommentUserDto {
                private Long id;
                private String username;

                public CommentUserDto(User user) {
                    this.id = user.getId(); // lazy
                    this.username = user.getUsername(); // lazy
                }

            }

            public CommentDto(Comment comment) {
                this.id = comment.getId();
                this.content = comment.getContent();
                this.user = new CommentUserDto(comment.getUser());
            }

        }

        @Setter
        @Getter
        public static class BoardUserDto { // 외부로 노출시키는 것이 아니라서 그냥 DTO인지 확인만 해주자
            // 이 친구는 화면에 따라서 달라지기 때문에 뭔가 정해진 것은 없다. 다만 한 프로젝트 내에서는 규칙으로 정해두어야 한다.
            // 만약 공통적으로 사용된다고 따로 빼놨는데, 뭔가 수정사항이 발생하면, 고쳐야 될 부분이 엄청나게 늘어난다.
            // 그래서 강사님은 분리시키는 것을 좋아한다.
            private Long id;
            private String username;

            public BoardUserDto(User user) {
                this.id = user.getId(); // Lazy
                this.username = user.getUsername(); // Lazy
            }
        }

        @Builder
        public BoardDetailRespDto(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.user = new BoardUserDto(board.getUser());
            this.comments = board.getComments().stream().map((comment) -> new CommentDto(comment))
                    .collect(Collectors.toList());
        }

    }

    @Getter
    @Setter
    public static class BoardUpdateRespDto {
        private Long id;
        private String title;
        private String content;
        private UserDto user;

        @Setter
        @Getter
        public static class UserDto {
            private Long id;

            public UserDto(User user) {
                this.id = user.getId();
            }
        }

        @Builder
        public BoardUpdateRespDto(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.user = new UserDto(board.getUser());
        }

    }

}
