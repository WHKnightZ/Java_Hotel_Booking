package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.LoaiKhachSan;

public class DAOLoaiKhachSan {

    private static Connection con;

    public static ArrayList<LoaiKhachSan> getAll() {
        ArrayList<LoaiKhachSan> list = new ArrayList();
        try {
            con = SQLConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select L.Id as A, L.Ten as B, L.MoTa as C, "
                    + "L.UrlHinhAnh as D, count(L.Id) as E from LoaiKhachSan L left join KhachSan K "
                    + "on L.Id = K.IdLoaiKhachSan group by L.Id, L.Ten, L.MoTa, L.UrlHinhAnh");
            while (rs.next()) {
                LoaiKhachSan tmp = new LoaiKhachSan();
                tmp.setId(rs.getInt("A"));
                tmp.setTen(rs.getString("B"));
                tmp.setMoTa(rs.getString("C"));
                tmp.setUrlHinhAnh(rs.getString("D"));
                tmp.setSoKhachSan(rs.getInt("E"));
                list.add(tmp);
            }
            con.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static boolean insert(LoaiKhachSan tmp) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into LoaiKhachSan output inserted.Id values(?,?,?)");
            stmt.setString(1, tmp.getTen());
            stmt.setString(2, tmp.getMoTa());
            stmt.setString(3, tmp.getUrlHinhAnh());
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

    public static boolean update(LoaiKhachSan tmp) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("update LoaiKhachSan set Ten=?, MoTa=?, UrlHinhAnh=? where Id=?");
            stmt.setString(1, tmp.getTen());
            stmt.setString(2, tmp.getMoTa());
            stmt.setString(3, tmp.getUrlHinhAnh());
            stmt.setInt(4, tmp.getId());
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
            PreparedStatement stmt = con.prepareStatement("delete from LoaiKhachSan where Id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
