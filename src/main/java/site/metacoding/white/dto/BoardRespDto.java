package site.metacoding.white.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.Board;
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
        public BoardDetailRespDto(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.user = new UserDto(board.getUser());
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
