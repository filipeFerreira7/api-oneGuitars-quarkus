package br.unitins.tp1.faixas.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity                     // Habilita recursos de inserção, alteração
public class Faixa extends PanacheEntityBase {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //gerar auto increment da table
        private long id;
        private String nome;

        private Double preco;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Double getPreco() {
            return preco;
        }

        public void setPreco(Double preco) {
            this.preco = preco;
        }

        

}
