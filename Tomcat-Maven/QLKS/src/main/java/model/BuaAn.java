package model;

import java.io.Serializable;
import java.util.ArrayList;

public class BuaAn implements Serializable {

    private static final long serialVersionUID = 1124521354L;

    public static ArrayList<BuaAn> listBuaAn = new ArrayList() {
        {
            add(new BuaAn(0, "Không có"));
            add(new BuaAn(1, "Bữa Sáng"));
            add(new BuaAn(2, "Bữa Sáng Và Trưa"));
            add(new BuaAn(3, "Bữa Sáng Và Tối"));
            add(new BuaAn(4, "Cả Ba Bữa"));
        }
    };

    int id;
    String ten;

    public BuaAn() {
    }

    public BuaAn(int id, String ten) {
        this.id = id;
        this.ten = ten;
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

}
