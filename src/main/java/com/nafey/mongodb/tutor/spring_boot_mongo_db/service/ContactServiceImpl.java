package com.nafey.mongodb.tutor.spring_boot_mongo_db.service;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.Contact;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.repository.ContactRepository;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.web.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    ContactRepository contactRepository;

    @Override
    public ContactDto save(ContactDto contactDto) {
        return new ContactDto(contactRepository.save(new Contact(contactDto)));
    }

    @Override
    public ContactDto findById(String emailId) {
        Optional<Contact> contact = contactRepository.findById(emailId);
        if (contact.isEmpty())
            return null;
        return new ContactDto(contact.get());
    }

    @Override
    public List<ContactDto> findByFullNameContaining(String name) {
        List<Contact> contacts = contactRepository.findByFullNameContaining(name);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByFirstName(String firstName) {
        List<Contact> contacts=contactRepository.findByFirstNameLike(firstName);
        if (contacts.isEmpty())
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByName(String name) {
        List<Contact> contacts = contactRepository.findByFullNameLike(name);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByNameStartingWith(String name) {
        List<Contact> contacts = contactRepository.findByFullNameStartingWith(name);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByNameEndingWith(String name) {
        List<Contact> contacts = contactRepository.findByFullNameEndingWith(name);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());

    }

    @Override
    public List<ContactDto> findByFirstNameLikeOrLastNameLike(String name) {
        return null;
    }

    @Override
    public List<ContactDto> findByGender(String gender) {
        return null;
    }

    @Override
    public List<ContactDto> findByAgeGreaterThan(int age) {
        return null;
    }

    @Override
    public List<ContactDto> findByAgeLessThan(int age) {
        return null;
    }

    @Override
    public List<ContactDto> findByAgeBetween(int from, int to) {
        return null;
    }

    @Override
    public List<ContactDto> findByVerified(boolean verified) {
        return null;
    }

    @Override
    public List<ContactDto> findByDateOfBirthAfter(Instant date) {
        return null;
    }

    @Override
    public List<ContactDto> findByDateOfBirthBefore(Instant date) {
        return null;
    }

    @Override
    public List<ContactDto> findByDateOfBirthBeforeSortByDateOfBirthDesc(Instant date) {
        return null;
    }


}
