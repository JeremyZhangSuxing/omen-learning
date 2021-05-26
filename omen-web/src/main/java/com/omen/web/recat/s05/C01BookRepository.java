package com.omen.web.recat.s05;

import com.omen.web.domain.Book;
import com.omen.web.domain.BookQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.relational.core.query.Criteria.where;

/**
 * @author : Knight
 * @date : 2021/5/25 4:08 下午
 */
@Repository
@RequiredArgsConstructor
public class C01BookRepository {
    private final DatabaseClient databaseClient;

    /**
     * 返回Mono通知后续的pipeline 可以操作的信号
     */
    public Mono<Void> insert(Book book) {
        return databaseClient.insert().into(Book.class)
                .using(book)
                .then();
    }

    public Mono<Void> update(Book book) {
        return databaseClient.update().table(Book.class)
                .using(book)
                .then();
    }

    public Mono<Book> findById(String isbn) {
        return databaseClient.execute("select * from book where isbn = :isbn")
                .bind("isbn", isbn)
                .as(Book.class)
                .fetch()
                .one();
    }

    public Flux<Book> findAll() {
        return databaseClient.select().from(Book.class).fetch().all();
    }

    public Mono<Void> delete(String isbn) {
        return databaseClient.delete().from(Book.class)
                .matching(where("isbn").is(isbn))
                .then();
    }

    public Flux<Book> findBooksByQuery(BookQuery bookQuery) {
        Criteria criteria = Criteria.empty();
        if (!StringUtils.isEmpty(bookQuery.getTitle())) {
            criteria = criteria.and(where("title").like(bookQuery.getTitle()));
        }
        if (bookQuery.getMinPrice() != null) {
            criteria = criteria.and(where("price").greaterThanOrEquals(bookQuery.getMinPrice()));
        }
        if (bookQuery.getMaxPrice() != null) {
            criteria = criteria.and(where("price").lessThanOrEquals(bookQuery.getMinPrice()));
        }
        Pageable pageable = PageRequest.of(bookQuery.getPage(), bookQuery.getSize());
        return databaseClient.select().from(Book.class)
                .matching(criteria)
                .page(pageable)
                .fetch()
                .all();
    }
}
