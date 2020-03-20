package com.springrestthymeleaf.excercise.repositories;

import com.springrestthymeleaf.excercise.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
