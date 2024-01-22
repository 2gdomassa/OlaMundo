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


public class ProdutosDAO {
 
    
   public static ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        listagem.add(produto);  
    }
   
 public void vendeProduto(int idProduto) {
    try {
        conectaDAO.conn=conectaDAO.connectDB();
        System.out.println("conectou");
        String sql = "UPDATE tabela SET status='Vendido' WHERE id=?";
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
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
  
    
        
}

