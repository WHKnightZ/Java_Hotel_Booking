package model;

import java.io.Serializable;
import java.util.Date;

public class DatPhong implements Serializable {

    private static final long serialVersionUID = 5436453121L;

    int id;
    String taiKhoan;
    int idPhong;
    Date ngayDat;
    Date ngayDen;
    Date ngayTra;
    String dichVu;
    String ghiChu;
    int thanhTien;
    boolean daHuy;

    public DatPhong() {
    }

    public DatPhong(int id, String taiKhoan, int idPhong, Date ngayDat, Date ngayDen, Date ngayTra, String dichVu, String ghiChu, int thanhTien, boolean daHuy) {
        this.id = id;
        this.taiKhoan = taiKhoan;
        this.idPhong = idPhong;
        this.ngayDat = ngayDat;
        this.ngayDen = ngayDen;
        this.ngayTra = ngayTra;
        this.dichVu = dichVu;
        this.ghiChu = ghiChu;
        this.thanhTien = thanhTien;
        this.daHuy = daHuy;
    }

    public DatPhong(DatPhong dp) {
        this.id = dp.id;
        this.taiKhoan = dp.taiKhoan;
        this.idPhong = dp.idPhong;
        this.ngayDat = dp.ngayDat;
        this.ngayDen = dp.ngayDen;
        this.ngayTra = dp.ngayTra;
        this.dichVu = dp.dichVu;
        this.ghiChu = dp.ghiChu;
        this.thanhTien = dp.thanhTien;
        this.daHuy = dp.daHuy;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getDichVu() {
        return dichVu;
    }

    public void setDichVu(String dichVu) {
        this.dichVu = dichVu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public boolean isDaHuy() {
        return daHuy;
    }

    public void setDaHuy(boolean daHuy) {
        this.daHuy = daHuy;
    }

}
