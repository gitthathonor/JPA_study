package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.User;

public class BoardReqDto {

    @Setter
    @Getter
    public static class BoardSaveReqDto {
        private String title;
        private String content;
        private User user; // 이 친구는 안 받음. 서비스로직

        // 클라이언트한테 받는게 아님!!!
        // @Getter
        // @Setter
        // public class ServiceDto {
        // private User user;
        // }

        // private ServiceDto serviceDto;

        // public void newInstance() {
        // serviceDto = new ServiceDto();
        // }
    }

    // DTO는 여기다가 추가로
}
