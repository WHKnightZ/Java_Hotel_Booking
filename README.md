# Project Quản Lý Khách Sạn

Quản Lý Khách Sạn - Hotel Booking By Java

BTL Môn Lập Trình Nâng Cao

## Sử dụng:

NetBeans IDE 8.2

Java 8.0_231

Glassfish 4.1.1

JSF 2.2

PrimeFaces 7.0

Thanks to Booking.com

Jdbc4-2.0

## Cài đặt:

Trước tiên chạy QuanLyKhachSanDb.sql bằng MSSQL, ở đây dùng Jdbc để kết nối MSSQL

Dùng NetBeans IDE 8.2 mở Project

Chạy thôi

## Demo:

Up lên host thấy nhiều lỗi quá http://whknightz.kilatiron.com/ - nhưng host chỉ cho trial 3 ngày :disappointed:

Và đã có host mới: https://whknightz.herokuapp.com/ :smiley:


## Giải thích các Bean:

ApplicationScoped: Tất cả các bean này sẽ được khởi tạo khi ứng dụng web chạy, có lẽ là khi build luôn, nó đi theo server, nghĩa là mỗi server chỉ tồn tại một bean có tên như vậy

SessionScoped: Đi theo session, mỗi khi một trình duyệt vào web thì tất cả các bean này được khởi tạo đi theo phiên làm việc đó, mất đi khi trình duyệt đó tắt hoặc hết thời gian, nghĩa là mỗi trình duyệt có một bean riêng

=> Các bean như BeanKhachSan, BeanLoaiKhachSan, BeanThanhPho chỉ để hiển thị cho người dùng, ai cũng thấy như nhau nên để ApplicaionScoped

=> BeanNavigation, BeanDangNhap lưu các thông tin người dùng, mỗi người một khác nên để SessionScoped

## Danh sách lỗi chưa sửa:

Hai btn DangNhap trong form DangKy va DangKy trong form DangNhap chưa làm

Lỗi lọc phòng có lúc sai khi vừa đặt xong (chưa để ý lúc nào) &emsp; :heavy_check_mark:
- Do không tạo đối tượng datPhong mới khi đặt nên bị chung địa chỉ => phòng cũ và phòng mới cùng địa chỉ => trở thành một

Lỗi minDate ở cả chọn Date ở trang chủ và trang đặt phòng (tạm bỏ)

Tìm cách đặt phòng luôn hiện link xem ngay lịch sử, nên là ở messageBox thông báo nhưng chưa biết làm

Background trong khách sạn ko đổi khi cập nhật thành phố, do Model Khách sạn có thêm trường dư thừa urlHinhAnh => cần cập nhật lại thông tin danh sách khách sạn (url, ...) khi cập nhật thành phố, loại khách sạn ... &emsp; :heavy_check_mark:

Cập nhật ds phòng khi cập nhật Khách sạn

Nếu thêm loại khách sạn phải cập nhật listKhachSan trong bộ lọc

Form Admin Khách sạn thiếu chọn Loại KS &emsp; :heavy_check_mark:

Lỗi hủy đặt phòng ở Lịch sử khi vừa đặt xong &emsp; :heavy_check_mark:
- Do khi vào bảng DatPhong không lấy ra Id vừa thêm để cho vào lịch sử => Id luôn bằng 0 => lỗi

=> Hầu hết các lỗi trên là do sessionBean được khởi tạo đúng 1 lần duy nhất khi vào phiên nên khi khởi động lại trình duyệt sẽ được giải quyết

Tóm lại: Do có sử dụng các trường dư thừa nên Khi thêm mới thanhPho (loaiKhachSan) phải thêm vào hashThanhPho (hashLoaiKhachSan), sửa thanhPho (loaiKhachSan) phải sửa hashThanhPho (hashLoaiKhachSan), đồng thời sửa urlHinhAnhThanhPho, tenThanhPho (tenLoaiKhachSan) của các khách sạn liên quan. Khi thêm mới, sửa, xóa khachSan cần cập nhật soKhachSan của thanhPho (loaiKhachSan), sửa khachSan cần cập nhật tenKhachSan của các phong thuộc khachSan đó