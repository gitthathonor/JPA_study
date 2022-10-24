package site.metacoding.white.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.User;
import site.metacoding.white.domain.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional // 트랜젝션을 붙이지 않으면 영속화 되어 있는 객체가 flush가 안 됨
    public void save(User user) {
        userRepository.save(user);
    }

    public User login(User user) {
        return userRepository.login(user);
    }
}
