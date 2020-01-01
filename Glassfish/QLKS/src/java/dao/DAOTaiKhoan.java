package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.TaiKhoan;

public class DAOTaiKhoan {

    private static Connection con;

    public static ArrayList<TaiKhoan> getAll() {
        ArrayList<TaiKhoan> list = new ArrayList();
        try {
            con = SQLConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from TaiKhoan");
            while (rs.next()) {
                TaiKhoan tmp = new TaiKhoan();
                tmp.setTenTaiKhoan(rs.getString(1));
                tmp.setMatKhau(rs.getString(2));
                tmp.setHoTen(rs.getString(3));
                tmp.setGioiTinh(rs.getBoolean(4));
                tmp.setSoDienThoai(rs.getString(5));
                tmp.setEmail(rs.getString(6));
                tmp.setIsAdmin(rs.getBoolean(7));
                list.add(tmp);
            }
            con.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static TaiKhoan getByDangNhap(String TenTaiKhoan, String MatKhau) {
        TaiKhoan tmp = null;
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from TaiKhoan where TenTaiKhoan=? and MatKhau=?");
            stmt.setString(1, TenTaiKhoan);
            stmt.setString(2, MatKhau);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tmp = new TaiKhoan();
                tmp.setTenTaiKhoan(rs.getString(1));
                tmp.setMatKhau(rs.getString(2));
                tmp.setHoTen(rs.getString(3));
                tmp.setGioiTinh(rs.getBoolean(4));
                tmp.setSoDienThoai(rs.getString(5));
                tmp.setEmail(rs.getString(6));
                tmp.setIsAdmin(rs.getBoolean(7));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return tmp;
    }
    
    public static boolean insert(TaiKhoan tmp) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into TaiKhoan values(?,?,?,?,?,?,?)");
            stmt.setString(1, tmp.getTenTaiKhoan());
            stmt.setString(2, tmp.getMatKhau());
            stmt.setString(3, tmp.getHoTen());
            stmt.setBoolean(4, tmp.isGioiTinh());
            stmt.setString(5, tmp.getSoDienThoai());
            stmt.setString(6, tmp.getEmail());
            stmt.setBoolean(7, tmp.isIsAdmin());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean update(TaiKhoan tmp) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("update TaiKhoan set MatKhau=?, HoTen=?, GioiTinh=?, SoDienThoai=?, Email=?, IsAdmin=? where TenTaiKhoan=?");
            stmt.setString(1, tmp.getMatKhau());
            stmt.setString(2, tmp.getHoTen());
            stmt.setBoolean(3, tmp.isGioiTinh());
            stmt.setString(4, tmp.getSoDienThoai());
            stmt.setString(5, tmp.getEmail());
            stmt.setBoolean(6, tmp.isIsAdmin());
            stmt.setString(7, tmp.getTenTaiKhoan());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean delete(String tenTaiKhoan) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("delete from TaiKhoan where TenTaiKhoan=?");
            stmt.setString(1, tenTaiKhoan);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
