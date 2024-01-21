package model;


import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class conectaDAO {
    Connection conn = null;
    public Connection connectDB(){
                
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ihms", "root", "esquecido140!");   
        }
       
                catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Falha ao conectar no servidor! \n Verifique usuário/senha!","Erro: ",JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Deu ruim na sintaxe SQL! \n Verifique usuário/senha!","Erro: ",JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }
   
    
    List<ProdutosDTO>listaFantasma=new ArrayList<>();
    
    
    public void adicionarRegistro(ProdutosDTO parametro) {
        try {
            connectDB();
            String sql = "insert into tabela (nome,valor,status) values (?, ?, ?)";        
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, parametro.getNome());
            preparedStatement.setDouble(2, parametro.getValor());
            preparedStatement.setString(3, parametro.getStatus());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null,"Sucesso ao salvar dado no banco!\n" );
            desconectar();    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar dado no banco: Problemas de conexao \n Código de erro: " + ex.getMessage());
        } catch (NumberFormatException iu) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar dado no banco de dados: Erro no formato dos dados.  \n Código do erro:" + iu.getMessage());
        }
    }
    
     public void carregaTabela(JTable par){
               try{
               DefaultTableModel pro = (DefaultTableModel)par.getModel();
               pro.setNumRows(0);
               connectDB();
               PreparedStatement pstm;
               ResultSet rs;
               pstm=conn.prepareStatement("select * from tabela;");
               rs=pstm.executeQuery();
               while(rs.next()){
                  pro.addRow(new Object[]{
                  rs.getInt(1),
                  rs.getString(2),
                  rs.getDouble(3),
                  rs.getString(4)
                  });  
               }
               desconectar();
                       }
               catch(SQLException SQL){
                  JOptionPane.showMessageDialog(null,"Deu erro no carregamento da tabela:\n Código de erro:"+SQL.getMessage());}
                     
               
           }
    
      public void desconectar() {
        try {
            if (conn != null) {
                conn.close();         
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao fechar a conexao com o banco de dados:\n " + ex.getMessage());
        }
    }
    
}
