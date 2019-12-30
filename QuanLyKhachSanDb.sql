-- Tham khảo khi đặt phòng tại https://www.booking.com/
-- DROP DATABASE QuanLyKhachSanDb

CREATE DATABASE QuanLyKhachSanDb
GO
USE QuanLyKhachSanDb
GO

CREATE TABLE ThanhPho (
	Id int PRIMARY KEY identity(1, 1),
	Ten nvarchar(100),
	MoTa nvarchar(200),
	UrlHinhAnh varchar(200)
)
GO

CREATE TABLE LoaiKhachSan (
	Id int PRIMARY KEY identity(1, 1),
	Ten nvarchar(100),
	MoTa nvarchar(200),
	UrlHinhAnh varchar(200)
)
GO

CREATE TABLE KhachSan(
	Id int PRIMARY KEY identity(1, 1),
	Ten nvarchar(100),
	DiaChi nvarchar(100),
	SoDienThoai varchar(10),
	CachTrungTam int,
	MoTa nvarchar(1000),
	GiapBien bit,
	DanhGia int check(DanhGia >= 0 and DanhGia <= 5),
	BuaAn int check(BuaAn >= 0 and BuaAn <= 4), --['Không Có', 'Bữa Sáng', 'Bữa Sáng Và Trưa', 'Bữa Sáng Và Tối', 'Cả Ba Bữa']
	IdThanhPho int REFERENCES ThanhPho(Id),
	IdLoaiKhachSan int REFERENCES LoaiKhachSan(Id),
)
GO

CREATE TABLE Phong(
	Id int PRIMARY KEY identity(1, 1),
	Ten nvarchar(100),
	DienTich int,
	GiaThue int,
	TienNghi nvarchar(200),
	MoTa nvarchar(1000),
	LoaiGiuong int check(LoaiGiuong >= 0 and LoaiGiuong <= 1), --['Giường Đơn', 'Giường Đôi']
	IdKhachSan int REFERENCES KhachSan(Id)
)
GO

CREATE TABLE TaiKhoan(
	TenTaiKhoan varchar(50) PRIMARY KEY,
	MatKhau varchar(20),
	HoTen nvarchar(50),
	GioiTinh bit,
	SoDienThoai varchar(15),
	Email varchar(50),
	IsAdmin bit
)
GO

CREATE TABLE DatPhong(
	Id int PRIMARY KEY identity(1, 1),
	TaiKhoan varchar(50) REFERENCES TaiKhoan(TenTaiKhoan),
	IdPhong int REFERENCES Phong(Id),
	NgayDat date,
	NgayDen date,
	NgayTra date,
	DichVu nvarchar(200),
	GhiChu nvarchar(200),
	ThanhTien int,
	DaHuy bit -- True: Chưa hủy, True: Đã hủy
)
GO

-- Insert data

insert into ThanhPho values
(N'Hà Nội', N'Thành Phố Hà Nội', 'Content/Images/ThanhPho/HaNoi.jpg'),
(N'TP. Hồ Chí Minh', N'Thành Phố Hồ Chí Minh', 'Content/Images/ThanhPho/HoChiMinh.jpg'),
(N'Đà Nẵng', N'Thành Phố Đà Nẵng', 'Content/Images/ThanhPho/DaNang.jpg'),
(N'Đà Lạt', N'Thành Phố Đà Lạt', 'Content/Images/ThanhPho/DaLat.jpg'),
(N'Hội An', N'Thành Phố Hội An', 'Content/Images/ThanhPho/HoiAn.jpg'),
(N'Phú Quốc', N'Thành Phố Phú Quốc', 'Content/Images/ThanhPho/PhuQuoc.jpg'),
(N'Sapa', N'Thành Phố Sapa', 'Content/Images/ThanhPho/Sapa.jpg'),
(N'Huế', N'Thành Phố Huế', 'Content/Images/ThanhPho/Hue.jpg'),
(N'Nha Trang', N'Thành Phố Nha Trang', 'Content/Images/ThanhPho/NhaTrang.jpg'),
(N'Buôn Ma Thuột', N'Thành Phố Buôn Ma Thuột', 'Content/Images/ThanhPho/BuonMaThuot.jpg')
GO

