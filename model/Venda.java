package model;

/**
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class Venda {

    private Secao secao;
    private int contadorAssentos;

    public Venda(Secao secao, int contadorAssentos) {
        this.secao = secao;
        this.contadorAssentos = contadorAssentos;
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
