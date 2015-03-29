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

    public boolean verificaSeJaExisteSalaPorNumero(int numero){
        for (Sala sala : salas){
            if(sala.getNumero() == numero){
                return (true);
            }
        }
        return (false);
    }
    public Sala buscaSalaPorNumero(int numero){
        for (Sala sala : salas){
            if(sala.getNumero() == numero){
                return sala;
            }
        }
        return null;
    }

}
