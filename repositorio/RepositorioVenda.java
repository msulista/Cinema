package repositorio;

import model.Secao;
import model.Venda;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe RepositorioVenda representa um banco de dados onde serão armazenados as Vendas que
 * o Cine Up realizar.
 * Created by marcus.rodrigues on 11/04/2015.
 */
public class RepositorioVenda {

    private List<Venda> listaDeVendas;
    private Secao sessao;
    private double valorFinal;
    private final double DESCONTO_MEIA_ENTRADA = 0.5;

    /**
     * Método construtor de RepositorioVenda
     *
     * Responsavél por criar um ArrayList de vendas e inicializar o valorFinal cm 0.
     */
    public RepositorioVenda() {
        this.sessao = sessao;
        this.listaDeVendas = new ArrayList<Venda>();
        this.valorFinal = 0.0;
    }

    /**
     * Método addVendas adiciona a venda a lista de vendas
     * @param venda do tipo Venda
     */
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

    /**
     * Método ValorIngressoComDesconto responsavél por calcular o valor do desconto.
     *
     * @param sessao do tipo Secao
     * @return valorFinal do ingresso com o desconto calculado.
     */
    public double ValorIngressoComDesconto(Secao sessao){
        if (sessao.getSala().getQtdAssentos() > 0){
            this.valorFinal = sessao.getValor() * DESCONTO_MEIA_ENTRADA;
        }
        return (valorFinal);
    }

    /**
     * Métdo ValorIngressoSemDesconto responsével por atribuir o valor do ingresso.
     *
     * @param sessao do tipo Secao
     * @return valorFinal com o valor do ingresso.
     */
    public double valorIngressoSemDesconto(Secao sessao){
        if (sessao.getSala().getQtdAssentos() > 0){
            this.valorFinal = sessao.getValor();
        }
        return (valorFinal);
    }
}
