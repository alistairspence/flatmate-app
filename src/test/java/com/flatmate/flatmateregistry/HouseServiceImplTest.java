package com.flatmate.flatmateregistry;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.House;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class HouseServiceImplTest {

    @InjectMocks
    private HouseServiceImpl underTest;

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private House house;

    @Mock
    private Account account;

    @Test
    public void shouldGetHouseById() {
        when(houseRepository.findOne(house.getId())).thenReturn(house);
        Assert.assertEquals(house, underTest.getHouseById(house.getId()));
    }

    @Test
    public void shouldGetAllHouses() {
        List<House> houses = new ArrayList<>();
        houses.add(house);
        when(houseRepository.findAll()).thenReturn(houses);
        Assert.assertEquals(houses, underTest.getAllHouses());
    }

    @Test
    public void shouldCreateValidHouse() {
        when(houseRepository.save(house)).thenReturn(house);
        Assert.assertEquals(house, underTest.createHouse(house));
    }

    @Test
    public void shouldNotCreateInvalidHouse() {
    }

    @Test
    public void shouldDeleteValidHouse() {
    }

    @Test
    public void shouldNotDeleteInvalidHouse() {
    }

    @Test
    public void shouldAddUserToHouse() {
    }

    @Test
    public void shouldRemoveUserFromHouse() {
    }

}
