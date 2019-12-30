package bean;

import java.io.File;
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

@ManagedBean(name = "beanLoaiKhachSan", eager = true)
@ApplicationScoped
public class BeanLoaiKhachSan implements Serializable {

    private static final long serialVersionUID = 185755L;
    private static final String url = "Content/Images/LoaiKhachSan/";

    public static HashMap<Integer, String> hashLoaiKhachSan;

    @ManagedProperty(value = "#{beanKhachSan.listKhachSan}")
    private ArrayList<KhachSan> lstKS;
    private UploadedFile file;
    private LoaiKhachSan loaiKhachSan;
    private ArrayList<LoaiKhachSan> listLoaiKhachSan;

    public BeanLoaiKhachSan() {
        loaiKhachSan = new LoaiKhachSan();
        listLoaiKhachSan = dao.DAOLoaiKhachSan.getAll();
        hashLoaiKhachSan = new HashMap();
        for (LoaiKhachSan tmp : listLoaiKhachSan) {
            hashLoaiKhachSan.put(tmp.getId(), tmp.getTen());
        }
    }

    public void reset() {
        loaiKhachSan = new LoaiKhachSan();
        loaiKhachSan.setTen("");
        loaiKhachSan.setMoTa("");
        loaiKhachSan.setUrlHinhAnh("");
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        loaiKhachSan.setUrlHinhAnh(url + file.getFileName());
    }

    public void insert(LoaiKhachSan tmp) throws IOException {
        if (tmp.getTen().length() == 0 || file == null) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống tên hoặc hình ảnh!");
            return;
        }
        if (dao.DAOLoaiKhachSan.insert(tmp)) {
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            File f = new File(path + url + file.getFileName());
            try (FileOutputStream fos = new FileOutputStream(f)) {
                byte[] content = file.getContents();
                fos.write(content);
            }
            file = null;
            LoaiKhachSan tp = new LoaiKhachSan(tmp);
            listLoaiKhachSan.add(tp);
            hashLoaiKhachSan.put(tmp.getId(), tmp.getTen());
            msg.Message.addMessage("Thành Công", "Thêm Loại khách sạn thành công!");
        }else {
            msg.Message.errorMessage("Thất Bại", "Thêm Loại khách sạn thất bại!");
        }
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dialog_them').hide();");
    }

    public void update(LoaiKhachSan tmp) throws IOException {
        if (tmp.getTen().length() == 0) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống tên!");
            return;
        }
        if (dao.DAOLoaiKhachSan.update(tmp)) {
            if (file != null) {
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                File f = new File(path + url + file.getFileName());
                try (FileOutputStream fos = new FileOutputStream(f)) {
                    byte[] content = file.getContents();
                    fos.write(content);
                }
                file = null;
            }
            int id = tmp.getId();
            for (LoaiKhachSan tp : listLoaiKhachSan) {
                if (tp.getId() == id) {
                    tp.reload(tmp.getId(), tmp.getTen(), tmp.getMoTa(), tmp.getUrlHinhAnh());
                    break;
                }
            }
            hashLoaiKhachSan.replace(id, tmp.getTen());
            for (KhachSan ks : lstKS) {
                if (ks.getIdLoaiKhachSan()== id) {
                    ks.setTenLoaiKhachSan(tmp.getTen());
                }
            }
            msg.Message.addMessage("Thành Công", "Sửa Loại khách sạn thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Sửa Loại khách sạn thất bại!");
        }
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dialog_sua').hide();");
    }

    public void delete(int Id) {
        if (dao.DAOLoaiKhachSan.delete(Id)) {
            for (LoaiKhachSan tp : listLoaiKhachSan) {
                if (tp.getId() == Id) {
                    listLoaiKhachSan.remove(tp);
                    break;
                }
            }
            msg.Message.addMessage("Thành Công", "Xóa Loại khách sạn thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Xóa Loại khách sạn thất bại!");
        }
    }

    //
    // Get - Set, Don't care
    //
    public LoaiKhachSan getLoaiKhachSan() {
        return loaiKhachSan;
    }

    public void setLoaiKhachSan(LoaiKhachSan loaiKhachSan) {
        this.loaiKhachSan = loaiKhachSan;
    }

    public ArrayList<LoaiKhachSan> getListLoaiKhachSan() {
        return listLoaiKhachSan;
    }

    public void setListLoaiKhachSan(ArrayList<LoaiKhachSan> listLoaiKhachSan) {
        this.listLoaiKhachSan = listLoaiKhachSan;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public ArrayList<KhachSan> getLstKS() {
        return lstKS;
    }

    public void setLstKS(ArrayList<KhachSan> lstKS) {
        this.lstKS = lstKS;
    }

}
