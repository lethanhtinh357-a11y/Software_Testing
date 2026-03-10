package lab82;

public class VayVon {

    public static boolean duDieuKienVay(
            int tuoi,
            double thuNhap,
            boolean coTaiSanBaoLanh,
            int diemTinDung) {

        boolean dieuKienCoBan =
                (tuoi >= 22) && (thuNhap >= 10000000);

        boolean dieuKienBaoDam =
                coTaiSanBaoLanh || (diemTinDung >= 700);

        return dieuKienCoBan && dieuKienBaoDam;
    }
}