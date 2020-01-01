package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TaiKhoan;

// Khi định vào các view .xhtml thì bộ lọc này sẽ được chạy trước khi view được đến
@WebFilter(filterName = "Filter", urlPatterns = {"*.xhtml"})
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // Lấy URL
        String reqURI = req.getRequestURI();
        // Lấy session để kiểm tra xem đã đăng nhập chưa, nếu đã đăng nhập thì tk khác null
        HttpSession session = req.getSession();
        TaiKhoan tk = (TaiKhoan) session.getAttribute("TaiKhoan");
        if (tk == null) {
            // tk null thì check cookie, cookie có thì đăng nhập và lưu tài khoản vào session luôn
            Cookie[] cookies = req.getCookies();
            String TenTaiKhoan = null, MatKhau = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("TenTaiKhoan")) {
                        TenTaiKhoan = cookie.getValue();
                    } else if (cookie.getName().equals("MatKhau")) {
                        MatKhau = cookie.getValue();
                    }
                }
                if (TenTaiKhoan != null && MatKhau != null) {
                    tk = dao.DAOTaiKhoan.getByDangNhap(TenTaiKhoan, MatKhau);
                }
                if (tk != null) {
                    session.setAttribute("TaiKhoan", tk);
                }
            }
        }
        if (tk != null) {
            // tk khác null nghĩa là đã đăng nhập, nếu tk đó là Admin thì cần kiểm tra
            // URL có chứa /Admin/ ko, nếu có thì cho phép đi đến (doFilter), nếu ko thì
            // nghĩa là đang ở view ko phải view quản trị => chuyển đến trang quản trị
            if (tk.isIsAdmin()) {
                if (!reqURI.contains("/Admin/")) {
                    res.sendRedirect(req.getContextPath() + "/faces/Admin/adminTaiKhoan.xhtml");
                } else {
                    chain.doFilter(request, response);
                }
            }
        }
        if (tk == null || !tk.isIsAdmin()) {
            // Nếu chưa đăng nhập và ko phải admin mà cố truy cập trang admin thì phải đưa về trang chủ
            if (reqURI.contains("/Admin/")) {
                res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }

}
