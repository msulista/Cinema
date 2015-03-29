package model;

import java.util.Date;

/**
 * Created by marcus.rodrigues on 29/03/2015.
 */
public class Filme {

    private static int COD_FILME = 1;
    private int codigo;
    private String titulo;
    private String genero;
    private String sinopse;
    private Date dataInicio;
    private Date dataTermino;

    public Filme(int codigo, String titulo, String genero, String sinopse, Date dataInicio, Date dataTermino) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.genero = genero;
        this.sinopse = sinopse;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }
}
