package com.example.caloriecalculator.service;

import com.example.caloriecalculator.dto.FoodDTO;
import com.example.caloriecalculator.model.Food;
import com.example.caloriecalculator.repositories.FoodRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FoodServiceUnitTest {

    @Autowired
    private FoodService foodService;

    @MockBean
    private FoodRepository foodRepository;

    private List<Food> foods;
    @BeforeEach
    void setUp() {
        this.foods = new ArrayList<>();
        Food burger = new Food(1, "Hamburger", 321.0, 100, 23.0, 13.1, 33.4);
        Food pizza = new Food(1, "Pizza", 233.0, 100, 33.0, 23.1, 23.4);
        Food BBQ = new Food(1, "BBQ", 299.0, 100, 43.6, 33.1, 23.4);
        foods.add(burger);
        foods.add(pizza);
        foods.add(BBQ);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        when(foodRepository.findAll()).thenReturn(foods);
        List<Food> foodList = foodService.findAll();
        assertEquals(3, foodList.size());
        verify(foodRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(foodRepository.findById(1)).thenReturn(Optional.ofNullable(foods.get(1)));
        Food food = foodService.findById(1);
        assertEquals("Pizza", food.getName());
        verify(foodRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_whenNotExist_thanException() {
        assertThrows(NoSuchElementException.class, ()-> foodService.findById(4));
        verify(foodRepository, times(1)).findById(4);
    }

    @Test
    void save() {
        Food cheese = new Food(null, "Cheese", 143.1, 100, 13.1, 15.0, 29.0);
        when(foodRepository.save(cheese)).thenReturn(cheese);
        Food saved = foodService.save(new FoodDTO("Cheese", 143.1, 100, 13.1, 15.0, 29.0));
        assertEquals(143.1, saved.getCalories());
        verify(foodRepository, times(1)).save(cheese);
    }

    @Test
    void updateFood() {
        Food cheese = new Food(4, "Cheese", 143.1, 100, 13.1, 15.0, 29.0);
        when(foodRepository.save(cheese)).thenReturn(cheese);
        Food saved = foodService.updateFood(new FoodDTO("Cheese", 143.1, 100, 13.1, 15.0, 29.0), 4);
        verify(foodRepository, times(1)).save(cheese);
    }

    @Test
    void delete() {
        foodService.delete(1);
        verify(foodRepository, times(1)).deleteById(1);
    }
}