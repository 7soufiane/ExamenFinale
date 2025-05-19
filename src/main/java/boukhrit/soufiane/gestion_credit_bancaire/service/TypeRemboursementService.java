package boukhrit.soufiane.gestion_credit_bancaire.service;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.TypeRemboursement;
import boukhrit.soufiane.gestion_credit_bancaire.DTOs.TypeRemboursementDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.TypeRemboursementRepository;
import boukhrit.soufiane.gestion_credit_bancaire.exceptions.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TypeRemboursementService {

    private final TypeRemboursementRepository typeRemboursementRepository;

    public TypeRemboursementService(final TypeRemboursementRepository typeRemboursementRepository) {
        this.typeRemboursementRepository = typeRemboursementRepository;
    }

    public List<TypeRemboursementDTO> findAll() {
        final List<TypeRemboursement> typeRemboursements = typeRemboursementRepository.findAll(Sort.by("id"));
        return typeRemboursements.stream()
                .map(typeRemboursement -> mapToDTO(typeRemboursement, new TypeRemboursementDTO()))
                .toList();
    }

    public TypeRemboursementDTO get(final Integer id) {
        return typeRemboursementRepository.findById(id)
                .map(typeRemboursement -> mapToDTO(typeRemboursement, new TypeRemboursementDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final TypeRemboursementDTO typeRemboursementDTO) {
        final TypeRemboursement typeRemboursement = new TypeRemboursement();
        mapToEntity(typeRemboursementDTO, typeRemboursement);
        return typeRemboursementRepository.save(typeRemboursement).getId();
    }

    public void update(final Integer id, final TypeRemboursementDTO typeRemboursementDTO) {
        final TypeRemboursement typeRemboursement = typeRemboursementRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(typeRemboursementDTO, typeRemboursement);
        typeRemboursementRepository.save(typeRemboursement);
    }

    public void delete(final Integer id) {
        typeRemboursementRepository.deleteById(id);
    }

    private TypeRemboursementDTO mapToDTO(final TypeRemboursement typeRemboursement,
            final TypeRemboursementDTO typeRemboursementDTO) {
        typeRemboursementDTO.setId(typeRemboursement.getId());
        typeRemboursementDTO.setLibelle(typeRemboursement.getLibelle());
        return typeRemboursementDTO;
    }

    private TypeRemboursement mapToEntity(final TypeRemboursementDTO typeRemboursementDTO,
            final TypeRemboursement typeRemboursement) {
        typeRemboursement.setLibelle(typeRemboursementDTO.getLibelle());
        return typeRemboursement;
    }

}
