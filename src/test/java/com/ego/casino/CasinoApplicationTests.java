package com.ego.casino;

import com.ego.casino.configuration.PasswordEncoder;
import com.ego.casino.dto.LoginRequestDto;
import com.ego.casino.service.Impl.AuthServiceImpl;
import com.ego.casino.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CasinoApplicationTests {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserServiceImpl userService;


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
