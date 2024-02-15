package com.example.module11.service;

import com.example.module11.entity.User;
import com.example.module11.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private UserRepository repository;

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(repository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1L, "Anna", 16),
                new User(2L, "Olga", 29)
        ));

        when(repository.findAll()).thenReturn(users);

        List<User> allUsers= service.findAll();
        assertThat(allUsers.size()).isGreaterThan(0);
    }

    @Test
    void findById() {
        User user = new User(1L, "Anna", 16);

        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        User singleUser = service.findById(1L);
        assertThat(singleUser).isNotNull().isEqualTo(user );
    }

    @Test
    void save() {
        User user = new User(1L, "Anna", 16);

        when(repository.save(user)).thenReturn(user);
        User savedUser = service.save(user);
        assertThat(savedUser.getName()).isNotNull();
    }

    @Test
    void update() {
        User user = new User(1L, "Anna", 19);

        when(repository.save(user)).thenReturn(user);

        User savedUser = service.save(user);
        assertThat(savedUser.getName()).isNotNull();
    }

    @Test
    void deleteById() {
        User user = new User(1L, "Anna", 19);

        when(repository.findById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(1L);
        verify(repository, times(1)).deleteById(anyLong());
    }

//    @Test
//    void itemNotFoundException() {
//        //CarService serviceMock = mock(CarService.class);
//        //doThrow(new ItemNotFoundException("Car not found, id = 1")).when(serviceMock).deleteById(1L);
//        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> service.deleteById(1L));
//        assertEquals("Car not found, id = 1", exception.getMessage());
//        //verify(repository, times(0)).deleteById(1L);
//        //assertThrows(ItemNotFoundException.class, () -> service.deleteById(1L));
//    }
}