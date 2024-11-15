package org.example.repository.impl;

import java.util.List;
import java.util.Optional;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory factory;

    @Autowired
    public BookRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public Book save(Book book) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert new book " + book, e);
        }
    }

    public List<Book> findAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from " + Book.class.getSimpleName(), Book.class)
                    .getResultList();
        }
    }

    public Optional<Book> findById(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Book.class, id));
        }
    }
}
