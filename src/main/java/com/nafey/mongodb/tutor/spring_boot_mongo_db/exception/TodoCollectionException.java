package com.nafey.mongodb.tutor.spring_boot_mongo_db.exception;

import java.io.Serial;

public class TodoCollectionException extends Exception{

    @Serial
    private static final long serialVersionUID=1L;

    public TodoCollectionException(String message) {
        super(message);
    }
    public static String NotFoundException(String id){
        return "Todo with " + id + "not found";
    }

    public static String TodoAlreadyExists(){
        return "todo with given name already exists";
    }

}
