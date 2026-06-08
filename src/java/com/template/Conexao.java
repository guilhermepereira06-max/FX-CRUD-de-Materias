package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // String de conexão JDBC para o PostgreSQL — ajuste conforme seu ambiente
    static String conexao="jdbc:postgresql://localhost:5432/db_materia";
    // Usuário e senha do banco. Em um projeto real, externalize essas credenciais
    // para um arquivo de configuração ou variáveis de ambiente.
    static String usuario="postgres";
    static String senha="postgres";

    /**
     * Abre e retorna uma conexão com o banco.
     * Usa DriverManager diretamente conforme exemplo simples dos slides.
     */
    public static Connection conectaDB(){
        try{
            return DriverManager.getConnection(conexao,usuario,senha);
        }catch(SQLException e){
            // Em vez de propagar SQLException, lançamos RuntimeException para
            // simplificar o exemplo; em produção trate a exceção adequadamente.
            throw new RuntimeException(e.getMessage());
        }
    }
}
