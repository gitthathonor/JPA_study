package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.User;

public class UserReqDto {

    @Setter
    @Getter
    public static class JoinReqDto { // 인증관련 로직들(로그인 전 로직들)은 전부 다 앞에 엔티티 안 붙임. POST "/user -> /join"
        private String username;
        private String password;

        public User toEntity() {
            return User.builder().username(username).password(password).build();
        }
    }

    @Setter
    @Getter
    public static class LoginReqDto {
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class UpdateReqDto {
        private String password;

        public User toEntity() {
            return User.builder().password(password).build();
        }
    }
}
