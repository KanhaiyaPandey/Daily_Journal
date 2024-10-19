package net.croods.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import net.croods.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByUserName(String username);
}
