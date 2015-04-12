package repositorio;

import model.Secao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class RepositorioSecao {
    
    private List<Secao> secoes;

    public RepositorioSecao() {
        this.secoes = new ArrayList<Secao>();
    }
    
    public boolean adicionaSecao(Secao secao){
        return (secoes.add(secao));
    }
    public List<Secao> getSecoes(){
        return secoes;
    }
    
    public boolean verificaSeJaExisteSecaoPorTituloDoFilme(String titulo){
        for (Secao secao : secoes){
            if(secao.getFilme().getTitulo().equalsIgnoreCase(titulo)){
                return (true);
            }
        }
        return (false);
    }
    public Secao buscaSecaoPorTituloDoFilme(String titulo){
        for (Secao secao : secoes){
            if (secao.getFilme().getTitulo().equalsIgnoreCase(titulo)){
                return secao;
            }
        }
        return null;
    }
    
    public boolean verificaSeJaExisteSecaoPorHorario(Date horario){
        for (Secao secao : secoes){
            if (secao.getHorario().equals(horario)){
                return (true);
            }
        }
        return (false);            
    }
    public  Secao buscaSecaoPorCodFilmeEHorario(int codFilme, Date horario){
        for (Secao secao : secoes){
            if (secao.getFilme().getCodigo() == codFilme && secao.getHorario().equals(horario)){
                return secao;
            }
        }
        return null;
    }
}
