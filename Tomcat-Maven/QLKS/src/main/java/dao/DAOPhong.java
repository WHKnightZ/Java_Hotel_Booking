package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Phong;

public class DAOPhong {

    private static Connection con;

    public static ArrayList<Phong> getAll() {
        ArrayList<Phong> list = new ArrayList();
        try {
            con = SQLConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select P.Id, P.Ten, P.DienTich, P.GiaThue, P.TienNghi, "
                    + "P.MoTa, P.LoaiGiuong, P.IdKhachSan, K.Ten as TenKhachSan from Phong P, "
                    + "KhachSan K where P.IdKhachSan=K.Id");
            while (rs.next()) {
                Phong tmp = new Phong();
                tmp.setId(rs.getInt("Id"));
                tmp.setTen(rs.getString("Ten"));
                tmp.setDienTich(rs.getInt("DienTich"));
                tmp.setGiaThue(rs.getInt("GiaThue"));
                tmp.setTienNghi(rs.getString("TienNghi"));
                tmp.setMoTa(rs.getString("MoTa"));
                tmp.setLoaiGiuong(rs.getInt("LoaiGiuong"));
                tmp.setIdKhachSan(rs.getInt("IdKhachSan"));
                tmp.setTenKhachSan(rs.getString("TenKhachSan"));
                list.add(tmp);
            }
            con.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static boolean insert(Phong tmp) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into Phong output inserted.Id values(?,?,?,?,?,?,?)");
            stmt.setString(1, tmp.getTen());
            stmt.setInt(2, tmp.getDienTich());
            stmt.setInt(3, tmp.getGiaThue());
            stmt.setString(4, tmp.getTienNghi());
            stmt.setString(5, tmp.getMoTa());
            stmt.setInt(6, tmp.getLoaiGiuong());
            stmt.setInt(7, tmp.getIdKhachSan());
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

    public static boolean update(Phong tmp) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("update Phong set Ten=?, DienTich=?, GiaThue=?, TienNghi=?, MoTa=?, LoaiGiuong=?, IdKhachSan=? where Id=?");
            stmt.setString(1, tmp.getTen());
            stmt.setInt(2, tmp.getDienTich());
            stmt.setInt(3, tmp.getGiaThue());
            stmt.setString(4, tmp.getTienNghi());
            stmt.setString(5, tmp.getMoTa());
            stmt.setInt(6, tmp.getLoaiGiuong());
            stmt.setInt(7, tmp.getIdKhachSan());
            stmt.setInt(8, tmp.getId());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean delete(int id) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("delete from Phong where id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
