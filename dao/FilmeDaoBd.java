package dao;

import model.Filme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus.rodrigues on 16/05/2015.
 */
public class FilmeDaoBd implements FilmeDao {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void inserir(Filme filme) {

        String sql = "INSERT INTO Filme (codigo, titulo, genero, sinopse, data_Inicio, data_Fim) VALUES(?,?,?,?,?,?)";
        try {
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, filme.getCodigo());
            comando.setString(2, filme.getTitulo());
            comando.setString(3, filme.getGenero());
            comando.setString(4, filme.getSinopse());
            comando.setDate(5, (Date) filme.getDataInicio());
            comando.setDate(6, (Date) filme.getDataTermino());
            comando.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deletar(Filme filme) {

        String sql = "DELETE FROM Filme WHERE codigo = ?";
        try {
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, filme.getCodigo());
            comando.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Filme filme) {

        String sql = "UPDATE Filme SET codigo=?, titulo=?, genero=?, sinopse=?, data_Inicio=?, data_Fim=?";
        try {
            comando = conexao.prepareStatement(sql);
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, filme.getCodigo());
            comando.setString(2, filme.getTitulo());
            comando.setString(3, filme.getGenero());
            comando.setString(4, filme.getSinopse());
            comando.setDate(5, (Date) filme.getDataInicio());
            comando.setDate(6, (Date) filme.getDataTermino());
            comando.executeUpdate();
            conexao.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Filme buscarPorCodigo(int codigo) {
        Filme filme = null;
        String sql = "SELECT * FROM Filme WHERE codigo = ?";
        try {
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, codigo);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                filme = new Filme(resultado.getInt("codigo"),
                        resultado.getString("titulo"),
                        resultado.getString("genero"),
                        resultado.getString("sinopse"),
                        resultado.getDate("data_inicio"),
                        resultado.getDate("data_fim"));
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filme;
    }

    @Override
    public List<Filme> buscaPorGenero(String genero) {

        List<Filme> filmeList = new ArrayList<Filme>();
        String sql = "SELECT * FROM Filme WHERE genero = ?";
        try {
            comando = conexao.prepareStatement(sql);
            comando.setString(1, genero);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                Filme filme = new Filme(resultado.getInt("codigo"),
                        resultado.getString("titulo"),
                        resultado.getString("genero"),
                        resultado.getString("sinopse"),
                        resultado.getDate("data_inicio"),
                        resultado.getDate("data_fim"));
                filmeList.add(filme);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmeList;
    }

    @Override
    public List<Filme> listar() {
        List<Filme> filmeList = new ArrayList<Filme>();
        String sql = "SELECT * FROM Filme";
        try {
            comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                Filme filme = new Filme(resultado.getInt("codigo"),
                        resultado.getString("titulo"),
                        resultado.getString("genero"),
                        resultado.getString("sinopse"),
                        resultado.getDate("data_inicio"),
                        resultado.getDate("data_fim"));
                filmeList.add(filme);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmeList;
    }
}
