create database DUAN1
USE [DUAN1]
GO
/****** Object:  User [sa1]    Script Date: 11/26/2023 6:29:54 PM ******/
CREATE USER [sa1] FOR LOGIN [sa1] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[ChatLieu]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[CoAo]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[DangAo]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[HinhAnh]    Script Date: 11/26/2023 6:29:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HinhAnh](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaHinhAnh] [varchar](20) NULL,
	[TenHinhAnh] [nvarchar](max) NOT NULL,
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
/****** Object:  Table [dbo].[HinhThucThanhToan]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[HoaDon]    Script Date: 11/26/2023 6:29:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaHoaDon] [varchar](20) NULL,
	[LoaiKhachHang] [bit] NOT NULL,
	[TongTien] [decimal](20, 0) NULL,
	[TongTienSauGiamGia] [decimal](20, 0) NULL,
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
	[IdNhanVien] [int] NOT NULL,
	[IdVoucher] [int] NOT NULL,
	[IdKhachHang] [int] NOT NULL,
	[IdHinhThucThanhToan] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonChiTiet]    Script Date: 11/26/2023 6:29:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonChiTiet](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[DonGia] [decimal](20, 0) NULL,
	[IdHoaDon] [int] NOT NULL,
	[IdSanPhamCt] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[KichThuoc]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[LichSuHoaDon]    Script Date: 11/26/2023 6:29:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LichSuHoaDon](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[HanhDong] [nvarchar](max) NOT NULL,
	[Trangthai] [bit] NOT NULL,
	[CreateAt] [date] NULL,
	[CreateBy] [varchar](20) NOT NULL,
	[UpdateAt] [date] NULL,
	[UpdateBy] [varchar](20) NOT NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MauSac]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[NhanHieu]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[SANPHAM]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[SANPHAMCHITIET]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  Table [dbo].[Voucher]    Script Date: 11/26/2023 6:29:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Voucher](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaVoucher] [varchar](20) NULL,
	[TenVoucher] [nvarchar](50) NOT NULL,
	[MucGiamGia] [varchar](20) NOT NULL,
	[DieuKien] [int] NOT NULL,
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
/****** Object:  Table [dbo].[XuatXu]    Script Date: 11/26/2023 6:29:54 PM ******/
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
SET IDENTITY_INSERT [dbo].[ChatLieu] ON 

