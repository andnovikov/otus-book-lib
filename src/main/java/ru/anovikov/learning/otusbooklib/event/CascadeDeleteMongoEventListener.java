package ru.anovikov.learning.otusbooklib.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.anovikov.learning.otusbooklib.repository.BookRepository;

import java.util.Objects;

@Component
public class CascadeDeleteMongoEventListener extends AbstractMongoEventListener<Object> {

    private final BookRepository bookRepository;

    @Autowired
    public CascadeDeleteMongoEventListener(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) throws RuntimeException {
        if (Objects.equals(event.getCollectionName(), "genres") || Objects.equals(event.getCollectionName(), "authors")) {
            String id = null;
            for (Object key : event.getSource().values()) {
                id = String.valueOf(key);
            }

            if (bookRepository.findFirstByGenreId(id) != null || bookRepository.findFirstByAuthorId(id) != null) throw new LinkedObjectException();
        }
    }
}
