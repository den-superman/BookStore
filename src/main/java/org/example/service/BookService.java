package org.example.service;

import java.util.List;
import org.example.dto.BookDto;
import org.example.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll();
    
    BookDto getById(Long id);
}
