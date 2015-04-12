package model;

/**
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class Sala {

    private String numero;
    private int qtdAssentos;

    public Sala(String numero, int qtdAssentos) {
        this.numero = numero;
        this.qtdAssentos = qtdAssentos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }

    public void diminuiAssentos(){
        this.qtdAssentos--;
    }

}
