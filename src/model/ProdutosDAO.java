package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import model.conectaDAO;
import static model.conectaDAO.conn;
import static model.conectaDAO.connectDB;
import static model.conectaDAO.desconectar;

public class ProdutosDAO {
 

 public static ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
 public void cadastrarProduto (ProdutosDTO produto){
        listagem.add(produto);  
    }
   
 public void vendeProduto(int idProduto) {
    try {
        conectaDAO.conn=conectaDAO.connectDB();
        System.out.println("conectou");
        String sql = "UPDATE produtos SET status='Vendido' WHERE id=?";
        System.out.println("deu update");
        PreparedStatement preparedStatement = conectaDAO.conn.prepareStatement(sql);
        preparedStatement.setInt(1, idProduto);
        preparedStatement.executeUpdate();
        System.out.println("deu execute update");
        JOptionPane.showMessageDialog(null, "Sucesso ao atualizar status no banco!\n");
        conectaDAO.desconectar();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar status no banco: Problemas de conexão\n Código de erro: " + ex.getMessage());
    } catch (NumberFormatException iu) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar status no banco de dados: Erro no formato dos dados.\n Código do erro:" + iu.getMessage());
    }
} 
     
 
 
static public void filtraItens(JTable par) {
    try {
        DefaultTableModel pro = (DefaultTableModel) par.getModel();
        pro.setNumRows(0);
        conectaDAO.connectDB();
        PreparedStatement pstm;
        java.sql.ResultSet rs;

        // Ajuste na consulta SQL para buscar apenas os itens com status "Vendido"
        pstm = conectaDAO.conn.prepareStatement("SELECT * FROM produtos WHERE status = 'Vendido'");
        rs = pstm.executeQuery();

        while (rs.next()) {
            pro.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4)
            });
        }
        desconectar();
    } catch (SQLException SQL) {
        JOptionPane.showMessageDialog(null, "Deu erro no carregamento da tabela:\n Código de erro:" + SQL.getMessage());
    }
}
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }

    private static class ResultSet {

        public ResultSet() {
        }
    }
  
    
        
}

