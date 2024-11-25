package br.unitins.tp1.faixas.Conta.model;

public enum Perfil {
    ADMIN(1,"Adm"),
    USER(2,"User");

    private final Integer id;
    private final String descricao;

    Perfil(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
   

    public Integer getId() {
        return id;
    }


    public String getDescricao() {
        return descricao;
    }


    public static Perfil valueOf(Integer id) throws IllegalArgumentException{

        if(id==null){
            return null;
        }
        for(Perfil perfil : values()){
            if(id==perfil.getId())
                return perfil;
        }
        throw new IllegalArgumentException("Numero fora das opções disponíveis");
    }
}