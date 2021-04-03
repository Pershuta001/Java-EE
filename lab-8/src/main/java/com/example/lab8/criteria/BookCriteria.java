package com.example.lab8.criteria;

import com.example.lab8.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BookCriteria {

    private final EntityManager entityManager;

    public List<Book> getListOfBooksByCriteria(String titles) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cr = cb.createQuery(Book.class);
        Root<Book> root = cr.from(Book.class);
        cr.select(root).where(root.get("title").in(titles));
        TypedQuery<Book> query = entityManager.createQuery(cr);
        return query.getResultList();
    }

}
