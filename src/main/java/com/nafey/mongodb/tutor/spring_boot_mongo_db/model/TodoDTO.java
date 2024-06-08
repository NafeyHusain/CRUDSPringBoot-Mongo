package com.nafey.mongodb.tutor.spring_boot_mongo_db.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="todos")
public class TodoDTO {

    @Id
    private String id;

    @NotNull(message="todo cannot be null")
    private String todo;

    @NotNull(message="description cannot be null")
    private String description;
    @NotNull(message="completed cannot be null")
    private Boolean completed;

    @Field("createdAt")
    private Date createdAt;
    private Date updatedAt;

    @Id
    private String emailId;
    @Field("firstName")
    private String firstName;
    @Field("middleName")
    private String middleName;
    @Field("lastName")
    private String lastName;
    @Field
    private String fullName;
    @Field("gender")
    private Gender gender;
    @Field("salutation")
    private Salutation salutation;
    @Field("age")
    private short age;
    @Field("address")
    private List<Address> addressList;
    @Field("phone")
    private Phone phone;
    @Field
    private Instant dateOfBirth;
    @Field("friends")
    private List<String> friends;
    @Field("verified")
    private boolean verified;
    @Field("knownLanguages")
    private List<String> knownLanguages;
    @Field("bodyAttrs")
    private List<Integer> bodyAttrs;
    @Field("createdDate")
    private Instant createdDate;
    @Field("updatedDate")
    private Instant updatedDate;
}
