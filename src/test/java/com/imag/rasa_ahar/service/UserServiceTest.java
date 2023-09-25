package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.exceptions.InValidMobileNumber;
import com.imag.rasa_ahar.repo.UserRepo;
import com.imag.rasa_ahar.requestDto.UserRequest;
import com.imag.rasa_ahar.responseDto.UserResponse;
import com.imag.rasa_ahar.validation.Validation;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode =  DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    UserRepo userRepo;
    @Mock
    private static UserService userService;
    private static UserRequest userRequest;
    private static User user;
    private static UserResponse userResponse;
    private  UserRepo userRepoMock;
    private Validation validation;
    private Map<Integer,UserRequest> mockusers = new HashMap<>();
    private Map<Integer,UserResponse> mockusersresponce = new HashMap<>();
    @BeforeEach
    public void setUp(){
        userRepoMock = Mockito.mock(UserRepo.class);
        validation = Mockito.mock(Validation.class);
        this.userService=new UserService(userRepoMock,validation);
        userRequest = new UserRequest("pavan", "pk@gmail.com", "12345", "7036110229", "user");
        user = new User(0, "pavan", "pk@gmail.com", "12345", "7036110229", "user");
        userResponse = new UserResponse("pavan", "pk@gmail.com",  "7036110229");
    }

    @Test
    @Order(1)
    @DisplayName("Mapper Method Testing Dto To User")
    void testDtoToUser() {
        User result = userService.dtoToUser(userRequest);
        assertEquals(user, result,"mapping not working");
    }

    @Test
    void userToDtoResponse() {
        UserResponse result=userService.userToDtoResponse(user);
        assertEquals(userResponse,result,"mapping not working");
    }

    @Test
    @Disabled
    void newUser(){
            mockusers.put(1,new UserRequest("pavan", "pk@gmail.com", "12345", "9110793168", "user"));
            when(userRepoMock.findByPhone("7036110229")).thenReturn(user);
            when(userRepoMock.save(user)).thenReturn(user);
            when(validation.verifyMobile("9110793168")).thenReturn(true);
            when(validation.verifyEmail("pk@gmail.com")).thenReturn(true);
            mockusersresponce.put(1,new UserResponse("pavan", "pk@gmail.com", "9110793168"));
        assertEquals(mockusersresponce.get(1),userService.newUser(mockusers.get(1)));
    }

    @Test
    void allUsers() {
    }

    @Test
    void userByPhone() {
    }

    @Test
    void orderHistory() {
    }
}