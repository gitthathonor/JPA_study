package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.User;

public class UserRespDto {

    @Setter
    @Getter
    public static class JoinRespDto {
        private Long id;
        private String username;

        public JoinRespDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
        }
    }

    @Setter
    @Getter
    public static class UpdateRespDto {
        private String username;
        private String password;

        public UpdateRespDto(User user) {
            this.username = user.getUsername();
            this.password = user.getPassword();
        }
    }
}
