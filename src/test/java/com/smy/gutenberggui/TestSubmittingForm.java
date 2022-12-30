package com.smy.gutenberggui;

import com.smy.gutenberggui.model.User;
import com.smy.gutenberggui.util.DbHelper;
import com.smy.gutenberggui.view.BookSearchJpanel;
import com.smy.gutenberggui.view.MainFrame;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestSubmittingForm {
    
    private DbHelper dbHelper = new DbHelper();
    private User user = new User("", dbHelper);
    private MainFrame mainframe = new MainFrame(user, dbHelper);
    private BookSearchJpanel bookSearchJpanel = new BookSearchJpanel(mainframe);
    
    @DisplayName("Test BookSearchMainPanel.submittingForm, title = blue elephant, author = null")
    @Test
    void testSubmittingFormWithTitleBlueElephant() throws IOException {
        assertEquals(2, bookSearchJpanel.submittingForm(null,"Blue Elephant").size()/4);
    }
    
    
    @DisplayName("Test BookSearchMainPanel.submittingForm, title = null, author = May Hall")
    @Test
    void testSubmittingFormWithAuthorManlyWade() throws IOException {
        assertEquals(1, bookSearchJpanel.submittingForm("May Hall", null).size()/4);
    }
    
    
    @DisplayName("Test BookSearchMainPanel.submittingForm, title = bukitapvarmi, author = null")
    @Test
    void testSubmittingFormWithTitlea() throws IOException {
        assertEquals(0, bookSearchJpanel.submittingForm(null, "bukitapvarmi").size()/4);
        
    }
    
    
}
