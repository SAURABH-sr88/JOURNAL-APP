package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.journalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    public JournalEntryRepository journalEntryRepository;
    @Autowired
    public UserService userService;


    public void saveEntry (journalEntry entry, String userName){
        User user = userService.findByName(userName);
        if (user == null) {
            throw new RuntimeException("User not found: " + userName);
        }

        journalEntry saved = journalEntryRepository.save(entry);
        if (user.getJournal_Entries() == null) {
            user.setJournal_Entries(new ArrayList<>());
        }
        user.getJournal_Entries().add(saved);
        userService.saveUser(user);
    }
    public void saveEntry (journalEntry entry){
        journalEntryRepository.save(entry);
    }
    public List<journalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<journalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }



    public void deleteById (ObjectId id, String username){
        User user = userService.findByName(username);
        boolean removed = user.getJournal_Entries().removeIf(x->x.getId().equals(id));
        if (removed) {
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
    }
}
