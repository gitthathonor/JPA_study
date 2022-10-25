package site.metacoding.white.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.User;
import site.metacoding.white.domain.UserRepository;
import site.metacoding.white.dto.SessionUser;
import site.metacoding.white.dto.UserReqDto.JoinReqDto;
import site.metacoding.white.dto.UserReqDto.LoginReqDto;
import site.metacoding.white.dto.UserReqDto.UpdateReqDto;
import site.metacoding.white.dto.UserRespDto.JoinRespDto;
import site.metacoding.white.dto.UserRespDto.UpdateRespDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // 응답의 Dto는 서비스에서 만든다.
    @Transactional // 트랜젝션을 붙이지 않으면 영속화 되어 있는 객체가 flush가 안 됨
    public JoinRespDto save(JoinReqDto joinReqDto) {
        User userPS = userRepository.save(joinReqDto.toEntity());
        return new JoinRespDto(userPS);
    }

    @Transactional(readOnly = true)
    public SessionUser login(LoginReqDto loginReqDto) {
        User userPS = userRepository.findByUsername(loginReqDto.getUsername());
        if (userPS.getPassword().equals(loginReqDto.getPassword())) {
            System.out.println("ccc : SessionUser로 변환시작");
            return new SessionUser(userPS);
        } else {
            throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
        }
        // 트랜젝션 종료
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UpdateRespDto update(Long id, UpdateReqDto updateReqDto) {
        User userPS = userRepository.findById(id);
        userPS.update(updateReqDto);
        return new UpdateRespDto(userPS);
    }

}
