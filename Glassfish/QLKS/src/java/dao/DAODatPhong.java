package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.DatPhong;

public class DAODatPhong {

    private static Connection con;

    public static ArrayList<DatPhong> getAll() {
        ArrayList<DatPhong> list = new ArrayList();
        try {
            con = SQLConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from DatPhong");
            while (rs.next()) {
                DatPhong tmp = new DatPhong();
                tmp.setId(rs.getInt("Id"));
                tmp.setTaiKhoan(rs.getString("TaiKhoan"));
                tmp.setIdPhong(rs.getInt("IdPhong"));
                tmp.setNgayDat(rs.getDate("NgayDat"));
                tmp.setNgayDen(rs.getDate("NgayDen"));
                tmp.setNgayTra(rs.getDate("NgayTra"));
                tmp.setDichVu(rs.getString("DichVu"));
                tmp.setGhiChu(rs.getString("GhiChu"));
                tmp.setThanhTien(rs.getInt("ThanhTien"));
                tmp.setDaHuy(rs.getBoolean("DaHuy"));
                list.add(tmp);
            }
            con.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static boolean insert(DatPhong tmp) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into DatPhong output inserted.Id values(?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, tmp.getTaiKhoan());
            stmt.setInt(2, tmp.getIdPhong());
            stmt.setDate(3, new java.sql.Date(tmp.getNgayDat().getTime()));
            stmt.setDate(4, new java.sql.Date(tmp.getNgayDen().getTime()));
            stmt.setDate(5, new java.sql.Date(tmp.getNgayTra().getTime()));
            stmt.setString(6, tmp.getDichVu());
            stmt.setString(7, tmp.getGhiChu());
            stmt.setInt(8, tmp.getThanhTien());
            stmt.setBoolean(9, tmp.isDaHuy());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tmp.setId(rs.getInt("Id"));
            }
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean update(int id) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("update DatPhong set DaHuy=? where Id=?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean delete(int id) {
        try {
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
