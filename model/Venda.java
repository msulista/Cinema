package model;

/**
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class Venda {

    private Secao secao;
    private int contadorAssentos;
    private double valor;

    public Venda(Secao secao, double valor) {
        this.secao = secao;
        this.contadorAssentos = contadorAssentos;
        this.valor = valor;
    }

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

}
