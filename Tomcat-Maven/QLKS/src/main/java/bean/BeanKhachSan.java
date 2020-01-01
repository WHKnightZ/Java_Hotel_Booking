package bean;

import static bean.BeanLoaiKhachSan.hashLoaiKhachSan;
import static bean.BeanThanhPho.hashThanhPho;
import static bean.BeanThanhPho.hashUrlHinhAnhThanhPho;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import model.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "beanKhachSan", eager = true)
@ApplicationScoped
public class BeanKhachSan implements Serializable {

    private static final long serialVersionUID = 1786783L;
    private static final String url = "Content/Images/KhachSan/";

    // Một hashmap để lấy ra Tên khách sạn từ Id khách sạn
    public static HashMap<Integer, String> hashKhachSan;

    private UploadedFile file;
    private KhachSan khachSan;
    private String urlHinhAnh;
    private ArrayList<KhachSan> listKhachSan;
    private ArrayList<BuaAn> listBuaAn;
    private String[] strDanhGia = {"Bình thường", "Khá ổn", "Chất lượng", "Sang trọng", "Tuyệt vời", "Xuất sắc"};

    // Khởi tạo Bean
    public BeanKhachSan() {
        khachSan = new KhachSan();
        listBuaAn = new ArrayList();
        for (BuaAn tmp : BuaAn.listBuaAn) {
            listBuaAn.add(tmp);
        }
        listKhachSan = dao.DAOKhachSan.getAll();
        hashKhachSan = new HashMap();
        for (KhachSan tmp : listKhachSan) {
            hashKhachSan.put(tmp.getId(), tmp.getTen());
        }
    }

    // Khi bấm vào nút thêm mới, các trường sẽ được reset
    public void reset() {
        khachSan = new KhachSan();
        khachSan.setTen("");
        khachSan.setDiaChi("");
        khachSan.setSoDienThoai("");
        khachSan.setCachTrungTam(0);
        khachSan.setMoTa("");
        khachSan.setGiapBien(false);
        khachSan.setDanhGia(5);
        khachSan.setBuaAn(0);
        khachSan.setIdThanhPho(1);
        khachSan.setTenThanhPho("");
        khachSan.setIdLoaiKhachSan(1);
        khachSan.setTenLoaiKhachSan("");
    }

    // Khi bấm vào upload ảnh, ảnh sẽ được lưu lại, đồng thời in ra đường dẫn ảnh
    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        this.setUrlHinhAnh(url + khachSan.getId() + ".jpg");
    }

    public void insert(KhachSan tmp) throws FileNotFoundException, IOException {
        if (tmp.getTen().length() == 0 || tmp.getDiaChi().length() == 0 || file == null) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống trường nào!");
            return;
        }
        if (dao.DAOKhachSan.insert(tmp)) {
            // Lưu ảnh được chọn vào Web server
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            File f = new File(path + url + tmp.getId() + ".jpg");
            try (FileOutputStream fos = new FileOutputStream(f)) {
                byte[] content = file.getContents();
                fos.write(content);
            }
            file = null;
            tmp.setTenThanhPho(hashThanhPho.get(tmp.getIdThanhPho()));
            tmp.setUrlHinhAnhThanhPho(hashUrlHinhAnhThanhPho.get(tmp.getIdThanhPho()));
            tmp.setTenLoaiKhachSan(hashLoaiKhachSan.get(tmp.getIdLoaiKhachSan()));
            KhachSan ks = new KhachSan(tmp);
            listKhachSan.add(ks);
            msg.Message.addMessage("Thành Công", "Thêm Khách sạn thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Thêm Khách sạn thất bại!");
        }
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dialog_them').hide();");
    }

    public void update(KhachSan tmp) throws FileNotFoundException, IOException {
        if (tmp.getTen().length() == 0 || tmp.getDiaChi().length() == 0) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống trường nào!");
            return;
        }
        if (dao.DAOKhachSan.update(tmp)) {
            if (file != null) {
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                File f = new File(path + url + tmp.getId() + ".jpg");
                try (FileOutputStream fos = new FileOutputStream(f)) {
                    byte[] content = file.getContents();
                    fos.write(content);
                }
                file = null;
            }
            int id = tmp.getId();
            tmp.setTenThanhPho(hashThanhPho.get(tmp.getIdThanhPho()));
            tmp.setUrlHinhAnhThanhPho(hashUrlHinhAnhThanhPho.get(tmp.getIdThanhPho()));
            tmp.setTenLoaiKhachSan(hashLoaiKhachSan.get(tmp.getIdLoaiKhachSan()));
            for (KhachSan ks : listKhachSan) {
                if (ks.getId() == id) {
                    ks.reload(id, tmp.getTen(), tmp.getDiaChi(), tmp.getSoDienThoai(), tmp.getCachTrungTam(), tmp.getMoTa(), tmp.isGiapBien(), tmp.getDanhGia(), tmp.getBuaAn(), tmp.getIdThanhPho(), tmp.getTenThanhPho(), tmp.getIdLoaiKhachSan(), tmp.getTenLoaiKhachSan(), tmp.getUrlHinhAnhThanhPho());
                    break;
                }
            }
            msg.Message.addMessage("Thành Công", "Sửa Khách sạn thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Sửa Khách sạn thất bại!");
        }
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dialog_sua').hide();");
    }

    public void delete(int id) {
        KhachSan tmp = null;
        if (dao.DAOKhachSan.delete(id)) {
            for (KhachSan ks : listKhachSan) {
                if (ks.getId() == id) {
                    listKhachSan.remove(ks);
                    tmp = ks;
                    break;
                }
            }
            msg.Message.addMessage("Thành Công", "Xóa Khách sạn thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Xóa Khách sạn thất bại!");
        }
    }

    //
    // Get - Set, Don't care
    //
    public KhachSan getKhachSan() {
        return khachSan;
    }

    public void setKhachSan(KhachSan khachSan) {
        this.khachSan = khachSan;
    }

    public ArrayList<KhachSan> getListKhachSan() {
        return listKhachSan;
    }

    public void setListKhachSan(ArrayList<KhachSan> listKhachSan) {
        this.listKhachSan = listKhachSan;
    }

    public ArrayList<BuaAn> getListBuaAn() {
        return listBuaAn;
    }

    public void setListBuaAn(ArrayList<BuaAn> listBuaAn) {
        this.listBuaAn = listBuaAn;
    }

    public String[] getStrDanhGia() {
        return strDanhGia;
    }

    public void setStrDanhGia(String[] strDanhGia) {
        this.strDanhGia = strDanhGia;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getUrlHinhAnh() {
        return urlHinhAnh;
    }

    public void setUrlHinhAnh(String urlHinhAnh) {
        this.urlHinhAnh = urlHinhAnh;
    }

}
