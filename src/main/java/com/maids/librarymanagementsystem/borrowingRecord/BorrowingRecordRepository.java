package com.maids.librarymanagementsystem.borrowingRecord;

import com.maids.librarymanagementsystem.book.Book;
import com.maids.librarymanagementsystem.patron.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.id = :bookId AND br.patron.id = :patronId")
    Optional<BorrowingRecord> findByBookIdAndPatronId(Long bookId, Long patronId);
}