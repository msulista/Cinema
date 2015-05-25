package model;

/**
 * Classe Venda representa uma venda de ingresso do Cine Up
 *
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class Venda {

    private Secao secao;
    private int contadorAssentos;
    private double valor;
    private int id;

    /**
     * Método construtor da Classe Venda
     *
     * @param secao é um atributo Sessao que recebe um objeto de Sessao que faz parte da Venda criada.
     * @param valor é um atributo double que recebe um valor referente a venda com descontos ou não da Venda criada.
     */
    public Venda(Secao secao, double valor) {
        this.secao = secao;
        this.valor = valor;
    }
    public Venda(Secao secao, double valor, int id) {
        this.secao = secao;
        this.valor = valor;
        this.id = id;
    }

    //Métodos Getters e Setters
    public double getValor() {
        return valor;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public int getContadorAssentos() {
        return contadorAssentos;
    }

    public void setContadorAssentos(int contadorAssentos) {
        this.contadorAssentos = contadorAssentos;
    }

    public int getId() {
        return id;
    }
}
