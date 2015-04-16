package repositorio;

import model.Sala;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe RepositorioSala representa um banco de dados onde serão armazenados os Salas que
 * o Cine Up possui.
 *
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class RepositorioSala {

    private List<Sala> salas;

    /**
     * Método construtor de RepositórioSala
     *
     * responsavel por criar um ArrayList de sala.
     */
    public RepositorioSala() {
        this.salas = new ArrayList<Sala>();
    }
    /**
     * Método adicionaSala responsavel por adicionar um objeto de sala na lista de salas.
     *
     * @param sala parametro do tipo Sala
     * @return utiliza o metodo add do ArrayList para adicionar uma sala em salas.
     */
    public boolean adicionaSala(Sala sala){
        return (salas.add(sala));
    }
    //Método Getter
    public List<Sala> getSalas(){
        return salas;
    }
    /**
     * Método verificaSeJaExisteSalaPorNumero percorre a lista de salas testando cada elemento se existir
     * elemento com mesmo numero retorna true senão false.
     *
     * @param numero parametro do tipo String.
     * @return true se existir se não false.
     */
    public boolean verificaSeJaExisteSalaPorNumero(String numero){
        for (Sala sala : salas){
            if(sala.getNumero().equalsIgnoreCase(numero)){
                return (true);
            }
        }
        return (false);
    }

    /**
     * Método buscaSalaPorNumero
     * @param numero
     * @return
     */
    public Sala buscaSalaPorNumero(String numero){
        for (Sala sala : salas){
            if(sala.getNumero().equalsIgnoreCase(numero)){
                return sala;
            }
        }
        return null;
    }
}
