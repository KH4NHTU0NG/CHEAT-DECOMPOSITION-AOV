package com.android.support.ThemesData.CYGNUS;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
final class CygnusDisplayNames {
    private static final Map<String, String> PRETTY;

    static {
        HashMap map = new HashMap();
        PRETTY = map;
        map.put("Ngo Ton NVT", "Ngộ Không NVT");
        map.put("DT Sailor Moon", "ĐT Sailor Moon");
        map.put("BF Thanh Nu", "BF Thánh Nữ");
        map.put("Airi The Nguyen", "Airi Thề Nguyện");
        map.put("Murad Than Binh", "Murad Thần Binh");
        map.put("Grakk Than AT", "Grakk Thần AT");
        map.put("Nak Quy Thong", "Nak Quỷ Thông");
        map.put("Aya Cong Chua", "Aya Công Chúa");
        map.put("Nak Bach Dien", "Nak Bạch Diện");
        map.put("Veera Phu Thuy", "Veera Phù Thủy");
        map.put("Lili Ma Phap", "Lili Ma Pháp");
        map.put("Vio The Nguyen", "Vio Thề Nguyện");
        map.put("Cap Can Nguyen", "Cáp Cận Nguyên");
        map.put("Tel Lan Quang", "Tel Lãn Quang");
        map.put("Murad Tien De", "Murad Tiên Đế");
        map.put("BF Binh Minh", "BF Bình Minh");
        map.put("Val Vu Than", "Val Vũ Thần");
        map.put("Kaine Tho San", "Kaine Thợ Săn");
        map.put("Tel TN Vu Than", "Tel TN Vũ Thần");
        map.put("Stuart Sieu Trom", "Stuart Siêu Trộm");
        map.put("Nak TN Vu Than", "Nak TN Vũ Thần");
        map.put("Elandorr Tau Mo", "Elandorr Tàu Mộ");
        map.put("Elsu Tau Mo", "Elsu Tàu Mộ");
        map.put("Lauriel TN Vu Than", "Lauriel TN Vũ Thần");
        map.put("Vio Than Long", "Vio Thần Long");
        map.put("BF Kim Ngu", "BF Kim Ngư");
        map.put("Billow Thien Tuong", "Billow Thiên Tướng");
        map.put("Baron Thien Phu", "Baron Thiên Phủ");
        map.put("Veera That Sat", "Veera Thất Sát");
        map.put("Yena Tran Yeu", "Yena Trấn Yêu");
    }

    private CygnusDisplayNames() {
    }

    public static String pretty(String str) {
        if (str == null) {
            return null;
        }
        String str2 = PRETTY.get(str);
        return str2 != null ? str2 : str;
    }
}
