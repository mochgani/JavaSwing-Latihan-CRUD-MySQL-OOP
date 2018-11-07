package id.mochgani.crudoop.model;

import id.mochgani.crudoop.config.Database;
import id.mochgani.crudoop.entity.Siswa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import id.mochgani.crudoop.service.ServiceSiswa;
import java.sql.Connection;

public class SiswaModel implements ServiceSiswa{

    private Connection connection;
    
    public SiswaModel(){
        connection = Database.getConnection();
    }
    
    @Override
    public void insertSiswa(Siswa sis) {
        PreparedStatement st = null;
        String sql = "insert into siswa value (?, ?, ?, ?, ?)";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, sis.getNis());
            st.setString(2, sis.getNama());
            st.setString(3, sis.getJenis());
            st.setString(4, sis.getKelas());
            st.setString(5, sis.getAlamat());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
                }         
            }
        }
    }

    @Override
    public void updateSiswa(Siswa sis) {
        PreparedStatement st = null;
        String sql = "update siswa set nama=?, kelamin=?, kelas=?, alamat=? where nis=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, sis.getNama());
            st.setString(2, sis.getJenis());
            st.setString(3, sis.getKelas());
            st.setString(4, sis.getAlamat());
            st.setString(5, sis.getNis());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deleteSiswa(Siswa sis) {
        PreparedStatement st = null;
        String sql = "delete from siswa where nis=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, sis.getNis());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public Siswa getByid(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Siswa s = null;
        String sql = "select * from siswa where nis=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, id);
            rs= st.executeQuery();
            while (rs.next()) {
                s = new Siswa();
                s.setNis(rs.getString("nis"));
                s.setNama(rs.getString("nama"));
                s.setJenis(rs.getString("kelamin"));
                s.setKelas(rs.getString("kelas"));
                s.setAlamat(rs.getString("alamat"));
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Siswa> getsisSiswa() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="select * from siswa";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Siswa s = new Siswa();
                s.setNis(rs.getString("nis"));
                s.setNama(rs.getString("nama"));
                s.setJenis(rs.getString("kelamin"));
                s.setKelas(rs.getString("kelas"));
                s.setAlamat(rs.getString("alamat"));
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
