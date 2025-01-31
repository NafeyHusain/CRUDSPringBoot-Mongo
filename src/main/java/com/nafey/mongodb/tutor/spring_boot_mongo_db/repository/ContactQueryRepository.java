package com.nafey.mongodb.tutor.spring_boot_mongo_db.repository;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.awt.print.Book;
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

    @Query("{author: ?0, cost: ?1}")                            // SQL Equivalent : SELECT * FROM BOOK where author = ? and cost=?
        //@Query("{$and :[{author: ?0},{cost: ?1}] }")
    List<Contact> getBooksByAuthorAndCost(String author, Double cost);


    @Query("{$or :[{author: ?0},{name: ?1}]}")            //SQL Equivalent : select count(*) from book where author=? or name=?
    List<Contact> getBooksByAuthorOrName(String author, String name);


    @Query(value ="{author: ?0}", count=true)           //SQL Equivalent : select count(*) from book where author=?
    Integer getBooksCountByAuthor(String author);

    //Sorting
    @Query(value = "{author:?0}", sort= "{name:1}") //ASC
    //@Query(value = "{author=?0}", sort= "{name:-1}") //DESC
    List<Contact> getBooksByAuthorSortByName(String author);

    //------------------- @Query with Projection ---------------------------------------
    @Query(value= "{pages: ?0}", fields="{name:1, author:1}")   // only data of name & author properties will be displayed
    //@Query(value= "{pages: ?0}", fields="{name:1, author:1, cost:1, pages:1}") // will display all properties data
    List<Book> getBookNameAndAuthorByPages(Integer pages);


    @Query(value= "{author : ?0}")           // SQL Equivalent : SELECT * FROM BOOK select * from books where author=?
    List<Book> getAllBooksByAuthor(String author);

    //------------------MongoDB Regular Expressions--------------------------------------
    @Query("{ author : { $regex : ?0 } }")
    List<Book> getBooksByAuthorRegEx(String author);
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
