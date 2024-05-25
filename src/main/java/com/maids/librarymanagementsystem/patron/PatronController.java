package com.maids.librarymanagementsystem.patron;

import com.maids.librarymanagementsystem.book.BookDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
@Transactional
public class PatronController {

    private final PatronService patronService;

    @GetMapping
    public List<PatronDTO> getAllPatrons() {
        return patronService.getAllPatrons();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Patron patron = patronService.getPatronById(id);
        return new ResponseEntity<>(patron, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPatron(@Valid @RequestBody PatronDTO patronDTO) {
        PatronDTO savedPatron = patronService.savePatron(patronDTO);
        return new ResponseEntity<>(savedPatron, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron(@PathVariable Long id,
                                          @Valid @RequestBody PatronDTO patronDTO) {
        PatronDTO updatedPatron = patronService.updatePatron(id, patronDTO);
        return new ResponseEntity<>(updatedPatron, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}