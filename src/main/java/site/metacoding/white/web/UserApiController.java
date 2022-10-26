package site.metacoding.white.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.dto.ResponseDto;
import site.metacoding.white.dto.SessionUser;
import site.metacoding.white.dto.UserReqDto.JoinReqDto;
import site.metacoding.white.dto.UserReqDto.LoginReqDto;
import site.metacoding.white.dto.UserReqDto.UpdateReqDto;
import site.metacoding.white.dto.UserRespDto.JoinRespDto;
import site.metacoding.white.dto.UserRespDto.UpdateRespDto;
import site.metacoding.white.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    // 회원정보 수정

    // 회원정보 보기

    @PostMapping("/join")
    public ResponseDto<?> save(@RequestBody JoinReqDto joinReqDto) {
        JoinRespDto joinRespDto = userService.save(joinReqDto);
        return new ResponseDto<>(1, "ok", joinRespDto);
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody LoginReqDto loginReqDto) {
        SessionUser sessionUser = userService.login(loginReqDto);
        session.setAttribute("sessionUser", sessionUser);
        return new ResponseDto<>(1, "ok", sessionUser);
    }

    @GetMapping("/user")
    public String getUserLIst() {
        return "hello";
    }

    @PutMapping("/user/{id}")
    public ResponseDto<?> update(@PathVariable Long id, @RequestBody UpdateReqDto updateReqDto) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return new ResponseDto<>(-1, "로그인 하셈", null);
        }
        UpdateRespDto updateRespDto = userService.update(id, updateReqDto);
        return new ResponseDto<>(1, "ok", updateRespDto);
    }

}
