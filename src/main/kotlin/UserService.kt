//package com.aws.inzynierka.service
//
//import com.aws.inzynierka.model.dto.UserDto
//import com.aws.inzynierka.repository.UserRepository
//import lombok.RequiredArgsConstructor
//import lombok.extern.log4j.Log4j2
//import org.springframework.cache.annotation.Cacheable
//import org.springframework.stereotype.Service
//
//@Service
//@RequiredArgsConstructor
//@Log4j2
//class UserService {
//    private val userRepository: UserRepository? = null
//    @Cacheable(value = ["Users"], key = "#uuid")
//    @Throws(java.lang.Exception::class)
//    fun getUser(uuid: String?): UserDto {
////        userRepository.save(User.builder().userUuid(UUID.fromString("803b71f0-f0c6-11ec-8ea0-0242ac120002")).username("225").build());
//        val user = userRepository!!.findUserByUsername(uuid).orElseThrow { Exception() }
//        return UserDto()
//            .username(user.username)
//            .uuid(user.userUuid)
//            .isCertified(user.isCertified)
//            .build()
//    }
//}
