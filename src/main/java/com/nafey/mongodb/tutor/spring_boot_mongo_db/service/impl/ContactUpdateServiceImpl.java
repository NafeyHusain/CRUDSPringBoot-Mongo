package com.nafey.mongodb.tutor.spring_boot_mongo_db.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.Contact;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.repository.ContactRepository;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.service.ContactUpdateService;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.web.dto.ContactDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class ContactUpdateServiceImpl implements ContactUpdateService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public long updateFirstName(String email, String firstName) {
        Optional<Contact> contact = contactRepository.findById(email);
        if (!contact.isPresent())
            return -1;

        Contact contactToUpdate = contact.get();
        contactToUpdate.setFirstName(firstName);
        String fullName = firstName + (contactToUpdate.getMiddleName() == null
                ? StringUtils.EMPTY : " " + contactToUpdate.getMiddleName())
                + (contactToUpdate.getLastName() == null ?
                StringUtils.EMPTY : " " + contactToUpdate.getLastName());
        contactToUpdate.setFullName(fullName);

        Contact result = contactRepository.save(contactToUpdate);
        return 1;
    }

    @Override
    public long updateFirstNameMongoTemplate(String email, String firstName) {
        Contact contact =
                mongoTemplate.findOne(new Query(where("_id").is(email)), Contact.class);

        assert contact != null;
        String fullName = firstName + (contact.getMiddleName() == null
                ? StringUtils.EMPTY : " " + contact.getMiddleName())
                + (contact.getLastName() == null ?
                StringUtils.EMPTY : " " + contact.getLastName());
        contact.setFullName(fullName);

        Query query = new Query(where("_id").is(email));
        Update contactToUpdate = new Update();
        contactToUpdate.set("firstName", firstName);
        contactToUpdate.set("fullName", fullName);
        contactToUpdate.currentTimestamp("updatedDate");
        UpdateResult updateResult =
                mongoTemplate.updateFirst(query, contactToUpdate, Contact.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateVerifiedFlag(String email, boolean verifiedFlag) {
        Query query = new Query(where("_id").is(email));
        Update contactToUpdate = Update.update("verified", verifiedFlag);
        contactToUpdate.currentDate("updatedDate");
        UpdateResult updateResult =
                mongoTemplate.updateFirst(query, contactToUpdate, Contact.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updatePhone(String email, String phone) {
        Query query = new Query(where("_id").is(email));
        Update contactToUpdate = Update.update("phone.mobile", phone);
        contactToUpdate.currentDate("updatedDate");
        UpdateResult updateResult =
                mongoTemplate.updateFirst(query, contactToUpdate, Contact.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateLanguage(String email, String language) {
        Query query = new Query(where("_id").is(email));
        Update contactToUpdate = new Update();
        contactToUpdate.push("knownLanguages", language);
        UpdateResult updateResult =
                mongoTemplate.updateFirst(query, contactToUpdate, Contact.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateLanguages(String email, List<String> languages) {
        Query query = new Query(where("_id").is(email));
        Update contactToUpdate = new Update();
        contactToUpdate.currentDate("updatedDate");
        contactToUpdate.push("knownLanguages").each(languages.toArray());
        UpdateResult updateResult =
                mongoTemplate.updateFirst(query, contactToUpdate, Contact.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateAge(String email, int age) {
        Query query = new Query(where("_id").is(email));
        Update contactToUpdate = new Update();
        contactToUpdate.currentDate("updatedDate");
        contactToUpdate.inc("age", age);
        UpdateResult updateResult =
                mongoTemplate.updateFirst(query, contactToUpdate, Contact.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateLanguageToSet(String email, String language) {
        Query query = new Query(where("_id").is(email));
        Update contactToUpdate = new Update();
        contactToUpdate.currentDate("updatedDate");
        contactToUpdate.addToSet("knownLanguages", language);
        UpdateResult updateResult =
                mongoTemplate.updateFirst(query, contactToUpdate, Contact.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateVerifiedFlagByState(String state, boolean verifiedFlag) {
        Query query=new Query(where("address.state").is(state));
        Update contactToUpdate = new Update();
        contactToUpdate.currentDate("updatedDate");
        contactToUpdate.set("verified", verifiedFlag);
        UpdateResult updateResult =
                mongoTemplate.updateMulti(query, contactToUpdate,
                        Contact.class, "contacts");
        return updateResult.getModifiedCount();
    }

    @Override
    public long upsert(ContactDto contactDto) {
        return 0;
    }

}
