package com.nafey.mongodb.tutor.spring_boot_mongo_db.service;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.web.dto.ContactDto;

import java.time.Instant;
import java.util.List;

public interface ContactService {
    ContactDto save(ContactDto contactDto);
    ContactDto findById(String emailId);

    // Filter by name - first name ,Full Name
    List<ContactDto> findByFullNameContaining(String name);
    List<ContactDto> findByFirstName(String firstName);
    List<ContactDto> findByName(String name);
    List<ContactDto> findByNameStartingWith(String name);
    List<ContactDto> findByNameEndingWith(String name);
    List<ContactDto> findByFirstNameLikeOrLastNameLike(String name);

    //Filter By Gender - Enum Based (Internally String)
    List<ContactDto> findByGender(String gender);

    /*
        Filter by Age - Integer
     */

    List<ContactDto> findByAgeGreaterThan(int age);
    List<ContactDto> findByAgeLessThan(int age);
    List<ContactDto> findByAgeBetween(int from,int to);

    /*
        Filter by verified - Boolean filter
     */
    List<ContactDto> findByVerified(boolean verified);

    /*
        Filter by Date of Birth - Datetime based filter
     */
    List<ContactDto> findByDateOfBirthAfter(Instant date);
    List<ContactDto> findByDateOfBirthBefore(Instant date);
    List<ContactDto> findByDateOfBirthBeforeSortByDateOfBirthDesc(Instant date);


}
