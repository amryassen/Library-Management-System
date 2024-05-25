package com.maids.librarymanagementsystem.book;

import com.maids.librarymanagementsystem.borrowingRecord.BorrowingRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDTO mapToBookDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear()).build();
    }

    public Book mapToBookDAO(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .publicationYear(bookDTO.getPublicationYear())
                .isbn(bookDTO.getIsbn())
                .author(bookDTO.getAuthor())
                .build();
    }
}
