package ru.anovikov.learning.otusbooklib.domain;

import javax.persistence.*;

@Entity
@NamedQuery(name="Comment.findAll", query="select c from Comment c")
@NamedQuery(name="Comment.findByBook", query="select c from Comment c where c.book.id = :bookId")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private Book book;

    @Column(name = "commentText")
    private String commentText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
