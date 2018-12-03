package com.chuyenctn.assignment.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.dao.KhoanChi_DAO;
import com.chuyenctn.assignment.dao.KhoanThu_DAO;
import com.chuyenctn.assignment.model.Khoanchi;
import com.chuyenctn.assignment.model.Khoanthu;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Fragment_Thongke extends Fragment {

    private KhoanThu_DAO khoanThuDao;
    private KhoanChi_DAO khoanChiDao;


    private CardView cardviewKtTk;
    private ImageView imgKtTk;
    private TextView tvSotienKhoanThuTK;
    private CardView cardviewKcTk;
    private ImageView imgKcTk;
    private TextView tvSotienKhoanChiTK;
    private CardView cardviewStLk;
    private ImageView imgStTk;
    private TextView tvSotienhiencoTK;
    private TextView tvTextKtTk;
    private TextView tvTextKtmaxTk;
    private CardView cardviewKtmaxTk;
    private ImageView imgMaxtTk;
    private TextView tvTenKhoanThumaxTK;
    private TextView tvTenloaiKhoanThumaxTK;
    private TextView tvSotienKhoanThumaxTK;
    private TextView tvNgayKhoanThumaxTK;
    private TextView tvGhichuKhoanThumaxTK;
    private TextView tvTextKcmaxTk;
    private CardView cardviewKcmaxTk;
    private ImageView imgMaxcTk;
    private TextView tvTenKhoanChimaxTK;
    private TextView tvTenloaiKhoanChimaxTK;
    private TextView tvSotienKhoanChimaxTK;
    private TextView tvNgayKhoanChimaxTK;
    private TextView tvGhichuKhoanChimaxTK;
    private TextView tvMaKhoanChimaxTK;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        cardviewKtTk = (CardView) view.findViewById(R.id.cardview_kt_tk);
        imgKtTk = (ImageView) view.findViewById(R.id.img_kt_tk);
        tvSotienKhoanThuTK = (TextView) view.findViewById(R.id.tv_sotienKhoanThu_TK);
        cardviewKcTk = (CardView) view.findViewById(R.id.cardview_kc_tk);
        imgKcTk = (ImageView) view.findViewById(R.id.img_kc_tk);
        tvSotienKhoanChiTK = (TextView) view.findViewById(R.id.tv_sotienKhoanChi_TK);
        cardviewStLk = (CardView) view.findViewById(R.id.cardview_st_lk);
        imgStTk = (ImageView) view.findViewById(R.id.img_st_tk);
        tvSotienhiencoTK = (TextView) view.findViewById(R.id.tv_sotienhienco_TK);
        tvTextKtTk = (TextView) view.findViewById(R.id.tv_text_kt_tk);
        tvTextKtmaxTk = (TextView) view.findViewById(R.id.tv_text_ktmax_tk);
        cardviewKtmaxTk = (CardView) view.findViewById(R.id.cardview_ktmax_tk);
        imgMaxtTk = (ImageView) view.findViewById(R.id.img_maxt_tk);
        tvTenKhoanThumaxTK = (TextView) view.findViewById(R.id.tv_tenKhoanThumax_TK);
        tvTenloaiKhoanThumaxTK = (TextView) view.findViewById(R.id.tv_tenloaiKhoanThumax_TK);
        tvSotienKhoanThumaxTK = (TextView) view.findViewById(R.id.tv_sotienKhoanThumax_TK);
        tvNgayKhoanThumaxTK = (TextView) view.findViewById(R.id.tv_ngayKhoanThumax_TK);
        tvGhichuKhoanThumaxTK = (TextView) view.findViewById(R.id.tv_ghichuKhoanThumax_TK);
        tvTextKcmaxTk = (TextView) view.findViewById(R.id.tv_text_kcmax_tk);
        cardviewKcmaxTk = (CardView) view.findViewById(R.id.cardview_kcmax_tk);
        imgMaxcTk = (ImageView) view.findViewById(R.id.img_maxc_tk);
        tvTenKhoanChimaxTK = (TextView) view.findViewById(R.id.tv_tenKhoanChimax_TK);
        tvTenloaiKhoanChimaxTK = (TextView) view.findViewById(R.id.tv_tenloaiKhoanChimax_TK);
        tvSotienKhoanChimaxTK = (TextView) view.findViewById(R.id.tv_sotienKhoanChimax_TK);
        tvNgayKhoanChimaxTK = (TextView) view.findViewById(R.id.tv_ngayKhoanChimax_TK);
        tvGhichuKhoanChimaxTK = (TextView) view.findViewById(R.id.tv_ghichuKhoanChimax_TK);
        tvMaKhoanChimaxTK = (TextView) view.findViewById(R.id.tvMaKhoanChimax_TK);
        TextView tv_MaKhoanThumax_TK = view.findViewById(R.id.tv_MaKhoanThumax_TK);
        khoanChiDao = new KhoanChi_DAO(new SqliteHelper(getContext()));
        khoanThuDao = new KhoanThu_DAO(new SqliteHelper(getContext()));
        String tienchi = khoanChiDao.getsotienKhoanChi();
        String tienthu = khoanThuDao.getSoTienKhoanThu() + "";
        double dbtienchi = 0;
        double dbtienthu = 0;
        if (tienthu.equalsIgnoreCase("null")) {
            tvSotienKhoanThuTK.setText("Số tiền: " + " Vnđ");
        } else {
            tvSotienKhoanThuTK.setText("Số tiền: " + khoanThuDao.getSoTienKhoanThu() + " Vnđ");
            dbtienthu = Double.parseDouble(khoanThuDao.getSoTienKhoanThu());
        }

        if (tienchi.equalsIgnoreCase("null")) {
            tvSotienKhoanChiTK.setText("Số tiền: " + "" + " Vnđ");
        } else {
            dbtienchi = Double.parseDouble(khoanChiDao.getsotienKhoanChi());
            tvSotienKhoanChiTK.setText("Số tiền: " + khoanChiDao.getsotienKhoanChi() + " Vnđ");
        }
        if (dbtienchi >= 0 & dbtienthu >= 0) {
            tvSotienhiencoTK.setText("Số tiền: " + (dbtienthu - dbtienchi) + " Vnđ");
        } else {
            tvSotienhiencoTK.setText("Số tiền: ");
        }
        try {
            Khoanchi khoanchi = new Khoanchi();
            khoanchi = khoanChiDao.getKhoanChimax();
            if (khoanchi != null) {
                tvTenKhoanChimaxTK.setText(khoanChiDao.getKhoanChimax().getTenKhoanChi());
                tvMaKhoanChimaxTK.setText("Mã: " + khoanChiDao.getKhoanChimax().getMaKhoanChi());
                tvSotienKhoanChimaxTK.setText("Số tiền: " + khoanChiDao.getKhoanChimax().getSotien() + " Vnđ");
                tvGhichuKhoanChimaxTK.setText("Ghi chú: " + khoanChiDao.getKhoanChimax().getGhichu());
                tvNgayKhoanChimaxTK.setText("Ngày: " + sdf.format(sdf.parse(khoanChiDao.getKhoanChimax().getNgay())));
                tvTenloaiKhoanChimaxTK.setText("Loại: " + khoanChiDao.getKhoanChimax().getTenLoaiChi());
            } else {

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            Khoanthu khoanthu = new Khoanthu();
            khoanthu = khoanThuDao.getKhoanThumax();
            if (khoanthu != null) {
                tvGhichuKhoanThumaxTK.setText("Ghi chú: " + khoanThuDao.getKhoanThumax().getGhichu() + "");
                tvSotienKhoanThumaxTK.setText("Số tiền: " + khoanThuDao.getKhoanThumax().getSotien() + " Vnđ");
                tvTenloaiKhoanThumaxTK.setText("Loại: " + khoanThuDao.getKhoanThumax().getTenLoaiThu());
                tvNgayKhoanThumaxTK.setText("Ngày: " + sdf.format(sdf.parse(khoanThuDao.getKhoanThumax().getNgay())));
                tvTenKhoanThumaxTK.setText(khoanThuDao.getKhoanThumax().getTenKhoanThu());
                tv_MaKhoanThumax_TK.setText("Mã: " + khoanThuDao.getKhoanThumax().getMaKhoanThu());
            } else {
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;

    }
}
