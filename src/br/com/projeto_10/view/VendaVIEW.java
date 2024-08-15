/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_10.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import br.com.projeto_10.dto.VendaDTO;
import br.com.projeto_10.ctr.VendaCTR;
import br.com.projeto_10.dto.CanarioDTO;
import br.com.projeto_10.ctr.CanarioCTR;
import br.com.projeto_10.dto.CompradorDTO;
import br.com.projeto_10.ctr.CompradorCTR;

import java.util.Date;

/**
 *
 * @author gusta
 */
public class VendaVIEW extends javax.swing.JInternalFrame {

    VendaCTR vendaCTR = new VendaCTR();
    VendaDTO vendaDTO = new VendaDTO();
    CanarioCTR canarioCTR = new CanarioCTR();
    CanarioDTO canarioDTO = new CanarioDTO();
    CompradorCTR compradorCTR = new CompradorCTR();
    CompradorDTO compradorDTO = new CompradorDTO();

    ResultSet rs;
    int gravar_alterar;
    DefaultTableModel modelo_jtl_consultar_com;
    DefaultTableModel modelo_jtl_consultar_can;
    DefaultTableModel modelo_jtl_consultar_can_selecionado;

    /**
     * Creates new form VendaVIEW
     */
    public VendaVIEW() {
        initComponents();

        liberaCampos(false);
        liberaBotoes(true, false, false, true);

        modelo_jtl_consultar_com = (DefaultTableModel) jtl_consultar_com.getModel();
        modelo_jtl_consultar_can = (DefaultTableModel) jtl_consultar_can.getModel();
        modelo_jtl_consultar_can_selecionado = (DefaultTableModel) jtl_consultar_can_selecionado.getModel();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void gravar() {
        vendaDTO.setDat_vend(new Date());
        vendaDTO.setVal_vend(Double.parseDouble(TotalVenda.getText()));
        compradorDTO.setId_com(Integer.parseInt(String.valueOf(
                jtl_consultar_com.getValueAt(jtl_consultar_com.getSelectedRow(), 0))));

        JOptionPane.showMessageDialog(null,
                vendaCTR.inserirVenda(vendaDTO, compradorDTO, jtl_consultar_can_selecionado)
        );
    }

    private void preencheTabelaComprador(String nome_com) {
        try {
            modelo_jtl_consultar_com.setNumRows(0);

            compradorDTO.setNome_com(nome_com);
            rs = compradorCTR.consultarComprador(compradorDTO, 1);
            while (rs.next()) {
                modelo_jtl_consultar_com.addRow(new Object[]{
                    rs.getString("id_com"),
                    rs.getString("nome_com")
                });
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }

    private void preencheTabelaCanario(String raca_can) {
        try {
            modelo_jtl_consultar_can.setNumRows(0);
            canarioDTO.setRaca_can(raca_can);
            rs = canarioCTR.consultarCanario(canarioDTO, 1);

            while (rs.next()) {
                modelo_jtl_consultar_can.addRow(new Object[]{
                    rs.getString("id_can"),
                    rs.getString("raca_can"),
                    rs.getDouble("c_venda_can")
                });
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }

    private void calculaTotalVenda() {
        try {
            double total = 0;
            for (int cont = 0; cont < jtl_consultar_can_selecionado.getRowCount(); cont++) {
                total += (Double.parseDouble(String.valueOf(
                        jtl_consultar_can_selecionado.getValueAt(cont, 2)))
                        * Integer.parseInt(String.valueOf(
                                jtl_consultar_can_selecionado.getValueAt(cont, 3))));
            }
            TotalVenda.setText(String.valueOf(total));
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }

    private void adicionarProdutoSelecionado(int id_can, String raca_can, double c_venda_can) {
        try {
            modelo_jtl_consultar_can_selecionado.addRow(new Object[]{
                id_can,
                raca_can,
                c_venda_can
            });
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }

    private void removeProdutoSelecionado(int linha_selecionada) {
        try {
            if (linha_selecionada >= 0) {
                modelo_jtl_consultar_can_selecionado.removeRow(linha_selecionada);
                calculaTotalVenda();
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        }
    }

    private void liberaCampos(boolean a) {
        pesquisa_nome_com.setEnabled(a);
        btnPesquisarCom.setEnabled(a);
        jtl_consultar_com.setEnabled(a);
        pesquisa_raca_can.setEnabled(a);
        btnPesquisarCan.setEnabled(a);
        jtl_consultar_can.setEnabled(a);
        btnCanAdd.setEnabled(a);
        btnCanRem.setEnabled(a);
        jtl_consultar_can_selecionado.setEnabled(a);
        TotalVenda.setText("0.00");
    }

    private void limpaCampos() {
        pesquisa_nome_com.setText("");
        modelo_jtl_consultar_com.setNumRows(0);
        pesquisa_raca_can.setText("");
        modelo_jtl_consultar_can.setNumRows(0);
        modelo_jtl_consultar_can_selecionado.setNumRows(0);
    }

    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d) {
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnSair.setEnabled(d);
    }

    private boolean verificaPreenchimento() {
        if (jtl_consultar_com.getSelectedRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Deve ser selecionado um Comprador");
            jtl_consultar_com.requestFocus();
            return false;
        } else {
            int verifica = 0;
            for (int cont = 0; cont < jtl_consultar_can_selecionado.getRowCount(); cont++) {
                if (String.valueOf(jtl_consultar_can_selecionado.getValueAt(
                        cont, 3)).equalsIgnoreCase("null")) {
                    verifica++;
                }
            }
            if (verifica > 0) {
                JOptionPane.showMessageDialog(null,
                        "A quantidade de cada canario deve ser informado");
                jtl_consultar_can_selecionado.requestFocus();
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pesquisa_nome_com = new javax.swing.JTextField();
        btnPesquisarCom = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_com = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        TotalVenda = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pesquisa_raca_can = new javax.swing.JTextField();
        btnPesquisarCan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_can = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtl_consultar_can_selecionado = new javax.swing.JTable();
        btnCanAdd = new javax.swing.JButton();
        btnCanRem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        jLabel1.setText("Comprador:");

        btnPesquisarCom.setText("OK");
        btnPesquisarCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarComActionPerformed(evt);
            }
        });

        jtl_consultar_com.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_com);

        jLabel2.setFont(new java.awt.Font("MS UI Gothic", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Total Venda:");

        TotalVenda.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        TotalVenda.setForeground(new java.awt.Color(102, 255, 102));
        TotalVenda.setText("0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisa_nome_com, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPesquisarCom))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TotalVenda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pesquisa_nome_com, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TotalVenda))
                .addGap(38, 38, 38))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Canarios"));

        jLabel3.setText("Raça:");

        btnPesquisarCan.setText("OK");
        btnPesquisarCan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCanActionPerformed(evt);
            }
        });

        jtl_consultar_can.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Raça", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_can);

        jtl_consultar_can_selecionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Raça", "Valor", "QTD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_can_selecionado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtl_consultar_can_selecionadoKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtl_consultar_can_selecionado);

        btnCanAdd.setText("Comprar");
        btnCanAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanAddActionPerformed(evt);
            }
        });

        btnCanRem.setText("Retirar");
        btnCanRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanRemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_raca_can, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarCan)
                        .addGap(0, 91, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(btnCanAdd)
                .addGap(18, 18, 18)
                .addComponent(btnCanRem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pesquisa_raca_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCanAdd)
                    .addComponent(btnCanRem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_com.setNumRows(0);
        modelo_jtl_consultar_can.setNumRows(0);
        liberaBotoes(true, false, false, true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisarComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarComActionPerformed
        preencheTabelaComprador(pesquisa_nome_com.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarComActionPerformed

    private void btnPesquisarCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCanActionPerformed
        preencheTabelaCanario(pesquisa_raca_can.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarCanActionPerformed

    private void btnCanAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanAddActionPerformed
        adicionarProdutoSelecionado(
                Integer.parseInt(String.valueOf(jtl_consultar_can.getValueAt(
                        jtl_consultar_can.getSelectedRow(), 0))),
                String.valueOf(jtl_consultar_can.getValueAt(jtl_consultar_can.getSelectedRow(), 1))
                , Double.parseDouble(String.valueOf(jtl_consultar_can.getValueAt(
                jtl_consultar_can.getSelectedRow(), 2))));
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCanAddActionPerformed

    private void btnCanRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanRemActionPerformed
        removeProdutoSelecionado(jtl_consultar_can_selecionado.getSelectedRow());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCanRemActionPerformed

    private void jtl_consultar_can_selecionadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtl_consultar_can_selecionadoKeyReleased
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            calculaTotalVenda();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jtl_consultar_can_selecionadoKeyReleased

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (verificaPreenchimento()) {
            gravar();
            limpaCampos();
            liberaCampos(true);
            liberaBotoes(true, false, false, true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotalVenda;
    private javax.swing.JButton btnCanAdd;
    private javax.swing.JButton btnCanRem;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarCan;
    private javax.swing.JButton btnPesquisarCom;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtl_consultar_can;
    private javax.swing.JTable jtl_consultar_can_selecionado;
    private javax.swing.JTable jtl_consultar_com;
    private javax.swing.JTextField pesquisa_nome_com;
    private javax.swing.JTextField pesquisa_raca_can;
    // End of variables declaration//GEN-END:variables
}
