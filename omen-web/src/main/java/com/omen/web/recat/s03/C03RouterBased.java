package com.omen.web.recat.s03;

import com.omen.web.domain.Book;
import com.omen.web.support.InMemoryDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Knight
 * @date : 2021/5/20 4:03 下午
 */
@Configuration
@RequiredArgsConstructor
public class C03RouterBased {
    private static final String PATH_PREFIX = "/routed/";
    private static final String PATH_PARAM = "{isbn}";
    private final Validator validator;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Bean
    public RouterFunction<ServerResponse> routers() {
        return RouterFunctions.route()
                .POST(PATH_PREFIX + "book", this::create)
                .GET(PATH_PREFIX + "books", this::findAll)
                .GET(PATH_PREFIX + "book/" + PATH_PARAM, this::findOne)
                .PUT(PATH_PREFIX + "book/" + PATH_PARAM, this::update)
                .DELETE(PATH_PREFIX + "book/" + PATH_PARAM, this::delete)
                .build();
    }

    private Mono<ServerResponse> create(ServerRequest request) {
        if (atomicInteger.getAndIncrement() > 3) {
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return C04ReactiveControllerHelper.requestBodyToMono(request, validator,
                (t, errors) -> InMemoryDataSource.findBookMonoById(t.getIsbn())
                        .map((book -> {
                            errors.rejectValue("isbn", "already.exists", "Already exists");
                            return Tuples.of(book, errors);
                        })), Book.class)
                .map(InMemoryDataSource::saveBook)
                //使用flatmap 从上一个管道--->下一个管道
                .flatMap(book -> ServerResponse.created(UriComponentsBuilder.fromHttpRequest(request.exchange()
                        .getRequest())
                        .path("/")
                        .path(book.getIsbn())
                        .build()
                        .toUri())
                        .build());
    }

    private Mono<ServerResponse> findOne(ServerRequest request) {
        String isbn = request.pathVariable("isbn");
        return InMemoryDataSource.findBookMonoById(isbn)
                .flatMap(v -> ServerResponse.ok().bodyValue(v))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    private Mono<ServerResponse> findAll(ServerRequest request) {
        Collection<Book> allBooks = InMemoryDataSource.findAllBooks();
        return ServerResponse.ok().bodyValue(allBooks);
    }

    private Mono<ServerResponse> update(ServerRequest request) {
        String isbn = request.pathVariable("isbn");
        return InMemoryDataSource.findBookMonoById(isbn)
                .map(InMemoryDataSource::saveBook)
                .flatMap(monoBook -> ServerResponse.ok().bodyValue(monoBook))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    private Mono<ServerResponse> delete(ServerRequest request) {
        String isbn = request.pathVariable("isbn");
        return InMemoryDataSource.findBookMonoById(isbn)
                .flatMap(book -> {
                    InMemoryDataSource.removeBook(book);
                    return ServerResponse.ok().build();
                }).switchIfEmpty(ServerResponse.notFound().build());
    }
}
