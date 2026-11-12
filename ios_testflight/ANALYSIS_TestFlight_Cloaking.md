# PHÂN TÍCH CHUYÊN SÂU: KỸ THUẬT NGỤY TRANG QUA MẶT APPLE TESTFLIGHT (CLOAKING)

## 1. BỐI CẢNH VÀ MỤC ĐÍCH
Apple TestFlight là hệ thống thử nghiệm ứng dụng nội bộ có quy trình kiểm duyệt (Beta App Review) khắt khe trước khi cấp phép gửi link mời ra bên ngoài. Để phân phối được bản mod game Liên Quân mà không bị nhân viên Apple từ chối, kẻ làm mod sử dụng kỹ thuật **Cloaking (Ngụy trang ứng dụng hai mặt)**.

---

## 2. CÁC TỆP THAM GIA VÀO QUY TRÌNH NGỤY TRANG

### 2.1. Tệp `Info.plist` (iOS App Manifest)
```xml
<key>CFBundleIdentifier</key>
<string>com.cong.nhayduhacanh</string>
<key>CFBundleName</key>
<string>nhayduhacanh</string>
<key>CFBundleDisplayName</key>
<string>Map Có Menu</string>
```
- Khai báo với Apple đây là ứng dụng trò chơi giải trí nhẹ mang tên *"Nhảy Dù Hạ Cánh"* (`nhayduhacanh`).
- Nhưng tên hiển thị ra màn hình người dùng là `"Map Có Menu"`.

### 2.2. Tệp `Entitlement.entitlements`
```xml
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>aps-environment</key>
    <string>development</string>
</dict>
</plist>
```
- Sử dụng chứng chỉ lập trình viên Apple Developer để ký số và tải lên TestFlight.

### 2.3. Quy Trình Vượt Qua Kiểm Duyệt Của Apple
1. **Giai đoạn gửi duyệt (App Review Phase)**:
   - Khi nhân viên Apple mở ứng dụng để kiểm tra, framework `QH.framework` gửi truy vấn tới máy chủ:
     `GET https://qh.quyen.xyz/api/checkkey?key=KienApp1`
   - Trong thời gian kiểm duyệt, máy chủ trả về `{"check": false}` hoặc trả về giao diện mô phỏng một trò chơi nhảy dù đơn giản.
   - Nhân viên Apple không thấy bất kỳ dấu hiệu nào của game Liên Quân Mobile hay giao diện cheat, do đó chấp thuận cho phép đưa lên TestFlight.
2. **Giai đoạn kích hoạt công khai (Public Distribution Phase)**:
   - Sau khi link TestFlight được phê duyệt, kẻ làm mod đổi cờ trên máy chủ `qh.quyen.xyz` thành `{"check": true}`.
   - Khi người dùng tải app qua TestFlight và mở lên, cờ `true` kích hoạt: `QHGate` rút lui, cho phép `UnityFramework` và `aovnet.framework` bung toàn bộ trò chơi Liên Quân cùng menu can thiệp.
