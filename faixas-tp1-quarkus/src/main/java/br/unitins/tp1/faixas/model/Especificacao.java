package br.unitins.tp1.faixas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
public class Especificacao extends DefaultEntity {
        @Column(length = 16, nullable = false)
        private String sku;

        @Column(nullable = false)
        private Integer comprimento;
        
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

        public Integer getComprimento() {
            return comprimento;
        }

        public void setComprimento(Integer comprimento) {
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
