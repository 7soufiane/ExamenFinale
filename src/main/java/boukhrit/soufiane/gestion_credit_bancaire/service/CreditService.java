package boukhrit.soufiane.gestion_credit_bancaire.service;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.Client;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.Credit;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditImmobilier;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditPersonnel;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditProfessionnel;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.Remboursement;
import boukhrit.soufiane.gestion_credit_bancaire.DTOs.CreditDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.ClientRepository;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.CreditImmobilierRepository;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.CreditPersonnelRepository;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.CreditProfessionnelRepository;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.CreditRepository;
import boukhrit.soufiane.gestion_credit_bancaire.Repositories.RemboursementRepository;
import boukhrit.soufiane.gestion_credit_bancaire.exceptions.NotFoundException;
import boukhrit.soufiane.gestion_credit_bancaire.exceptions.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final CreditProfessionnelRepository creditProfessionnelRepository;
    private final RemboursementRepository remboursementRepository;
    private final CreditImmobilierRepository creditImmobilierRepository;

    public CreditService(final CreditRepository creditRepository,
            final ClientRepository clientRepository,
            final CreditPersonnelRepository creditPersonnelRepository,
            final CreditProfessionnelRepository creditProfessionnelRepository,
            final RemboursementRepository remboursementRepository,
            final CreditImmobilierRepository creditImmobilierRepository) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
        this.creditPersonnelRepository = creditPersonnelRepository;
        this.creditProfessionnelRepository = creditProfessionnelRepository;
        this.remboursementRepository = remboursementRepository;
        this.creditImmobilierRepository = creditImmobilierRepository;
    }

    public List<CreditDTO> findAll() {
        final List<Credit> credits = creditRepository.findAll(Sort.by("id"));
        return credits.stream()
                .map(credit -> mapToDTO(credit, new CreditDTO()))
                .toList();
    }

    public CreditDTO get(final Long id) {
        return creditRepository.findById(id)
                .map(credit -> mapToDTO(credit, new CreditDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CreditDTO creditDTO) {
        final Credit credit = new Credit();
        mapToEntity(creditDTO, credit);
        return creditRepository.save(credit).getId();
    }

    public void update(final Long id, final CreditDTO creditDTO) {
        final Credit credit = creditRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(creditDTO, credit);
        creditRepository.save(credit);
    }

    public void delete(final Long id) {
        creditRepository.deleteById(id);
    }

    private CreditDTO mapToDTO(final Credit credit, final CreditDTO creditDTO) {
        creditDTO.setId(credit.getId());
        creditDTO.setDateDemande(credit.getDateDemande());
        creditDTO.setStatut(credit.getStatut());
        creditDTO.setDateAcceptation(credit.getDateAcceptation());
        creditDTO.setMontant(credit.getMontant());
        creditDTO.setDureeRemboursement(credit.getDureeRemboursement());
        creditDTO.setTauxInteret(credit.getTauxInteret());
        creditDTO.setTypeCredit(credit.getTypeCredit());
        creditDTO.setClient(credit.getClient() == null ? null : credit.getClient().getId());
        return creditDTO;
    }

    private Credit mapToEntity(final CreditDTO creditDTO, final Credit credit) {
        credit.setDateDemande(creditDTO.getDateDemande());
        credit.setStatut(creditDTO.getStatut());
        credit.setDateAcceptation(creditDTO.getDateAcceptation());
        credit.setMontant(creditDTO.getMontant());
        credit.setDureeRemboursement(creditDTO.getDureeRemboursement());
        credit.setTauxInteret(creditDTO.getTauxInteret());
        credit.setTypeCredit(creditDTO.getTypeCredit());
        final Client client = creditDTO.getClient() == null ? null : clientRepository.findById(creditDTO.getClient())
                .orElseThrow(() -> new NotFoundException("client not found"));
        credit.setClient(client);
        return credit;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Credit credit = creditRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final CreditPersonnel creditCreditPersonnel = creditPersonnelRepository.findFirstByCredit(credit);
        if (creditCreditPersonnel != null) {
            referencedWarning.setKey("credit.creditPersonnel.credit.referenced");
            referencedWarning.addParam(creditCreditPersonnel.getId());
            return referencedWarning;
        }
        final CreditProfessionnel creditCreditProfessionnel = creditProfessionnelRepository.findFirstByCredit(credit);
        if (creditCreditProfessionnel != null) {
            referencedWarning.setKey("credit.creditProfessionnel.credit.referenced");
            referencedWarning.addParam(creditCreditProfessionnel.getId());
            return referencedWarning;
        }
        final Remboursement creditRemboursement = remboursementRepository.findFirstByCredit(credit);
        if (creditRemboursement != null) {
            referencedWarning.setKey("credit.remboursement.credit.referenced");
            referencedWarning.addParam(creditRemboursement.getId());
            return referencedWarning;
        }
        final CreditImmobilier creditCreditImmobilier = creditImmobilierRepository.findFirstByCredit(credit);
        if (creditCreditImmobilier != null) {
            referencedWarning.setKey("credit.creditImmobilier.credit.referenced");
            referencedWarning.addParam(creditCreditImmobilier.getId());
            return referencedWarning;
        }
        return null;
    }

}
