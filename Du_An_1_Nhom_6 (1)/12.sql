USE [DUAN11]
GO
/****** Object:  User [sa1]    Script Date: 12/1/2023 3:57:38 PM ******/
CREATE USER [sa1] FOR LOGIN [sa1] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[ChatLieu]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChatLieu](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaChatLieu] [varchar](20) NULL,
	[TenChatLieu] [nvarchar](50) NOT NULL,
	[MoTa] [text] NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[Deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CoAo]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CoAo](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaCoAo] [varchar](20) NULL,
	[TenCoAo] [nvarchar](50) NOT NULL,
	[MoTa] [text] NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[Deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DangAo]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DangAo](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaDangAo] [varchar](20) NULL,
	[TenDangAo] [nvarchar](50) NOT NULL,
	[Mota] [text] NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[Deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HinhThucThanhToan]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HinhThucThanhToan](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaHinhThucThanhToan] [varchar](20) NULL,
	[TenHinhThucThanhToan] [nvarchar](50) NOT NULL,
	[MoTa] [text] NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[Deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaHoaDon] [varchar](20) NULL,
	[LoaiKhachHang] [bit] NOT NULL,
	[TongTien] [decimal](20, 0) NULL,
	[TrangThai] [bit] NOT NULL,
	[TenNguoiNhan] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](100) NULL,
	[Sdt] [varchar](30) NULL,
	[Email] [varchar](50) NULL,
	[PhiShip] [decimal](20, 0) NULL,
	[NgayShip] [date] NULL,
	[NgayNhan] [date] NULL,
	[NgayThanhToan] [date] NULL,
	[GhiChu] [text] NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[deleted] [bit] NOT NULL,
	[IdNhanVien] [int] NULL,
	[IdVoucher] [int] NULL,
	[IdKhachHang] [int] NULL,
	[IdHinhThucThanhToan] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonChiTiet]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonChiTiet](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[ThanhTien] [decimal](20, 0) NULL,
	[IdHoaDon] [int] NOT NULL,
	[IdSanPhamCt] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaKhachHang] [varchar](20) NULL,
	[Hoten] [nvarchar](50) NULL,
	[SoDT] [varchar](20) NULL,
	[Email] [varchar](50) NULL,
	[DiaChi] [nvarchar](100) NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KichThuoc]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KichThuoc](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaKichThuoc] [varchar](20) NULL,
	[TenKichThuoc] [nvarchar](50) NOT NULL,
	[MoTa] [text] NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[Deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MauSac]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MauSac](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaMauSac] [varchar](20) NULL,
	[TenMauSac] [nvarchar](50) NOT NULL,
	[MoTa] [text] NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[Deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanHieu]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanHieu](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaNhanHieu] [varchar](20) NULL,
	[TenNhanHieu] [nvarchar](50) NOT NULL,
	[MoTa] [text] NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[Deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaNhanVien] [varchar](20) NULL,
	[MatKhau] [varchar](20) NOT NULL,
	[ChucVu] [bit] NOT NULL,
	[Hoten] [nvarchar](50) NULL,
	[GioiTinh] [bit] NOT NULL,
	[CCCD] [varchar](20) NULL,
	[SoDT] [varchar](20) NULL,
	[Email] [varchar](20) NULL,
	[DiaChi] [nvarchar](100) NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SANPHAM]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAM](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaSanPham] [varchar](20) NULL,
	[TenSanPham] [nvarchar](50) NOT NULL,
	[CreateAt] [date] NULL,
	[CreateBY] [varchar](20) NULL,
	[trangthai] [bit] NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SANPHAMCHITIET]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAMCHITIET](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaSanPhamCT] [varchar](20) NULL,
	[TenSanPhamCT] [nvarchar](50) NOT NULL,
	[SoLuongTon] [int] NOT NULL,
	[GiaBan] [decimal](20, 0) NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[Deleted] [bit] NOT NULL,
	[IdXuatXu] [int] NOT NULL,
	[IdSanPham] [int] NOT NULL,
	[IdMauSac] [int] NOT NULL,
	[IdKichThuoc] [int] NOT NULL,
	[IdCoAo] [int] NOT NULL,
	[IdDangAo] [int] NOT NULL,
	[IdNhanHieu] [int] NOT NULL,
	[IdChatLieu] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[XuatXu]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[XuatXu](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaXuatXu] [varchar](20) NULL,
	[NoiXuatXu] [nvarchar](50) NOT NULL,
	[Mota] [text] NULL,
	[TrangThai] [bit] NOT NULL,
	[CreateAt] [date] NOT NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NOT NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[Deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChatLieu] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[ChatLieu] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[ChatLieu] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[CoAo] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[CoAo] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[CoAo] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[DangAo] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[DangAo] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[DangAo] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[HinhThucThanhToan] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[HinhThucThanhToan] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[HinhThucThanhToan] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT ((0)) FOR [TongTien]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (NULL) FOR [TenNguoiNhan]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (NULL) FOR [DiaChi]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (NULL) FOR [Sdt]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT ((0)) FOR [PhiShip]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[HoaDonChiTiet] ADD  DEFAULT ((0)) FOR [ThanhTien]
