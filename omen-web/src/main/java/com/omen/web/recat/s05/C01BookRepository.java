package com.omen.web.recat.s05;

import com.omen.web.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        return databaseClient.insert()
                .into(Book.class)
                .using(book)
                .then();
    }

    public Mono<Void> update(Book book) {
        return databaseClient.update()
                .table(Book.class)
                .using(book)
                .then();
    }

    public Mono<Book> selectOne(String isbn) {
        return databaseClient.select()
                .from(Book.class)
                .matching(Criteria.where("isbn").is(isbn))
                .fetch()
                .one();
    }

    public Flux<Book> selectAll() {
        return databaseClient.select()
                .from(Book.class)
                .fetch()
                .all();
    }

    public Mono<Void> deleteByIsbn(String isbn) {
        return databaseClient.delete()
                .from(Book.class)
                .matching(Criteria.where("isbn").greaterThanOrEquals(isbn))
                .then();
    }
}
