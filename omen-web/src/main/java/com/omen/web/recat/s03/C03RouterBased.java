package com.omen.web.recat.s03;

import com.omen.web.domain.Book;
import com.omen.web.support.InMemoryDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.Collection;

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

    @Bean
    public RouterFunction<ServerResponse> routers() {
        return RouterFunctions.route()
                .POST(PATH_PREFIX + "book", this::create)
                .GET(PATH_PREFIX + "book", this::findAll)
                .GET(PATH_PREFIX + "book" + PATH_PARAM, this::findOne)
                .build();

    }

    private Mono<ServerResponse> create(ServerRequest request) {
        return C04ReactiveControllerHelper.requestBodyToMono(request, validator,
                (t, errors) -> InMemoryDataSource.findBookMonoById(t.getIsbn())
                        .map((book -> {
                            errors.rejectValue("isbn", "already.exists", "Already exists");
                            return Tuples.of(book, errors);
                        })), Book.class)
                .map(InMemoryDataSource::saveBook)
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
}
