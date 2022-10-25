package site.metacoding.white.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.User;
import site.metacoding.white.domain.UserRepository;
import site.metacoding.white.dto.UserReqDto.JoinReqDto;
import site.metacoding.white.dto.UserReqDto.LoginReqDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional // 트랜젝션을 붙이지 않으면 영속화 되어 있는 객체가 flush가 안 됨
    public User save(JoinReqDto joinReqDto) {
        User userPS = userRepository.save(joinReqDto.toEntity());
        System.out.println("ccc : " + userPS.getId());
        return userPS;
    }

    @Transactional(readOnly = true)
    public User login(LoginReqDto loginReqDto) {
        User userPS = userRepository.findByUsername(loginReqDto.getUsername());
        if (userPS.getPassword().equals(loginReqDto.getPassword())) {
            return userPS;
        } else {
            throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
        }
        // 트랜젝션 종료
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
