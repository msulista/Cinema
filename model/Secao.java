package model;

import java.util.Date;

/**
 * Classe Secao representa uma sessão que o Cine Up apresenta.
 *
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class Secao {

    private Sala sala;
    private Filme filme;
    private Date horario;
    private double valor;
    private int id;
    private int contadorDeCadeirasDisponiveis;

    /**
     * Método cosntrutor da classe Sessao
     *
     * @param sala é um atributo Sala que recebe um objeto de Sala que faz parte da Sessao criada.
     * @param filme é um atributo Filme que recebe um objeto de Filme que faz parte da Sessao criada.
     * @param horario é um atributo Date que recebe um horario que define a hora da Sessao criada.
     * @param valor é um atributo double que recebe um valor referente ao custo da Sessao criada.
     */
    public Secao(Sala sala, Filme filme, Date horario, double valor) {
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
        this.valor = valor;
    }
    public Secao(Sala sala, Filme filme, Date horario, double valor, int id) {
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
        this.valor = valor;
        this.id = id;
    }
    public Secao(Sala sala, Filme filme, Date horario, double valor, int id, int contadorDeCadeirasDisponiveis) {
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
        this.valor = valor;
        this.id = id;
        this.contadorDeCadeirasDisponiveis = contadorDeCadeirasDisponiveis;
    }

    //Métodos Getters e Setters
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public int getContadorDeCadeirasDisponiveis() {
        return contadorDeCadeirasDisponiveis;
    }

    public void setContadorDeCadeirasDisponiveis(int contadorDeCadeirasDisponiveis) {
        this.contadorDeCadeirasDisponiveis = contadorDeCadeirasDisponiveis;
    }
}
