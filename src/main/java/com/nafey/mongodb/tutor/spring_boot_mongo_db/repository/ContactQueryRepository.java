package com.nafey.mongodb.tutor.spring_boot_mongo_db.repository;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactQueryRepository extends MongoRepository<Contact, String> {
    @Query("{'address.city':{$regex: '^?0'}}")
    List<Contact> findByCity(String city);

    @Query("{emailId:?0}")
    Optional<Contact> getContactById(String emailId);

    @Query("{pages : {$lt: ?0}}")                                 // SQL Equivalent : SELECT * FROM BOOK where pages<?
        //@Query("{ pages : { $gte: ?0 } }")                        // SQL Equivalent : SELECT * FROM BOOK where pages>=?
        //@Query("{ pages : ?0 }")                                      // SQL Equivalent : SELECT * FROM BOOK where pages=?
    List<Contact> getBooksByPages(Integer pages);

    @Query(value = "{'phone.mobile':{$regex: '^?0'}}", sort = "{ age: -1 }")
    List<Contact> findByMobile(String mobile);

    @Query(value = "{'address.state': {$regex: '^?0'}}", sort = "{firstName: 1}")
    List<Contact> findByState(String state);

    @Query(value = "{$and:[{'firstName':{$regex:'^?0'}},{'address.city':{$regex: '^?1'}}]}")
    List<Contact> findByFirstNameAndCity(String name, String city);

    @Query(value = "{'knownLanguages':'?0'}")
    List<Contact> findByKnownLanguage(String knownLanguage);

    @Query(value = "{'knownLanguages':{ $all:['?0','?1']}}")
    List<Contact> findByKnownLanguages(String lang1, String lang2);

    @Query(value = "{bodyAttrs: {$gt: ?0}}")
    List<Contact> findByHeight(int height);

    @Query(value = "{'phone.secondaryMobile': null}")
    List<Contact> findBySecondaryMobile(String secondaryMobile);
}
