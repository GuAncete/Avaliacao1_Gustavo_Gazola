/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_10.ctr;

import br.com.projeto_10.dao.ConexaoDAO;
import br.com.projeto_10.dao.CanarioDAO;
import br.com.projeto_10.dto.CanarioDTO;
import br.com.projeto_10.dto.AviarioDTO;
import java.sql.ResultSet;

/**
 *
 * @author gusta
 */
public class CanarioCTR {
        CanarioDAO canarioDAO = new CanarioDAO();

    public CanarioCTR() {

    }

    public String inserirCanario(CanarioDTO canarioDTO, AviarioDTO aviarioDTO) {
        try {
            if (canarioDAO.inserirCanario(canarioDTO, aviarioDTO)) {
                return "Canario Cadastrado com sucesso!!!";
            } else {
                return "Canario NÃO Cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Canario NÃO Cadastrado";
        }
    }

    public String alterarCanario(CanarioDTO canarioDTO, AviarioDTO aviarioDTO) {
        try {
            if (canarioDAO.alterarCanario(canarioDTO, aviarioDTO)) {
                return "Canario Alterado com sucesso!!!";
            } else {
                return "Canario NÃO Alterado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Canario NÃO Alterado";
        }
    }

    public String excluirCanario(CanarioDTO canarioDTO) {
        try {
            if (canarioDAO.excluirCanario(canarioDTO)) {
                return "Canario Excluido com sucesso!!!";
            } else {
                return "Canario NÃO Excluido!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Canario NÃO Excluido";
        }
    }
    
    public ResultSet consultarCanario(CanarioDTO canarioDTO, int opcao){
        ResultSet rs = null;
        rs = canarioDAO.consultarCanario(canarioDTO, opcao);
        return rs;
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}
