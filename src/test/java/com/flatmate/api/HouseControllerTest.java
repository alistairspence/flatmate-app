package com.flatmate.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flatmate.errorhandling.ErrorHandler;
import com.flatmate.registry.HouseServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class HouseControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private HouseController underTest;

    @Mock
    private HouseServiceImpl houseService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTest)
                .setControllerAdvice(new ErrorHandler())
                .build();
    }

    @Test
    public void onGetHouseShouldReturnFoundHouse() {
    }

    @Test
    public void onGetHouseShouldReturnNotFoundIfInvalidId() {
    }

    @Test
    public void onGetAllHousesShouldReturnFoundHouses() {
    }

    @Test
    public void onGetAllHousesShouldReturnEmptyListIfNoHousesExist() {
    }

    @Test
    public void onUpdateHouseShouldUpdateIfValidHouse() {
    }

    @Test
    public void onUpdateHouseShouldReturnNotFoundIfInvalidHouseId() {
    }

    @Test
    public void onUpdateHouseShouldReturnBadRequestIfInvalidHouseBody() {
    }

    @Test
    public void onDeleteHouseShouldDeleteIfValidHouse() {
    }

    @Test
    public void onDeleteHouseShouldReturnNotFoundIfInvalidHouseId() {
    }

    @Test
    public void onGetAccountsForHouseShouldReturnFoundAccounts() {
    }

    @Test
    public void onGetAccountsForHouseShouldReturnEmptyListIfNoAccountsExist() {
    }

    @Test
    public void onGetAccountsForHouseShouldReturnNotFoundIfInvalidHouseId() {
    }

    @Test
    public void onAddAccountToHouseShouldBeSuccessfulIfValidHouseAndAccountIds() {
    }

    @Test
    public void onAddAccountToHouseShouldReturnNotFoundIfInvalidHouseId() {
    }

    @Test
    public void onAddAccountToHouseShouldReturnNotFoundIfInvalidAccountId() {
    }

    @Test
    public void onRemoveAccountFromHouseShouldBeSuccessfulIfValidHouseAndAccountIds() {
    }

    @Test
    public void onRemoveAccountFromHouseShouldReturnNotFoundIfInvalidHouseId() {
    }

    @Test
    public void onRemoveAccountFromHouseShouldReturnNotFoundIfInvalidAccountId() {
    }

}
