package com.smy.gutenberggui.model;

import com.smy.gutenberggui.util.DbHelper;
import com.smy.gutenberggui.view.MainFrame;
import java.util.HashMap;
import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private DbHelper dbHelper;
    
    
    private HashMap<String, Integer> books;
    
    public User(String email){
        this.email = email;
        this.books = new HashMap<>();
    }
    
    
    public void addBook(String etext_no, int currentPage){
        if(this.books.containsKey(etext_no)){
            
        }else{
            this.books.put(etext_no, currentPage);
        }
    }
    
    public void updateBookPosition(String etext_no, int position){
        if(this.books.containsKey(etext_no)){
            this.books.put(etext_no, position);
        }
    }
    
}
