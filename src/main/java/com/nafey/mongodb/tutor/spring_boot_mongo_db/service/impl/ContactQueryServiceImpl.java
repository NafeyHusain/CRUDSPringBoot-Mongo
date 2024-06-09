package com.nafey.mongodb.tutor.spring_boot_mongo_db.service.impl;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.Contact;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.repository.ContactQueryRepository;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.service.ContactQueryService;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.web.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactQueryServiceImpl implements ContactQueryService {

    @Autowired
    ContactQueryRepository contactQueryRepository;

    @Override
    public List<ContactDto> findByCity(String city) {
        List<Contact> contacts = contactQueryRepository.findByCity(city);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByMobile(String mobile) {
        List<Contact> contacts = contactQueryRepository.findByMobile(mobile);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByState(String state) {
        List<Contact> contacts = contactQueryRepository.findByState(state);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByFirstNameAndCity(String name, String state) {
        List<Contact> contacts = contactQueryRepository.findByFirstNameAndCity(name, state);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByKnownLanguage(String knownLanguage) {
        List<Contact> contacts = contactQueryRepository.findByKnownLanguage(knownLanguage);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByKnownLanguages(String lang1, String lang2) {
        List<Contact> contacts = contactQueryRepository.findByKnownLanguages(lang1, lang2);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByHeight(int weight) {
        List<Contact> contacts = contactQueryRepository.findByHeight(weight);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findBySeconaryMobile(String seondaryMobile) {
        List<Contact> contacts = contactQueryRepository.findBySecondaryMobile(seondaryMobile);
        if (null == contacts || contacts.size() == 0)
            return null;
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

}
