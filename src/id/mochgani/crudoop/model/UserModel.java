/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.mochgani.crudoop.model;

import id.mochgani.crudoop.config.Database;
import id.mochgani.crudoop.entity.User;
import id.mochgani.crudoop.service.ServiceUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mochgani
 */
public class UserModel implements ServiceUser {
    
    private Connection connection;
    
    public UserModel(){
        connection = Database.getConnection();
    }
    
    @Override
    public void insertUser(User usr) {
        PreparedStatement st = null;
        String sql = "insert into user value (null, ?, ?, md5(?), ?)";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, usr.getNama());
            st.setString(2, usr.getUsername());
            st.setString(3, usr.getPassword());
            st.setString(4, usr.getAkses());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }         
            }
        }
    }

    @Override
    public void updateUser(User usr) {
        PreparedStatement st = null;
        String sql;
        
        if("".equals(usr.getPassword())){
            sql = "update user set nama=?, username=?, akses=? where id=?";
        }else{
            sql = "update user set nama=?, username=?, akses=?, password=md5(?) where id=?";
        }
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, usr.getNama());
            st.setString(2, usr.getUsername());
            st.setString(3, usr.getAkses());
            
            if("".equals(usr.getPassword())){
                st.setString(4, usr.getId());
            } else {
                st.setString(4, usr.getPassword());
                st.setString(5, usr.getId());
            }
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deleteUser(User usr) {
        PreparedStatement st = null;
        String sql = "delete from user where id=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, usr.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public User getByid(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        User u = null;
        String sql = "select * from user where id=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, id);
            rs= st.executeQuery();
            while (rs.next()) {
                u = new User();
                u.setId(rs.getString("id"));
                u.setNama(rs.getString("nama"));
                u.setUsername(rs.getString("username"));
                u.setAkses(rs.getString("akses"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<User> getUser() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="select * from user";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                User u = new User();
                u.setId(rs.getString("id"));
                u.setNama(rs.getString("nama"));
                u.setUsername(rs.getString("username"));
                u.setAkses(rs.getString("akses"));
                list.add(u);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public Boolean getLogin(User usr) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select count(*) from user where username=? and password=md5(?)";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, usr.getUsername());
            st.setString(2, usr.getPassword());
            rs= st.executeQuery();
            
            int h = 0;
            
            if (rs.next())
                h = rs.getInt(1);
            
            if(h==0)
                return false;
            else
                return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public User getLoginData(User usr) {
        PreparedStatement st = null;
        ResultSet rs = null;
        User u = null;
        String sql = "select * from user where username=? and password=md5(?)";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, usr.getUsername());
            st.setString(2, usr.getPassword());
            rs= st.executeQuery();
            
            while (rs.next()) {
                u = new User();
                u.setId(rs.getString("id"));
                u.setNama(rs.getString("nama"));
                u.setUsername(rs.getString("username"));
                u.setAkses(rs.getString("akses"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
