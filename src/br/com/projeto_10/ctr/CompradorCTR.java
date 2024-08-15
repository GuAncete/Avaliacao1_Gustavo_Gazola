/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_10.ctr;

import br.com.projeto_10.dao.ConexaoDAO;
import br.com.projeto_10.dao.CompradorDAO;
import br.com.projeto_10.dto.CompradorDTO;
import java.sql.ResultSet;

/**
 *
 * @author gusta
 */
public class CompradorCTR {
        CompradorDAO CompradorDAO = new CompradorDAO();

    public CompradorCTR() {

    }

    public String inserirComprador(CompradorDTO CompradorDTO) {
        try {
            if (CompradorDAO.inserirComprador(CompradorDTO)) {
                return "Comprador Cadastrado com sucesso!!!";
            } else {
                return "Comprador NÃO Cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Comprador NÃO Cadastrado";
        }
    }

    public String alterarComprador(CompradorDTO CompradorDTO) {
        try {
            if (CompradorDAO.alterarComprador(CompradorDTO)) {
                return "Comprador Alterado com sucesso!!!";
            } else {
                return "Comprador NÃO Alterado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Comprador NÃO Alterado";
        }
    }

    public String excluirComprador(CompradorDTO CompradorDTO) {
        try {
            if (CompradorDAO.excluirComprador(CompradorDTO)) {
                return "Comprador Excluido com sucesso!!!";
            } else {
                return "Comprador NÃO Excluido!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Comprador NÃO Excluido";
        }
    }
    
    public ResultSet consultarComprador(CompradorDTO CompradorDTO, int opcao){
        ResultSet rs = null;
        rs = CompradorDAO.consultarComprador(CompradorDTO, opcao);
        return rs;
    }
    

    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}
