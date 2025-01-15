package com.ego.casino;

import com.ego.casino.configuration.PasswordEncoder;
import com.ego.casino.dto.LoginRequestDto;
import com.ego.casino.dto.LoginResponseDto;
import com.ego.casino.entity.TokenEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.service.AuthService;
import com.ego.casino.service.Impl.AuthServiceImpl;
import com.ego.casino.service.Impl.TokenServiceImpl;
import com.ego.casino.service.Impl.UserServiceImpl;
import com.ego.casino.service.TokenService;
import com.ego.casino.service.UserService;
import com.ego.casino.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class CasinoApplicationTests {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private TokenServiceImpl tokenService;


    @Mock
    private PasswordEncoder passwordEncoder;

    private LoginRequestDto loginRequestDto;

    @Test
    void contextLoads() {
    }
    /*

    @Test
    public void testLoginMethod() {
        LoginRequestDto dto = new LoginRequestDto("egemenmermer@gmail.com", "testpassword");
        LoginResponseDto response = authService.login(dto);
        System.out.println("Response: " + response);
    }

     */

}
