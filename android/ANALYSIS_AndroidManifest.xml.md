# PHÂN TÍCH CHUYÊN SÂU: `AndroidManifest.xml` (Android Manifest Hijacking)

## 1. THÔNG SỐ PHÁP Y
- **Tên tệp**: `AndroidManifest.xml`
- **Kích thước gốc**: 77,792 bytes
- **Khác gì so với bản gốc?**: Đổi Package ID, thay thế Application class, thay thế Launcher Activity, và bổ sung các Activity phục vụ kiểm soát bản quyền.

---

## 2. CHI TIẾT CÁC ĐIỂM CAN THIỆP

### 2.1. Đổi Định Danh Gói (Package Renaming)
- **Bản gốc**: `package="com.garena.game.kgvn"`
- **Bản mod**: `package="com.garena.game.kgvn1"`
- **Ý nghĩa kỹ thuật**: Hệ điều hành Android cấm cài đặt hai ứng dụng có cùng `package` trên cùng một máy (nếu chữ ký bảo mật khác nhau). Bằng cách thêm số `1`, bản mod cho phép cài đặt song song với Liên Quân chính thức từ Google Play Store, giúp người chơi dễ dàng chuyển đổi qua lại giữa bản hack và bản sạch.

### 2.2. Chiếm Quyền Khởi Tạo Ứng Dụng (Application Hijacking)
```xml
<!-- Bản gốc: android:name="com.garena.game.kgtw.NGameApplication" -->
<application
    android:name="com.android.support.auth.GateApplication"
    android:icon="@mipmap/app_icon"
    android:label="@string/app_name"
    android:theme="@android:style/Theme.NoTitleBar">
```
- **Ý nghĩa kỹ thuật**: `GateApplication` kế thừa `NGameApplication`. Nó chặn đứng quy trình nạp game mặc định, cho phép kẻ làm mod chạy code kiểm tra máy chủ trước khi khởi tạo game engine Unity.

### 2.3. Chiếm Quyền Màn Hình Khởi Động (Launcher Activity Hijacking)
Trong game gốc, Activity đón nhận hành vi nhấn vào icon trên màn hình chính (`MAIN` + `LAUNCHER`) là `com.garena.game.kgtw.SGameActivity`. Bản mod đã hoán đổi:
```xml
<activity
    android:name="com.android.support.auth.GateActivity"
    android:configChanges="density|fontScale|keyboard|keyboardHidden|layoutDirection|locale|mcc|mnc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|touchscreen|uiMode"
    android:exported="true"
    android:hardwareAccelerated="true"
    android:launchMode="singleTask"
    android:screenOrientation="sensorLandscape">
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
</activity>
```
Đồng thời thêm Activity thông báo bảo trì:
```xml
<activity
    android:name="com.android.support.auth.MaintenanceActivity"
    android:process=":apkGate"
    android:theme="@android:style/Theme.Material.NoActionBar"/>
```
Tất cả các thành phần này chạy trên tiến trình riêng `:apkGate` để độc lập với vòng đời của game engine.
