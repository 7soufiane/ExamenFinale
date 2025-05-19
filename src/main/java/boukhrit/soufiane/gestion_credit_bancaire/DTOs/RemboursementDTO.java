package boukhrit.soufiane.gestion_credit_bancaire.DTOs;

import boukhrit.soufiane.gestion_credit_bancaire.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RemboursementDTO {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    @Digits(integer = 17, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "95.08")
    private BigDecimal montant;

    private Type type;

    @NotNull
    private Long credit;

}
