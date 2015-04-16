package repositorio;

import model.Filme;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe RepositorioFilme representa um banco de dados onde serão armazenados os filmes que
 * o Cine Up apresenta.
 *
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class RepositorioFilme {

    private List<Filme> filmes;

    /**
     * Método construtor de RepositorioFilme
     *
     * Este método apenas cria a lista de filmes.
     */
    public RepositorioFilme() {
        this.filmes = new ArrayList<Filme>();
    }

    /**
     * Médoto addFilmes - este método adiciona o filme criado a lista de filmes
     *
     * @param filme recebe por parametro o filme criado
     * @return o retorno é a chamada de método add da classe ArrayList passando por parametro filme.
     */
    public boolean addFilmes(Filme filme){
        return (filmes.add(filme));
    }

    public List<Filme> getFilmes(){
        return filmes;
    }

    /**
     * Método verificaSeJaExisteFilmePorTitulo este método recebe por parametro uma String de titulo e percorre
     * a lista testando cada elemento se existe um filme com o mesmo titulo.
     *
     * @param titulo String recebida por parametro
     * @return retorna true se titulo existir ou false se não for encontrado.
     */
    public boolean verificaSeJaExisteFilmePorTitulo(String titulo){
        for (Filme filme : filmes){
            if(filme.getTitulo().equalsIgnoreCase(titulo)){
                return (true);
            }
        }
        return (false);
    }

    /**
     * Método buscarFilmePorTitulo percorre a lista de filmes buscando por resultados com o mesmo titulo.
     *
     * @param titulo String recebida por parametro.
     * @return retorna um filme se existir, se não retorna null.
     */
    public Filme buscarFilmePorTitulo(String titulo){
        for (Filme filme : filmes){
            if(filme.getTitulo().equalsIgnoreCase(titulo)){
                return filme;
            }
        }
        return null;
    }

    /**
     * Método verificaSeJaExisteFilmePorCodigo este método recebe por parametro um int de codigo e percorre
     * a lista testando cada elemento se existe um filme com o mesmo codigo.
     *
     * @param codigo int recebido por parametro
     * @return true se encontrar e false se não existir
     */
    public boolean verificaSeJaExisteFilmePorCodigo(int codigo){
        for(Filme filme : filmes){
            if (filme.getCodigo() == codigo){
                return (true);
            }
        }
        return (false);
    }

    /**
     * Método buscarFilmePorCodigo percorre a lista de filmes buscando por resultados com o mesmo codigo.
     *
     * @param codigo parametro int recebido.
     * @return filme se existir e null se não existir.
     */
    public Filme buscaFilmePorCodigo(int codigo){
        for (Filme filme : filmes){
            if (filme.getCodigo() == codigo){
                return filme;
            }
        }
        return null;
    }
}
