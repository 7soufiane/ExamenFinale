package boukhrit.soufiane.gestion_credit_bancaire.service;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.StatutCredit;
import boukhrit.soufiane.gestion_credit_bancaire.DTOs.StatutCreditDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.StatutCreditRepository;
import boukhrit.soufiane.gestion_credit_bancaire.exceptions.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class StatutCreditService {

    private final StatutCreditRepository statutCreditRepository;

    public StatutCreditService(final StatutCreditRepository statutCreditRepository) {
        this.statutCreditRepository = statutCreditRepository;
    }

    public List<StatutCreditDTO> findAll() {
        final List<StatutCredit> statutCredits = statutCreditRepository.findAll(Sort.by("id"));
        return statutCredits.stream()
                .map(statutCredit -> mapToDTO(statutCredit, new StatutCreditDTO()))
                .toList();
    }

    public StatutCreditDTO get(final Integer id) {
        return statutCreditRepository.findById(id)
                .map(statutCredit -> mapToDTO(statutCredit, new StatutCreditDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final StatutCreditDTO statutCreditDTO) {
        final StatutCredit statutCredit = new StatutCredit();
        mapToEntity(statutCreditDTO, statutCredit);
        return statutCreditRepository.save(statutCredit).getId();
    }

    public void update(final Integer id, final StatutCreditDTO statutCreditDTO) {
        final StatutCredit statutCredit = statutCreditRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(statutCreditDTO, statutCredit);
        statutCreditRepository.save(statutCredit);
    }

    public void delete(final Integer id) {
        statutCreditRepository.deleteById(id);
    }

    private StatutCreditDTO mapToDTO(final StatutCredit statutCredit,
            final StatutCreditDTO statutCreditDTO) {
        statutCreditDTO.setId(statutCredit.getId());
        statutCreditDTO.setLibelle(statutCredit.getLibelle());
        return statutCreditDTO;
    }

    private StatutCredit mapToEntity(final StatutCreditDTO statutCreditDTO,
            final StatutCredit statutCredit) {
        statutCredit.setLibelle(statutCreditDTO.getLibelle());
        return statutCredit;
    }

}
