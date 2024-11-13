package org.example.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.dto.BookDto;
import org.example.dto.CreateBookRequestDto;
import org.example.dto.mapper.BookMapper;
import org.example.exception.EntityNotFoundException;
import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookDto> getAll() {
        List<Book> books = bookService.findAll();
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No book with id " + id));
        return bookMapper.toDto(book);
    }

    @PostMapping
    public BookDto createBook(@RequestBody CreateBookRequestDto bookDto) {
        Book savedBook = bookService.save(bookMapper.toEntity(bookDto));
        return bookMapper.toDto(savedBook);
    }
}
