package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.House;
import com.flatmate.flatmateregistry.HouseServiceImpl;
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
    private HouseController underTest;

    @Mock
    private HouseServiceImpl houseService;

    @Mock
    private House house;

    @Mock
    private Account account;

    @Test
    public void shouldGetHouse() {
        when(houseService.getHouseById(house.getId())).thenReturn(house);
        Assert.assertEquals(house, underTest.getHouseById(house.getId()));
    }

    @Test
    public void shouldGetAllHouses() {
        List<House> houses = new ArrayList<>();
        houses.add(house);
        when(houseService.getAllHouses()).thenReturn(houses);
        Assert.assertEquals(houses, underTest.getAllHouses());
    }

    @Test
    public void shouldCreateValidHouse() {
        when(houseService.createHouse(house)).thenReturn(house);
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
        when(houseService.addAccountToHouse(house.getId(), account.getId())).thenReturn(house);
        Assert.assertEquals(house, underTest.addAccountToHouse(house.getId(), account.getId()));
    }

    @Test
    public void shouldRemoveUserFromHouse() {
        when(houseService.removeAccountFromHouse(house.getId(), account.getId())).thenReturn(house);
        Assert.assertEquals(house, underTest.removeAccountFromHouse(house.getId(), account.getId()));
    }

}
