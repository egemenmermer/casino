package com.ego.casino;

import com.ego.casino.dto.LoginRequestDto;
import com.ego.casino.dto.LoginResponseDto;
import com.ego.casino.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CasinoApplicationTests {

    @Autowired
    public AuthService authService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testLoginMethod() {
        LoginRequestDto dto = new LoginRequestDto("egemenmermer@gmail.com", "testpassword");
        LoginResponseDto response = authService.login(dto);
        System.out.println("Response: " + response);
    }

}
