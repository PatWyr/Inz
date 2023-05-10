package com.aws.inzynierka.service;

import com.aws.inzynierka.model.dto.UserDto;
import com.aws.inzynierka.model.entity.User;
import com.aws.inzynierka.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    @Cacheable(value= "Users", key="#uuid")
    public UserDto getUser(String uuid) throws Exception {
//        userRepository.save(User.builder().userUuid(UUID.fromString("803b71f0-f0c6-11ec-8ea0-0242ac120002")).username("225").build());
        User user = userRepository.findUserByUsername(uuid).orElseThrow(Exception::new);
        return UserDto.builder()
                .username(user.getUsername())
                .uuid(user.getUserUuid())
                .isCertified(user.getIsCertified())
                .build();
    }
}
