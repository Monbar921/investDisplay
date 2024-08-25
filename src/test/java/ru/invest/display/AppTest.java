package ru.invest.display;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.invest.display.config.HibernateConfiguration;
import ru.invest.display.dao.ShareRepository;
import ru.invest.display.dao.UserRepository;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.Share;
import ru.invest.display.entity.User;
import ru.invest.display.mapper.GeneralMapper;
import ru.invest.display.service.UserService;

import java.util.Optional;
//@RequiredArgsConstructor
@SpringBootTest
@Slf4j
public class AppTest {
//    private final ShareRepository shareRepository;
    private final UserService userService;

    private final GeneralMapper<User, UserReadDto> userReadMapper;


    public AppTest(@Autowired UserService userService, @Autowired GeneralMapper<User, UserReadDto> userReadMapper) {
        this.userService = userService;
        this.userReadMapper = userReadMapper;
    }

    @Test
    void testInsertShare() {
        String username = "alae";
//        User user;
        log.info("START");
        Optional<User> opUser = userService.findByUsername(username);


        if(opUser.isEmpty()){
            UserCreateDto userCreateDto = new UserCreateDto(username);
            Long id = userService.create(userCreateDto);

            User savedUser = User
                    .builder()
                    .id(id)
                    .username(username)
                    .build();

            opUser = Optional.of(savedUser);
        }


//        Share share = Share.builder()
//                .name("Facebook")
//                .price(100)
//                .quantity(1)
//                .platform("AlfaInvest")
//                .user(opUser.get())
//                .code("FCB")
//                .country("US")
//                .sector("IT")
//                .build();
//
//        shareRepository.save(share);
//        hibernateConfiguration.commitCurrentSession();
    }
}
