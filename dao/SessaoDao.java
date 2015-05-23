package dao;

import model.Secao;

import java.util.Date;
import java.util.List;

/**
 * Created by marcus.rodrigues on 16/05/2015.
 */
public interface SessaoDao {

    public void inserir(Secao secao, String horaSql, String numSala, int codFilme);
    public void deletar(Secao secao);
    public void atualizar(Secao secao);
    public List<Secao> buscaPorHorarioEFilme(String titulo);
    public List<Secao> buscaPorHorario(Date horario);
    public List<Secao> listar();
}
