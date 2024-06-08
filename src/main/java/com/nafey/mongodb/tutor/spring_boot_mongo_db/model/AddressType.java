package com.nafey.mongodb.tutor.spring_boot_mongo_db.model;

import lombok.Getter;

@Getter
public enum AddressType {

    PERMANENT("permanent"),
    RESIDENT("resident");

    private final String addressType;

    AddressType(String addressTpe) {
        this.addressType = addressTpe;
    }
}
