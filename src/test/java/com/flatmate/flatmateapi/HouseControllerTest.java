package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.House;
import com.flatmate.flatmateregistry.HouseService;
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
public class HouseControllerTest {

    @InjectMocks
    private HouseController houseController;

    @Mock
    private HouseService houseService;

    @Mock
    private House house;

    @Mock
    private Account account;

    @Test
    public void shouldGetHouse() {
        when(houseService.getHouseById(house.getId())).thenReturn(house);
        Assert.assertEquals(house, houseController.getHouse(house.getId()));
    }

    @Test
    public void shouldGetAllHouses() {
        List<House> houses = new ArrayList<>();
        houses.add(house);
        when(houseService.getHouses()).thenReturn(houses);
        Assert.assertEquals(houses, houseController.getHouses());
    }

    @Test
    public void shouldCreateValidHouse() {
        when(houseService.createHouse(house)).thenReturn(house);
        Assert.assertEquals(house, houseController.createHouse(house));
    }

    // TODO(alistair): how should these be tested?

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
        when(houseService.addUserToHouse(house.getId(), account.getId())).thenReturn(house);
        Assert.assertEquals(house, houseController.addUserToHouse(house.getId(), account.getId()));
    }

    @Test
    public void shouldRemoveUserFromHouse() {
        when(houseService.removeUserFromHouse(house.getId(), account.getId())).thenReturn(house);
        Assert.assertEquals(house, houseController.removeUserFromHouse(house.getId(), account.getId()));
    }

}
