package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
public class MateriaDAO {
    private static final Logger logger = Logger.getLogger(MateriaDAO.class.getName());

    public void cadastrarMateria(MateriaDTO materia) {
        String sql = "INSERT INTO materia (nome, professor, nota_media, aulas_semana) VALUES (?, ?, ?, ?)";
        try (
                Connection conexao = Conexao.conectaDB();
                PreparedStatement ps = conexao.prepareStatement(sql)
        ) {
            ps.setString(1, materia.getNome());
            ps.setString(2, materia.getProfessor());
            ps.setDouble(3, materia.getNotaMedia());
            ps.setInt(4, materia.getAulasSemana());
            ps.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, excecao);
        }
    }

    ArrayList<MateriaDTO> listaMaterias = new ArrayList<>();
    public ArrayList<MateriaDTO> listarMaterias(){
        String sql = "SELECT * from materia";

        try(Connection c = new Conexao().conectaDB(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                MateriaDTO materia = new MateriaDTO();
                materia.setId(rs.getInt("id"));
                materia.setNome(rs.getString("nome"));
                materia.setProfessor(rs.getString("professor"));
                materia.setNotaMedia(rs.getDouble("nota_media"));
                materia.setAulasSemana(rs.getInt("aulas_semana"));
                listaMaterias.add(materia);}
            } catch (SQLException e) {
                logger.log(Level.SEVERE,"Erro ao listar materia",e);
            }
            return listarMaterias();

    }
    //Metodo para alterar uma materia
    public void alterarMateria(MateriaDTO materia) {
        String sql = "UPDATE materia SET nome = ?, professor = ?, nota_media = ?, aulas_semana = ? WHERE id = ?";

        try (
                Connection conexao = Conexao.conectaDB();
                PreparedStatement ps = conexao.prepareStatement(sql)
        ) {
            ps.setString(1, materia.getNome());
            ps.setString(2, materia.getProfessor());
            ps.setDouble(3, materia.getNotaMedia());
            ps.setInt(4, materia.getAulasSemana());
            ps.setInt(5, materia.getId());
            ps.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, excecao);
        }
    }

    //Metodo para deletar uma materia
    public void deletarMateria(int id) {
        String sql = "DELETE FROM materia WHERE id = ?";

        try (
                Connection conexao = Conexao.conectaDB();
                PreparedStatement ps = conexao.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, excecao);
        }
    }
}
