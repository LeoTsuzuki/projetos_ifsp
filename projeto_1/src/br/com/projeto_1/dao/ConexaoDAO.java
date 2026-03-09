/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_1.dao;

import java.sql.*;

public class ConexaoDAO {

    public static Connection con = null;

    public ConexaoDAO() {

    }

    public static void CloseDB() {
        try {
            con.close();
        } catch (Exception e) {

        }
    }

    public static void ConnectDB() {
        try {
            String dsn = "projeto_1"; // nome do banco
            String user = "postgres"; // nome do usuario
            String senha = "postdba"; // senha para login

            DriverManager.registerDriver(new org.postgresql.Driver());

            String url = "jdbc:postgresql://localhost:5432/" + dsn;

            con = DriverManager.getConnection(url, user, senha);

            con.setAutoCommit(false);
            if (con == null) {
                System.out.println("erro ao abrir o banco");
            }
        } catch (Exception e) {
            System.out.println("Problema ao abrir a base de dados!" + e.getMessage());
        }
    }

}
