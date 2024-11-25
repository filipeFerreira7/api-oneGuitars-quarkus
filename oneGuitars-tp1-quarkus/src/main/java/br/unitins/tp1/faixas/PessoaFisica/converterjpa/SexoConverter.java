package br.unitins.tp1.faixas.PessoaFisica.converterjpa;

import br.unitins.tp1.faixas.Pagamento.model.BandeiraCartao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<BandeiraCartao,Integer>{

    @Override
    public Integer convertToDatabaseColumn(BandeiraCartao sexo) {
        return sexo == null ? null : sexo.getId();
    }

    @Override
    public BandeiraCartao convertToEntityAttribute(Integer idSexo) {
        return BandeiraCartao.valueOf(idSexo);
    }
    
}
