package com.nafey.mongodb.tutor.spring_boot_mongo_db.repository;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact, String> {

    List<Contact> findByFirstNameLike(String firstName);
    List<Contact> findByLastNameLike(String lastName);

    List<Contact> findByFullNameLike(String name);
    List<Contact> findByFullNameStartingWith(String name);
    List<Contact> findByFullNameEndingWith(String name);
    List<Contact> findByFullNameContaining(String name);
    List<Contact> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);

}
