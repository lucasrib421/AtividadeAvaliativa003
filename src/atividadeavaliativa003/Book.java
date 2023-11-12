package atividadeavaliativa003;

import atividadeavaliativa003.DAO.BookDao;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public final class Book implements BookDao {
    private static long proxId = 1;
    private long  id;
    private String title, authors;
    private LocalDate acquisition;
    private Short pages, year;
    private Byte edition;
    private BigDecimal price;
    
    public Book(){
        this.id = proxId++;
    }
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("( Alert ) Title cannot be null.");
        }

        if (title.length() > 150) {
            throw new IllegalArgumentException(" ( Alert ) Title cannot exceed 150 characters.");
        }

        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        if (authors == null) {
            throw new IllegalArgumentException("( Alert ) Authors cannot be null.");
        }

        if (authors.length() > 250) {
            throw new IllegalArgumentException("( Alert ) Authors cannot exceed 250 characters.");
        }

        this.authors = authors;
    }

    public LocalDate getAcquisition() {
        return acquisition;
    }

    public void setAcquisition(LocalDate acquisition) {
        LocalDate now = LocalDate.now(); // Obtém a data atual
        if (acquisition.isAfter(now) || ChronoUnit.DAYS.between(now, acquisition) != 0) {
            throw new IllegalArgumentException("( Alert ) Acquisition date cannot be after or ahead of the current date.");
        }
        this.acquisition = acquisition;
    }


    public Short getPages() {
        return pages;
    }

    public void setPages(Short pages) {
        if (pages == null || pages < 1) {
            throw new IllegalArgumentException("( Alert ) Pages must be a positive number.");
        }
        this.pages = pages;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        if (year == null) {
            throw new IllegalArgumentException("( Alert ) Year cannot be null.");
        }
        this.year = year;
    }


    public Byte getEdition() {
        return edition;
    }

    public void setEdition(Byte edition) {
        if (edition == null || edition < 1) {
            throw new IllegalArgumentException("( Alert ) Edition must be a positive number.");
        }
        this.edition = edition;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("( Alert ) Price must be a non-negative value.");
        }
        this.price = price;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Interface Methods">
    
    @Override
    public String getSaveStatement() {
        String stringSQL = "INSERT INTO book VALUES (";

        stringSQL += getId() + ", ";
        stringSQL += "'" + getTitle() + "', "; // Adicione aspas simples em torno de valores de string
        stringSQL += "'" + getAuthors() + "', "; // Adicione aspas simples em torno de valores de string
        stringSQL += "'" + getAcquisition().toString() + "', "; // Trate a data como uma string
        stringSQL += getPages() + ", ";

        stringSQL += getYear() + ", ";
        stringSQL += getEdition() + ", ";
        stringSQL += getPrice();

        stringSQL += ");";

        return stringSQL;
    }

    @Override
    public String getUpdateStatement() {
        String stringSQL = "UPDATE book SET ";
    
        stringSQL += "title = ?, ";
        stringSQL += "authors = ?, ";
        stringSQL += "acquisition = ?, ";
        stringSQL += "pages = ?, ";
        stringSQL += "year = ?, ";
        stringSQL += "edition = ?, ";
        stringSQL += "price = ? ";

        stringSQL += "WHERE id = " + getId() + ";";

        return stringSQL;
        
    }

    @Override
    public String getFindByIdStatement() {
        
        return "SELECT * from book where id = " + getId() + ";";
        
    }

    @Override
    public String getFindAllStatement() {
        return "SELECT * FROM books;";
    }

    @Override
    public String getDeleteStatement() {
        return "DELETE FROM book WHERE id = " + getId() + ";";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Book book) {
        try {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthors());
            pstmt.setDate(3, java.sql.Date.valueOf(book.getAcquisition()));
            pstmt.setShort(4, book.getPages());
            pstmt.setShort(5, book.getYear());
            pstmt.setByte(6, book.getEdition());
            pstmt.setBigDecimal(7, book.getPrice());
            pstmt.setLong(8, book.getId());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "javacrude/Book/composeSaveOrUpdateStatement: " + e.getMessage());
        }
        
    }

    @Override
    public Book extractObject(ResultSet rs) {
        Book book = new Book();

        // Extrair os valores do ResultSet e definir os atributos do livro
        
        try{
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthors(rs.getString("authors"));
            book.setAcquisition(rs.getDate("acquisition").toLocalDate());
            book.setPages(rs.getShort("pages"));
            book.setYear(rs.getShort("year"));
            book.setEdition(rs.getByte("edition"));
            book.setPrice(rs.getBigDecimal("price"));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "javacrude/Book/extractObject: " + e.getMessage());
        }
        

        return book;
    }
    
    @Override
    public List<Book> extractobject(ResultSet rs) {
           List<Book> books = new ArrayList<>();

        try{
            while (rs.next()) {
                Book book = new Book();
                // Extrair os valores do ResultSet e definir os atributos do livro
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
        
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "javacrude/Book/extractObject: " + e.getMessage());
            }

            
            
        return books;
    }
    
    
    //</editor-fold>    
    
    public void imprimeBook (){
        if(this.id == -1){
            System.out.println("Não tem informações sobre esse livro");
        }else{
            System.out.println("Book ID: " + getId());
            System.out.println("Title: " + getTitle());
            System.out.println("Authors: " + getAuthors());
            System.out.println("Acquisition Date: " + getAcquisition());
            System.out.println("Pages: " + getPages());
            System.out.println("Year: " + getYear());
            System.out.println("Edition: " + getEdition());
            System.out.println("Price: " + getPrice());
        }
    }

}
