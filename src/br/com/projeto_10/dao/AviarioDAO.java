/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_10.dao;
import java.sql.*;
import br.com.projeto_10.dto.AviarioDTO;
import java.text.SimpleDateFormat;
/**
 *
 * @author gusta
 */
public class AviarioDAO {
    
    public AviarioDAO() {
    }
    
    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    
    private ResultSet rs = null;
    
    private Statement stmt = null;
    
    public boolean inserirAviario(AviarioDTO aviarioDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into aviario (ninhada_avi, raca_avi, "
                    + "cor_avi, raca2_avi, cor2_avi, data_nas2_avi, data_nas_avi ) values ( "
                    + "'" + aviarioDTO.getNinhada_avi()+ "', "
                    + "'" + aviarioDTO.getRaca_avi()+ "', "
                    + "'" + aviarioDTO.getCor_avi()+ "', "
                    + "'" + aviarioDTO.getRaca2_avi()+ "', "
                    + "'" + aviarioDTO.getCor2_avi()+ "', "
                    + "to_date ('" + data_format.format(aviarioDTO.getData_nas2_avi()) + "','dd/mm/yyyy'), "
                    + "to_date('" + data_format.format(aviarioDTO.getData_nas_avi()) + "','dd/mm/yyyy')) ";
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
            
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean alterarAviario(AviarioDTO aviarioDTO){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update aviario set "
                    + "ninhada_avi = '" + aviarioDTO.getNinhada_avi()+ "', "
                    + "raca_avi = '" + aviarioDTO.getRaca_avi()+ "', "
                    + "cor_avi = '" + aviarioDTO.getCor_avi()+ "', "
                    + "raca2_avi = '" + aviarioDTO.getRaca2_avi()+ "', "
                    + "cor2_avi = '" + aviarioDTO.getCor2_avi()+ "', "
                    + "data_nas2_avi = to_date ('" + data_format.format(aviarioDTO.getData_nas2_avi())+"','dd/mm/yyyy'), "
                    + "data_nas_avi = to_date ('" + data_format.format(aviarioDTO.getData_nas_avi())+"','dd/mm/yyyy') "
                    + "where id_avi = " + aviarioDTO.getId_avi();
            
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
    
    public boolean excluirAviario(AviarioDTO aviarioDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from aviario where id_avi = "
                    + aviarioDTO.getId_avi();
            
            stmt.execute(comando);
            
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
    
    public ResultSet consultarAviario(AviarioDTO aviarioDTO, int opcao){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch (opcao){
                case 1:
                    comando = "Select a.id_avi, a.ninhada_avi "+
                            "from aviario a "+
                            "where a.ninhada_avi ilike '" + aviarioDTO.getNinhada_avi()+ "%' " +
                            "order by a.ninhada_avi";
                    break;
                case 2:
                    comando = "Select a.ninhada_avi, a.raca_avi, a.cor_avi, a.raca2_avi, a.cor2_avi, "+
                            "to_char(a.data_nas_avi, 'dd/mm/yyyy') as data_nas_avi, "+
                            "to_char(a.data_nas2_avi, 'dd/mm/yyyy') as data_nas2_avi " +
                            "from aviario a " +
                            "where a.id_avi = " + aviarioDTO.getId_avi();
                    break;
            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
}
