package net.croods.journalApp.entity;

import java.util.ArrayList;
import java.util.List;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import org.springframework.data.mongodb.core.index.Indexed;
import lombok.Data;

@Document(collection = "users")
@Data
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
   
    @DBRef
   private List <JournalEntry> journalEntries = new ArrayList<>();


}
