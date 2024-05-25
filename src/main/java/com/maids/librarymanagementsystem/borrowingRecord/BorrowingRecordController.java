package com.maids.librarymanagementsystem.borrowingRecord;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/borrowingRecords")
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingService;

    @GetMapping("/getBorrowingRecords")
    public List<BorrowingRecordDTO> getAllBorrowingRecords() {
        return borrowingService.getAllBorrowingRecords();
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Void> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord success = borrowingService.borrowBook(bookId, patronId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowingService.returnBook(bookId, patronId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}