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
        final House houseToDelete = houseRepository.findOne(houseId);
        final Set<Account> users = houseToDelete.getUsers();
        users.forEach(account -> account.setHouse(null));
        houseRepository.delete(houseId);
    }

    public Set<Account> getUsersForHouse(final Long houseId) {
        final House houseToReturn = houseRepository.findOne(houseId);
        return houseToReturn.getUsers();
    }

    public House addUserToHouse(final Long houseId, final Long userId) {
        final House temporaryHouse = houseRepository.findOne(houseId);
        final Account userToAdd = accountRepository.findOne(userId);
        temporaryHouse.addUser(userToAdd);
        userToAdd.setHouse(temporaryHouse);
        final House updatedHouse = houseRepository.save(temporaryHouse);
        return updatedHouse;
    }

    public House removeUserFromHouse(final Long houseId, final Long userId) {
        final Account userToRemove = accountRepository.findOne(userId);
        final House house = houseRepository.findOne(houseId);
        house.removeUser(userToRemove);
        return houseRepository.save(house);
    }

}
