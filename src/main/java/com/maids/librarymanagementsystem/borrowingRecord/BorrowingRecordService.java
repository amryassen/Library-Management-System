package com.maids.librarymanagementsystem.borrowingRecord;

import com.maids.librarymanagementsystem.book.Book;
import com.maids.librarymanagementsystem.book.BookRepository;
import com.maids.librarymanagementsystem.patron.Patron;
import com.maids.librarymanagementsystem.patron.PatronRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowingRecordMapper borrowingRecordMapper;

    public List<BorrowingRecordDTO> getAllBorrowingRecords() {
        List<BorrowingRecord> borrowingRecords = borrowingRecordRepository.findAll();
        return borrowingRecords.stream().map(borrowingRecordMapper::mapToBorrowingRecordDTO).toList();
    }

    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalArgumentException("Patron not found"));

        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .book(book)
                .patron(patron)
                .build();

        return borrowingRecordRepository.save(borrowingRecord);
    }

    public void returnBook(Long bookId, Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository
                .findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() -> new IllegalArgumentException("Borrowing record not found"));
        borrowingRecord.setReturnDate(new Date());
        borrowingRecordRepository.save(borrowingRecord);
    }

}
