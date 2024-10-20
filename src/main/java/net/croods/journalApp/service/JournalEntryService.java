package net.croods.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;

// import org.apache.el.stream.Optional;
// import org.apache.el.stream.Optional;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Component;

import net.croods.journalApp.entity.JournalEntry;
import net.croods.journalApp.entity.User;
import net.croods.journalApp.repository.JournalEntryRepository;

// all the business logic go through  service
@Component
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String username){
        User user = userService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved =  journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
      journalEntryRepository.deleteById(id);
    }
}

