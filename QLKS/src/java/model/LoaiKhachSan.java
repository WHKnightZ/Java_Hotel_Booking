package model;

import java.io.Serializable;

public class LoaiKhachSan implements Serializable {

    private static final long serialVersionUID = 543213541L;

    int id;
    String ten;
    String moTa;
    String urlHinhAnh;
    int soKhachSan;

    public LoaiKhachSan() {
    }

    public LoaiKhachSan(int id, String ten, String moTa, String urlHinhAnh, int soKhachSan) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.urlHinhAnh = urlHinhAnh;
        this.soKhachSan = soKhachSan;
    }

    public LoaiKhachSan(LoaiKhachSan tp) {
        this.id = tp.id;
        this.ten = tp.ten;
        this.moTa = tp.moTa;
        this.urlHinhAnh = tp.urlHinhAnh;
    }

    public void reload(int id, String ten, String moTa, String urlHinhAnh) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.urlHinhAnh = urlHinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getUrlHinhAnh() {
        return urlHinhAnh;
    }

    public void setUrlHinhAnh(String urlHinhAnh) {
        this.urlHinhAnh = urlHinhAnh;
    }

    public int getSoKhachSan() {
        return soKhachSan;
    }

    public void setSoKhachSan(int soKhachSan) {
        this.soKhachSan = soKhachSan;
    }

}
