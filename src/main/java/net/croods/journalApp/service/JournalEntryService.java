package net.croods.journalApp.service;

import java.util.List;

// import org.apache.el.stream.Optional;
// import org.apache.el.stream.Optional;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Component;

import net.croods.journalApp.entity.JournalEntry;
import net.croods.journalApp.repository.JournalEntryRepository;

// all the business logic go through  service
@Component
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
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

