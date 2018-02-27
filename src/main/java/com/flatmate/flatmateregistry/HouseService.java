package com.flatmate.flatmateregistry;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class HouseService {

    private final HouseRepository houseRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public HouseService(final HouseRepository houseRepository, final AccountRepository accountRepository) {
        this.houseRepository = houseRepository;
        this.accountRepository = accountRepository;
    }

    public House getHouseById(final Long houseId) { return houseRepository.findOne(houseId); }

    public List<House> getHouses() {
        final List<House> houses = new ArrayList<>();
        houseRepository.findAll().forEach(houses::add);
        return houses;
    }

    public House createHouse(final House house) { return houseRepository.save(house); }

    public void deleteHouse(final Long houseId) {
        House houseToDelete = houseRepository.findOne(houseId);
        Set<Account> users = houseToDelete.getUsers();
        for (Account user : users) {
            user.setHouse(null);
        }
        houseRepository.delete(houseId);
    }

    public Set<Account> getUsersForHouse(final Long houseId) {
        House houseToReturn = houseRepository.findOne(houseId);
        return houseToReturn.getUsers();
    }

    public House addUserToHouse(final Long houseId, final Long userId) {
        House house = houseRepository.findOne(houseId);
        Account userToAdd = accountRepository.findOne(userId);
        house.addUser(userToAdd);
        userToAdd.setHouse(house);
        house = houseRepository.save(house);
        return house;
    }

    public House removeUserFromHouse(final Long houseId, final Long userId) {
        Account userToRemove = accountRepository.findOne(userId);
        House house = houseRepository.findOne(houseId);
        house.removeUser(userToRemove);
        return houseRepository.save(house);
    }

}
