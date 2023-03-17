package br.com.agenda.agenda.estudante;

import java.time.LocalDate;

public class Estudante {
    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dt_nascimento;
    private String profissao;

    public Estudante() {

    }

    public Estudante(Long id, String nome, String telefone, LocalDate dt_nascimento, String profissao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dt_nascimento = dt_nascimento;
        this.profissao = profissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDtNascimento() {
        return dt_nascimento;
    }

    public void setDtNascimento(LocalDate dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + nome + '\'' +
                ", phone='" + telefone + '\'' +
                ", birthDate=" + dt_nascimento +
                ", occupation='" + profissao + '\'' +
                '}';
    }
}
