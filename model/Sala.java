package model;

/**
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class Sala {

    private int numero;
    private int qtdAssentos;

    public Sala(int numero, int qtdAssentos) {
        this.numero = numero;
        this.qtdAssentos = qtdAssentos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }
}
