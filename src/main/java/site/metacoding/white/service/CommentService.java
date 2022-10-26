package site.metacoding.white.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.white.domain.BoardRepository;
import site.metacoding.white.domain.UserRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

}
