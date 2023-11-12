/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atividadeavaliativa003.DAO;

import atividadeavaliativa003.Book;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public final class Dao implements lDao {
    Connection conn;
    PreparedStatement pstm;
    
    @Override
    public long saveOrUpdate(Book book) {
        String sql = "SELECT id FROM book WHERE id = ?";
        conn = new ConnectionDao().connectBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, book.getId());
            ResultSet rs = pstm.executeQuery();
            
            
            
            if(rs.next()){
                try {
                    sql = book.getUpdateStatement();
                   pstm = conn.prepareStatement(sql);
                    // title authors acquisition pages year edition price
                    pstm.setString(1, book.getTitle());
                    pstm.setString(2, book.getAuthors());
                    pstm.setDate(3, java.sql.Date.valueOf(book.getAcquisition()));
                    pstm.setShort(4, book.getPages());
                    pstm.setShort(5, book.getYear());
                    pstm.setByte(6, book.getEdition());
                    pstm.setBigDecimal(7, book.getPrice());
                
                    pstm.executeUpdate();
                    System.out.println("\n( ! ) Atualizado com sucesso\n");
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Erro: " + erro.getMessage());
                }

            }else{
                sql = book.getSaveStatement();
                try {
                    pstm = conn.prepareStatement(sql);
                    pstm.executeUpdate();
                    System.out.println("\n( ! ) Inserido com sucesso\n");
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Erro:" + erro.getMessage());
                }
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } 
        
        return book.getId();
    }

    @Override
    public Book findById(long id) {
        conn = new ConnectionDao().connectBD();
        PreparedStatement pstm;
       
        try {
            String sql = "SELECT * FROM book WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthors(rs.getString("authors"));
                book.setAcquisition(rs.getDate("acquisition").toLocalDate());
                book.setPages(rs.getShort("pages"));
                book.setYear(rs.getShort("year"));
                book.setEdition(rs.getByte("edition"));
                book.setPrice(rs.getBigDecimal("price"));
                return book;
            }else{
                Book voidBook = new Book();
                voidBook.setId(-1);
                return voidBook;
            }
           
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
       
    return null;
    }

    @Override
    public ArrayList<Book> findAll() {
        //Connection conn = new ConnectionDao().conectaBD();
        Connection conn = new ConnectionDao().connectBD();
        ResultSet rs = null;

        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT * FROM book";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthors(rs.getString("authors"));
                book.setAcquisition(rs.getDate("acquisition").toLocalDate());
                book.setPages(rs.getShort("pages"));
                book.setYear(rs.getShort("year"));
                book.setEdition(rs.getByte("edition"));
                book.setPrice(rs.getBigDecimal("price"));

                books.add(book);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }

        return books;
    }

    @Override
    public void delete(long id) {
        
        Connection conn = new ConnectionDao().connectBD();
        PreparedStatement pstm = null;

        try {
            String sql = "DELETE FROM book WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id);
            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Livro excluido");
            } else {
                System.out.println("Nenhum livro foi excluido");
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        } 
    }
    
}
