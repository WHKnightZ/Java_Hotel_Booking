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

@ManagedBean(name = "beanThanhPho", eager = true)
@ApplicationScoped
public class BeanThanhPho implements Serializable {

    private static final long serialVersionUID = 1124771L;
    private static final String url = "Content/Images/ThanhPho/";

    public static HashMap<Integer, String> hashThanhPho;
    public static HashMap<Integer, String> hashUrlHinhAnhThanhPho;

    @ManagedProperty(value = "#{beanKhachSan.listKhachSan}")
    private ArrayList<KhachSan> lstKS;
    private ThanhPho thanhPho;
    private UploadedFile file;
    private ArrayList<ThanhPho> listThanhPho;

    public BeanThanhPho() {
        thanhPho = new ThanhPho();
        listThanhPho = dao.DAOThanhPho.getAll();
        hashThanhPho = new HashMap();
        hashUrlHinhAnhThanhPho = new HashMap();
        for (ThanhPho tmp : listThanhPho) {
            hashThanhPho.put(tmp.getId(), tmp.getTen());
            hashUrlHinhAnhThanhPho.put(tmp.getId(), tmp.getUrlHinhAnh());
        }
    }

    public void reset() {
        thanhPho = new ThanhPho();
        thanhPho.setTen("");
        thanhPho.setMoTa("");
        thanhPho.setUrlHinhAnh("");
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        thanhPho.setUrlHinhAnh(url + file.getFileName());
    }

    public void insert(ThanhPho tmp) throws IOException {
        if (tmp.getTen().length() == 0 || file == null) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống tên hoặc hình ảnh!");
            return;
        }
        if (dao.DAOThanhPho.insert(tmp)) {
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            File f = new File(path + url + file.getFileName());
            try (FileOutputStream fos = new FileOutputStream(f)) {
                byte[] content = file.getContents();
                fos.write(content);
            }
            file = null;
            ThanhPho tp = new ThanhPho(tmp);
            listThanhPho.add(tp);
            hashThanhPho.put(tmp.getId(), tmp.getTen());
            hashUrlHinhAnhThanhPho.put(tmp.getId(), tmp.getUrlHinhAnh());
            msg.Message.addMessage("Thành Công", "Thêm Thành phố thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Thêm Thành phố thất bại!");
        }
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dialog_them').hide();");
    }

    public void update(ThanhPho tmp) throws IOException {
        if (tmp.getTen().length() == 0) {
            msg.Message.errorMessage("Thất Bại", "Không được để trống tên!");
            return;
        }
        if (dao.DAOThanhPho.update(tmp)) {
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
            for (ThanhPho tp : listThanhPho) {
                if (tp.getId() == id) {
                    tp.reload(tmp.getId(), tmp.getTen(), tmp.getMoTa(), tmp.getUrlHinhAnh());
                    break;
                }
            }
            hashThanhPho.replace(id, tmp.getTen());
            hashUrlHinhAnhThanhPho.replace(tmp.getId(), tmp.getUrlHinhAnh());
            for (KhachSan ks : lstKS) {
                if (ks.getIdThanhPho() == id) {
                    ks.setTenThanhPho(tmp.getTen());
                    ks.setUrlHinhAnhThanhPho(tmp.getUrlHinhAnh());
                }
            }
            msg.Message.addMessage("Thành Công", "Sửa Thành phố thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Sửa Thành Phố thất bại!");
        }
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dialog_sua').hide();");
    }

    public void delete(int id) {
        if (dao.DAOThanhPho.delete(id)) {
            for (ThanhPho tp : listThanhPho) {
                if (tp.getId() == id) {
                    listThanhPho.remove(tp);
                    break;
                }
            }
            msg.Message.addMessage("Thành Công", "Xóa Thành phố thành công!");
        } else {
            msg.Message.errorMessage("Thất Bại", "Xóa Thành phố thất bại!");
        }
    }

    //
    // Get - Set, Don't care
    //
    public ThanhPho getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(ThanhPho thanhPho) {
        this.thanhPho = thanhPho;
    }

    public ArrayList<ThanhPho> getListThanhPho() {
        return listThanhPho;
    }

    public void setListThanhPho(ArrayList<ThanhPho> listThanhPho) {
        this.listThanhPho = listThanhPho;
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
