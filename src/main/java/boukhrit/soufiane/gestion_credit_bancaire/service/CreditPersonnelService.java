package boukhrit.soufiane.gestion_credit_bancaire.service;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.Credit;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditPersonnel;
import boukhrit.soufiane.gestion_credit_bancaire.DTOs.CreditPersonnelDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.CreditPersonnelRepository;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.CreditRepository;
import boukhrit.soufiane.gestion_credit_bancaire.exceptions.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CreditPersonnelService {

    private final CreditPersonnelRepository creditPersonnelRepository;
    private final CreditRepository creditRepository;

    public CreditPersonnelService(final CreditPersonnelRepository creditPersonnelRepository,
            final CreditRepository creditRepository) {
        this.creditPersonnelRepository = creditPersonnelRepository;
        this.creditRepository = creditRepository;
    }

    public List<CreditPersonnelDTO> findAll() {
        final List<CreditPersonnel> creditPersonnels = creditPersonnelRepository.findAll(Sort.by("id"));
        return creditPersonnels.stream()
                .map(creditPersonnel -> mapToDTO(creditPersonnel, new CreditPersonnelDTO()))
                .toList();
    }

    public CreditPersonnelDTO get(final Integer id) {
        return creditPersonnelRepository.findById(id)
                .map(creditPersonnel -> mapToDTO(creditPersonnel, new CreditPersonnelDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final CreditPersonnelDTO creditPersonnelDTO) {
        final CreditPersonnel creditPersonnel = new CreditPersonnel();
        mapToEntity(creditPersonnelDTO, creditPersonnel);
        return creditPersonnelRepository.save(creditPersonnel).getId();
    }

    public void update(final Integer id, final CreditPersonnelDTO creditPersonnelDTO) {
        final CreditPersonnel creditPersonnel = creditPersonnelRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(creditPersonnelDTO, creditPersonnel);
        creditPersonnelRepository.save(creditPersonnel);
    }

    public void delete(final Integer id) {
        creditPersonnelRepository.deleteById(id);
    }

    private CreditPersonnelDTO mapToDTO(final CreditPersonnel creditPersonnel,
            final CreditPersonnelDTO creditPersonnelDTO) {
        creditPersonnelDTO.setId(creditPersonnel.getId());
        creditPersonnelDTO.setMotif(creditPersonnel.getMotif());
        creditPersonnelDTO.setCredit(creditPersonnel.getCredit() == null ? null : creditPersonnel.getCredit().getId());
        return creditPersonnelDTO;
    }

    private CreditPersonnel mapToEntity(final CreditPersonnelDTO creditPersonnelDTO,
            final CreditPersonnel creditPersonnel) {
        creditPersonnel.setMotif(creditPersonnelDTO.getMotif());
        final Credit credit = creditPersonnelDTO.getCredit() == null ? null : creditRepository.findById(creditPersonnelDTO.getCredit())
                .orElseThrow(() -> new NotFoundException("credit not found"));
        creditPersonnel.setCredit(credit);
        return creditPersonnel;
    }

}
