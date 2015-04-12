package repositorio;

import model.Secao;
import model.Venda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus.rodrigues on 11/04/2015.
 */
public class RepositorioVenda {

    private List<Venda> listaDeVendas;
    private Secao sessao;
    private double valorFinal;
    private final double DESCONTO_MEIA_ENTRADA = 0.5;

    public RepositorioVenda() {
        this.sessao = sessao;
        this.listaDeVendas = new ArrayList<Venda>();
        this.valorFinal = 0.0;
    }

    public void addVendas(Venda venda){
        this.listaDeVendas.add(venda);
    }

    public List<Venda> getVenda(){
        return listaDeVendas;
    }
    public Secao getSessao() {
        return sessao;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public double ValorIngressoComDesconto(Secao sessao){
        if (sessao.getSala().getQtdAssentos() > 0){
            this.valorFinal = sessao.getValor() * DESCONTO_MEIA_ENTRADA;
        }
        return (valorFinal);
    }

    public double valorIngressoSemDesconto(Secao sessao){
        if (sessao.getSala().getQtdAssentos() > 0){
            this.valorFinal = sessao.getValor();
        }
        return (valorFinal);
    }
}
