/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atividadeavaliativa003;

import atividadeavaliativa003.DAO.Dao;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class AtividadeAvaliativa003 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Book HarryPotter = new Book(); 
        Book mundoEmcaos = new Book();
        Book theWitcher = new Book();
        Book senhorDosAneis = new Book();
        
        Dao bancoDados = new Dao();
        
        try {
            HarryPotter.setTitle("Harry Potter");
            HarryPotter.setAuthors("JK Rowling");
            HarryPotter.setPages((short)700);
            HarryPotter.setYear((short)2003);
            HarryPotter.setEdition((byte)5);
            HarryPotter.setPrice(new BigDecimal("69.90"));
            HarryPotter.setAcquisition(LocalDate.now().plusDays(3));
            
            bancoDados.saveOrUpdate(HarryPotter);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        try {
            mundoEmcaos.setTitle("Mundo em Caos");
            mundoEmcaos.setAuthors("Patrick Ness");
            mundoEmcaos.setPages((short)500);
            mundoEmcaos.setYear((short)2014);
            mundoEmcaos.setEdition((byte)2);
            mundoEmcaos.setPrice(new BigDecimal("65.90"));
            mundoEmcaos.setAcquisition(LocalDate.now());
            
            bancoDados.saveOrUpdate(mundoEmcaos);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        try {
            theWitcher.setTitle("The Withcer");
            theWitcher.setAuthors("Andrzej");
            theWitcher.setPages((short)300);
            theWitcher.setYear((short)1980);
            theWitcher.setEdition((byte)3);
            theWitcher.setPrice(new BigDecimal("34.90"));
            theWitcher.setAcquisition(LocalDate.now());
            
            bancoDados.saveOrUpdate(theWitcher);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        try {
            senhorDosAneis.setTitle("O Senhor dos Aneis");
            senhorDosAneis.setAuthors("J.R.R Tolkien");
            senhorDosAneis.setPages((short)1200);
            senhorDosAneis.setYear((short)1930);
            senhorDosAneis.setEdition((byte)3);
            senhorDosAneis.setPrice(new BigDecimal("120.90"));
            senhorDosAneis.setAcquisition(LocalDate.now());
            
            bancoDados.saveOrUpdate(senhorDosAneis);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        
        
        try {
            Book theWitcherUpdate = new Book();
            
            
            theWitcherUpdate.setId(theWitcher.getId());
            theWitcherUpdate.setTitle("The Withcer");
            theWitcherUpdate.setAuthors("Andrzej Sapkowski");
            theWitcherUpdate.setPages((short)300);
            theWitcherUpdate.setYear((short)1980);
            theWitcherUpdate.setEdition((byte)3);
            theWitcherUpdate.setPrice(new BigDecimal("34.90"));
            theWitcherUpdate.setAcquisition(LocalDate.now());
            
            bancoDados.saveOrUpdate(theWitcherUpdate);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }        
        
        System.out.println();
        
        Book livroPesquisado = bancoDados.findById(2);
        livroPesquisado.imprimeBook();
        
        livroPesquisado = bancoDados.findById(1);
        livroPesquisado.imprimeBook();
        
        System.out.println("---- Lista de Livros ----");
        
        ArrayList<Book> listaLivros = bancoDados.findAll();
        for(Book livro : listaLivros){
            livro.imprimeBook();
            System.out.println();
        }
        
        bancoDados.delete(mundoEmcaos.getId());
        
        
        
        
        //System.out.println(HarryPotter.getSaveStatement());
        //System.out.println(HarryPotter.getUpdateStatement());
        //System.out.println(HarryPotter.getDeleteStatement());
    }
    
}
