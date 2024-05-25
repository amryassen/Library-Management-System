package com.maids.librarymanagementsystem.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDTO> getAllBooks() {
        List<Book> bookListDAO = bookRepository.findAll();
        return bookListDAO.stream().map(bookMapper::mapToBookDTO).toList();
    }

    public BookDTO getBookDTOById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapToBookDTO)
                .orElse(null);
//        Book book = bookRepository.findById(id).orElse(null);
//        return (book == null ? null : bookMapper.mapToBookDTO(book));
    }

    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = bookMapper.mapToBookDAO(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.mapToBookDTO(savedBook);
    }

    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("can not find book"));
        bookDTO.setId(id);
        return saveBook(bookDTO);
    }
}

