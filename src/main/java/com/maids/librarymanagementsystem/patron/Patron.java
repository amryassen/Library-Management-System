package com.maids.librarymanagementsystem.patron;

import com.maids.librarymanagementsystem.borrowingRecord.BorrowingRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @NotBlank(message = "Contact information is required")
    @Size(max = 255, message = "Contact information must be less than 255 characters")
    private String contactInformation;

    @OneToMany(mappedBy = "patron",cascade = CascadeType.ALL)
    private List<BorrowingRecord> borrowingRecords;
}