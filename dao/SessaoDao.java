package dao;

import model.Secao;

import java.util.Date;
import java.util.List;

/**
 * Created by marcus.rodrigues on 16/05/2015.
 */
public interface SessaoDao {

    public void inserir(Secao secao, String horaSql, String numSala, int codFilme);
    public void deletar(int id_sessao);
    public void atualizar(Secao secao, String horario, String numSala, int codFilme, int idSessao);
    public Secao buscaSessaoPorFilmeEHorario(int codFilme, String horario);
    public List<Secao> buscaSessaoPorCodFilme(int codFilme);
    public List<Secao> buscaSessaoPorHorario(String horario);
    public Secao buscaPorID(int id);
    public List<Secao> listar();
    public int retornaIDSessao(int id_sala, int id_filme, String hora);
}