insert into LoaiKhachSan values
(N'Khách sạn', N'Khách sạn', 'Content/Images/LoaiKhachSan/KhachSan.jpg'),
(N'Biệt thự', N'Biệt thự', 'Content/Images/LoaiKhachSan/BietThu.jpg'),
(N'Resort', N'Resort', 'Content/Images/LoaiKhachSan/Resort.jpg'),
(N'Nhà khách', N'Nhà khách', 'Content/Images/LoaiKhachSan/NhaKhach.jpg'),
(N'Nhà trọ', N'Nhà trọ', 'Content/Images/LoaiKhachSan/NhaTro.jpg'),
(N'Thôn dã', N'Thôn dã', 'Content/Images/LoaiKhachSan/ThonDa.jpg'),
(N'Nhà gỗ', N'Nhà gỗ', 'Content/Images/LoaiKhachSan/NhaGo.jpg'),
(N'Căn hộ', N'Căn hộ', 'Content/Images/LoaiKhachSan/CanHo.jpg'),
(N'Glamping', N'Glamping', 'Content/Images/LoaiKhachSan/Glamping.jpg')
GO

insert into KhachSan values
('The Light Hotel', N'79 Tran Cung, Quận Từ Liêm, Hà Nội', '0366918587', 1045, N'Nằm ở quận Hoàn Kiếm, thuộc thành phố Hà Nội, The Light Hotel chỉ cách 300 m từ Nhà Thờ Lớn. Với các phòng nghỉ thanh lịch và hiện đại, khách sạn có hồ bơi ngoài trời cùng tầm nhìn ra vườn.', 0, 5, 2, 1, 1),
('Rising Dragon Palace Hotel', N'24 Hang Ga Street, Quận Ba Đình, Hà Nội', '0328758787', 2455, N'Rising Dragon Palace Hotel cách Hồ Hoàn Kiếm và Nhà hát múa rối nước chỉ vài dãy nhà. Khách sạn cung cấp chỗ đỗ xe cũng như Wi-Fi miễn phí và bữa sáng tự chọn miễn phí.', 0, 4, 4, 1, 2),
('Classic Street Hotel', N'41 Hang Be Street, Quận Hoàn Kiếm, Hà Nội', '0365618587', 1410, N'Classic Street Hotel tọa lạc trên Phố cổ Hàng Bè ở thành phố Hà Nội, nằm trong bán kính 5 phút đi bộ từ Hồ Hoàn Kiếm, Đền Ngọc Sơn và Nhà hát Múa rối Nước Thăng Long. Khách sạn có lối trang trí kiểu Việt Nam truyền thống, Wi-Fi miễn phí và nhà hàng trong khuôn viên phục vụ các món ăn Việt Nam.', 0, 5, 1, 1, 3),
('O Gallery Majestic Hotel', N'38 Tran Phu, Quận Bắc Từ Liêm, Hà Nội', '0366916587', 8812, N'Tọa lạc tại Khu phố Pháp yên bình, O Gallery Majestic Hotel có hồ bơi ngoài trời, phòng tập thể dục và vườn. Khách sạn nằm trong bán kính 700 m từ Nhà thờ Lớn Hà Nội, Bảo tàng Lịch sử Quân sự Việt Nam, Cột cờ Hà Nội và Hoàng thành Thăng Long. Du khách có thể truy cập Wi-Fi miễn phí.', 0, 5, 4, 1, 4),
('Hanoi Allure Hotel', N'52 Dao Duy Tu, Quận Hai Bà Trưng, Hà Nội', '0366918891', 1341, N'Hanoi Allure Hotel tọa lạc tại vị trí lý tưởng ở quận Hoàn Kiếm của thành phố Hà Nội, cách Ô Quan Chưởng 200 m, Chợ Đồng Xuân 700 m và Nhà hát múa rối nước Thăng Long 800 m. Trong số các tiện nghi của chỗ nghỉ này có nhà hàng, lễ tân 24 giờ, dịch vụ phòng và WiFi miễn phí trong toàn bộ khuôn viên. Chỗ nghỉ cũng cung cấp sảnh khách chung, trung tâm dịch vụ doanh nhân và dịch vụ thu đổi ngoại tệ cho khách.', 0, 3, 3, 1, 2),
('HCM Cristina Hotel & Travel', N'40 Phat Loc, Quận Tân Bình, TP. Hồ Chí Minh', '0366915457', 1778, N'Có vị trí thuận tiện ở quận Tân Bình thuộc thành phố Hồ Chí Minh, HCM Cristina Hotel & Travel nằm cách Ô Quan Chưởng 500 m, Nhà hát múa rối nước Thăng Long 600 m và Chợ Đồng Xuân chưa đến 1 km. Trong số các tiện nghi của khách sạn này có nhà hàng, lễ tân 24 giờ, dịch vụ phòng và Wi-Fi miễn phí, Khách sạn cung cấp các phòng gia đình.', 1, 5, 4, 2, 5),
('Dola Hostel', N'205/26 Bui Vien, Quận 1, TP. Hồ Chí Minh', '0366956527', 504, N'Tọa lạc tại Thành phố Hồ Chí Minh, cách Bảo tàng Mỹ thuật trong vòng 1,3 km và trung tâm thương mại Takashimaya Việt Nam 1,6 km, Dola Hostel cung cấp chỗ nghỉ với sảnh khách chung đồng thời miễn phí WiFi cũng như chỗ đỗ xe riêng cho khách lái xe. Chỗ nghỉ này còn có dịch vụ phòng và sân hiên. Tại đây cũng có lễ tân 24 giờ, bếp chung và dịch vụ thu đổi ngoại tệ cho khách.', 1, 2, 3, 2, 6),
('9 Hostel and Bar', N'9 Bui Thi Xuan street, Quận 10, TP. Hồ Chí Minh', '0365456287', 150, N'Chỉ cách Phố đi bộ Bùi Viện nổi tiếng một quãng đi bộ ngắn, 9 Hostel and Rooftop cung cấp các phòng ngủ tập thể và phòng riêng bắt mắt với truy cập Wi-Fi miễn phí. Tọa lạc tại trung tâm Quận 1 sôi động ở Thành phố Hồ Chí Minh, nhà trọ này có lối đi dễ dàng đến hầu hết các địa danh chính của thành phố như Chợ Bến Thành, Nhà hát Thành phố, Bảo tàng Mỹ thuật và Nhà thờ Đức Bà.', 0, 3, 4, 2, 7),
('Town House 23 Saigon', N'23 Dang Thi Nhu, Quận 2, TP. Hồ Chí Minh', '0366454787', 188, N'Chỉ cách Chợ Bến Thành nổi tiếng 300 m, Town House 23 Saigon cung cấp các phòng được bài trí trang nhã với phòng tắm riêng. Khách cũng được truy cập Wi-Fi miễn phí.', 1, 4, 3, 4, 8),
('Victory Airport Hotel', N'96 Yên Thế, Quận Tân Bình, TP. Hồ Chí Minh', '0366475187', 3416, N'Nằm ở thành phố Hồ Chí Minh, cách chợ Tân Định 6 km, Victory Airport Hotel có chỗ nghỉ với quầy bar, chỗ đỗ xe riêng miễn phí, sảnh khách chung và sân hiên. Khách sạn cung cấp WiFi miễn phí, đồng thời nằm trong bán kính khoảng 6 km từ Chùa Giác Lâm và 7 km từ Bảo tàng Chứng tích Chiến tranh. Tại đây cũng cung cấp dịch vụ lễ tân 24 giờ, dịch vụ phòng và dịch vụ thu đổi ngoại tệ cho khách.', 1, 5, 1, 2, 9),
('Da Nang Sunlit Sea Hotel & Apartment', N'41-42 Do Bi Street, Đà Nẵng', '0374775187', 3005, N'Tọa lạc tại Đà Nẵng, cách 2,7 km từ Cầu tàu Tình yêu, Da Nang Sunlit Sea Hotel & Apartment cung cấp chỗ nghỉ với nhà hàng, chỗ đỗ xe riêng miễn phí, hồ bơi ngoài trời và quán bar. Khách sạn 4 sao này có sảnh khách chung và phòng nghỉ gắn máy điều hòa đi kèm WiFi miễn phí cùng phòng tắm riêng. Chỗ nghỉ còn có lễ tân 24 giờ, bếp chung và dịch vụ thu đổi ngoại tệ cho khách.', 1, 4, 3, 3, 5),
('OYO 403 Bao Quyen', N'35 Tran Hung Dao, Đà Nẵng, Việt Nam', '0366475187', 1210, N'Khách sạn chỉ cách thành phố Đà Nẵng và Bãi biển Mỹ Khê nổi tiếng 2 km. Sân bay Quốc tế Đà Nẵng và Ga Đà Nẵng nằm ở vị trí thuận tiện cách nơi đây 3 km trong khi khách đi 30 km là tới Phố Cổ Hội An.', 1, 2, 3, 3, 1),
('Sunflower Hotel', N'Corner Vo Van Kiet - Ho Nghinh, Đà Nẵng', '0366000187', 4332, N'Phòng nghỉ của khách sạn được trang bị máy điều hòa, TV truyền hình cáp màn hình phẳng, ấm đun nước, chậu rửa vệ sinh (bidet), máy sấy tóc và bàn làm việc. Phòng tắm riêng đi kèm vòi sen và đồ vệ sinh cá nhân miễn phí. Các phòng cũng có tầm nhìn ra hồ và tủ quần áo.', 1, 3, 1, 4, 2),
('Koceano Resort Villas', N'Võ Nguyên Giáp Villas S21 - Furama Villas, Đà Nẵng, Việt Nam', '0366046182', 250, N'Offering lake or pool views, each unit comes with a kitchen, a satellite flat-screen TV and Blu-ray player, desk, a washing machine and a private bathroom with hot tub and a hairdryer. All units are air conditioned and include a seating and/or dining area.', 1, 5, 4, 3, 3),
('DNBV - BeachVillas', N'55 Cẩm Phô, Đà Nẵng', '0344475162', 2155, N'Nằm cách Ngũ Hành Sơn 5 km, DNBV - BeachVillas có chỗ nghỉ, nhà hàng, hồ bơi ngoài trời, trung tâm thể dục và quán bar. Biệt thự này cung cấp cả WiFi lẫn chỗ đỗ xe riêng miễn phí.', 0, 4, 2, 3, 4),
('Lan Phuong Hotel', N'60 Phạm Ngũ Lão, Tp. Đà lạt, Tỉnh Lâm Đồng', '0366445210', 345, N'Lan Phuong Hotel nằm cách Vườn Hoa Đà Lạt 3 km và Thiền viện Trúc Lâm 5 km. Sân bay gần nhất là sân bay Liên Khương, cách khách sạn 29 km. Khách sạn cung cấp dịch vụ đưa đón sân bay với một khoản phụ phí.', 0, 4, 2, 4, 5),
('Thiên Phúc', N'7 Phan Bội Châu, Phường 1, Tp Đà Lạt, Lâm Đồng', '0366475187', 550, N'Thiên Phúc nằm cách Công viên Yersin 1,8 km và Vườn Hoa Đà Lạt 3 km. Sân bay gần nhất là sân bay Liên Khương, cách đó 30 km, và chỗ nghỉ cung cấp dịch vụ đưa đón sân bay với một khoản phụ phí.', 0, 1, 0, 4, 6),
('Hotel Colline', N'10 Phan Boi Chau Street, Hà Nội', '0344444447', 145, N'Các phòng tại đây được trang bị máy điều hòa, TV truyền hình cáp màn hình phẳng, ấm đun nước, chậu rửa vệ sinh, đồ vệ sinh cá nhân miễn phí, bàn làm việc, phòng tắm riêng và tầm nhìn ra quang cảnh thành phố. Ngoài ra còn có tủ quần áo.', 0, 4, 3, 1, 7),
('Rosemary Studio Dalat', N'29 Đường Mạc Đĩnh Chi, Đà Lạt', '0366542888', 1015, N'Rosemary Studio Dalat nằm ở thành phố Đà Lạt, cách Quảng trường Lâm Viên 2 km và Hồ Xuân Hương 2,2 km. Căn hộ này có chỗ nghỉ với sảnh khách chung, WiFi miễn phí, lễ tân 24 giờ, bếp chung và khu vườn.', 0, 5, 4, 4, 8),
('The Luxe Da Lat ', N'17 bis duong 3/4, Đà Lạt', '0366475400', 2550, N'Nằm ở thành phố Đà Lạt, cách Hồ Xuân Hương 1,3 km, The Luxe Da Lat cung cấp chỗ nghỉ với nhà hàng, chỗ đỗ xe riêng miễn phí, sảnh khách chung và khu vườn. Trong số các tiện nghi của khách sạn này còn có lễ tân 24 giờ, dịch vụ phòng cũng như Wi-Fi miễn phí trong toàn bộ khuôn viên. Khách sạn có các phòng gia đình.', 1, 3, 2, 4, 9),
('Acacia Heritage Hotel', N'54 Nguyen Tri Phuong , Cẩm Nam, Hội An', '0444405427', 2200, N'Acacia Heritage Hotel cách Hội quán Triều Châu cũng như Bảo tàng Lịch sử và Văn hoá Hội An 9 phút đi bộ. Sân bay gần nhất là sân bay quốc tế Đà Nẵng, cách đó 50 phút lái xe.', 1, 4, 2, 5, 1),
('Tribee Ede Hostel & Bar', N'30 Ba Trieu, Cẩm Phô, Hà Nội', '0361278654', 1158, N'Chỉ cách khu Phố Cổ và chợ địa phương 5 phút đi bộ, Tribee Ede tận hưởng vị trí yên tĩnh được bao quanh bởi thiên nhiên. Nơi đây có WiFi miễn phí trong toàn bộ khuôn viên.', 0, 0, 0, 1, 2),
('The AN retreat Hoi An', N'Tống Văn Sương, Cẩm Thanh, Hội An', '0322848672', 3004, N'Cách Hội quán Triều Châu 3 km, The AN retreat Hoi An cung cấp chỗ nghỉ, nhà hàng, hồ bơi ngoài trời, quán bar và sảnh khách chung. Du khách có thể truy cập Wi-Fi miễn phí trong toàn bộ khuôn viên.', 1, 4, 4, 5, 3),
('Serenity Villa Hoi An', N'91 Ngô Quyền, Minh An, Hội An', '0366475256', 5236, N'Providing city views, Serenity Villa Hoi An in Hoi An provides accommodation, an outdoor swimming pool, a bar, a shared lounge, a garden and a terrace. Complimentary WiFi is featured.', 0, 2, 2, 5, 4),
('Hi Guy Homestay', N'15/6 Thoai Ngoc Hau, Minh An, Hội An', '0123456187', 6411, N' Một trong những chỗ nghỉ bán chạy nhất ở Hội An của chúng tôi! Nằm ở thành phố Hội An, cách Hội quán Quảng Đông 600 m và Chùa Cầu Nhật Bản 800 m, Hi Guy Homestay cung cấp chỗ nghỉ với WiFi cùng xe máy miễn phí, máy điều hòa và sảnh khách chung.', 1, 4, 3, 5, 5),
('LA Beach Hotel', N'22 Lê Bình, Đà Nẵng, Việt Nam', '0346475256', 2346, N'Providing city views, Serenity Villa Hoi An in Hoi An provides accommodation, an outdoor swimming pool, a bar, a shared lounge, a garden and a terrace. Complimentary WiFi is featured.', 0, 2, 2, 3, 4),
('ND Building', N'9 Le Van Huan, Tân Bình, TP. Hồ Chí Minh', '0326475256', 6436, N'LA Beach Hotel nằm trong bán kính 2,2 km từ Cầu Sông Hàn và 2,9 km từ Trung tâm thương mại Indochina Riverside. Sân bay gần nhất là sân bay quốc tế Đà Nẵng, cách chỗ nghỉ 7 km.', 0, 2, 1, 2, 3),
('Lisbon Hanoi', N'91 Ngô Quyền, Minh An, Hà Nội', '0344475256', 1346, N'Set in Lisbon historical downtown, The Visionaire Apartments features apartments, studios and suites right across from the iconic Figueira Square. Rossio is 130 metres away, while Commerce Square and the Tagus riverside are at a 10-minute walk.', 0, 1, 2, 1, 2)
GO

