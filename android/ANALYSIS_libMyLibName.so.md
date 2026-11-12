# PHÂN TÍCH CHUYÊN SÂU: `libMyLibName.so` (Android Native Cheat Engine)

## 1. THÔNG SỐ PHÁP Y (FORENSIC PROFILE)
- **Tên tệp**: `libMyLibName.so`
- **Đường dẫn trong APK**: `lib/arm64-v8a/libMyLibName.so`
- **Kích thước**: 1,628,032 bytes (1.55 MB)
- **Kiến trúc nhị phân**: ELF 64-bit LSB shared object, ARM aarch64, version 1 (SYSV), dynamically linked
- **Khác gì so với bản gốc?**: **Hoàn toàn KHÔNG TỒN TẠI** trong game chính thức của Garena/Tencent. Đây là thư viện native bên ngoài được kẻ làm mod biên dịch bằng C++ và tiêm vào APK.

---

## 2. KIẾN TRÚC VÀ CÁC THÀNH PHẦN LÕI

### 2.1. Framework Hooking Nhúng
Thư viện này tích hợp sẵn **Dobby Hook** (`DobbyHook` - mã nguồn mở của jmpews). Dobby cho phép ghi đè mã máy tại thời điểm thực thi (Dynamic In-line Hooking) trên kiến trúc ARM64:
- Tự động cấp phát bộ nhớ gần (Near Code Cave/Trampoline).
- Thao tác trên quyền bộ nhớ trang thực thi (`mprotect`, `PROT_READ | PROT_WRITE | PROT_EXEC`).
- Xử lý các chỉ thị rẽ nhánh ARM64 BTI (Branch Target Identification).

### 2.2. Module Anti-Anti-Cheat: `[V4_ANTI]`
Trò chơi chính thức có thư viện bảo mật `libanort.so` (Tencent ACE / Anti-Cheat Expert) để quét bộ nhớ và phát hiện can thiệp. Kẻ làm mod đã viết module `[V4_ANTI]` để triệt tiêu nó:
1. Đọc danh sách vùng nhớ tiến trình từ `/proc/self/maps`.
2. Kiểm tra các trang thực thi (`RX pages`) và mã ELF Build-ID của `libanort.so`.
3. Hook 14 phương thức ghi nhận và gửi báo cáo hành vi gian lận (14 report methods) của game về server Garena.
4. Chặn các luồng kiểm tra (Thread Rendezvous & Thread Parking) để cô lập cơ chế quét chữ ký.

### 2.3. Cơ Chế Vẽ ESP & Trực Quan Hoá Đồ Họa
- Thư viện hook trực tiếp hàm `eglSwapBuffers` của EGL/OpenGL ES.
- Mỗi khi một khung hình chuẩn bị được xuất ra màn hình (Surface Swap), hook `eglSwapBuffers` sẽ kích hoạt:
  - Lấy ma trận thế giới góc nhìn (`get_worldToCameraMatrix_Injected`).
  - Chuyển toạ độ 3D của tướng địch sang toạ độ màn hình 2D (World-to-Screen Projection).
  - Vẽ khung chữ nhật (ESP Box), tia chỉ hướng (Aim Ray), và thanh máu lên nhân vật.

---

## 3. DANH SÁCH TÍNH NĂNG ĐƯỢC GIẢI MÃ TỪ NHỊ PHÂN

| Tên Định Danh Trong File | Loại Điều Khiển | Chức Năng Can Thiệp |
| :--- | :--- | :--- |
| `1_Toggle_Hack Map` | Bật / Tắt | Xoá sương mù bản đồ (Fog of War) trong game |
| `2_SeekBar_Cam Xa_0_30` | Thanh trượt (0-30) | Can thiệp `get_SupportedBoth60FPS_CameraHeight` (Drone View) |
| `3_Toggle_Thời Gian Hồi Chiêu` | Bật / Tắt | Đọc bộ nhớ đối phương để hiển thị thời gian hồi chiêu cuối/phép |
| `4_Toggle_Mod Full Skin` | Bật / Tắt | Hook `HeroSkinOverwrite` / `IsCanUseSkin` để dùng skin miễn phí |
| `5_Toggle_Mở Khóa 120 fps` | Bật / Tắt | Bỏ giới hạn FPS trên màn hình có tần số quét cao |
| `101_Toggle_Kích Hoạt Aim Game` | Bật / Tắt | Kích hoạt tự động ngắm chiêu thức |
| `102_Toggle_Tự động Bộc Phá` | Bật / Tắt | Tự động bấm Bộc Phá khi máu địch rơi vào ngưỡng kết liễu |
| `103_Toggle_Tự động Trừng Trị` | Bật / Tắt | Tự trừng trị quái rừng/Rồng/Caesar khi máu chạm ngưỡng sát thương |
| `104_Toggle_Tự động Băng Sương` | Bật / Tắt | Tự kích hoạt Quả Cầu Băng Sương khi máu nguy hiểm |
| `110_Spinner_Mục Tiêu AIM` | Chọn lựa | Ưu tiên chọn mục tiêu: *Máu thấp nhất*, *% máu thấp nhất*, *Gần nhất* |
| `120_Toggle_Mua Bán Đồ Nhanh` | Bật / Tắt | Tự động bán món đồ kích hoạt vừa dùng và mua đồ sinh tồn thay thế |
| `140_Toggle_Tự Động Check Tố Cáo` | Bật / Tắt | Bắt gói tin Lua `CSID_CMD_COMPLAINT_RECORD_RSP` để cảnh báo bị report |