GO
ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT (NULL) FOR [Hoten]
GO
ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT (NULL) FOR [SoDT]
GO
ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT (NULL) FOR [Email]
GO
ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT (NULL) FOR [DiaChi]
GO
ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[KichThuoc] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[KichThuoc] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[KichThuoc] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[MauSac] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[MauSac] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[MauSac] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[NhanHieu] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[NhanHieu] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[NhanHieu] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (NULL) FOR [Hoten]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (NULL) FOR [CCCD]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (NULL) FOR [SoDT]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (NULL) FOR [Email]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (NULL) FOR [DiaChi]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[SANPHAM] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[SANPHAM] ADD  DEFAULT ((0)) FOR [trangthai]
GO
ALTER TABLE [dbo].[SANPHAM] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[SANPHAMCHITIET] ADD  DEFAULT ((0)) FOR [GiaBan]
GO
ALTER TABLE [dbo].[SANPHAMCHITIET] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[SANPHAMCHITIET] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[SANPHAMCHITIET] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[XuatXu] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[XuatXu] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[XuatXu] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([IdHinhThucThanhToan])
REFERENCES [dbo].[HinhThucThanhToan] ([Id])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([IdKhachHang])
REFERENCES [dbo].[KhachHang] ([Id])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([IdNhanVien])
REFERENCES [dbo].[NhanVien] ([Id])
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD FOREIGN KEY([IdHoaDon])
REFERENCES [dbo].[HoaDon] ([Id])
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD FOREIGN KEY([IdSanPhamCt])
REFERENCES [dbo].[SANPHAMCHITIET] ([Id])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([IdChatLieu])
REFERENCES [dbo].[ChatLieu] ([Id])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([IdCoAo])
REFERENCES [dbo].[CoAo] ([Id])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([IdDangAo])
REFERENCES [dbo].[DangAo] ([Id])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([IdKichThuoc])
REFERENCES [dbo].[KichThuoc] ([Id])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([IdMauSac])
REFERENCES [dbo].[MauSac] ([Id])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([IdNhanHieu])
REFERENCES [dbo].[NhanHieu] ([Id])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([IdSanPham])
REFERENCES [dbo].[SANPHAM] ([Id])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([IdXuatXu])
REFERENCES [dbo].[XuatXu] ([Id])
GO
/****** Object:  StoredProcedure [dbo].[FindSPCT]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create proc [dbo].[FindSPCT]
as
begin
	Select SANPHAM.TenSanPham
	,SANPHAMCHITIET.TenSanPhamCT
	,MauSac.TenMauSac
	from SANPHAMCHITIET
	join sanpham on SANPHAM.Id=SANPHAMCHITIET.IdSanPham
	join MauSac on MauSac.id=SANPHAMCHITIET.IdMauSac
	where SanPham.TenSanPham like N'S%' or SanPhamChiTiet.TenSanPhamCT like N'A%' or MauSac.TenMauSac like N'Đ%'
	end;
GO
/****** Object:  StoredProcedure [dbo].[GetCTSP]    Script Date: 12/1/2023 3:57:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create proc [dbo].[GetCTSP]
as
begin
	Select SANPHAM.TenSanPham
	,SANPHAMCHITIET.MaSanPhamCT
	,SANPHAMCHITIET.TenSanPhamCT
	,SANPHAMCHITIET.SoLuongTon
	,XuatXu.NoiXuatXu
	,MauSac.TenMauSac
	,NhanHieu.TenNhanHieu
	,KichThuoc.TenKichThuoc
	,CoAo.TenCoAo
	,DangAo.TenDangAo
	,ChatLieu.TenChatLieu
	,SANPHAMCHITIET.GiaBan
	,SANPHAMCHITIET.TrangThai
	from SANPHAMCHITIET
	join sanpham on SANPHAM.Id=SANPHAMCHITIET.IdSanPham
	join XuatXu on XuatXu.id= SANPHAMCHITIET.IdXuatXu
	join MauSac on MauSac.id=SANPHAMCHITIET.IdMauSac
	join  NhanHieu on NhanHieu.id= SANPHAMCHITIET.IdNhanHieu
	join KichThuoc on KichThuoc.id=SANPHAMCHITIET.IdKichThuoc
	join CoAo on CoAo.id=SANPHAMCHITIET.IdCoAo
	join DangAo on DangAo.id= SANPHAMCHITIET.IdDangAo
	join ChatLieu on ChatLieu.id= SANPHAMCHITIET.IdChatLieu
	where SANPHAM.trangthai=1
	end;
exec GetCTSP
GO
