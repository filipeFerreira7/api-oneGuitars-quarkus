package br.unitins.tp1.oneGuitars.PessoaFisica.converterjpa;

import br.unitins.tp1.oneGuitars.Pagamento.model.BandeiraCartao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BandeiraCartaoConverter implements AttributeConverter<BandeiraCartao,Integer>{

    @Override
    public Integer convertToDatabaseColumn(BandeiraCartao BandeiraCartao) {
        return BandeiraCartao == null ? null : BandeiraCartao.getId();
    }

    @Override
    public BandeiraCartao convertToEntityAttribute(Integer idBandeira) {
        return BandeiraCartao.valueOf(idBandeira);
    }
    
}
