package bean;

import java.io.Serializable;
import model.*;
import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "beanTaiKhoan", eager = true)
@ApplicationScoped
public class BeanTaiKhoan implements Serializable {

    private static final long serialVersionUID = 45438764L;

    private TaiKhoan taiKhoan;
    private ArrayList<TaiKhoan> listTaiKhoan;

    public BeanTaiKhoan() {
        taiKhoan = new TaiKhoan();
        listTaiKhoan = dao.DAOTaiKhoan.getAll();
    }

    public void reset() {
        taiKhoan = new TaiKhoan();
    }

    public void insert(TaiKhoan tmp) {
        if (tmp.getTenTaiKhoan().isEmpty() || tmp.getMatKhau().isEmpty()) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống Tên tài khoản hoặc Mật khẩu!");
            return;
        }
        if (dao.DAOTaiKhoan.insert(tmp)) {
            TaiKhoan tk = new TaiKhoan(tmp);
            listTaiKhoan.add(tk);
            msg.Message.addMessage("Thành Công", "Thêm tài khoản thành công!");
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('dialog_them').hide();");
        } else {
            msg.Message.errorMessage("Thất Bại", "Tài khoản đã tồn tại!");
        }
    }

    public void update(TaiKhoan tmp) {
        if (tmp.getTenTaiKhoan().isEmpty() || tmp.getMatKhau().isEmpty()) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống Tên tài khoản hoặc Mật khẩu!");
            return;
        }
        if (dao.DAOTaiKhoan.update(tmp)) {
            String tenTaiKhoan = tmp.getTenTaiKhoan();
            for (TaiKhoan tk : listTaiKhoan) {
                if (tk.getTenTaiKhoan().equals(tenTaiKhoan)) {
                    tk.reload(tmp.getTenTaiKhoan(), tmp.getMatKhau(), tmp.getHoTen(), tmp.isGioiTinh(), tmp.getSoDienThoai(), tmp.getEmail(), tmp.isIsAdmin());
                    break;
                }
            }
            msg.Message.addMessage("Thành Công", "Sửa Tài khoản thành công!");
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('dialog_sua').hide();");
        } else {
            msg.Message.errorMessage("Thất Bại", "Sửa Tài khoản thất bại!");
        }
    }

    public void delete(String tenTaiKhoan) {
        if (dao.DAOTaiKhoan.delete(tenTaiKhoan)) {
            for (TaiKhoan tk : listTaiKhoan) {
                if (tk.getTenTaiKhoan().equals(tenTaiKhoan)) {
                    listTaiKhoan.remove(tk);
                    break;
                }
            }
            msg.Message.addMessage("Thành Công", "Xóa Tài khoản thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Xóa Tài khoản thất bại!");
        }
    }

    //
    // Get - Set, Don't care
    //
    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public ArrayList<TaiKhoan> getListTaiKhoan() {
        return listTaiKhoan;
    }

    public void setListTaiKhoan(ArrayList<TaiKhoan> listTaiKhoan) {
        this.listTaiKhoan = listTaiKhoan;
    }

}
