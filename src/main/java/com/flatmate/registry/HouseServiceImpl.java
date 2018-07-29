package com.flatmate.registry;

import com.flatmate.persistence.Account;
import com.flatmate.persistence.House;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return Lists.newArrayList(houseRepository.findAll());
    }

    public House createHouse(final House house) { return houseRepository.save(house); }

    public House updateHouse(final Long houseId, final House house) {
        house.setId(houseId);
        return houseRepository.save(house);
    }

    public void deleteHouse(final Long houseId) {
        houseRepository.findOne(houseId).getAccounts().forEach(account -> account.setHouse(null));
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
