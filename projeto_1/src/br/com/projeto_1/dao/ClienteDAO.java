
package br.com.projeto_1.dao;

import java.sql.*;
import br.com.projeto_1.dto.ClienteDTO; 

public class ClienteDAO {
    
    public ClienteDAO(){
    
    }
    
    // atributo do tipo resultSet para realizar consultas
    private ResultSet rs = null;
    //manipular o banco de dados
    private Statement stnt = null;
    
    public boolean inserirCliente(ClienteDTO clienteDTO){
        try{
            ConexaoDAO.ConnectDB(); // chama o metodo que esta dentro da classe para abrir o banco
            
            stnt = ConexaoDAO.con.createStatement(); //
            
            //Comando SQL que vai para o banco de dados
            String comando = "Insert into cliente(nome_cli, logradouro_cli, numero_cli,"
                    + "bairro_cli, cidade_cli, estado_cli, cep_cli, cpf_cli,rg_cli) values ("
                    + "'" + clienteDTO.getNome_cli() + "', "
                    + "'" + clienteDTO.getLogradouro_cli() + "', "
                    + clienteDTO.getNumero_cli() + ", "
                    + "'" + clienteDTO.getBairro_cli() + "', "
                    + "'" + clienteDTO.getCidade_cli() + "', "
                    + "'" + clienteDTO.getEstado_cli() + "', "
                    + "'" + clienteDTO.getCep_cli() + "', "
                    + "'" + clienteDTO.getCpf_cli() + "', "
                    + "'" + clienteDTO.getRg_cli() + "') ";
            
            //executa o comando SQL no banco
            stnt.execute(comando.toUpperCase());
            
            //Da um commit no banco de dados
            ConexaoDAO.con.commit();
            
            //fecha o statement
            stnt.close();
            return true;
            //Caso tenha algum erro no código acima, é enviado uma mensagem do que está acontecendo no console
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            //Sai do banco de dados independentemente se der
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao){
        try{
            ConexaoDAO.ConnectDB();
            //Cria o Statement que é responsavel por executar alguma coisa no banco de dados
            stnt = ConexaoDAO.con.createStatement();
            //Comando SQL que sera executado no banco de dados
            String comando = "";
            
            switch(opcao){
                case 1:
                    comando = "Select c.* " +
                              "from cliente c " +
                              "where nome_cli like '" + clienteDTO.getNome_cli() + "%'" +
                              "order by c.nome_cli";
                break;
                case 2:
                        comando = "Select .*" +
                                  "from cliente c" +
                                  "where c.id_cli " + clienteDTO.getId_cli();
                break;
                case 3:
                        comando = "Select c.id_cli, c.nome_cli" +
                                  "from cliente c";
                break;
            }
            //executa o comando SQL no banco de dados
            rs = stnt.executeQuery(comando.toUpperCase());
            return rs;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return rs;
        }
    } 
    
} // fecha classe ClienteDAO
