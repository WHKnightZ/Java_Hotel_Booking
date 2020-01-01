package model;

import java.io.Serializable;

public class KhachSan implements Serializable  {

    private static final long serialVersionUID = 1234551L;
    
    int id;
    String ten;
    String diaChi;
    String soDienThoai;
    int cachTrungTam;
    String moTa;
    boolean giapBien;
    int danhGia;
    int buaAn;
    int idThanhPho;
    String tenThanhPho;
    int idLoaiKhachSan;
    String tenLoaiKhachSan;
    String urlHinhAnhThanhPho;

    public KhachSan() {
    }

    public KhachSan(int id, String ten, String diaChi, String soDienThoai, int cachTrungTam, String moTa, boolean giapBien, int danhGia, int buaAn, int idThanhPho, String tenThanhPho, int idLoaiKhachSan, String tenLoaiKhachSan, String urlHinhAnhThanhPho) {
        this.id = id;
        this.ten = ten;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.cachTrungTam = cachTrungTam;
        this.moTa = moTa;
        this.giapBien = giapBien;
        this.danhGia = danhGia;
        this.buaAn = buaAn;
        this.idThanhPho = idThanhPho;
        this.tenThanhPho = tenThanhPho;
        this.idLoaiKhachSan = idLoaiKhachSan;
        this.tenLoaiKhachSan = tenLoaiKhachSan;
        this.urlHinhAnhThanhPho = urlHinhAnhThanhPho;
    }

    public KhachSan(KhachSan ks) {
        this.id = ks.id;
        this.ten = ks.ten;
        this.diaChi = ks.diaChi;
        this.soDienThoai = ks.soDienThoai;
        this.cachTrungTam = ks.cachTrungTam;
        this.moTa = ks.moTa;
        this.giapBien = ks.giapBien;
        this.danhGia = ks.danhGia;
        this.buaAn = ks.buaAn;
        this.idThanhPho = ks.idThanhPho;
        this.tenThanhPho = ks.tenThanhPho;
        this.idLoaiKhachSan = ks.idLoaiKhachSan;
        this.tenLoaiKhachSan = ks.tenLoaiKhachSan;
        this.urlHinhAnhThanhPho = ks.urlHinhAnhThanhPho;
    }
    
    public void reload(int id, String ten, String diaChi, String soDienThoai, int cachTrungTam, String moTa, boolean giapBien, int danhGia, int buaAn, int idThanhPho, String tenThanhPho, int idLoaiKhachSan, String tenLoaiKhachSan, String urlHinhAnhThanhPho) {
        this.id = id;
        this.ten = ten;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.cachTrungTam = cachTrungTam;
        this.moTa = moTa;
        this.giapBien = giapBien;
        this.danhGia = danhGia;
        this.buaAn = buaAn;
        this.idThanhPho = idThanhPho;
        this.tenThanhPho = tenThanhPho;
        this.idLoaiKhachSan = idLoaiKhachSan;
        this.tenLoaiKhachSan = tenLoaiKhachSan;
        this.urlHinhAnhThanhPho = urlHinhAnhThanhPho;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getCachTrungTam() {
        return cachTrungTam;
    }

    public void setCachTrungTam(int cachTrungTam) {
        this.cachTrungTam = cachTrungTam;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public boolean isGiapBien() {
        return giapBien;
    }

    public void setGiapBien(boolean giapBien) {
        this.giapBien = giapBien;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }

    public int getBuaAn() {
        return buaAn;
    }

    public void setBuaAn(int buaAn) {
        this.buaAn = buaAn;
    }

    public int getIdThanhPho() {
        return idThanhPho;
    }

    public void setIdThanhPho(int idThanhPho) {
        this.idThanhPho = idThanhPho;
    }

    public String getTenThanhPho() {
        return tenThanhPho;
    }

    public void setTenThanhPho(String tenThanhPho) {
        this.tenThanhPho = tenThanhPho;
    }

    public int getIdLoaiKhachSan() {
        return idLoaiKhachSan;
    }

    public void setIdLoaiKhachSan(int idLoaiKhachSan) {
        this.idLoaiKhachSan = idLoaiKhachSan;
    }

    public String getTenLoaiKhachSan() {
        return tenLoaiKhachSan;
    }

    public void setTenLoaiKhachSan(String tenLoaiKhachSan) {
        this.tenLoaiKhachSan = tenLoaiKhachSan;
    }

    public String getUrlHinhAnhThanhPho() {
        return urlHinhAnhThanhPho;
    }

    public void setUrlHinhAnhThanhPho(String urlHinhAnhThanhPho) {
        this.urlHinhAnhThanhPho = urlHinhAnhThanhPho;
    }

}
