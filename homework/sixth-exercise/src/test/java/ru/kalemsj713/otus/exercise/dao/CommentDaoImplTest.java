package ru.kalemsj713.otus.exercise.dao;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kalemsj713.otus.exercise.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(CommentDaoImpl.class)
class CommentDaoImplTest {
	@Autowired
	CommentDao commentDao;
	@Autowired
	private TestEntityManager em;

	@DirtiesContext
	@Test
	void getComment() {
		val optionalComment = commentDao.getComment(1L);
		val expectedComment = em.find(Comment.class, 1L);
		assertThat(optionalComment).isPresent().get()
				.isEqualToComparingFieldByField(expectedComment);
	}

	@Test
	void saveComment() {
		val optionalComment = new Comment();
		optionalComment.setText("name");
		commentDao.saveComment(optionalComment);
		assertThat(optionalComment.getId()).isGreaterThan(0);
		Comment expectedComment = em.find(Comment.class, optionalComment.getId());
		assertThat(optionalComment).isEqualToComparingFieldByField(expectedComment);
		optionalComment.setText("name1");
		commentDao.saveComment(optionalComment);
		expectedComment = em.find(Comment.class, optionalComment.getId());
		assertEquals(optionalComment.getText(), expectedComment.getText());
		assertEquals(optionalComment.getText(), "name1");
	}

	@Test
	void deleteComment() {
		val comment = em.find(Comment.class, 1L);
		assertThat(comment).isNotNull();
		em.detach(comment);
		commentDao.deleteComment(1L);
		val deletedComment = em.find(Comment.class, 1L);
		assertThat(deletedComment).isNull();
	}
}