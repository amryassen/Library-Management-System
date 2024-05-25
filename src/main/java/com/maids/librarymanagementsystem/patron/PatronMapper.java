package com.maids.librarymanagementsystem.patron;

import com.maids.librarymanagementsystem.borrowingRecord.BorrowingRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PatronMapper {

    public Patron mapToDAO(PatronDTO patronDTO) {
        return Patron.builder()
                .name(patronDTO.getName())
                .contactInformation(patronDTO.getContactInformation())
                .build();
    }

    public PatronDTO mapToDTO(Patron patron) {
        return PatronDTO.builder()
                .id(patron.getId())
                .name(patron.getName())
                .contactInformation(patron.getContactInformation())
                .build();
    }
}
