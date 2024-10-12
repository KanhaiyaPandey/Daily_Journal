package net.croods.journalApp.entity;

// import java.sql.Date;

// import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import java.time.LocalDateTime;


@Document(collection = "journal_entries")
@Data
public class JournalEntry {
    
    @Id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime  date;

}
