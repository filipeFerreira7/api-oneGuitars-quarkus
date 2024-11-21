package br.unitins.tp1.faixas.Pagamento.model;

public enum BandeiraCartao {
    VISA(1,"Visa"),
    MASTERCARD(2,"Mastercard"),
    ELO(3,"Elo"),
    HIPERCARD(4,"Hipercard"),
    DINERS(5,"Diners"),
    DISCOVER(6,"Discover"),
    JCB(7,"Jcb"),
    AMERICAN_EXPRESS(8,"American Express");

    private final Integer id;
    private final String descricao;

    BandeiraCartao(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
   

    public Integer getId() {
        return id;
    }


    public String getDescricao() {
        return descricao;
    }


    public static BandeiraCartao valueOf(Integer id) throws IllegalArgumentException{

        if(id==null){
            return null;
        }
        for(BandeiraCartao sexo : values()){
            if(id==sexo.getId())
                return sexo;
        }
        throw new IllegalArgumentException("Numero fora das opções disponíveis");
    }
}