package com.maids.librarymanagementsystem.borrowingRecord;

import com.maids.librarymanagementsystem.book.BookMapper;
import com.maids.librarymanagementsystem.patron.PatronMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BorrowingRecordMapper {
    private final BookMapper bookMapper;
    private final PatronMapper patronMapper;

    public BorrowingRecordDTO mapToBorrowingRecordDTO(BorrowingRecord borrowingRecord) {
        return BorrowingRecordDTO.builder()
                .id(borrowingRecord.getId())
                .borrowingDate(borrowingRecord.getBorrowingDate())
                .book(bookMapper.mapToBookDTO(borrowingRecord.getBook()))
                .patron(patronMapper.mapToDTO(borrowingRecord.getPatron()))
                .returnDate(borrowingRecord.getReturnDate())
                .build();
    }
}
