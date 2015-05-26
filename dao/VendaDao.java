package dao;

import model.Venda;

import java.util.Date;
import java.util.List;

/**
 * Created by marcus.rodrigues on 24/05/2015.
 */
public interface VendaDao {

    public void inserir(Venda venda, Date data,String hora, int id_sessao);
    public void deletar(Venda venda);
    public void atualizar(Venda venda, int id_venda);
    public List<Venda> buscaVendasPorCodFilme(int cod_filme);
    public Venda buscaPorID(int id);
    public List<Venda> listar();
    public int retornaIDVenda(String num_sala, String hora_sessao);
}
