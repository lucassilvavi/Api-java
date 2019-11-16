package com.example.SpringGradle.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotEmpty(message = "Nome não pode ser vazio!")
    @NotBlank(message = "Nome não pode ser vazio!")
    private String no_name;

    @Column
    private Integer nu_quantidade;
    @Column
    private Date dt_criacao;

    public Product() {

    }

    @PrePersist
    public void onPrePersist() {
        if (this.dt_criacao == null) {
            this.dt_criacao = new Date();
        }
    }

    public Product(String no_name, Integer nu_quantidade) {
        this.setNo_name(no_name);
        this.setNu_quantidade(nu_quantidade);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo_name() {
        return no_name;
    }

    public void setNo_name(String no_name) {
        this.no_name = no_name;
    }

    public Integer getNu_quantidade() {
        return nu_quantidade;
    }

    public void setNu_quantidade(Integer nu_quantidade) {
        this.nu_quantidade = nu_quantidade;
    }

    public Date getDt_criacao() {
        return dt_criacao;
    }

    public void setDt_criacao(Date dt_criacao) {
        this.dt_criacao = dt_criacao;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + this.id +
                ",name:" + this.no_name +
                ",quantidade:" + this.nu_quantidade +
                ",dt_criacao:" + this.dt_criacao +
                "}";
    }

}
