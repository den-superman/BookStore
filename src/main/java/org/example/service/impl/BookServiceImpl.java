package org.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.dto.BookDto;
import org.example.dto.CreateBookRequestDto;
import org.example.dto.mapper.BookMapper;
import org.example.exception.EntityNotFoundException;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        Book savedBook = bookRepository.save(bookMapper.toEntity(createBookRequestDto));
        return bookMapper.toDto(savedBook);
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No book with id " + id));
        return bookMapper.toDto(book);
    }
}
