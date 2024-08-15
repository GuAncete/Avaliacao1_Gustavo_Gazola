/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_10.ctr;
import br.com.projeto_10.dao.ConexaoDAO;
import br.com.projeto_10.dao.VendaDAO;
import br.com.projeto_10.dto.VendaDTO;
import br.com.projeto_10.dto.CompradorDTO;
import javax.swing.JTable;
/**
 *
 * @author gusta
 */
public class VendaCTR {
    VendaDAO vendaDAO = new VendaDAO();
    
    public VendaCTR(){
        
    }
    
    public String inserirVenda(VendaDTO vendaDTO, CompradorDTO compradorDTO, JTable canario){
        try{
            if(vendaDAO.inserirVenda(vendaDTO, compradorDTO, canario)){
                return "Venda Cadastrada com Sucesso!!!";
            } else {
                return "Venda NÃO Cadastrada";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Venda NÃO Cadastrada";
        }
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}
