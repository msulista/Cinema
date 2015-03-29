package repositorio;

import model.Filme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class RepositorioFilme {

    private List<Filme> filmes;

    public RepositorioFilme() {
        this.filmes = new ArrayList<Filme>();
    }

    public boolean addFilmes(Filme filme){
        return (filmes.add(filme));
    }

    public List<Filme> getFilmes(){
        return filmes;
    }

    public boolean verificaSeJaExisteFilmePorTitulo(String titulo){
        for (Filme filme : filmes){
            if(filme.getTitulo().equalsIgnoreCase(titulo)){
                return (true);
            }
        }
        return (false);
    }
    public Filme buscarFilmePorTitulo(String titulo){
        for (Filme filme : filmes){
            if(filme.getTitulo().equalsIgnoreCase(titulo)){
                return filme;
            }
        }
        return null;
    }

    public boolean verificaSeJaExisteFilmePorCodigo(int codigo){
        for(Filme filme : filmes){
            if (filme.getCodigo() == codigo){
                return (true);
            }
        }
        return (false);
    }
    public Filme buscaFilmePorCodigo(int codigo){
        for (Filme filme : filmes){
            if (filme.getCodigo() == codigo){
                return filme;
            }
        }
        return null;
    }
}
