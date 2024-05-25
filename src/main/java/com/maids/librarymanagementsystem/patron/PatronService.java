package com.maids.librarymanagementsystem.patron;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatronService {

    private final PatronRepository patronRepository;
    private final PatronMapper patronMapper;

    public List<PatronDTO> getAllPatrons() {
        List<Patron> patronListDAO = patronRepository.findAll();
        return patronListDAO.stream().map(patronMapper::mapToDTO).toList();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("can not find Patron with id " + id));
    }

    public PatronDTO savePatron(PatronDTO patronDTO) {
        Patron patron = patronMapper.mapToDAO(patronDTO);
        Patron savedPatron = patronRepository.save(patron);
        return patronMapper.mapToDTO(savedPatron);
    }

    public void deletePatron(Long id) {
        if (patronRepository.existsById(id)) {
            patronRepository.deleteById(id);
        }
        throw new IllegalArgumentException("can not find Patron with id " + id);
    }

    public PatronDTO updatePatron(Long id, PatronDTO patronDTO) {
        if (patronRepository.existsById(id)) {
            Patron patron = patronMapper.mapToDAO(patronDTO);
            patron.setId(id);
            Patron savedPatron = patronRepository.save(patron);
            return patronMapper.mapToDTO(savedPatron);
        }
        throw new IllegalArgumentException("can not find Patron with id " + id);
    }
}
