package com.eduardomagalhaes.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.eduardomagalhaes.dto.UserDto;
import com.eduardomagalhaes.model.User;
import com.eduardomagalhaes.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    public void testCreateUser() {
        UserDto dto = new UserDto();
        dto.setName("João");
        dto.setEmail("joao@email.com");
        dto.setPassword("123");

        User user = new User();
        user.setId(1L);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(user);

        User created = service.createUser(dto);
        Assertions.assertThat(created.getName()).isEqualTo("João");
    }
}

