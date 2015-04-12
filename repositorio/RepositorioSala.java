package repositorio;

import model.Sala;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class RepositorioSala {

    private List<Sala> salas;

    public RepositorioSala() {
        this.salas = new ArrayList<Sala>();
    }

    public boolean adicionaSala(Sala sala){
        return (salas.add(sala));
    }
    public List<Sala> getSalas(){
        return salas;
    }

    public boolean verificaSeJaExisteSalaPorNumero(String numero){
        for (Sala sala : salas){
            if(sala.getNumero().equalsIgnoreCase(numero)){
                return (true);
            }
        }
        return (false);
    }
    public Sala buscaSalaPorNumero(String numero){
        for (Sala sala : salas){
            if(sala.getNumero().equalsIgnoreCase(numero)){
                return sala;
            }
        }
        return null;
    }
}
