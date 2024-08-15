/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_10.ctr;

import java.sql.ResultSet;
import br.com.projeto_10.dto.AviarioDTO;
import br.com.projeto_10.dao.AviarioDAO;
import br.com.projeto_10.dao.ConexaoDAO;

/**
 *
 * @author gusta
 */
public class AviarioCTR {

    AviarioDAO aviarioDAO = new AviarioDAO();

    public AviarioCTR() {

    }

    public String inserirAviario(AviarioDTO aviarioDTO) {
        try {
            if (aviarioDAO.inserirAviario(aviarioDTO)) {
                return "Aviario Cadastrado com sucesso!!!";
            } else {
                return "Aviario NÃO Cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Aviario NÃO Cadastrado";
        }
    }

    public String alterarAviario(AviarioDTO aviarioDTO) {
        try {
            if (aviarioDAO.alterarAviario(aviarioDTO)) {
                return "Aviario Alterado com sucesso!!!";
            } else {
                return "Aviario NÃO Alterado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Aviario NÃO Alterado";
        }
    }

    public String excluirAviario(AviarioDTO aviarioDTO) {
        try {
            if (aviarioDAO.excluirAviario(aviarioDTO)) {
                return "Aviario Excluido com sucesso!!!";
            } else {
                return "Aviario NÃO Excluido!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Aviario NÃO Excluido";
        }
    }
    
    public ResultSet consultarAviario(AviarioDTO aviarioDTO, int opcao){
        ResultSet rs = null;
        rs = aviarioDAO.consultarAviario(aviarioDTO, opcao);
        return rs;
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}
