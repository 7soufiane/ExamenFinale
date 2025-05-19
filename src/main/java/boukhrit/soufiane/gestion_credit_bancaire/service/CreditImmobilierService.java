package boukhrit.soufiane.gestion_credit_bancaire.service;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.Credit;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditImmobilier;
import boukhrit.soufiane.gestion_credit_bancaire.DTOs.CreditImmobilierDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.CreditImmobilierRepository;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.CreditRepository;
import boukhrit.soufiane.gestion_credit_bancaire.exceptions.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CreditImmobilierService {

    private final CreditImmobilierRepository creditImmobilierRepository;
    private final CreditRepository creditRepository;

    public CreditImmobilierService(final CreditImmobilierRepository creditImmobilierRepository,
            final CreditRepository creditRepository) {
        this.creditImmobilierRepository = creditImmobilierRepository;
        this.creditRepository = creditRepository;
    }

    public List<CreditImmobilierDTO> findAll() {
        final List<CreditImmobilier> creditImmobiliers = creditImmobilierRepository.findAll(Sort.by("id"));
        return creditImmobiliers.stream()
                .map(creditImmobilier -> mapToDTO(creditImmobilier, new CreditImmobilierDTO()))
                .toList();
    }

    public CreditImmobilierDTO get(final Integer id) {
        return creditImmobilierRepository.findById(id)
                .map(creditImmobilier -> mapToDTO(creditImmobilier, new CreditImmobilierDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final CreditImmobilierDTO creditImmobilierDTO) {
        final CreditImmobilier creditImmobilier = new CreditImmobilier();
        mapToEntity(creditImmobilierDTO, creditImmobilier);
        return creditImmobilierRepository.save(creditImmobilier).getId();
    }

    public void update(final Integer id, final CreditImmobilierDTO creditImmobilierDTO) {
        final CreditImmobilier creditImmobilier = creditImmobilierRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(creditImmobilierDTO, creditImmobilier);
        creditImmobilierRepository.save(creditImmobilier);
    }

    public void delete(final Integer id) {
        creditImmobilierRepository.deleteById(id);
    }

    private CreditImmobilierDTO mapToDTO(final CreditImmobilier creditImmobilier,
            final CreditImmobilierDTO creditImmobilierDTO) {
        creditImmobilierDTO.setId(creditImmobilier.getId());
        creditImmobilierDTO.setType(creditImmobilier.getType());
        creditImmobilierDTO.setCredit(creditImmobilier.getCredit() == null ? null : creditImmobilier.getCredit().getId());
        return creditImmobilierDTO;
    }

    private CreditImmobilier mapToEntity(final CreditImmobilierDTO creditImmobilierDTO,
            final CreditImmobilier creditImmobilier) {
        creditImmobilier.setType(creditImmobilierDTO.getType());
        final Credit credit = creditImmobilierDTO.getCredit() == null ? null : creditRepository.findById(creditImmobilierDTO.getCredit())
                .orElseThrow(() -> new NotFoundException("credit not found"));
        creditImmobilier.setCredit(credit);
        return creditImmobilier;
    }

}