insert into Phong values
(N'Phòng Tiêu chuẩn', 15, 350, N'1 Giường đơn', N'Phòng này có một số trang thiết bị đơn giản.', 0, 1),
(N'Phòng Trăng Mật', 15, 400, N'1 Giường đôi', N'Phòng này có máy lạnh, minibar và được cách âm. Phòng này được trang trí bằng hoa và bánh ngọt.', 1, 2),
(N'Phòng Cao Cấp', 20, 400, N'1 Giường đôi', N'Phòng cách âm này có máy lạnh.', 1, 3),
(N'Phòng Gia Đình', 25, 500, N'2 Giường đôi', N'Phòng gia đình này có quầy bar mini, truyền hình cáp và máy lạnh.', 1, 4),
(N'Phòng Trung Cấp', 10, 200, N'1 Giường đơn', N'Phòng trung cấp nên chẳng có gì hết, thích hợp với bọn kiết xác.', 0, 5),
(N'Phòng Superior', 30, 600, N'1 Giường đôi', N'Phòng cách âm này có nhiều thứ vl.', 1, 6),
(N'Phòng Không', 15, 350, N'2 Giường đơn', N'Phòng không không quân.', 1, 8),
(N'Phòng Thủ', 15, 350, N'2 Giường đơn', N'Phòng gia đình này có quầy bar mini, truyền hình cáp và máy lạnh.', 1, 9),
(N'Phòng 4 Người', 15, 350, N'2 Giường đôi', N'Phòng có tivi siêu to khổng lồ.', 1, 10),
(N'Phòng 3 Người', 15, 350, N'2 Giường đơn', N'Phòng có truyền hình cáp và máy lạnh.', 1, 11),
(N'Phòng Ban', 15, 350, N'2 Giường đơn', N'Là cơ quan quyền lực cao nhất Công ty, quyết định cơ cấu tổ chức.', 1, 12),
(N'Phòng Đào Tạo', 25, 350, N'1 Giường đơn', N'Có trách nhiệm trong công tác tuyển sinh, quản lý chương trình đào tạo.', 1, 13),
(N'Phòng Deluxe Giường Đôi', 20, 500, N'1 Giường đôi lớn', N'This double room has air conditioning and electric kettle.', 1, 1),
(N'Phòng Tuyến', 18, 350, N'1 Giường đơn', N'Phòng gia đình này có quầy bar mini, truyền hình cáp và máy lạnh.', 1, 14),
(N'Phòng 2 Người', 25, 350, N'2 Giường đơn', N'Phòng được trang bị 2 máy tính, thích hợp để solo game.', 1, 15),
(N'Phòng 1 Người', 15, 350, N'1 Giường đơn', N'Phòng dành cho một người.', 1, 16),
(N'Phòng Deluxe Giường Đôi', 20, 500, N'1 Giường đôi lớn', N'This double room has air conditioning and electric kettle.', 1, 2),
(N'Phòng Trăng Mật', 15, 100, N'1 Giường đôi', N'Phòng này có máy lạnh, minibar và được cách âm. Phòng này được trang trí bằng hoa và bánh ngọt.', 1, 3),
(N'Phòng Cao Cấp', 20, 400, N'1 Giường đôi', N'Phòng cách âm này có máy lạnh.', 1, 4),
(N'Phòng Gia Đình', 25, 500, N'2 Giường đôi', N'Phòng gia đình này có quầy bar mini, truyền hình cáp và máy lạnh.', 1, 5),
(N'Phòng Trung Cấp', 10, 200, N'1 Giường đơn', N'Phòng trung cấp nên chẳng có gì hết, thích hợp với bọn kiết xác.', 0, 6),
(N'Phòng Superior', 30, 200, N'1 Giường đôi', N'Phòng cách âm này có nhiều thứ vl.', 1, 7),
(N'Phòng Tiêu chuẩn', 15, 220, N'2 Giường đơn', N'Phòng này ít thứ vl.', 1, 8),
(N'Phòng Không', 15, 350, N'2 Giường đơn', N'Phòng không không quân.', 1, 9),
(N'Phòng Thủ', 15, 350, N'2 Giường đơn', N'Phòng gia đình này có quầy bar mini, truyền hình cáp và máy lạnh.', 1, 10),
(N'Phòng 4 Người', 15, 350, N'2 Giường đôi', N'Phòng có tivi siêu to khổng lồ.', 1, 11),
(N'Phòng 3 Người', 15, 220, N'2 Giường đơn', N'Phòng có truyền hình cáp và máy lạnh.', 1, 12),
(N'Phòng Ban', 15, 350, N'2 Giường đơn', N'Là cơ quan quyền lực cao nhất Công ty, quyết định cơ cấu tổ chức.', 1, 13),
(N'Phòng Đào Tạo', 25, 410, N'1 Giường đơn', N'Có trách nhiệm trong công tác tuyển sinh, quản lý chương trình đào tạo.', 1, 14),
(N'Phòng Tuyến', 18, 350, N'1 Giường đơn', N'Phòng gia đình này có quầy bar mini, truyền hình cáp và máy lạnh.', 1, 15),
(N'Phòng 2 Người', 25, 350, N'2 Giường đơn', N'Phòng được trang bị 2 máy tính, thích hợp để solo game.', 1, 16),
(N'Phòng 1 Người', 15, 260, N'1 Giường đơn', N'Phòng dành cho một người.', 1, 1),
(N'Phòng Deluxe Giường Đôi', 20, 500, N'1 Giường đôi lớn', N'This double room has air conditioning and electric kettle.', 1, 3),
(N'Phòng Trăng Mật', 15, 400, N'1 Giường đôi', N'Phòng giường đôi này có ban công, TV truyền hình vệ tinh và máy lạnh.', 1, 4),
(N'Phòng Cao Cấp', 20, 150, N'1 Giường đôi', N'Phòng cách âm này có máy lạnh.', 1, 5),
(N'Phòng Gia Đình', 25, 600, N'2 Giường đôi', N'Phòng gia đình này có quầy bar mini, truyền hình cáp và máy lạnh.', 1, 6),
(N'Phòng Trung Cấp', 10, 200, N'1 Giường đơn', N'This apartment features a electric kettle and air conditioning.', 0, 7),
(N'Phòng Superior', 30, 150, N'1 Giường đôi', N'Phòng cách âm này có nhiều thứ vl.', 1, 8),
(N'Phòng Tiêu chuẩn', 15, 420, N'2 Giường đơn', N'Phòng này ít thứ vl.', 1, 9),
(N'Phòng Không', 15, 400, N'2 Giường đơn', N'Phòng không không quân.', 1, 10),
(N'Phòng Thủ', 15, 350, N'2 Giường đơn', N'This twin room has a electric kettle and air conditioning.', 1, 11),
(N'Phòng 4 Người', 15, 350, N'2 Giường đôi', N'Phòng có tivi siêu to khổng lồ.', 1, 12),
(N'Phòng 3 Người', 15, 350, N'2 Giường đơn', N'Phòng có truyền hình cáp và máy lạnh.', 1, 13),
(N'Phòng Ban', 15, 350, N'2 Giường đơn', N'Là cơ quan quyền lực cao nhất Công ty, quyết định cơ cấu tổ chức.', 1, 14),
(N'Phòng Đào Tạo', 25, 450, N'1 Giường đơn', N'Có trách nhiệm trong công tác tuyển sinh, quản lý chương trình đào tạo.', 1, 15),
(N'Phòng Tuyến', 18, 250, N'2 Giường đơn', N'Phòng 2 giường đơn này được trang bị ban công, áo choàng tắm cùng máy lạnh.', 1, 16),
(N'Phòng 2 Người', 25, 330, N'2 Giường đơn', N'Phòng được trang bị 2 máy tính, thích hợp để solo game.', 1, 1),
(N'Phòng 1 Người', 15, 350, N'1 Giường đơn', N'Phòng dành cho một người.', 1, 2),
(N'Phòng Streamer', 25, 450, N'1 Giường đơn', N'Phòng chuyên dành cho sờ chim mơ.', 0, 2),
(N'Phòng Vinahouse', 22, 999, N'1 Giường đơn', N'Phòng chuyên dành Khá Bảnh.', 0, 3),
(N'Phòng Thuốc Lào', 4, 199, N'1 Giường đơn', N'Vào làm vài bi nào mấy em, phê cùng anh.', 0, 4),
(N'Phòng Trần Dần', 1, 399, N'1 Giường đơn', N'Nhà tiên tri lộn xào vũ trụ Trần Dần và các ba que tụ họp.', 0, 5)
GO

insert into TaiKhoan values
('NguyenKhanh', '123456', N'Nguyễn Khánh', 1, '0366918587', 'nguyenkhanh@gmail.com', 1),
('LyChan', '123456', N'Lý Văn Chản', 1, '0123455678', 'lychan@gmail.com', 1),
('BuiThuy', '123456', N'Bùi Đình Thủy', 1, '0556878945', 'buithuy@gmail.com', 1),
('NgoThuong', '123456', N'Ngô Văn Thường', 1, '0358763412', 'ngothuong@gmail.com', 0),
('User', '123456', N'Người Dùng', 0, '0358763412', 'user@gmail.com', 0)
GO