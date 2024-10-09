package br.unitins.tp1.faixas.model;

public enum Sexo {
    MASCULINO(1,"Masculino"),
    FEMININO(2,"Feminino");

    private Integer id;
    private String descricao;

    Sexo(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Sexo valueOf(Integer id) throws IllegalArgumentException{

        if(id==null){
            return null;
        }
        for(Sexo sexo : values()){
            if(id==sexo.id)
                return sexo;
        }
        throw new IllegalArgumentException("Numero fora das opções disponíveis");
    }
}