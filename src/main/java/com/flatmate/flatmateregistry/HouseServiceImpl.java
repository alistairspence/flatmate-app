package com.flatmate.flatmateregistry;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public HouseServiceImpl(final HouseRepository houseRepository, final AccountRepository accountRepository) {
        this.houseRepository = houseRepository;
        this.accountRepository = accountRepository;
    }

    public House getHouseById(final Long houseId) { return houseRepository.findOne(houseId); }

    public List<House> getAllHouses() {
        final List<House> houses = new ArrayList<>();
        houseRepository.findAll().forEach(houses::add);
        return houses;
    }

    public House createHouse(final House house) { return houseRepository.save(house); }

    public House updateHouse(final Long houseId, final House house) {
        return null;
    }

    public void deleteHouse(final Long houseId) {
        final House houseToDelete = houseRepository.findOne(houseId);
        final Set<Account> users = houseToDelete.getAccounts();
        users.forEach(account -> account.setHouse(null));
        houseRepository.delete(houseId);
    }

    public Set<Account> getAccountsForHouse(final Long houseId) {
        return houseRepository.findOne(houseId).getAccounts();
    }

    public House addAccountToHouse(final Long houseId, final Long accountId) {
        final House temporaryHouse = houseRepository.findOne(houseId);
        final Account userToAdd = accountRepository.findOne(accountId);
        temporaryHouse.addAccount(userToAdd);
        userToAdd.setHouse(temporaryHouse);
        return houseRepository.save(temporaryHouse);
    }

    public House removeAccountFromHouse(final Long houseId, final Long accountId) {
        final Account userToRemove = accountRepository.findOne(accountId);
        final House house = houseRepository.findOne(houseId);
        house.removeAccount(userToRemove);
        return houseRepository.save(house);
    }

}
