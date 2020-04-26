package ru.kalemsj713.otus.exercise.event;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.BookRepository;

@Component
@AllArgsConstructor
public class CascadeMongoEventListener extends AbstractMongoEventListener<Object> {
    private final BookRepository bookRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Object> afterDeleteEvent) {
        super.onAfterDelete(afterDeleteEvent);

        val source = afterDeleteEvent.getSource();
        val id = source.get("_id").toString();
        if (("authors".equalsIgnoreCase(afterDeleteEvent.getCollectionName()))) {
            bookRepository.removeAuthorArrayElementsById(id);
        } else if (("genres".equalsIgnoreCase(afterDeleteEvent.getCollectionName()))) {
            bookRepository.removeGenreArrayElementsById(id);
        } else if (("comments".equalsIgnoreCase(afterDeleteEvent.getCollectionName()))) {
            bookRepository.removeCommentArrayElementsById(id);
        }
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        super.onBeforeConvert(event);
        if (("authors".equalsIgnoreCase(event.getCollectionName()))) {
            Author source = (Author) event.getSource();
            bookRepository.setAuthorArrayElementsById(source);
        } else if (("genres".equalsIgnoreCase(event.getCollectionName()))) {
            Genre source = (Genre) event.getSource();
            bookRepository.setGenreArrayElementsById(source);
        }
    }

}
