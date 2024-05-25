package com.maids.librarymanagementsystem.borrowingRecord;

import com.maids.librarymanagementsystem.book.Book;
import com.maids.librarymanagementsystem.patron.Patron;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Book is required")
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull(message = "Patron is required")
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date borrowingDate;

    private Date returnDate;
}