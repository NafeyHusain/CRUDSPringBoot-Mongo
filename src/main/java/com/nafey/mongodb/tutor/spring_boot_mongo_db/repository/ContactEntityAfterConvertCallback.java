package com.nafey.mongodb.tutor.spring_boot_mongo_db.repository;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Order(1)
@Component
@Slf4j
public class ContactEntityAfterConvertCallback extends AbstractMongoEventListener<Contact> {

    @Override
    public void onAfterLoad(AfterLoadEvent<Contact> event) {
        log.info(Objects.requireNonNull(event.getDocument()).toJson());
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Contact> event) {
        log.info(Objects.requireNonNull(event.getDocument()).toJson());
    }

    @Override
    public void onAfterConvert(AfterConvertEvent<Contact> event) {
        log.info(Objects.requireNonNull(event.getDocument()).toJson());
    }
}