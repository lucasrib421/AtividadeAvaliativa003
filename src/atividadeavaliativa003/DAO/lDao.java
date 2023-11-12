/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package atividadeavaliativa003.DAO;
import java.util.ArrayList;
import atividadeavaliativa003.Book;

public interface lDao {
    long saveOrUpdate(Book book);
    Book findById(long id); 
    ArrayList<Book> findAll();
    void delete(long id);
}
