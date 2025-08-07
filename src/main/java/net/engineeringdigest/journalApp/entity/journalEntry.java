package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import net.engineeringdigest.journalApp.Enum.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Journal_Entries")
@Data
public class journalEntry {
    @Id
    private ObjectId id;
    private  String title;
    private String content;
    private Sentiment sentiment ;


}
