package br.unitins.tp1.faixas.PessoaFisica.model;

public enum Sexo {
    MASCULINO(1,"Masculino"),
    FEMININO(2,"Feminino");

    private final Integer id;
    private final String descricao;

    Sexo(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
   

    public Integer getId() {
        return id;
    }


    public String getDescricao() {
        return descricao;
    }


    public static Sexo valueOf(Integer id) throws IllegalArgumentException{

        if(id==null){
            return null;
        }
        for(Sexo sexo : values()){
            if(id==sexo.getId())
                return sexo;
        }
        throw new IllegalArgumentException("Numero fora das opções disponíveis");
    }
}