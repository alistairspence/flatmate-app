package com.flatmate.flatmateregistry;

import com.flatmate.flatmatepersistence.House;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends CrudRepository<House, Long> {
}
