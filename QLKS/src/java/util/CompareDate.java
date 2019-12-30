package util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CompareDate {

    // So sánh 2 ngày khi không có giờ phút giây, tại sao ko dùng hàm có sẵn?
    // Khi chọn thời gian sẽ được trả về Date, gồm có cả giờ phút giây hiện tại
    // => nếu chọn ngày trả trước ngày đến => dù cả 2 cùng ngày nhưng hàm compare mặc định
    // sẽ cho là ngày trả < ngày đến mặc dù bằng nhau
    public static int compareNoTime(Date date1, Date date2) {
        int d1 = date1.getDate(), m1 = date1.getMonth(), y1 = date1.getYear();
        int d2 = date2.getDate(), m2 = date2.getMonth(), y2 = date2.getYear();
        if (y1 == y2) {
            if (m1 == m2) {
                if (d1 == d2) {
                    return 0;
                }
                if (d1 < d2) {
                    return -1;
                }
                return 1;
            }
            if (m1 < m2) {
                return -1;
            }
            return 1;
        }
        if (y1 < y2) {
            return -1;
        }
        return 1;
    }

    // So sánh 2 ngày khi lược bỏ đi giờ phút giây
    public static int daysBetweenNoTime(Date d1, Date d2) {
        LocalDate ld1 = LocalDate.of(d1.getYear(), d1.getMonth() + 1, d1.getDate());
        LocalDate ld2 = LocalDate.of(d2.getYear(), d2.getMonth() + 1, d2.getDate());
        return (int) (ChronoUnit.DAYS.between(ld1, ld2) + 1);
    }
    
}
