package com.maids.librarymanagementsystem.patron;

import com.maids.librarymanagementsystem.borrowingRecord.BorrowingRecord;
import com.maids.librarymanagementsystem.borrowingRecord.BorrowingRecordDTO;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatronDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @NotBlank(message = "Contact information is required")
    @Size(max = 255, message = "Contact information must be less than 255 characters")
    private String contactInformation;

    private List<BorrowingRecordDTO> borrowingRecords;
}
