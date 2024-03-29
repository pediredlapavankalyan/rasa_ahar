package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.exceptions.UserAlreadyExistsException;
import com.imag.rasa_ahar.exceptions.UserNotFoundException;
import com.imag.rasa_ahar.repo.UserRepo;
import com.imag.rasa_ahar.requestDto.UserRequest;
import com.imag.rasa_ahar.responseDto.UserResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.properties")
//@DirtiesContext(classMode =  DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {


    @InjectMocks
    private static UserService userService;
    private static UserRequest userRequest;
    private static User user;
    private static UserResponse userResponse;
    @Mock
    private UserRepo userRepoMock;

    private Map<Integer, UserRequest> mockUsersReq = new HashMap<>();
    private Map<Integer, UserResponse> mockusersresponce = new HashMap<>();

    private List<User> users = new ArrayList<>();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @BeforeEach
    public void setUp() {
        //we used @Mock Annotation so no need below 1 line of code
        //  userRepoMock = Mockito.mock(UserRepo.class);
        //we used injectmocks so that no need to initialize the userservice
        // this.userService = new UserService(userRepoMock, validation);
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
    void newUser() throws UserAlreadyExistsException {
        mockUsersReq.put(1, new UserRequest("pavan" , "pk@gmail.com", "12345", "9110793168", "user"));
        UserRequest request = mockUsersReq.get(1);
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(),"Invalid Data to Test");
        if (violations.isEmpty()) {
            when(userRepoMock.findByPhone("7036110229")).thenReturn(user);
            when(userRepoMock.save(user)).thenReturn(user);
            mockusersresponce.put(1, new UserResponse("pavan", "pk@gmail.com", "9110793168"));
            assertAll(
                    ()->assertThrows(UserAlreadyExistsException.class,()->userService.newUser(userRequest)),
                    ()->assertEquals(mockusersresponce.get(1), userService.newUser(mockUsersReq.get(1)),"Fields not matched"));
        }
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
                () -> assertTrue(()->userService.allUsers().get(0).getName().equals("John Doe"),()->"Users list altered"),
                () -> assertTrue(list instanceof List<UserResponse>)
        );
    }

    @Test
    @Order(5)
    @DisplayName("User by phone number test")
    void userByPhone() throws UserNotFoundException {
        when(userRepoMock.findByPhone("7036110229")).thenReturn(user);
        assertEquals(userResponse, userService.userByPhone("7036110229"));
    }


}