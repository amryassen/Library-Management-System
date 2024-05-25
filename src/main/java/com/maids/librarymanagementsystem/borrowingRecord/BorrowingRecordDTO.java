package com.maids.librarymanagementsystem.borrowingRecord;

import com.maids.librarymanagementsystem.book.BookDTO;
import com.maids.librarymanagementsystem.patron.PatronDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecordDTO {
    private Long id;

    @NotNull(message = "Book is required")
    private BookDTO book;

    @NotNull(message = "Patron is required")
    private PatronDTO patron;

    private Date borrowingDate;

    private Date returnDate;
}
