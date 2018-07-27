package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.House;
import com.flatmate.flatmateregistry.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/houses", produces = "application/json")
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(final HouseService houseService) { this.houseService = houseService; }

    @ResponseBody
    @RequestMapping(value = "/{houseId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public House getHouseById(@PathVariable final Long houseId) { return houseService.getHouseById(houseId); }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<House> getAllHouses() { return houseService.getAllHouses(); }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public House createHouse(@RequestBody final House house) { return houseService.createHouse(house); }

    @ResponseBody
    @RequestMapping(value = "/{houseId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public House updateHouse(@PathVariable final Long houseId, final House house) {
        return houseService.updateHouse(houseId, house);
    }

    @RequestMapping(value = "/{houseId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHouse(@PathVariable final Long houseId) { houseService.deleteHouse(houseId); }

    @RequestMapping(value = "/{houseId}/users", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Set<Account> getAccountsForHouse(@PathVariable final Long houseId) { return houseService.getAccountsForHouse(houseId); }

    @RequestMapping(value = "/{houseId}/users/{accountId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public House addAccountToHouse(@PathVariable final Long houseId, @PathVariable final Long accountId) {
        return houseService.addAccountToHouse(houseId, accountId);
    }

    @RequestMapping(value = "/{houseId}/users/{accountId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public House removeAccountFromHouse(@PathVariable final Long houseId, @PathVariable final Long accountId) {
        return houseService.removeAccountFromHouse(houseId, accountId);
    }

}
