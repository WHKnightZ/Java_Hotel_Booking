package model;

import java.io.Serializable;

public class LichSu implements Serializable {

    private static final long serialVersionUID = 54353121L;

    int id;
    String tenPhong;
    int idKhachSan;
    String tenKhachSan;
    String ngayDat;
    String ngayDen;
    String ngayTra;
    String dichVu;
    String ghiChu;
    String thanhTien;
    int trangThai; // 0: Đang xử lý, 1: Quá hạn, 2: Đã hủy

    public LichSu() {
    }

    public LichSu(int id, String tenPhong, int idKhachSan, String tenKhachSan, String ngayDat, String ngayDen, String ngayTra, String dichVu, String ghiChu, String thanhTien, int trangThai) {
        this.id = id;
        this.tenPhong = tenPhong;
        this.idKhachSan = idKhachSan;
        this.tenKhachSan = tenKhachSan;
        this.ngayDat = ngayDat;
        this.ngayDen = ngayDen;
        this.ngayTra = ngayTra;
        this.dichVu = dichVu;
        this.ghiChu = ghiChu;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public int getIdKhachSan() {
        return idKhachSan;
    }

    public void setIdKhachSan(int idKhachSan) {
        this.idKhachSan = idKhachSan;
    }

    public String getTenKhachSan() {
        return tenKhachSan;
    }

    public void setTenKhachSan(String tenKhachSan) {
        this.tenKhachSan = tenKhachSan;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(String ngayDen) {
        this.ngayDen = ngayDen;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
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

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}