/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_10.view;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_10.dto.CanarioDTO;
import br.com.projeto_10.ctr.CanarioCTR;
import br.com.projeto_10.dto.AviarioDTO;
import br.com.projeto_10.ctr.AviarioCTR;
import java.text.SimpleDateFormat;
/**
 *
 * @author gusta
 */
public class CanarioVIEW extends javax.swing.JInternalFrame {
    
    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    
    CanarioDTO canarioDTO = new CanarioDTO();
    CanarioCTR canarioCTR = new CanarioCTR();
    AviarioDTO aviarioDTO = new AviarioDTO();
    AviarioCTR aviarioCTR = new AviarioCTR();
    
    int gravar_alterar;
    
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_canario;
    DefaultTableModel modelo_jtl_consultar_aviario;
    /**
     * Creates new form CanarioVIEW
     */
    public CanarioVIEW() {
        initComponents();
        
        liberaCampos(false);
        
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_canario = (DefaultTableModel) jtl_consultar_canario.getModel();
        modelo_jtl_consultar_aviario = (DefaultTableModel) jtl_consultar_aviario.getModel();
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private void gravar(){
        try{
            canarioDTO.setRaca_can(raca_can.getText());
            canarioDTO.setAnel_can(anel_can.getText());
            canarioDTO.setCor_can(cor_can.getSelectedItem().toString());
            canarioDTO.setObs_can(obs_can.getText());
            canarioDTO.setSexo_can(sexo_can.getSelectedItem().toString());
            canarioDTO.setC_custo_can(Double.parseDouble(c_custo_can.getText()));
            canarioDTO.setC_venda_can(Double.parseDouble(c_venda_can.getText()));
            aviarioDTO.setId_avi(Integer.parseInt(String.valueOf(
                jtl_consultar_aviario.getValueAt(
                        jtl_consultar_aviario.getSelectedRow(), 0))));
            

            
            JOptionPane.showMessageDialog(null, 
                    canarioCTR.inserirCanario(canarioDTO, aviarioDTO)
            );
        }
        catch(Exception e){
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
    }
    
        private void alterar(){
        try{
            canarioDTO.setRaca_can(raca_can.getText());
            canarioDTO.setAnel_can(anel_can.getText());
            canarioDTO.setCor_can(cor_can.getSelectedItem().toString());
            canarioDTO.setObs_can(obs_can.getText());
            canarioDTO.setSexo_can(sexo_can.getSelectedItem().toString());
            canarioDTO.setC_custo_can(Double.parseDouble(c_custo_can.getText()));
            canarioDTO.setC_venda_can(Double.parseDouble(c_venda_can.getText()));
            aviarioDTO.setId_avi(Integer.parseInt(String.valueOf(
                jtl_consultar_aviario.getValueAt(
                        jtl_consultar_aviario.getSelectedRow(), 0))));
            

            
            JOptionPane.showMessageDialog(null, 
                    canarioCTR.alterarCanario(canarioDTO, aviarioDTO)
            );
        }
        catch(Exception e){
            System.out.println("Erro ao Alterar" + e.getMessage());
        }
    }
        
        private void excluir(){
            if(JOptionPane.showConfirmDialog(null,"Deseja Realmente excluir o Canario?", "Aviso",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,
                        canarioCTR.excluirCanario(canarioDTO)
                );
            }
        }
        
        private void liberaCampos(boolean a){
            raca_can.setEnabled(a);
            anel_can.setEnabled(a);
            cor_can.setEnabled(a);
            obs_can.setEnabled(a);
            sexo_can.setEnabled(a);
            c_custo_can.setEnabled(a);
            c_venda_can.setEnabled(a);
            pesquisa_ninhada_aviario.setEnabled(a);
            btnPesquisarAviario.setEnabled(a);
            jtl_consultar_aviario.setEnabled(a);
            
            

        }
        
        private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
            btnNovo.setEnabled(a);
            btnSalvar.setEnabled(b);
            btnCancelar.setEnabled(c);
            btnExcluir.setEnabled(d);
            btnSair.setEnabled(e);
        }
        
        private void limpaCampos(){
            raca_can.setText("");
            anel_can.setText("");
            obs_can.setText("");
            
            c_custo_can.setText("");
            c_venda_can.setText("");
            pesquisa_ninhada_aviario.setText("");
            modelo_jtl_consultar_aviario.setNumRows(0);
            
        }
        
        private void preencheTabelaCanario(String raca_can){
            try{
                modelo_jtl_consultar_canario.setNumRows(0);
                canarioDTO.setRaca_can(raca_can);
                rs = canarioCTR.consultarCanario(canarioDTO, 1);
                while(rs.next()){
                    modelo_jtl_consultar_canario.addRow(new Object[]{
                        rs.getString("id_can"),
                        rs.getString("raca_can"),
                    });
                }
            }
            catch(Exception erTab){
                System.out.println("Erro SQL: "+erTab);
            }
            finally{
                canarioCTR.CloseDB();
            }
        }
        
        private void preencheCamposCanario(int id_can){
            try{
                canarioDTO.setId_can(id_can);
                rs = canarioCTR.consultarCanario(canarioDTO, 2);
                if(rs.next()){
                    limpaCampos();
                    
                    raca_can.setText(rs.getString("raca_can"));
                    anel_can.setText(rs.getString("anel_can"));
                    cor_can.setSelectedItem(rs.getString("cor_can"));
                    obs_can.setText(rs.getString("obs_can"));
                    sexo_can.setSelectedItem(rs.getString("sexo_can"));
                    c_custo_can.setText(rs.getString("c_custo_can"));
                    c_venda_can.setText(rs.getString("c_venda_can"));
                    
                    modelo_jtl_consultar_aviario.setNumRows(0);
                    modelo_jtl_consultar_aviario.addRow(new Object[]{rs.getInt("id_avi"), rs.getString("ninhada_avi"),});
                    jtl_consultar_aviario.setRowSelectionInterval(0, 0);
                    
                    gravar_alterar = 2;
                    liberaCampos(true);
                }
            }
            catch(Exception erTab){
                System.out.println("Erro SQL: "+erTab);
            }
            finally{
                canarioCTR.CloseDB();
            }
        }
        
        private void preencheTabelaAviario(String ninhada_avi){
        try{
            modelo_jtl_consultar_aviario.setNumRows(0);
            
            aviarioDTO.setNinhada_avi(ninhada_avi);
            rs = aviarioCTR.consultarAviario(aviarioDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_aviario.addRow(new Object[]{
                    rs.getString("id_avi"),
                    rs.getString("ninhada_avi"),
                });
            }
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: "+ erTab);
        }
        finally{
            canarioCTR.CloseDB();
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

        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        pesquisa_raca_can = new javax.swing.JTextField();
        btnPesquisarCanario = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_canario = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        raca_can = new javax.swing.JTextField();
        anel_can = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        cor_can = new javax.swing.JComboBox<>();
        sexo_can = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        c_custo_can = new javax.swing.JTextField();
        c_venda_can = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pesquisa_ninhada_aviario = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtl_consultar_aviario = new javax.swing.JTable();
        btnPesquisarAviario = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        obs_can = new javax.swing.JTextArea();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel12.setText("Raça:");

        btnPesquisarCanario.setText("OK");
        btnPesquisarCanario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCanarioActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Consulta");

        jtl_consultar_canario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Raça"
            }
        ));
        jtl_consultar_canario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_canarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_canario);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(158, 158, 158))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_raca_can, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarCanario)
                        .addGap(35, 35, 35))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel13)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(pesquisa_raca_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCanario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel58.setText("Canario");

        jLabel59.setText("Raça:");

        jLabel60.setText("Cor:");

        jLabel62.setText("Sexo:");

        jLabel66.setText("Obs:");

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

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

        cor_can.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BRANCO", "AMARELO", "VERMELHO", "OUTRA" }));

        sexo_can.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FÊMEA", "MACHO" }));

        jLabel1.setText("Anel:");

        jLabel2.setText("P.Custo:");

        jLabel3.setText("P.Venda:");

        jLabel4.setText("Ninhada:");

        jtl_consultar_aviario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ninhada"
            }
        ));
        jtl_consultar_aviario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_aviarioMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtl_consultar_aviario);

        btnPesquisarAviario.setText("OK");
        btnPesquisarAviario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarAviarioActionPerformed(evt);
            }
        });

        obs_can.setColumns(20);
        obs_can.setRows(5);
        jScrollPane2.setViewportView(obs_can);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel60)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cor_can, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel62))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(c_custo_can, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3)))
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(sexo_can, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel66))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(c_venda_can))))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pesquisa_ninhada_aviario, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnPesquisarAviario)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel59))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(raca_can, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(anel_can, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addGap(415, 415, 415))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel58)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel61)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(raca_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(anel_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel66)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel60)
                                        .addComponent(cor_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel62)
                                                .addComponent(sexo_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel2)
                                                .addComponent(c_custo_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(c_venda_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(54, 54, 54)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4)
                                                .addComponent(pesquisa_ninhada_aviario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnPesquisarAviario))))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarCanarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCanarioActionPerformed
        preencheTabelaCanario(pesquisa_raca_can.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarCanarioActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(gravar_alterar == 1){
            gravar();
            gravar_alterar = 0;
        }
        else{
            if(gravar_alterar == 2){
                alterar();
                gravar_alterar = 0;
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro no Sistema!!!");
            }
        }
        
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jtl_consultar_canarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_canarioMouseClicked
        preencheCamposCanario(Integer.parseInt(String.valueOf(  
        jtl_consultar_canario.getValueAt(
                jtl_consultar_canario.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jtl_consultar_canarioMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_canario.setNumRows(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_canario.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairActionPerformed

    private void jtl_consultar_aviarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_aviarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtl_consultar_aviarioMouseClicked

    private void btnPesquisarAviarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarAviarioActionPerformed
        preencheTabelaAviario(pesquisa_ninhada_aviario.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarAviarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anel_can;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarAviario;
    private javax.swing.JButton btnPesquisarCanario;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField c_custo_can;
    private javax.swing.JTextField c_venda_can;
    private javax.swing.JComboBox<String> cor_can;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtl_consultar_aviario;
    private javax.swing.JTable jtl_consultar_canario;
    private javax.swing.JTextArea obs_can;
    private javax.swing.JTextField pesquisa_ninhada_aviario;
    private javax.swing.JTextField pesquisa_raca_can;
    private javax.swing.JTextField raca_can;
    private javax.swing.JComboBox<String> sexo_can;
    // End of variables declaration//GEN-END:variables
}
