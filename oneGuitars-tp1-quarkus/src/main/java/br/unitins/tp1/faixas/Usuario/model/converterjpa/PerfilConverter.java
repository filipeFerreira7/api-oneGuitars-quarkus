package br.unitins.tp1.faixas.Usuario.model.converterjpa;

import br.unitins.tp1.faixas.Usuario.model.Perfil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PerfilConverter implements AttributeConverter<Perfil,Integer>{

    @Override
    public Integer convertToDatabaseColumn(Perfil Perfil) {
        return Perfil == null ? null : Perfil.getId();
    }

    @Override
    public Perfil convertToEntityAttribute(Integer idPerfil) {
        return Perfil.valueOf(idPerfil);
    }
    
}
