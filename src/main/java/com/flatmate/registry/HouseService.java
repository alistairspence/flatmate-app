package com.flatmate.registry;

import com.flatmate.persistence.Account;
import com.flatmate.persistence.House;

import java.util.List;
import java.util.Set;

public interface HouseService {

    House getHouseById(final Long houseId);

    List<House> getAllHouses();

    House createHouse(final House house);

    House updateHouse(final Long houseId, final House house);

    void deleteHouse(final Long houseId);

    Set<Account> getAccountsForHouse(final Long houseId);

    House addAccountToHouse(final Long houseId, final Long accountId);

    House removeAccountFromHouse(final Long houseId, final Long accountId);

}
