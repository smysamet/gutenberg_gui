package com.smy.gutenberggui.model;

import com.smy.gutenberggui.util.DbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

@Data
public class User {

    private int id;
    private String email;
    private String password;
    private DbHelper dbHelper;

    public User(String email, DbHelper dbHelper) {
        this.email = email;
        this.dbHelper = dbHelper;
    }

    public void addBook(String etext_no, int currentPage) {
        Connection connection = null;
        try {
            connection = this.dbHelper.getConnection();
            String sql = "select * from UserBookRecords where user_id=" + this.id + " and etext_no=" + etext_no;
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(sql);

            PreparedStatement statement2 = null;

            // eğer zaten kitap kayıtlıysa ve tekrar kayıt edilecekse, yeni kayıt yerine o kayıdın konumunu güncelliyoruz.
            if (resultSet.next()) {

                statement2 = connection.prepareStatement("update UserBookRecords set currentPage=? where user_id = ? and etext_no = ?");
                statement2.setInt(1, currentPage);
                statement2.setInt(2, this.id);
                statement2.setString(3, etext_no);
                // eğer kitap kayıtlı değilse yeni bir satır ekliyoruz    
            } else {
                statement2 = connection.prepareStatement("insert into UserBookRecords(user_id, etext_no, currentPage) values("
                        + this.id + "," + etext_no + "," + currentPage
                        + ");");
            }

            statement2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateCurrentPage(String etext_no, int currentPage) throws SQLException {
        Connection connection = this.dbHelper.getConnection();
        PreparedStatement statement2 = null;
        statement2 = connection.prepareStatement("update UserBookRecords set currentPage=? where user_id = ? and etext_no = ?");
        statement2.setInt(1, currentPage);
        statement2.setInt(2, this.id);
        statement2.setString(3, etext_no);
    }

}
