package br.unitins.tp1.oneGuitars.Especificacao.model;


import br.unitins.tp1.oneGuitars.Usuario.service.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
public class Especificacao extends DefaultEntity {
        @Column(length = 16, nullable = false, unique = true)
        private String sku;

        @Column(nullable = false)
        private Double comprimento;
        
        @Column(nullable = false)
        private String tipoMadeira;
        
        @Column(nullable = false)
        private String tipoCaptador;
        
        @Column(nullable = false)
        private String tipoChave;

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public Double getComprimento() {
            return comprimento;
        }

        public void setComprimento(Double comprimento) {
            this.comprimento = comprimento;
        }

        public String getTipoMadeira() {
            return tipoMadeira;
        }

        public void setTipoMadeira(String tipoMadeira) {
            this.tipoMadeira = tipoMadeira;
        }

        public String getTipoCaptador() {
            return tipoCaptador;
        }

        public void setTipoCaptador(String tipoCaptador) {
            this.tipoCaptador = tipoCaptador;
        }

        public String getTipoChave() {
            return tipoChave;
        }

        public void setTipoChave(String tipoChave) {
            this.tipoChave = tipoChave;
        }



       

        
    
}
