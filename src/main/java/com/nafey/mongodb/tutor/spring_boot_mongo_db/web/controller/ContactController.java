package com.nafey.mongodb.tutor.spring_boot_mongo_db.web.controller;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.service.ContactService;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.web.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/contactservice/v1")
public class ContactController {

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/contacts", method = RequestMethod.POST, produces = {"application/JSON"}, consumes = {"application/JSON"})
    public ResponseEntity<?> addContact(@RequestBody ContactDto contactDto) {
        return new ResponseEntity<>(contactService.save(contactDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/contacts/{emailId}", method = RequestMethod.GET, produces = {"application/JSON"})
    public ResponseEntity<?> getContactById(@PathVariable String emailId) {
        ContactDto contactDto = contactService.findById(emailId);
        if (contactDto == null)
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(contactDto, HttpStatus.OK);
    }

    @RequestMapping(value="/contacts/name/firstName/{firstName}",method=RequestMethod.GET,produces = {"application/JSON"})
    public ResponseEntity<?> getByFirstName(@PathVariable String firstName){
        List<ContactDto> contactDtos=null;
//        contactDtos=contactService.findByFirstName(firstName);

//        contactDtos=contactService.findByNameStartingWith(firstName);
        contactDtos = contactService.findByNameEndingWith(firstName);
        contactDtos = contactService.findByFullNameContaining(firstName);
        if(contactDtos==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contactDtos,HttpStatus.OK);
    }

}
