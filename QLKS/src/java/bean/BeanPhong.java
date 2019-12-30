package bean;

import static bean.BeanKhachSan.hashKhachSan;
import java.io.Serializable;
import model.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "beanPhong", eager = true)
@ApplicationScoped
public class BeanPhong implements Serializable {

    private static final long serialVersionUID = 1786783L;

    public static HashMap<Integer, KhachSan> hashPhongKhachSan;
    public static HashMap<Integer, String> hashPhong;

    private Phong phong;
    private ArrayList<Phong> listPhong;

    public BeanPhong() {
        phong = new Phong();
        hashPhongKhachSan = new HashMap();
        hashPhong = new HashMap();
        listPhong = dao.DAOPhong.getAll();
        for (Phong tmp : listPhong) {
            KhachSan ks = new KhachSan();
            ks.setId(tmp.getIdKhachSan());
            ks.setTen(tmp.getTenKhachSan());
            hashPhongKhachSan.put(tmp.getId(), ks);
            hashPhong.put(tmp.getId(), tmp.getTen());
        }
    }

    public void reset() {
        phong = new Phong();
        phong.setTen("");
        phong.setDienTich(0);
        phong.setGiaThue(0);
        phong.setTienNghi("");
        phong.setMoTa("");
        phong.setLoaiGiuong(0);
        phong.setIdKhachSan(1);
        phong.setTenKhachSan("");
    }

    public void insert(Phong tmp) {
        if (dao.DAOPhong.insert(tmp)) {
            Phong p = new Phong(tmp);
            listPhong.add(p);
            msg.Message.addMessage("Thành Công", "Thêm Phòng thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Thêm Phòng thất bại!");
        }
    }

    public void update(Phong tmp) {
        if (dao.DAOPhong.update(tmp)) {
            int id = tmp.getId();
            tmp.setTenKhachSan(hashKhachSan.get(tmp.getIdKhachSan()));
            for (Phong p : listPhong) {
                if (p.getId() == id) {
                    p.reload(id, tmp.getTen(), tmp.getDienTich(), tmp.getGiaThue(), tmp.getTienNghi(), tmp.getMoTa(), tmp.getLoaiGiuong(), tmp.getIdKhachSan(), tmp.getTenKhachSan());
                    break;
                }
            }
            msg.Message.addMessage("Thành Công", "Sửa Phòng thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Sửa Phòng thất bại!");
        }
    }

    public void delete(int id) {
        if (dao.DAOPhong.delete(id)) {
            for (Phong p : listPhong) {
                if (p.getId() == id) {
                    listPhong.remove(p);
                    break;
                }
            }
            msg.Message.addMessage("Thành Công", "Xóa Phòng thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Xóa Phòng thất bại!");
        }
    }

    //
    // Get - Set, Don't care
    //
    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public ArrayList<Phong> getListPhong() {
        return listPhong;
    }

    public void setListPhong(ArrayList<Phong> listPhong) {
        this.listPhong = listPhong;
    }

}
