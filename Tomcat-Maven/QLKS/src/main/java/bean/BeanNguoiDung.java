package bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "beanNguoiDung", eager = true)
@SessionScoped
public class BeanNguoiDung implements Serializable {
    
    private static final long serialVersionUID = 1437123L;
    private static final DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    @ManagedProperty(value = "#{beanKhachSan.listKhachSan}")
    private ArrayList<KhachSan> lstKS;
    @ManagedProperty(value = "#{beanNavigation.listDatPhong}")
    private ArrayList<DatPhong> lstDP;
    @ManagedProperty(value = "#{beanTaiKhoan.listTaiKhoan}")
    private ArrayList<TaiKhoan> lstTK;
    private TaiKhoan taiKhoanDangNhap;
    private String nhapLaiMatKhau;
    private KhachSan khachSanGoiY;
    private ArrayList<LichSu> listLichSu;
    
    public BeanNguoiDung() {
        taiKhoanDangNhap = new TaiKhoan();
    }
    
    public void dangNhap() {
        if (taiKhoanDangNhap.getTenTaiKhoan().isEmpty() || taiKhoanDangNhap.getMatKhau().isEmpty()) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống Tên tài khoản hoặc Mật khẩu!");
            return;
        }
        TaiKhoan tk = dao.DAOTaiKhoan.getByDangNhap(taiKhoanDangNhap.getTenTaiKhoan(), taiKhoanDangNhap.getMatKhau());
        if (tk != null) {
            // PrimeFaces current để lấy giá trị các thành phần trong view
            // ở đây để lấy form đăng nhập và bật form đó lên
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('dialog_dangnhap').hide();");
            msg.Message.addMessage("Thành Công", "Đăng nhập thành công!");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            // Sau khi đăng nhập thì thêm cookie với thời gian là 36000 giây
            Cookie cookie = new Cookie("TenTaiKhoan", tk.getTenTaiKhoan());
            cookie.setMaxAge(36000);
            cookie.setPath("/");
            response.addCookie(cookie);
            cookie = new Cookie("MatKhau", tk.getMatKhau());
            cookie.setMaxAge(36000);
            cookie.setPath("/");
            response.addCookie(cookie);
            // Thêm session tài khoản
            HttpSession session = request.getSession();
            session.setAttribute("TaiKhoan", tk);
            if (tk.isIsAdmin()) {
                // Đăng nhập nếu là admin thì chuyển sang trang quản trị
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                try {
                    ec.redirect(ec.getRequestContextPath() + "/faces/Admin/adminTaiKhoan.xhtml");
                } catch (IOException e) {
                }
            }
        } else {
            msg.Message.errorMessage("Thất Bại", "Sai tên tài khoản hoặc mật khẩu!");
        }
    }
    
    public void dangKy() {
        if (taiKhoanDangNhap.getTenTaiKhoan().isEmpty() || taiKhoanDangNhap.getMatKhau().isEmpty()) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống Tên tài khoản hoặc Mật khẩu!");
            return;
        }
        if (!taiKhoanDangNhap.getMatKhau().equals(nhapLaiMatKhau)) {
            msg.Message.errorMessage("Thất Bại", "Mật khẩu không khớp!");
            return;
        }
        taiKhoanDangNhap.setIsAdmin(false);
        if (dao.DAOTaiKhoan.insert(taiKhoanDangNhap)) {
            // Đăng ký xong phải cho vào list tài khoản thì mới thấy tài khoản này
            // bên trang quản trị
            lstTK.add(taiKhoanDangNhap);
            msg.Message.addMessage("Thành Công", "Đăng ký thành công!");
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('dialog_dangky').hide();");
        } else {
            msg.Message.errorMessage("Thất Bại", "Tên tài khoản đã được sử dụng!");
        }
    }
    
    public void dangXuat() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        // Đăng xuất thì xóa session và cookie
        HttpSession session = request.getSession();
        session.invalidate();
        Cookie cookie = new Cookie("TenTaiKhoan", "");
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            // Đồng thời đưa về trang chủ
            ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
        } catch (IOException e) {
        }
    }
    
    public void caNhan() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        TaiKhoan tk = (TaiKhoan) session.getAttribute("TaiKhoan");
        if (tk == null) {
            // Bấm vào trang cá nhân mà chưa đăng nhập thì bắt đăng nhập
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('dialog_dangnhap').show();");
            msg.Message.addMessage("Thông Báo", "Bạn cần đăng nhập trước!");
            return;
        }
        taiKhoanDangNhap = new TaiKhoan(tk);
        nhapLaiMatKhau = tk.getMatKhau();
        // Trong trang cá nhân ở bên phải có mục khách sạn gợi ý, khách sạn ở
        // đây là ngẫu nhiên nên dùng 1 biến để lấy ngẫu nhiên khách sạn trong
        // danh sách
        Random rand = new Random();
        khachSanGoiY = lstKS.get(rand.nextInt(lstKS.size()));
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            // Và rồi chuyển hướng sang trang cá nhân
            ec.redirect(ec.getRequestContextPath() + "/faces/caNhan.xhtml");
        } catch (IOException e) {
        }
    }
    
    public void capNhatThongTin() {
        if (taiKhoanDangNhap.getMatKhau().isEmpty()) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống mật khẩu!");
            return;
        }
        if (!taiKhoanDangNhap.getMatKhau().equals(nhapLaiMatKhau)) {
            msg.Message.errorMessage("Thất Bại", "Mật khẩu không khớp!");
            return;
        }
        if (dao.DAOTaiKhoan.update(taiKhoanDangNhap)) {
            msg.Message.addMessage("Thành Công", "Cập nhật thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Cập nhật thất bại!");
        }
        // Cập nhật thông tin trong csdl xong thì cũng phải cập nhật trong session
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        TaiKhoan tk = new TaiKhoan(taiKhoanDangNhap);
        session.setAttribute("TaiKhoan", tk);
    }
    
    public String lichSu() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        TaiKhoan tk = (TaiKhoan) session.getAttribute("TaiKhoan");
        listLichSu = new ArrayList();
        KhachSan ks;
        String thanhTien;
        Date homNay = new Date();
        // Có 3 trạng thái: 0: Có thể hủy, 1: Quá hạn, 2: Đã hủy
        int trangThai;
        String strNgayDat, strNgayDen, strNgayTra;
        for (DatPhong tmp : lstDP) {
            if (tmp.getTaiKhoan().equals(tk.getTenTaiKhoan())) {
                ks = BeanPhong.hashPhongKhachSan.get(tmp.getIdPhong());
                thanhTien = String.format("%,d", tmp.getThanhTien() * 1000);
                if (tmp.isDaHuy()) {
                    // Trong csdl, nếu đã hủy => trạng thái = 2 (đã hủy)
                    trangThai = 2;
                } else {
                    // Nếu ngày đến < hôm nay => trạng thái = 1 (quá hạn)
                    // Còn không thì vẫn hủy được
                    trangThai = (util.CompareDate.compareNoTime(tmp.getNgayDen(), homNay) == -1) ? 1 : 0;
                }
                strNgayDat = formatter.format(tmp.getNgayDat());
                strNgayDen = formatter.format(tmp.getNgayDen());
                strNgayTra = formatter.format(tmp.getNgayTra());
                LichSu ls = new LichSu(tmp.getId(), BeanPhong.hashPhong.get(tmp.getIdPhong()), ks.getId(),
                        ks.getTen(), strNgayDat, strNgayDen, strNgayTra,
                        tmp.getDichVu(), tmp.getGhiChu(), thanhTien, trangThai);
                // Add vào 0 để cái nào mới đặt được cho lên đầu
                listLichSu.add(0, ls);
            }
        }
        return "lichSu";
    }
    
    public void huyDatPhong(int id) {
        if (dao.DAODatPhong.update(id)) {            
            for (LichSu tmp : listLichSu) {
                if (tmp.getId() == id) {
                    tmp.setTrangThai(2);
                    break;
                }
            }
            for (DatPhong tmp : lstDP) {
                if (tmp.getId() == id) {
                    tmp.setDaHuy(true);
                    break;
                }
            }
            msg.Message.addMessage("Thành Công", "Hủy đặt phòng thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Hủy đặt phòng thất bại!");
        }
    }

    //
    // Get - Set, Don't care
    //
    public TaiKhoan getTaiKhoanDangNhap() {
        return taiKhoanDangNhap;
    }
    
    public void setTaiKhoanDangNhap(TaiKhoan taiKhoanDangNhap) {
        this.taiKhoanDangNhap = taiKhoanDangNhap;
    }
    
    public String getNhapLaiMatKhau() {
        return nhapLaiMatKhau;
    }
    
    public void setNhapLaiMatKhau(String nhapLaiMatKhau) {
        this.nhapLaiMatKhau = nhapLaiMatKhau;
    }
    
    public KhachSan getKhachSanGoiY() {
        return khachSanGoiY;
    }
    
    public void setKhachSanGoiY(KhachSan khachSanGoiY) {
        this.khachSanGoiY = khachSanGoiY;
    }
    
    public ArrayList<KhachSan> getLstKS() {
        return lstKS;
    }
    
    public void setLstKS(ArrayList<KhachSan> lstKS) {
        this.lstKS = lstKS;
    }
    
    public ArrayList<DatPhong> getLstDP() {
        return lstDP;
    }
    
    public void setLstDP(ArrayList<DatPhong> lstDP) {
        this.lstDP = lstDP;
    }
    
    public ArrayList<LichSu> getListLichSu() {
        return listLichSu;
    }
    
    public void setListLichSu(ArrayList<LichSu> listLichSu) {
        this.listLichSu = listLichSu;
    }
    
    public ArrayList<TaiKhoan> getLstTK() {
        return lstTK;
    }
    
    public void setLstTK(ArrayList<TaiKhoan> lstTK) {
        this.lstTK = lstTK;
    }
    
}
