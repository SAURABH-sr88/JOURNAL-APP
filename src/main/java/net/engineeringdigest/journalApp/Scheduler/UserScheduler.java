package net.engineeringdigest.journalApp.Scheduler;

import net.engineeringdigest.journalApp.Enum.Sentiment;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.journalEntry;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService ;

    @Autowired
    private UserRepositoryImpl userRepository ;

    @Autowired
    private AppCache appCache ;

    //@Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for( User user : users){
            List<journalEntry> journalEntries = user.getJournal_Entries();
            List<Sentiment> sentiments = journalEntries.stream().map(x ->x.getSentiment()).collect(Collectors.toList());
            Map< Sentiment , Integer> sentimentCount = new HashMap<>();
            for (Sentiment sentiment : sentiments){
                if (sentiment != null)
                    sentimentCount.put(sentiment , sentimentCount.getOrDefault(sentiment , 0)+1);
            }
            Sentiment mostFrequentSentiment = null ;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCount.entrySet()){
                if (entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null){
                emailService.sendEmail(user.getEmail(), "sentiment for last 7 day ",mostFrequentSentiment.toString());
            }

        }
    }

    //@Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }

}
