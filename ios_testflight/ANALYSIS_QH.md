# PHÂN TÍCH CHUYÊN SÂU: `QH.framework/QH` (iOS DRM Gate & Cloaking)

## 1. THÔNG SỐ PHÁP Y
- **Tên tệp**: `QH`
- **Đường dẫn**: `Frameworks/QH.framework/QH`
- **Kích thước**: 90,496 bytes (88.4 KB)
- **Kiến trúc nhị phân**: Mach-O 64-bit dynamically linked shared library arm64
- **Trạng thái mã hoá FairPlay**: `cryptid 0` (Hoàn toàn không bị mã hoá)
- **Khác gì so với bản gốc?**: **Hoàn toàn KHÔNG TỒN TẠI** trong game chính thức của Garena/Apple App Store.

---

## 2. CƠ CHẾ HOẠT ĐỘNG CHI TIẾT

### 2.1. Tự Động Kích Hoạt Bằng Objective-C `+[QHGate load]`
Tệp nhị phân chứa duy nhất một lớp cốt lõi là `QHGate`. Nó khai thác phương thức `+load` của runtime Objective-C:
```assembly
+[QHGate load]:
000000000000628c    sub    sp, sp, #0x40
0000000000006290    stp    x29, x30, [sp, #0x30]
...
00000000000062e0    bl     _dispatch_time
0000000000006318    bl     _dispatch_after
...
000000000000636c    bl     +[QHGate start]
```
Phương thức `+load` được dynamic linker của Apple (`dyld`) kích hoạt tự động ngay khi nạp framework vào vùng nhớ, trước cả khi hàm `main()` của ứng dụng bắt đầu chạy.

### 2.2. Đọc Cấu Hình Bản Quyền & Gọi API
- Hàm `+[QHGate currentKey]` truy cập vào `[NSBundle bundleForClass:[QHGate class]] infoDictionary` để đọc giá trị khoá:
  ```xml
  <key>QHKey</key>
  <string>KienApp1</string>
  ```
- Hàm `+[QHGate verify]` tạo phiên mạng ngầm `NSURLSession` với cấu hình `ephemeralSessionConfiguration` (không lưu cache):
  ```text
  GET https://qh.quyen.xyz/api/checkkey?key=KienApp1
  ```
- Phản hồi từ máy chủ được giải mã qua `NSJSONSerialization`:
  ```json
  {"check": true}
  ```

### 2.3. Cơ Chế Chặn Màn Hình (Screen Blocker)
Nếu trường `check` trả về `false` (hoặc xảy ra lỗi mạng / hết hạn sử dụng):
1. Phương thức `+[QHGate blockWithMessage:]` được gọi trên luồng chính (`dispatch_async` tới `_dispatch_main_q`).
2. Tạo một `UIWindow` mới phủ toàn bộ màn hình (`[UIScreen mainScreen] bounds`).
3. Đặt mức hiển thị cửa sổ là cao nhất (`setWindowLevel: UIWindowLevelAlert`).
4. Đặt màu nền đen (`setBackgroundColor: blackColor`) và hiển thị nhãn chữ trắng thông báo ứng dụng bị khoá.
5. Gọi `makeKeyAndVisible` để chiếm quyền điều khiển cảm ứng, vô hiệu hoá hoàn toàn trò chơi.
