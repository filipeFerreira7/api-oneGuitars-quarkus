package br.unitins.tp1.oneGuitars.Pagamento.dto;

import java.time.LocalDate;

import br.unitins.tp1.oneGuitars.Pagamento.model.BandeiraCartao;
import br.unitins.tp1.oneGuitars.Pagamento.model.CartaoCredito;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CartaoCreditoDTORequest (
    @NotBlank(message = "o campo nomeTitular deve ser preenchido")
    @Size(max=60)
    String nomeTitular,

    @NotBlank(message = "o campo numero deve ser preenchido")
    @Size(min = 16, max = 16)
    String numero,

    @NotBlank(message = "o campo cvv deve ser preenchido")
    @Size(min =3, max = 3)
    String cvv,
    @Future(message = "A data de validade sera validada apenas no futuro")
    LocalDate validade,

    @NotBlank(message = "o campo cpf deve ser preenchido")
    @Size(min = 11,max = 11)
    String cpfCartao,
    @NotNull()
    @Min(0)
    Double saldoCartao,

    @NotNull()
    BandeiraCartao bandeira

) {

    public static CartaoCredito converteCartaoCredito(CartaoCreditoDTORequest dto){
        CartaoCredito c = new CartaoCredito();

        c.setNomeTitular(dto.nomeTitular());
        c.setNumeroCartao(dto.numero());
        c.setCpfTitular(dto.cpfCartao);
        c.setValidade(dto.validade());
        c.setCvv(dto.cvv());
        c.setSaldoCartao(dto.saldoCartao());
        c.setBandeiraCartao(dto.bandeira());

        return c;
    }
    
}
