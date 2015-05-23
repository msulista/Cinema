package dao;

import model.Sala;

import java.util.List;

/**
 * Created by marcus.rodrigues on 16/05/2015.
 */
public interface SalaDao {

    public void inserir(Sala sala);
    public void deletar(Sala sala);
    public void atualizar(Sala sala);
    public Sala buscarPorNumero(int numero);
    public Sala buscaPorID(int id);
    public List<Sala> listar();
}
