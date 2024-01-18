package model;


import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class conectaDAO {
    Connection conn = null;
    public Connection connectDB(){
        
        
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ihms", "root", "esquecido140!");
            System.out.println("Sistema conectado ao banco de dados!");
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
                       
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Inserção de registro no banco de dados falhou: \n" + ex.getMessage());
        }
    }
    
}