INSERT [dbo].[ChatLieu] ([Id], [MaChatLieu], [TenChatLieu], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (1, N'CL001', N'Vải dù', N'Mô t? v? v?i dù', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[ChatLieu] ([Id], [MaChatLieu], [TenChatLieu], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (2, N'CL002', N'Vải thun', N'Mô t? v? v?i thun', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
SET IDENTITY_INSERT [dbo].[ChatLieu] OFF
GO
SET IDENTITY_INSERT [dbo].[CoAo] ON 

INSERT [dbo].[CoAo] ([Id], [MaCoAo], [TenCoAo], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (1, N'CA001', N'Áo thun', N'Mô t? v? áo thun', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[CoAo] ([Id], [MaCoAo], [TenCoAo], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (2, N'CA002', N'Áo polo', N'Mô t? v? áo polo', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
SET IDENTITY_INSERT [dbo].[CoAo] OFF
GO
SET IDENTITY_INSERT [dbo].[DangAo] ON 

INSERT [dbo].[DangAo] ([Id], [MaDangAo], [TenDangAo], [Mota], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (1, N'DA001', N'Dài tay', N'Mô t? v? áo dài tay', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[DangAo] ([Id], [MaDangAo], [TenDangAo], [Mota], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (2, N'DA002', N'Ng?n tay', N'Mô t? v? áo ng?n tay', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[DangAo] ([Id], [MaDangAo], [TenDangAo], [Mota], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (3, N'DA084813', N'Ngắn tay', NULL, 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0)
SET IDENTITY_INSERT [dbo].[DangAo] OFF
GO
SET IDENTITY_INSERT [dbo].[HinhAnh] ON 

INSERT [dbo].[HinhAnh] ([Id], [MaHinhAnh], [TenHinhAnh], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (1, N'HA001', N'Hình 1', CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[HinhAnh] ([Id], [MaHinhAnh], [TenHinhAnh], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (2, N'HA002', N'Hình 2', CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
SET IDENTITY_INSERT [dbo].[HinhAnh] OFF
GO
SET IDENTITY_INSERT [dbo].[HinhThucThanhToan] ON 

INSERT [dbo].[HinhThucThanhToan] ([Id], [MaHinhThucThanhToan], [TenHinhThucThanhToan], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (1, N'HTTT001', N'Tiền Mặt', N'Mô t?', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[HinhThucThanhToan] ([Id], [MaHinhThucThanhToan], [TenHinhThucThanhToan], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (2, N'HTTT002', N'Chuyển Khoản', N'Mô t? ', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
SET IDENTITY_INSERT [dbo].[HinhThucThanhToan] OFF
GO
SET IDENTITY_INSERT [dbo].[HoaDon] ON 

INSERT [dbo].[HoaDon] ([Id], [MaHoaDon], [LoaiKhachHang], [TongTien], [TongTienSauGiamGia], [TrangThai], [TenNguoiNhan], [DiaChi], [Sdt], [Email], [PhiShip], [NgayShip], [NgayNhan], [NgayThanhToan], [GhiChu], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [deleted], [IdNhanVien], [IdVoucher], [IdKhachHang], [IdHinhThucThanhToan]) VALUES (3, N'HD001', 1, CAST(100000 AS Decimal(20, 0)), CAST(90000 AS Decimal(20, 0)), 1, N'Nguyễn Quốc Vượng', N'125a6Trần Huy Liệu', N'0987654321', N'vuongnqPH43947@gmail.com', CAST(20000 AS Decimal(20, 0)), CAST(N'2023-11-15' AS Date), CAST(N'2023-11-16' AS Date), CAST(N'2023-11-16' AS Date), N'', CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0, 1, 1, 1, 1)
INSERT [dbo].[HoaDon] ([Id], [MaHoaDon], [LoaiKhachHang], [TongTien], [TongTienSauGiamGia], [TrangThai], [TenNguoiNhan], [DiaChi], [Sdt], [Email], [PhiShip], [NgayShip], [NgayNhan], [NgayThanhToan], [GhiChu], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [deleted], [IdNhanVien], [IdVoucher], [IdKhachHang], [IdHinhThucThanhToan]) VALUES (4, N'HD002', 0, CAST(150000 AS Decimal(20, 0)), CAST(130000 AS Decimal(20, 0)), 1, N'Đỗ Trung Đăng', N'456 Kim Mã', N'0123456789', N'dangtdPH43117@gmail.com', CAST(30000 AS Decimal(20, 0)), CAST(N'2023-11-15' AS Date), CAST(N'2023-11-17' AS Date), CAST(N'2023-11-17' AS Date), N'', CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0, 2, 2, 2, 2)
SET IDENTITY_INSERT [dbo].[HoaDon] OFF
GO
SET IDENTITY_INSERT [dbo].[HoaDonChiTiet] ON 

INSERT [dbo].[HoaDonChiTiet] ([Id], [SoLuong], [DonGia], [IdHoaDon], [IdSanPhamCt]) VALUES (5, 2, CAST(50000 AS Decimal(20, 0)), 3, 1)
INSERT [dbo].[HoaDonChiTiet] ([Id], [SoLuong], [DonGia], [IdHoaDon], [IdSanPhamCt]) VALUES (6, 1, CAST(70000 AS Decimal(20, 0)), 4, 2)
SET IDENTITY_INSERT [dbo].[HoaDonChiTiet] OFF
GO
SET IDENTITY_INSERT [dbo].[KhachHang] ON 

INSERT [dbo].[KhachHang] ([Id], [MaKhachHang], [Hoten], [SoDT], [Email], [DiaChi], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [deleted]) VALUES (1, N'KH001', N'Nguyễn Quốc Vượng', N'0987654321', N'vuongnqPH43947@gmail.com', N'125a6Trần Huy Liệu', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[KhachHang] ([Id], [MaKhachHang], [Hoten], [SoDT], [Email], [DiaChi], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [deleted]) VALUES (2, N'KH002', N'Đỗ Trung Đăng', N'0123456789', N'dangtPH43117@gmail.com', N'456 Kim Mã', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
SET IDENTITY_INSERT [dbo].[KhachHang] OFF
GO
SET IDENTITY_INSERT [dbo].[KichThuoc] ON 

INSERT [dbo].[KichThuoc] ([Id], [MaKichThuoc], [TenKichThuoc], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (1, N'KT001', N'S', N'Mô t?', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[KichThuoc] ([Id], [MaKichThuoc], [TenKichThuoc], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (2, N'KT002', N'M', N'Mô t?', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
SET IDENTITY_INSERT [dbo].[KichThuoc] OFF
GO
SET IDENTITY_INSERT [dbo].[MauSac] ON 

INSERT [dbo].[MauSac] ([Id], [MaMauSac], [TenMauSac], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (1, N'MS001', N'Ðen', N'Mô t?', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[MauSac] ([Id], [MaMauSac], [TenMauSac], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (2, N'MS002', N'Xanh', N'Mô t?', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[MauSac] ([Id], [MaMauSac], [TenMauSac], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (3, N'MS006803', N'Hồng', NULL, 1, CAST(N'2023-11-24' AS Date), N'NV002', CAST(N'2023-11-24' AS Date), N'NV002', 0)
SET IDENTITY_INSERT [dbo].[MauSac] OFF
GO
SET IDENTITY_INSERT [dbo].[NhanHieu] ON 

INSERT [dbo].[NhanHieu] ([Id], [MaNhanHieu], [TenNhanHieu], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (1, N'NH001', N'Adidas', N'Mô t?', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[NhanHieu] ([Id], [MaNhanHieu], [TenNhanHieu], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (2, N'NH002', N'Nike', N'Mô t?', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[NhanHieu] ([Id], [MaNhanHieu], [TenNhanHieu], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (3, N'XX004015', N'Comfor', NULL, 1, CAST(N'2023-11-23' AS Date), N'NV002', CAST(N'2023-11-23' AS Date), N'NV002', 0)
INSERT [dbo].[NhanHieu] ([Id], [MaNhanHieu], [TenNhanHieu], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (4, N'NH018439', N'Bitis', NULL, 1, CAST(N'2023-11-23' AS Date), N'NV002', CAST(N'2023-11-23' AS Date), N'NV002', 0)
SET IDENTITY_INSERT [dbo].[NhanHieu] OFF
GO
SET IDENTITY_INSERT [dbo].[NhanVien] ON 

INSERT [dbo].[NhanVien] ([Id], [MaNhanVien], [MatKhau], [ChucVu], [Hoten], [GioiTinh], [CCCD], [SoDT], [Email], [DiaChi], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [deleted]) VALUES (1, N'NV001', N'password123', 1, N'Phạm Minh Thành', 1, N'12320394880384', N'0987654321', N'thanh@gmail.com', N'789 Ðu?ng LMN', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[NhanVien] ([Id], [MaNhanVien], [MatKhau], [ChucVu], [Hoten], [GioiTinh], [CCCD], [SoDT], [Email], [DiaChi], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [deleted]) VALUES (2, N'NV002', N'pass456', 0, N'Nguyễn Quang Khải', 0, N'1989121112819', N'0123456789', N'khai@gmail.com', N'101 Ðu?ng UVW', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
SET IDENTITY_INSERT [dbo].[NhanVien] OFF
GO
SET IDENTITY_INSERT [dbo].[SANPHAM] ON 

INSERT [dbo].[SANPHAM] ([Id], [MaSanPham], [TenSanPham], [CreateAt], [CreateBY], [trangthai], [deleted]) VALUES (1, N'SP001', N'Sản phẩm 1', CAST(N'2023-11-22' AS Date), N'NV002', 0, 0)
INSERT [dbo].[SANPHAM] ([Id], [MaSanPham], [TenSanPham], [CreateAt], [CreateBY], [trangthai], [deleted]) VALUES (2, N'SP002', N'Sản phẩm 2', NULL, NULL, 1, NULL)
INSERT [dbo].[SANPHAM] ([Id], [MaSanPham], [TenSanPham], [CreateAt], [CreateBY], [trangthai], [deleted]) VALUES (4, N'SP003', N'Áo ', CAST(N'2023-11-17' AS Date), N'NV001', 1, NULL)
INSERT [dbo].[SANPHAM] ([Id], [MaSanPham], [TenSanPham], [CreateAt], [CreateBY], [trangthai], [deleted]) VALUES (5, N'SP056444', N'Áo phông nam', NULL, N'NV002', 1, 0)
INSERT [dbo].[SANPHAM] ([Id], [MaSanPham], [TenSanPham], [CreateAt], [CreateBY], [trangthai], [deleted]) VALUES (6, N'SP058907', N'Áo phông nữ', NULL, N'NV002', 1, 0)
INSERT [dbo].[SANPHAM] ([Id], [MaSanPham], [TenSanPham], [CreateAt], [CreateBY], [trangthai], [deleted]) VALUES (8, N'SP029338', N'Áo phông dài', NULL, N'NV002', 0, 0)
SET IDENTITY_INSERT [dbo].[SANPHAM] OFF
GO
SET IDENTITY_INSERT [dbo].[SANPHAMCHITIET] ON 

INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (1, N'SPCT001', N'Áo thun đen size M', 50, CAST(50000 AS Decimal(20, 0)), 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0, 1, 1, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (2, N'SPCT002', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 0, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-25' AS Date), N'Admin', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (6, N'SPCT091438', N'Áo thun đen size M', 50, CAST(50000 AS Decimal(20, 0)), 0, CAST(N'2023-11-24' AS Date), N'NV002', CAST(N'2023-11-24' AS Date), N'NV002', 0, 1, 1, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (8, N'SPCT011322', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 0, CAST(N'2023-11-25' AS Date), N'NV002', CAST(N'2023-11-25' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (9, N'SPCT068211', N'Áo thun đen size M', 50, CAST(50000 AS Decimal(20, 0)), 0, CAST(N'2023-11-25' AS Date), N'NV002', CAST(N'2023-11-25' AS Date), N'NV002', 0, 1, 1, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (10, N'SPCT031377', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 0, CAST(N'2023-11-25' AS Date), N'NV002', CAST(N'2023-11-25' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (11, N'SPCT092837', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 0, CAST(N'2023-11-25' AS Date), N'NV002', CAST(N'2023-11-25' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (12, N'SPCT020402', N'Áo phông nữ', 18, CAST(120000 AS Decimal(20, 0)), 0, CAST(N'2023-11-25' AS Date), N'NV002', CAST(N'2023-11-25' AS Date), N'NV002', 0, 1, 1, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (14, N'SPCT006565', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 0, CAST(N'2023-11-25' AS Date), N'NV002', CAST(N'2023-11-25' AS Date), N'NV002', 0, 2, 4, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (15, N'SPCT026289', N'Áo phông nữ', 18, CAST(120000 AS Decimal(20, 0)), 0, CAST(N'2023-11-25' AS Date), N'NV002', CAST(N'2023-11-25' AS Date), N'NV002', 0, 1, 1, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (16, N'SPCT050896', N'Áo phông nữ', 18, CAST(120000 AS Decimal(20, 0)), 0, CAST(N'2023-11-25' AS Date), N'NV002', CAST(N'2023-11-25' AS Date), N'NV002', 0, 1, 1, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (18, N'SPCT001724', N'Áo thun đen size M', 50, CAST(50000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 1, 1, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (19, N'SPCT016763', N'Áo thun đen size M', 50, CAST(50000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 1, 1, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (20, N'SPCT034735', N'Áo polo xanh size S', 301, CAST(60000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (21, N'SPCT067461', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (22, N'SPCT084427', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (23, N'SPCT086656', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (24, N'SPCT074611', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (25, N'SPCT062028', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (27, N'SPCT093395', N'Áo polo xanh size S', 301, CAST(60000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (28, N'SPCT055132', N'Áo polo xanh size S', 30, CAST(60000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 2, 2, 2, 2, 2, 2, 2, 2)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (30, N'SPCT061301', N'Áo thun đen size M', 50, CAST(50000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 1, 1, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (31, N'SPCT031533', N'Áo thun đen size M', 50, CAST(50000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 1, 5, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (32, N'SPCT040982', N'Áo thun đen size M', 501, CAST(50000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 1, 5, 1, 1, 1, 1, 1, 1)
INSERT [dbo].[SANPHAMCHITIET] ([Id], [MaSanPhamCT], [TenSanPhamCT], [SoLuongTon], [GiaBan], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted], [IdXuatXu], [IdSanPham], [IdMauSac], [IdKichThuoc], [IdCoAo], [IdDangAo], [IdNhanHieu], [IdChatLieu]) VALUES (33, N'SPCT004455', N'Áo phông nữ', 18, CAST(120000 AS Decimal(20, 0)), 1, CAST(N'2023-11-26' AS Date), N'NV002', CAST(N'2023-11-26' AS Date), N'NV002', 0, 1, 1, 1, 1, 1, 1, 1, 1)
SET IDENTITY_INSERT [dbo].[SANPHAMCHITIET] OFF
GO
SET IDENTITY_INSERT [dbo].[Voucher] ON 

INSERT [dbo].[Voucher] ([Id], [MaVoucher], [TenVoucher], [MucGiamGia], [DieuKien], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (1, N'VC001', N'Giảm 10%', N'10%', 100000, N'Voucher gi?m giá 10%', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[Voucher] ([Id], [MaVoucher], [TenVoucher], [MucGiamGia], [DieuKien], [MoTa], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (2, N'VC002', N'Giảm 20%', N'20%', 150000, N'Voucher gi?m giá 20%', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
SET IDENTITY_INSERT [dbo].[Voucher] OFF
GO
SET IDENTITY_INSERT [dbo].[XuatXu] ON 

INSERT [dbo].[XuatXu] ([Id], [MaXuatXu], [NoiXuatXu], [Mota], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (1, N'XX001', N'Việt Nam', N'Xu?t x? Vi?t Nam', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
INSERT [dbo].[XuatXu] ([Id], [MaXuatXu], [NoiXuatXu], [Mota], [TrangThai], [CreateAt], [CreateBy], [UpdateAt], [UpdateBy], [Deleted]) VALUES (2, N'XX002', N'Trung Quốc', N'Xu?t x? Trung Qu?c', 1, CAST(N'2023-11-15' AS Date), N'Admin', CAST(N'2023-11-15' AS Date), N'Admin', 0)
SET IDENTITY_INSERT [dbo].[XuatXu] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__ChatLieu__453995BD17DB4DF7]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[ChatLieu] ADD UNIQUE NONCLUSTERED 
(
	[MaChatLieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__ChatLieu__453995BD3D6CB25F]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[ChatLieu] ADD UNIQUE NONCLUSTERED 
(
	[MaChatLieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__ChatLieu__453995BD3ED00884]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[ChatLieu] ADD UNIQUE NONCLUSTERED 
(
	[MaChatLieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__ChatLieu__453995BDFD80D536]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[ChatLieu] ADD UNIQUE NONCLUSTERED 
(
	[MaChatLieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__CoAo__152390C83E0BE9B5]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[CoAo] ADD UNIQUE NONCLUSTERED 
(
	[MaCoAo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__CoAo__152390C85610F897]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[CoAo] ADD UNIQUE NONCLUSTERED 
(
	[MaCoAo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__CoAo__152390C8B791A363]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[CoAo] ADD UNIQUE NONCLUSTERED 
(
	[MaCoAo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__CoAo__152390C8EF9AFCDF]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[CoAo] ADD UNIQUE NONCLUSTERED 
(
	[MaCoAo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__DangAo__BA93235D0BA00EDE]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[DangAo] ADD UNIQUE NONCLUSTERED 
(
	[MaDangAo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__DangAo__BA93235D39324980]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[DangAo] ADD UNIQUE NONCLUSTERED 
(
	[MaDangAo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__DangAo__BA93235D795146B8]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[DangAo] ADD UNIQUE NONCLUSTERED 
(
	[MaDangAo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__DangAo__BA93235D9EDB0581]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[DangAo] ADD UNIQUE NONCLUSTERED 
(
	[MaDangAo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HinhThuc__F9C72F7240643817]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[HinhThucThanhToan] ADD UNIQUE NONCLUSTERED 
(
	[MaHinhThucThanhToan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HinhThuc__F9C72F727314C4D0]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[HinhThucThanhToan] ADD UNIQUE NONCLUSTERED 
(
	[MaHinhThucThanhToan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HinhThuc__F9C72F72BF1306B2]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[HinhThucThanhToan] ADD UNIQUE NONCLUSTERED 
(
	[MaHinhThucThanhToan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HinhThuc__F9C72F72F02E9AD3]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[HinhThucThanhToan] ADD UNIQUE NONCLUSTERED 
(
	[MaHinhThucThanhToan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HoaDon__835ED13A586005AB]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[HoaDon] ADD UNIQUE NONCLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HoaDon__835ED13A82639E36]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[HoaDon] ADD UNIQUE NONCLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HoaDon__835ED13AA03CAF68]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[HoaDon] ADD UNIQUE NONCLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HoaDon__835ED13AB5F5D3DB]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[HoaDon] ADD UNIQUE NONCLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachHan__88D2F0E40A20EF83]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[KhachHang] ADD UNIQUE NONCLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachHan__88D2F0E4459A11F2]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[KhachHang] ADD UNIQUE NONCLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachHan__88D2F0E46EB8A397]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[KhachHang] ADD UNIQUE NONCLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachHan__88D2F0E4717B5F71]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[KhachHang] ADD UNIQUE NONCLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KichThuo__22BFD6650767333D]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[KichThuoc] ADD UNIQUE NONCLUSTERED 
(
	[MaKichThuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KichThuo__22BFD66519B38CFD]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[KichThuoc] ADD UNIQUE NONCLUSTERED 
(
	[MaKichThuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KichThuo__22BFD665375DEF67]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[KichThuoc] ADD UNIQUE NONCLUSTERED 
(
	[MaKichThuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KichThuo__22BFD665AA3E355F]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[KichThuoc] ADD UNIQUE NONCLUSTERED 
(
	[MaKichThuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__MauSac__B9A91163164EF4FD]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[MauSac] ADD UNIQUE NONCLUSTERED 
(
	[MaMauSac] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__MauSac__B9A911632211E563]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[MauSac] ADD UNIQUE NONCLUSTERED 
(
	[MaMauSac] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__MauSac__B9A911637D59499B]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[MauSac] ADD UNIQUE NONCLUSTERED 
(
	[MaMauSac] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__MauSac__B9A91163BE041CA4]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[MauSac] ADD UNIQUE NONCLUSTERED 
(
	[MaMauSac] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanHieu__75BD22490A69AF3F]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[NhanHieu] ADD UNIQUE NONCLUSTERED 
(
	[MaNhanHieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanHieu__75BD2249736E56A1]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[NhanHieu] ADD UNIQUE NONCLUSTERED 
(
	[MaNhanHieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanHieu__75BD2249F8E2F4BB]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[NhanHieu] ADD UNIQUE NONCLUSTERED 
(
	[MaNhanHieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanHieu__75BD2249FF83C132]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[NhanHieu] ADD UNIQUE NONCLUSTERED 
(
	[MaNhanHieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__77B2CA46091AA44F]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD UNIQUE NONCLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__77B2CA465DAFCCA8]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD UNIQUE NONCLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__77B2CA46C993EA9A]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD UNIQUE NONCLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__77B2CA46F2F905F6]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD UNIQUE NONCLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__SANPHAMC__49685BAE8ACAACD7]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[SANPHAMCHITIET] ADD UNIQUE NONCLUSTERED 
(
	[MaSanPhamCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__SANPHAMC__49685BAEA3D7B8CE]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[SANPHAMCHITIET] ADD UNIQUE NONCLUSTERED 
(
	[MaSanPhamCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__SANPHAMC__49685BAEAB2E5497]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[SANPHAMCHITIET] ADD UNIQUE NONCLUSTERED 
(
	[MaSanPhamCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__SANPHAMC__49685BAEE0DF52C6]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[SANPHAMCHITIET] ADD UNIQUE NONCLUSTERED 
(
	[MaSanPhamCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Voucher__0AAC5B10525681D9]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[Voucher] ADD UNIQUE NONCLUSTERED 
(
	[MaVoucher] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Voucher__0AAC5B10B468492E]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[Voucher] ADD UNIQUE NONCLUSTERED 
(
	[MaVoucher] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Voucher__0AAC5B10BF95ECC0]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[Voucher] ADD UNIQUE NONCLUSTERED 
(
	[MaVoucher] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Voucher__0AAC5B10D8956BB0]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[Voucher] ADD UNIQUE NONCLUSTERED 
(
	[MaVoucher] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__XuatXu__27BB6B180ED760A1]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[XuatXu] ADD UNIQUE NONCLUSTERED 
(
	[MaXuatXu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__XuatXu__27BB6B1886BD104C]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[XuatXu] ADD UNIQUE NONCLUSTERED 
(
	[MaXuatXu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__XuatXu__27BB6B18A72FE1BF]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[XuatXu] ADD UNIQUE NONCLUSTERED 
(
	[MaXuatXu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__XuatXu__27BB6B18E0D76E1B]    Script Date: 11/26/2023 6:29:54 PM ******/
ALTER TABLE [dbo].[XuatXu] ADD UNIQUE NONCLUSTERED 
(
	[MaXuatXu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
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
ALTER TABLE [dbo].[HinhAnh] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[HinhAnh] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[HinhAnh] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[HinhThucThanhToan] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[HinhThucThanhToan] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[HinhThucThanhToan] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT ((0)) FOR [TongTien]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT ((0)) FOR [TongTienSauGiamGia]
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
ALTER TABLE [dbo].[HoaDonChiTiet] ADD  DEFAULT ((0)) FOR [DonGia]
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
ALTER TABLE [dbo].[LichSuHoaDon] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[LichSuHoaDon] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[LichSuHoaDon] ADD  DEFAULT ((0)) FOR [deleted]
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
ALTER TABLE [dbo].[Voucher] ADD  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[Voucher] ADD  DEFAULT (getdate()) FOR [UpdateAt]
GO
ALTER TABLE [dbo].[Voucher] ADD  DEFAULT ((0)) FOR [Deleted]
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
/****** Object:  StoredProcedure [dbo].[FindSPCT]    Script Date: 11/26/2023 6:29:54 PM ******/
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
/****** Object:  StoredProcedure [dbo].[GetCTSP]    Script Date: 11/26/2023 6:29:54 PM ******/
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