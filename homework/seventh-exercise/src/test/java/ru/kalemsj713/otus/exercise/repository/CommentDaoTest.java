package ru.kalemsj713.otus.exercise.repository;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kalemsj713.otus.exercise.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
 class CommentDaoTest {
	@Autowired
	CommentDao commentDao;
	@Autowired
	private TestEntityManager em;

	@DirtiesContext
	@Test
	void getComment() {
		val optionalComment = commentDao.findById(1L);
		val expectedComment = em.find(Comment.class, 1L);
		assertThat(optionalComment).isPresent().get()
				.isEqualToComparingFieldByField(expectedComment);
	}

	@Test
	void saveComment() {
		val optionalComment = new Comment("name");
 		commentDao.save(optionalComment);
		assertThat(optionalComment.getId()).isGreaterThan(0);
		Comment expectedComment = em.find(Comment.class, optionalComment.getId());
		assertThat(optionalComment).isEqualToComparingFieldByField(expectedComment);
		optionalComment.setText("name1");
		commentDao.save(optionalComment);
		expectedComment = em.find(Comment.class, optionalComment.getId());
		assertEquals(optionalComment.getText(), expectedComment.getText());
		assertEquals(optionalComment.getText(), "name1");
	}

	@Test
	void deleteComment() {
		val comment = em.find(Comment.class, 1L);
		assertThat(comment).isNotNull();
		em.detach(comment);
		commentDao.deleteById(1L);
		val deletedComment = em.find(Comment.class, 1L);
		assertThat(deletedComment).isNull();
	}

	@Test
	void getComments() {
		val comments = commentDao.findAllByBookId(2L);
		val expected = em.find(Comment.class, 2L);
		assertThat(comments).hasSize(1).containsOnly(expected);

	}
}