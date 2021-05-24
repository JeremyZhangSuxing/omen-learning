package com.omen.web.recat.s04;

import com.omen.web.domain.Book;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * @author : Knight
 * @date : 2021/5/23 9:46 上午
 */
public class C01WebClientShowcases {
    public static void main(String[] args) {
        Book book = Book.builder().isbn(String.valueOf(System.currentTimeMillis()))
                .category("TEST")
                .title("book")
                .price(BigDecimal.ONE)
                .build();
        //模拟一次webFlux调用
        WebClient webClient = WebClient.create("http://localhost:8083/routed");
        webClient.post().uri("/book")
                .body(Mono.just(book), Book.class)
                .exchange()
                .doOnNext(clientResponse -> System.err.println("response status--->" + clientResponse.statusCode()))
                .block();


        webClient.get().uri("/book/{isbn}", book.getIsbn())
                .retrieve()
                .bodyToMono(Book.class)
                //when this request has response json body,use retrieve
                //if http status is not 200 this webClient will throw a webClientException
                .doOnNext(book1 -> System.err.println("current book info " + book))
                .block();

        book.setPrice(BigDecimal.valueOf(33.46));
        webClient.put().uri("/book/{isbn}", book.getIsbn())
                .body(Mono.just(book), Book.class)
                .exchange()
                .doOnNext(clientResponse -> System.err.println("put response status -----> " + clientResponse.statusCode()))
                .block();

        webClient.get().uri("/books")
                .retrieve()
                .bodyToFlux(Book.class)
                //when this request has response json body,use retrieve
                //if http status is not 200 this webClient will throw a webClientException
                .doOnNext(aBook -> System.err.println("all book info " + aBook))
                .blockLast();

        webClient.delete().uri("/book/{isbn}", book.getIsbn())
                .exchange()
                .doOnNext(webRes -> System.err.println("delete response status---->" + webRes.statusCode()))
                .block();


    }
}
