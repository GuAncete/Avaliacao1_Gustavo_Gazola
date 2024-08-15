/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_10.dao;
import java.sql.*;
import br.com.projeto_10.dto.CompradorDTO;

import java.util.InputMismatchException;


import java.util.Scanner;
import static java.lang.Math.toIntExact;
import java.util.stream.IntStream;
import javax.swing.JOptionPane;


/**
 *
 * @author gusta
 */
public class CompradorDAO {
    
       
    
    
    public CompradorDAO(){
        
    }
    private ResultSet rs = null;
    
    private Statement stmt = null;
    
    public boolean inserirComprador(CompradorDTO CompradorDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into Comprador (nome_com, logradouro_com, numero_com, "
                    + "bairro_com, cidade_com, estado_com, cep_com, cpf_com, rg_com, celular_com ) values ( "
                    + "'" + CompradorDTO.getNome_com() + "', "
                    + "'" + CompradorDTO.getLogradouro_com()+ "', "
                    + CompradorDTO.getNumero_com()+ ", "
                    + "'" + CompradorDTO.getBairro_com()+ "', "
                    + "'" + CompradorDTO.getCidade_com()+ "', "
                    + "'" + CompradorDTO.getEstado_com()+ "', "
                    + "'" + CompradorDTO.getCep_com()+ "', "
                    + "'" + CompradorDTO.getCpf_com()+ "', "
                    + "'" + CompradorDTO.getRg_com()+ "', "
                    + "'" + CompradorDTO.getCelular_com()+ "') ";
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
                    
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean alterarComprador(CompradorDTO CompradorDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update Comprador set "
                    + "nome_com = '" + CompradorDTO.getNome_com()+ "', "
                    + "logradouro_com = '" + CompradorDTO.getLogradouro_com()+ "', "
                    + "numero_com = " + CompradorDTO.getNumero_com()+ ", "
                    + "bairro_com = '" + CompradorDTO.getBairro_com()+ "', "
                    + "cidade_com = '" + CompradorDTO.getCidade_com()+ "', "
                    + "estado_com = '" + CompradorDTO.getEstado_com()+ "', "
                    + "cep_com = '" + CompradorDTO.getCep_com()+ "', "
                    + "cpf_com = '" + CompradorDTO.getCpf_com()+ "', "
                    + "rg_com = '" + CompradorDTO.getRg_com()+ "', "
                    + "celular_com = '" + CompradorDTO.getCelular_com()+ "' "
                    + "where id_com = " + CompradorDTO.getId_com();
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirComprador(CompradorDTO CompradorDTO){
        try{
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from Comprador where id_com = "
                    + CompradorDTO.getId_com();
            stmt.execute(comando);
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarComprador(CompradorDTO CompradorDTO, int opcao){
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            
            switch (opcao){
                case 1:
                    comando = "Select c.* "+
                            "from Comprador c " +
                            "where nome_com like '" + CompradorDTO.getNome_com()+ "%' " +
                            "order by c.nome_com";
                    break;
                case 2:
                    comando = "Select c.* "+
                            "from Comprador c " +
                            "where c.id_com = " + CompradorDTO.getId_com();
                    break;
                case 3:
                    comando = "Select c.id_com, c.nome_com "+
                            "from Comprador c ";
                    break;
                            
            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
   

 
    }


