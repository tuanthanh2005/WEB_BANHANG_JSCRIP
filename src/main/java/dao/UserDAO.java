/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author trant
 */
public class UserDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    // đặt tên database không được đặt là user hoặc User  hãy sử dụng tên khác plsss!!
    
    public User checkLogin (String username , String password){
        User tk = null;
        conn = DbContext.getConnection();
        try {
            ps=conn.prepareStatement("select * from users where username=? and password=?");
            ps.setString(1,username);
            ps.setString(2, password);
            rs=ps.executeQuery();
            if (rs.next()) // có tồn tại 1 user
            {
                tk = new User(rs.getString(1),rs.getString(2));
            }
        } catch (Exception ex) {
            System.out.println("Loi :" + ex.toString());
        }
        
        return tk;
    }
    
    // thay đổi mật khẩu 
    //code

    public boolean changePassword(User tk)
    {
     conn = DbContext.getConnection();
   String sql = " update users set password=? where username=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, tk.getPassword());
            ps.setString(2,tk.getUsername());
            int kq = ps.executeUpdate();
            if (kq>0) 
                return true;
            
        } catch (Exception ex) {
            System.out.println("loi:" + ex.toString());
        }
        return false;
    }

   
    

}
