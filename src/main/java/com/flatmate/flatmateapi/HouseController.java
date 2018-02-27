package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.House;
import com.flatmate.flatmateregistry.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/houses", produces = "application/json")
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(final HouseService houseService) { this.houseService = houseService; }

    @ResponseBody
    @RequestMapping(value = "/{houseId}", method = RequestMethod.GET)
    public House getHouse(@PathVariable final Long houseId) { return houseService.getHouseById(houseId); }

    @RequestMapping(method = RequestMethod.GET)
    public List<House> getHouses() { return houseService.getHouses(); }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public House createHouse(@RequestBody House house) { return houseService.createHouse(house); }

    @RequestMapping(value = "/{houseId}", method = RequestMethod.DELETE)
    public void deleteHouse(@PathVariable final Long houseId) { houseService.deleteHouse(houseId); }

    @RequestMapping(value = "/{houseId}/users", method = RequestMethod.GET)
    public Set<Account> getUsersForHouse(@PathVariable final Long houseId) { return houseService.getUsersForHouse(houseId); }

    @RequestMapping(value = "/{houseId}/users/{userId}", method = RequestMethod.POST)
    public House addUserToHouse(@PathVariable final Long houseId, @PathVariable final Long userId) {
        return houseService.addUserToHouse(houseId, userId);
    }

    @RequestMapping(value = "/{houseId}/users/{userId}", method = RequestMethod.DELETE)
    public House removeUserFromHouse(@PathVariable final Long houseId, @PathVariable final Long userId) {
        return houseService.removeUserFromHouse(houseId, userId);
    }

}
