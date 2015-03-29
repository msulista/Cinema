package model;

import java.util.Date;

/**
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class Secao {

    private Sala sala;
    private Filme filme;
    private Date horario;

    public Secao(Sala sala, Filme filme, Date horario) {
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }
}
