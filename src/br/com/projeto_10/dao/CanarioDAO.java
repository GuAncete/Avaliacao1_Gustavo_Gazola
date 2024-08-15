/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_10.dao;
import java.sql.*;
import br.com.projeto_10.dto.CanarioDTO;
import br.com.projeto_10.dto.AviarioDTO;

/**
 *
 * @author gusta
 */
public class CanarioDAO {
    
    
    
    public CanarioDAO(){
        
    }
    private ResultSet rs = null;
    
    private Statement stmt = null;
    
    public boolean inserirCanario(CanarioDTO canarioDTO, AviarioDTO aviarioDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into canario (raca_can, obs_can, sexo_can, cor_can, "
                    + "anel_can, c_custo_can, c_venda_can, id_avi) values ( "
                    + "'" + canarioDTO.getRaca_can()+ "', "
                    + "'" + canarioDTO.getObs_can()+ "', "
                    + "'" + canarioDTO.getSexo_can()+ "', "
                    + "'" + canarioDTO.getCor_can()+ "', "
                    + "'" + canarioDTO.getAnel_can()+ "', "
                    + canarioDTO.getC_custo_can()+ ", "
                    + canarioDTO.getC_venda_can()+ ", "
                    + aviarioDTO.getId_avi()+ ") ";
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
    
    public boolean alterarCanario(CanarioDTO canarioDTO, AviarioDTO aviarioDTO){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update canario set "
                    + "raca_can = '" + canarioDTO.getRaca_can()+ "', "
                    + "obs_can = '" + canarioDTO.getObs_can()+ "', "
                    + "sexo_can = '" + canarioDTO.getSexo_can()+ "', "
                    + "cor_can = '" + canarioDTO.getCor_can()+ "', "
                    + "anel_can = '" + canarioDTO.getAnel_can()+ "', "
                    + "c_custo_can = " + canarioDTO.getC_custo_can()+ ", "
                    + "c_venda_can = " + canarioDTO.getC_venda_can()+ ", "
                    + "id_avi = " + canarioDTO.getId_can()+ " "
                    + "where id_can = " + canarioDTO.getId_can();
            
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
    
    public boolean excluirCanario(CanarioDTO canarioDTO){
        try{
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from canario where id_can = "
                    + canarioDTO.getId_can();
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
    
    public ResultSet consultarCanario(CanarioDTO canarioDTO, int opcao){
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            
            switch (opcao){
                case 1:
                    comando = "Select c.* "+
                            "from canario c " +
                            "where c.raca_can ilike '" + canarioDTO.getRaca_can()+ "%' " +
                            "order by c.raca_can";
                    break;
                case 2: //to_char(dataenc_cac, 'dd/mm/yyyy') as data_enc
                    comando = "Select c.*, a.ninhada_avi, a.id_avi  "+
                            "from canario c, aviario a " +
                            "where c.id_avi = a.id_avi and " +
                            "c.id_can = " + canarioDTO.getId_can();
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
