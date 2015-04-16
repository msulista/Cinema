package repositorio;

import model.Secao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe RepositorioSecao representa um banco de dados onde serão armazenados as Sessoes que
 * o Cine Up possui.
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class RepositorioSecao {
    
    private List<Secao> secoes;

    /**
     * Método construtor de RepositorioSecao
     *
     * Responsavel por criar um ArrayList de secoes
     */
    public RepositorioSecao() {
        this.secoes = new ArrayList<Secao>();
    }

    /**
     * Método adicionaSecao adiciona a secao recebida por parametro na lista de socoes
     * @param secao parametro do tipo Secao
     * @return utiliza metodo add do ArrayLista para adicionar a secao no ArrayList
     */
    public boolean adicionaSecao(Secao secao){
        return (secoes.add(secao));
    }
    public List<Secao> getSecoes(){
        return secoes;
    }

    /**
     * Método verificaSeJaExisteSecaoPorTituloDoFilme este método recebe por parametro uma String de titulo e percorre
     * a lista testando cada elemento se existe um filme com o mesmo titulo.
     *
     * @param titulo String passada por parametro
     * @return true se filme for encontrado e false se não.
     */
    public boolean verificaSeJaExisteSecaoPorTituloDoFilme(String titulo){
        for (Secao secao : secoes){
            if(secao.getFilme().getTitulo().equalsIgnoreCase(titulo)){
                return (true);
            }
        }
        return (false);
    }

    /**
     * Método buscaSecaoPorTituloDoFilme percorre lista de sessoes buscando sessao cujo filme seja com o mesmo titulo.     *
     *
     * @param titulo do tipo String
     * @return se encontrar retorna secao se não null
     */
    public Secao buscaSecaoPorTituloDoFilme(String titulo){
        for (Secao secao : secoes){
            if (secao.getFilme().getTitulo().equalsIgnoreCase(titulo)){
                return secao;
            }
        }
        return null;
    }

    /**
     * Método verificaSeJaExisteSecaoPorHorario este método recebe por parametro um Date de horario e percorre
     * a lista testando cada elemento se existe uma sessao com o mesmo horario.
     *
     * @param horario do tipo Date
     * @return true se sessao existir senão false
     */
    public boolean verificaSeJaExisteSecaoPorHorario(Date horario){
        for (Secao secao : secoes){
            if (secao.getHorario().equals(horario)){
                return (true);
            }
        }
        return (false);            
    }

    /**
     * Método buscaSecaoPorCodFilmeEHorario recebe por parametro um int de codFilme e um Date de horario percorre
     * a lista de sessoes buscando sessao que contenha um filme com o mesmo cod e horario de apresentação.
     *
     * @param codFilme do tipo int
     * @param horario do tipo Date
     * @return secao se encontrar se não null.
     */
    public  Secao buscaSecaoPorCodFilmeEHorario(int codFilme, Date horario){
        for (Secao secao : secoes){
            if (secao.getFilme().getCodigo() == codFilme && secao.getHorario().equals(horario)){
                return secao;
            }
        }
        return null;
    }
}
