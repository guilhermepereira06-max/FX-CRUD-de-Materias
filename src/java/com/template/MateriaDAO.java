package com.template;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
public class MateriaDAO {
    private static final Logger logger = Logger.getLogger(MateriaDAO.class.getName());

    ArrayList<MateriasDTO> listUsuarios = new ArrayList<>();
public ArrayList<MateriasDTO> listarMaterias(){
    String sql = "SELECT * from materias";

    try(Connection c = new Conexao().conectaBD(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
        while (rs.next()){
            MateriasDTO materia = new MateriaDTO();
            materia.setId(rs.getInt("id"));
            materia.setNome(rs.getString("nome"));
            materia.setEmail(rs.getString("email"));
            listarMaterias.add(materia);
        }catch (SQLException e){
            logger.log(Level.SEVERE,"Erro ao listar materia",e)
        }

        return listarMaterias();
    }

}
}
