package dao;

import model.Filme;

import java.util.List;

/**
 * Created by marcus.rodrigues on 16/05/2015.
 */
public interface FilmeDao {

    public void inserir(Filme filme);
    public void deletar(Filme filme);
    public void atualizar(Filme filme);
    public Filme buscarPorCodigo(int codigo);
    public List<Filme> buscaPorGenero(String genero);
    public List<Filme> listar();
}
