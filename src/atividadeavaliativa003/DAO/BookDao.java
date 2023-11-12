package atividadeavaliativa003.DAO;

import atividadeavaliativa003.Book;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface BookDao {
    String getSaveStatement();

    String getUpdateStatement();

    String getFindByIdStatement();

    String getFindAllStatement();

    String getDeleteStatement();

    void composeSaveOrUpdateStatement(PreparedStatement pstmt, Book book);

    Book extractObject(ResultSet rs);
    
    List<Book> extractobject(ResultSet rs);

    
}
