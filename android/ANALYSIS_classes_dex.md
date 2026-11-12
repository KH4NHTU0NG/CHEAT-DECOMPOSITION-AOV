# PHÂN TÍCH CHUYÊN SÂU: CÁC TỆP `classes7.dex`, `classes8.dex`, `classes9.dex`

## 1. THÔNG SỐ PHÁP Y
- `classes7.dex` (651,056 bytes): Giao diện Mod Menu + Xoá Token Garena MSDK + SoundCloud Player + Bắt Tố Cáo
- `classes8.dex` (20,884 bytes): Cổng kiểm soát bản quyền (Remote Gate) + Màn hình bảo trì + Killswitch
- `classes9.dex` (11,592 bytes): Cầu nối JNI (Main.java) + Khởi tạo menu theo vòng đời Activity (MenuStartup.java)
- **Khác gì so với bản gốc?**: Bản chính thức chỉ có từ `classes.dex` đến `classes6.dex`. Cả 3 file DEX này đều được **tạo mới và nhúng thêm** vào gói APK.

---

## 2. PHÂN TÍCH CHI TIẾT TỪNG TỆP DEX

### 2.1. `classes8.dex` — Cổng Kiểm Soát Bản Quyền (DRM & Remote Killswitch)
Tệp này chịu trách nhiệm kiểm tra xem phiên bản mod có còn được phép hoạt động hay không:
- **`GateApplication.java`**:
  - Kế thừa lớp `com.garena.game.kgtw.NGameApplication` gốc.
  - Kiểm tra tên tiến trình hiện tại. Nếu là `:apkGate`, nó không khởi tạo Unity Engine để tránh lãng phí RAM, chỉ mở `GateActivity`.
- **`GateActivity.java`**:
  - Dựng giao diện tạm thời thông báo *"MAP SÁNG ANDROID V3"* và gửi yêu cầu kiểm tra tới máy chủ.
  - Nếu máy chủ cho phép (`enabled: true`), nó gọi hàm `launchGame()`, tạo Intent mở `com.garena.game.kgtw.SGameActivity` (Game gốc) và đóng chính mình.
- **`RemoteGate.java`**:
  - Khởi tạo `ScheduledThreadPoolExecutor` gửi `POST` định kỳ **mỗi 3 giây**:
    ```text
    Endpoint: https://hackgame.lienquan.io.vn/apk-manager/status.php
    Payload: code=<KEY>&app_id=map-sang-v4-20261103-r2&package=com.garena.game.kgvo&phienban=11
    ```
  - Nếu kết quả trả về `{"status": "ok", "enabled": false}` hoặc thông báo bảo trì:
    - Mở `MaintenanceActivity`.
    - Sau 700ms, gọi hàm `closeOwnGame()`: Quét toàn bộ tiến trình con bằng `ActivityManager.getRunningAppProcesses()` và cưỡng chế kết liễu bằng `Process.killProcess(pid)` và `System.exit(0)`.

---

### 2.2. `classes9.dex` — Cầu Nối Nạp Thư Viện Native & Vòng Đời Menu
- **`Main.java`**:
  - Nạp thư viện native: `System.loadLibrary("MyLibName")`.
  - Quản lý trạng thái xác thực: `IsAuthorized()`, `SetAuthorized(boolean)`.
  - Khởi tạo Crash Handler và cấp quyền hiển thị cửa sổ nổi đè lên màn hình (`CheckOverlayPermission`).
- **`MenuStartup.java`**:
  - Đăng ký `Application.ActivityLifecycleCallbacks` để theo dõi khi nào Activity trò chơi (`SGameActivity`) vào trạng thái `Resumed`.
  - Chờ trạng thái runtime từ native (`Main.GetRuntimeStatusNative() == "applied"`).
  - Khi game đã sẵn sàng, nó gọi `Main.StartWithoutPermissionNow(activity)` để gắn menu nổi vào màn hình.

---

### 2.3. `classes7.dex` — Giao Diện Điều Khiển & Các Tiện Ích Can Thiệp
- **Giao diện Menu nổi (`ThemesData/CYGNUS`, `ThemesData/DZ`, `ThemesData/LGL`)**:
  - Hệ thống giao diện phức tạp bằng Android WindowManager, cho phép thu nhỏ thành biểu tượng trôi nổi và phóng to thành bảng menu chia tab.
- **`GameAccountLogout.java` (Bộ Xoá Token Garena Chống Ban / Đổi Acc Nhanh)**:
  - Can thiệp trực tiếp vào thư mục lưu trữ nội bộ của ứng dụng:
    ```text
    /data/data/com.garena.game.kgvn1/shared_prefs/com.garena.msdk.token_cache.xml
    /data/data/com.garena.game.kgvn1/shared_prefs/com.garena.msdk.token_cache.xml.bak
    ```
  - Xoá sạch các tệp này bằng hàm `clearTokenCache()`.
  - Sử dụng `AlarmManager.setExactAndAllowWhileIdle()` hoặc `Intent.makeRestartActivityTask()` để ép khởi động lại game sau 400ms. Kỹ thuật này giúp người dùng không phải vào Cài đặt để Xoá dữ liệu ứng dụng.
- **`ComplaintUi.java` (Bộ Cảnh Báo Bị Tố Cáo)**:
  - Nhận dữ liệu JSON từ hàm native `ComplaintUi.nativeSnapshot()`.
  - Nếu tài khoản trong trận bị đối thủ bấm Tố cáo (Report), ứng dụng sẽ hiện hộp thoại cảnh báo: *"Bạn vừa bị tố cáo..."* để người chơi biết và giảm thiểu hành vi lộ liễu.
- **`SoundCloudMusicService.java`**:
  - Dịch vụ Background Service kết hợp `MediaSession` và `WakeLock` để phát nhạc SoundCloud trực tiếp qua giao diện menu mod.
