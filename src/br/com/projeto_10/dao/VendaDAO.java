/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_10.dao;
import br.com.projeto_10.dto.CompradorDTO;
import br.com.projeto_10.dto.VendaDTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
/**
 *
 * @author gusta
 */
public class VendaDAO {
    
    public VendaDAO(){
        
    }
    
    private ResultSet rs = null;
    
    Statement stmt = null;
    Statement stmt1 = null;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    
    public boolean inserirVenda(VendaDTO vendaDTO, CompradorDTO compradorDTO, JTable canario){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            stmt1 = ConexaoDAO.con.createStatement();
            
            String comando1 = "Insert into Venda (dat_vend, val_vend, "
                    + "id_com) values ( "
                    + "to_date('" + date.format(vendaDTO.getDat_vend()) + "', 'DD/MM/YYYY'), "
                    + vendaDTO.getVal_vend() + ", "
                    + compradorDTO.getId_com()+ ")";
            
            stmt.execute(comando1.toUpperCase(), Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            rs.next();
            
            for(int cont=0; cont < canario.getRowCount(); cont++){
                String comando2 = "Insert into canario_venda (id_vend, id_can, "
                        + "val_can, qtd_can) values ( "
                        + rs.getInt("id_vend") + ", "
                        + canario.getValueAt(cont, 0) + ", "
                        + canario.getValueAt(cont, 2) + ", "
                        + canario.getValueAt(cont, 3) + "); ";
                
                stmt1.execute(comando2);
            }
            ConexaoDAO.con.commit();
            
            stmt.close();
            stmt1.close();
            rs.close();
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
}
