package net.croods.journalApp.controller;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.croods.journalApp.entity.JournalEntry;
import net.croods.journalApp.entity.User;
import net.croods.journalApp.service.JournalEntryService;
import net.croods.journalApp.service.UserService;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

     @Autowired
     private UserService userService;


    // if I do a get req for /journal endpoint i will hit this class
    @GetMapping("{username}")
    public ResponseEntity<?> getAllEntriesByUser(@PathVariable String username){
       User user = userService.findByUserName(username);
       if (user == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
      List<JournalEntry> all = user.getJournalEntries();
      if (all != null && !all.isEmpty()) {
        return new ResponseEntity<>(all, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

     // if I do a post req for /journal endpoint i will hit this class
    @PostMapping("{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username){
      try {
        journalEntryService.saveEntry(myEntry, username);
        String msg = "entry created";
         return new ResponseEntity<>(msg, HttpStatus.CREATED);
        
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

    }

    // get journal entry by id

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
     Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
    if (journalEntry.isPresent()) {
      return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // delete user's journal entry by id and username

    @DeleteMapping("/id/{username}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String username){
      journalEntryService.deleteById(myId, username);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/id/{username}/{myId}")
    public JournalEntry createEntry(@RequestBody JournalEntry newEntry, @PathVariable ObjectId myId, @PathVariable String username){
      JournalEntry old = journalEntryService.findById(myId).orElse(null);
      if(old != null){
        old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle() );
        old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
      }
      journalEntryService.saveEntry(old);
      return old;
    }
    
}
