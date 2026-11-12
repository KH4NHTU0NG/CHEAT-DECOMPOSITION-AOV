# PHÂN TÍCH CHUYÊN SÂU: `aovnet.framework/aovnet` (iOS Trojanized Network Framework)

## 1. THÔNG SỐ PHÁP Y
- **Tên tệp**: `aovnet`
- **Đường dẫn**: `Frameworks/aovnet.framework/aovnet`
- **Kích thước**: 51,514,240 bytes (49.13 MB)
- **Kiến trúc nhị phân**: Mach-O 64-bit dynamically linked shared library arm64
- **Trạng thái mã hoá FairPlay**: `cryptid 0`
- **Khác gì so với bản gốc?**: Ở game gốc, `aovnet` chỉ là thư viện mạng (Socket/Network Client). Ở bản mod này, nó đã bị **biến đổi thành Trojan Horse**, chứa toàn bộ engine gian lận (Aimbot, ImGui, Dobby Hook, Mod Skin, Fast Swap Đồ).

---

## 2. BẰNG CHỨNG CAN THIỆP BẤT THƯỜNG TRONG LIÊN KẾT NHỊ PHÂN

Kiểm tra các thư viện động mà `aovnet` liên kết (`otool -L`):
```text
/System/Library/Frameworks/Metal.framework/Metal
/System/Library/Frameworks/MetalKit.framework/MetalKit
/System/Library/Frameworks/GLKit.framework/GLKit
/System/Library/Frameworks/QuartzCore.framework/QuartzCore
/System/Library/Frameworks/UIKit.framework/UIKit
```
**Bất thường nghiêm trọng**: Một thư viện mạng của trò chơi không bao giờ cần giao tiếp với `MetalKit` hay `GLKit`. Kẻ làm mod đã liên kết các thư viện này để nhúng giao diện đồ họa **Dear ImGui** can thiệp thẳng vào Metal Graphics Pipeline của iOS.

---

## 3. ENGINE CAN THIỆP PHẦN CỨNG ARM64 (HARDWARE BREAKPOINT HOOKING)

Phân rã hàm `_hook` bên trong `aovnet`:
```assembly
_hook:
00000000024c6b40    stp    x26, x25, [sp, #-0x50]!
...
00000000024c6ba0    add    x0, x0, #0x416    ; chuỗi "hw.optional.breakpoint"
00000000024c6bb8    bl     _sysctlbyname
...
00000000024c6bc4    ldr    w0, [x23]         ; _mach_task_self_
00000000024c6bd4    sub    x5, x29, #0xb8
```

- **Nguyên lý hoạt động**:
  1. Sử dụng lệnh hệ thống `sysctlbyname("hw.optional.breakpoint")` để kiểm tra khả năng hỗ trợ thanh ghi điểm dừng phần cứng trên vi xử lý Apple Silicon (A-series / M-series).
  2. Dùng cổng tác vụ Mach kernel (`_mach_task_self_`) để can thiệp vào ngữ cảnh thực thi của luồng (`thread_set_state`).
  3. Kỹ thuật này cho phép chuyển hướng luồng thực thi mà **không cần ghi đè lên các trang mã nguồn thực thi** (`RX memory pages`), giúp qua mặt cơ chế kiểm tra tính toàn vẹn chữ ký mã (Code Signing Hardened Runtime) của Apple.

---

## 4. CÁC TÍNH NĂNG GIAN LẬN ĐƯỢC GIẢI MÃ

- **Aimbot Engine**:
  - Các biến kiểm soát: `AimbotEnable`, `ManualAim`, `AimbotNew`, `aimType`, `buttonaim`.
  - Tự động bắt góc toạ độ tướng đối phương và điều hướng kỹ năng.
- **Mua Bán Đồ Nhanh (Fast Item Swap)**:
  - Tích hợp sẵn chuỗi danh sách 11 trang bị sinh tồn: *Giáp Hồi Sinh, Cầu Băng Sương, Liềm Đoạt Mệnh, Thương Khung Kiếm, Thuẫn Nham Thạch, Hercules Thịnh Nộ, Cung Tà Ma, Xạ Nhật Cung, Thệ Ước Carano, Vô Ảnh Tiễn, Giáp Thống Khổ*.
  - Tự động bán trang bị kích hoạt vừa dùng và mua trang bị phòng thủ thay thế trong 0.1 giây.
- **Bộ Đọc Tố Cáo (`CheckToCao`)**:
  - Ghi nhận trạng thái vào tệp: `~/Library/Containers/com.cong.nhayduhacanh/Data/Documents/CheckToCao-status.txt`.
  - Lắng nghe gói tin mạng để cảnh báo khi có người tố cáo cheater trong trận.
- **Mod Skin & Minion Skin**:
  - Các hàm: `unlockskin`, `skinid2`.
  - Hỗ trợ đổi skin lính: *Mặc Định, Thám Tử Lừng Danh Conan, Slime, Cinnamon, Attack On Titan*.
