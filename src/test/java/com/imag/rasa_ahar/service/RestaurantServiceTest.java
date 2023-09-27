package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Restaurant;
import com.imag.rasa_ahar.repo.RestaurantRepo;
import com.imag.rasa_ahar.requestDto.RestaurantDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestaurantServiceTest {

    private RestaurantDto restaurantDto;
    private Restaurant restaurant;
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepo restaurantRepo;
    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setUp() {
        restaurantService = new RestaurantService(restaurantRepo, modelMapper);
        restaurantDto = new RestaurantDto("Zeshan", "jagadamba", "9232131239", "Visakhapatnam", "Andhra Pradesh", 530041);
        restaurant = new Restaurant(0, "Zeshan", "jagadamba", "9232131239", "Visakhapatnam", "Andhra Pradesh", 530041);
    }

    @AfterEach
    void tearDown() {
        restaurantService = null;
    }

    @Test
    void dtoToRestaurant() {
        assertEquals(restaurant, restaurantService.dtoToRestaurant(restaurantDto));
    }

    @Test
    void restaurantToDto() {
        assertEquals(restaurantDto, restaurantService.restaurantToDto(restaurant));
    }

    @Test
    void newRestaurant() {
        restaurantService.newRestaurant(restaurantDto);
        Mockito.verify(restaurantRepo, Mockito.times(1)).save(restaurant);
    }

    @Test
    void allRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant(1, "Spice Bistro", "12-1-7, Beach Road", "9876543210", "Visakhapatnam", "Andhra Pradesh", 530001));
        restaurantList.add(new Restaurant(2, "Coastal Delights", "2-3-45, Jagadamba Junction", "9876543221", "Visakhapatnam", "Andhra Pradesh", 530002));
        restaurantList.add(new Restaurant(3, "Sizzle Street", "8-9-10 Dwaraka Nagar", "9876543332", "Visakhapatnam", "Andhra Pradesh", 530003));
        restaurantList.add(new Restaurant(4, "Tandoori Express", "5-6-7 MVP Colony", "9876543443", "Visakhapatnam", "Andhra Pradesh", 530004));
        restaurantList.add(new Restaurant(5, "Green Leaf Cafe", "1-2-3 Asilmetta", "9876543554", "Visakhapatnam", "Andhra Pradesh", 530005));
        restaurantList.add(new Restaurant(6, "Tasty Treats", "123 Park Avenue", "9876543001", "Visakhapatnam", "Andhra Pradesh", 530101));
        restaurantList.add(new Restaurant(7, "Seafood Paradise", "45 Harbor Road", "9876543002", "Visakhapatnam", "Andhra Pradesh", 530102));
        restaurantList.add(new Restaurant(8, "Spicy Delights", "67 Spice Street", "9876543003", "Visakhapatnam", "Andhra Pradesh", 530103));

        when(restaurantRepo.findAll()).thenReturn(restaurantList);
        assertAll(
                () -> assertEquals(8, restaurantService.allRestaurants().size()),
                () -> assertEquals("9876543210", restaurantService.allRestaurants().get(0).getPhone()),
                ()-> assertTrue(restaurantService.allRestaurants().get(0) instanceof RestaurantDto),
                () -> Mockito.verify(restaurantRepo, Mockito.times(3)).findAll()
        );
    }

    @Test
    void getByPhone() {
        when(restaurantRepo.findByPhone("9232131239")).thenReturn(restaurant);
        assertEquals(restaurantDto,restaurantService.getByPhone("9232131239"));
    }

    @Test
    void updatePhone() {
        when(restaurantRepo.findByPhone("9232131239")).thenReturn(restaurant);
        assertEquals("9491265379",restaurantService.updatePhone("9232131239","9491265379").getPhone());
    }

}