package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.repo.UserRepo;
import com.imag.rasa_ahar.requestDto.UserRequest;
import com.imag.rasa_ahar.responseDto.UserResponse;
import com.imag.rasa_ahar.validation.Validation;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.properties")
//@DirtiesContext(classMode =  DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {


    private static UserService userService;
    private static UserRequest userRequest;
    private static User user;
    private static UserResponse userResponse;
    @Mock
    private UserRepo userRepoMock;
    @Mock
    private Validation validation;
    private Map<Integer, UserRequest> mockUsersReq = new HashMap<>();
    private Map<Integer, UserResponse> mockusersresponce = new HashMap<>();

    private List<User> users = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        //we used @Mock Annotation so no need below 2 lines of code
        //  userRepoMock = Mockito.mock(UserRepo.class);
        // validation = Mockito.mock(Validation.class);
        this.userService = new UserService(userRepoMock, validation);
        userRequest = new UserRequest("pavan", "pk@gmail.com", "12345", "7036110229", "user");
        user = new User(0, "pavan", "pk@gmail.com", "12345", "7036110229", "user");
        userResponse = new UserResponse("pavan", "pk@gmail.com", "7036110229");
    }

    @Test
    @Order(1)
    @DisplayName("Mapper Method Testing Dto To User")
    void testDtoToUser() {
        User result = userService.dtoToUser(userRequest);
        assertEquals(user, result, "mapping not working");
    }

    @Test
    @Order(2)
    @DisplayName("Mapping testing user to response")
    void userToDtoResponse() {
        UserResponse result = userService.userToDtoResponse(user);
        assertEquals(userResponse, result, "mapping not working");
    }

    @Test
    @Order(3)
    @DisplayName("New User insert test")
    void newUser() {
        mockUsersReq.put(1, new UserRequest("pavan", "pk@gmail.com", "12345", "9110793168", "user"));
        when(userRepoMock.findByPhone("7036110229")).thenReturn(user);
        when(userRepoMock.save(user)).thenReturn(user);
        when(validation.verifyMobile("9110793168")).thenReturn(true);
        when(validation.verifyEmail("pk@gmail.com")).thenReturn(true);
        mockusersresponce.put(1, new UserResponse("pavan", "pk@gmail.com", "9110793168"));
        assertEquals(mockusersresponce.get(1), userService.newUser(mockUsersReq.get(1)));
    }

    @Test
    @Order(4)
    @DisplayName("Test on allUsers ")
    void testAllUsers() {
        users.add(new User(1, "John Doe", "john@example.com", "password123", "1234567890", "user"));
        users.add(new User(2, "Alice Smith", "alice@example.com", "p@ssw0rd", "9876543210", "admin"));
        users.add(new User(3, "Bob Johnson", "bob@example.com", "secret", "5555555555", "user"));
        users.add(new User(4, "Eve Anderson", "eve@example.com", "letmein", "7777777777", "user"));
        users.add(new User(5, "Charlie Brown", "charlie@example.com", "qwerty", "6666666666", "admin"));
        when(userRepoMock.findAll()).thenReturn(users);
        var list = userService.allUsers();
        List<UserResponse> userResponsesList;
        assertAll(
                () -> assertEquals(5, userService.allUsers().size()),
                () -> assertTrue(userService.allUsers().get(0).getName().equals("John Doe")),
                () -> assertTrue(list instanceof List<UserResponse>)
        );
    }

    @Test
    @Order(5)
    @DisplayName("User by phone number test")
    void userByPhone() {
        when(userRepoMock.findByPhone("7036110229")).thenReturn(user);
        assertEquals(userResponse, userService.userByPhone("7036110229"));
    }


}