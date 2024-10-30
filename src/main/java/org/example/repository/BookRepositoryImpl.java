package org.example.repository;

import org.example.entity.Book;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findBooksByCriteria(String authorName, String title, LocalDate fromDate, LocalDate toDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();


        if (authorName != null) {
            predicates.add(cb.equal(book.get("author"), authorName));
        }


        if (title != null) {
            predicates.add(cb.like(book.get("title"), "%" + title + "%"));
        }


        if (fromDate != null && toDate != null) {
            predicates.add(cb.between(book.get("manufacturedDate"), fromDate, toDate));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Book>findAllBooks(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> book = query.from(Book.class);
        // query.select(book);

        Predicate titlePredicate = builder.notLike(book.get("title"), "The%");
        query.select(book).where(titlePredicate);
       // Predicate authorPredicate = builder.like(book.get("author"),"A");
       // query.select(book).where(authorPredicate);

        return em.createQuery(query).getResultList();
    }
}
