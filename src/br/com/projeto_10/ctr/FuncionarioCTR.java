/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_10.ctr;
import java.sql.ResultSet;
import br.com.projeto_10.dto.FuncionarioDTO;
import br.com.projeto_10.dao.FuncionarioDAO;
import br.com.projeto_10.dao.ConexaoDAO;
/**
 *
 * @author gusta
 */
public class FuncionarioCTR {
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    public FuncionarioCTR(){
        
    }
    
    public String inserirFuncionario(FuncionarioDTO funcionarioDTO){
        try{
            if(funcionarioDAO.inserirFuncionario(funcionarioDTO)){
                return "Funcionario Cadastrado com sucesso!!!";
                
            } else {
                return "Funcionario NÃO Cadastrado!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Funcionario NÃO Cadastrado";
        }
    }
    
    public String alterarFuncionario(FuncionarioDTO funcionarioDTO){
        try{
            if (funcionarioDAO.alterarFuncionario(funcionarioDTO)) {
                return "Funcionario Alterado com sucesso!!!";
            } else {
                return "Funcionario NÃO Alterado!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Funcionario NÃO Alterado!!!";
        }
    }
    
    public String excluirFuncionario(FuncionarioDTO funcionarioDTO){
        try{
            if (funcionarioDAO.excluirFuncionario(funcionarioDTO)) {
                return "Funcionario Excluido com sucesso!!!";
            } else {
                return "Funcionario NÃO Excluido!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Funcionario NÃO Excluido!!!";
        }
    }
    
    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao){
        ResultSet rs = null;
        
        rs = funcionarioDAO.consultarFuncionario(funcionarioDTO, opcao);
        return rs;
    }
    
    public String logarFuncionario(FuncionarioDTO funcionarioDTO){
        return funcionarioDAO.logarFuncionario(funcionarioDTO);
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}
