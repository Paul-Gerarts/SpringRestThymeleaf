package com.springrestthymeleaf.excercise.factories;

import com.springrestthymeleaf.excercise.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressFactory {

    public Address createAddress(String street, Integer number, String postBox, Integer postalCode, String city) {
        return Address.builder()
                .street(street)
                .number(number)
                .postBox(postBox)
                .zipCode(postalCode)
                .city(city)
                .build();
    }
}
