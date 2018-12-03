package com.chuyenctn.assignment.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.dao.KhoanChi_DAO;
import com.chuyenctn.assignment.dao.LoaiChi_DAO;
import com.chuyenctn.assignment.model.Khoanchi;
import com.chuyenctn.assignment.model.Loaichi;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Recycle_Adapter_Khoanchi extends RecyclerView.Adapter<Recycle_Adapter_Khoanchi.ViewHolder> {
    private Context context;
    private ArrayList<Khoanchi> khoanchiArrayList;
    private KhoanChi_DAO khoanChiDao;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private LoaiChi_DAO loaiChiDao;
    private List<Loaichi> loaichiList = new ArrayList<>();
    private String tenloaichi;
    private TextInputLayout tilThemLoaiKhoan;
    private EditText edThemLoaiKhoan;

    public Recycle_Adapter_Khoanchi(Context context, ArrayList<Khoanchi> khoanchiArrayList) {
        this.context = context;
        this.khoanchiArrayList = khoanchiArrayList;
        khoanChiDao = new KhoanChi_DAO(new SqliteHelper(context));
    }

    @NonNull
    @Override
    public Recycle_Adapter_Khoanchi.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_khoanthu, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Recycle_Adapter_Khoanchi.ViewHolder viewHolder, final int i) {
        viewHolder.tvItemTenkhoanthu.setText(khoanchiArrayList.get(i).getTenKhoanChi());
        viewHolder.tvItemMakhoanthu.setText("ID: " + khoanchiArrayList.get(i).getMaKhoanChi());
        viewHolder.tvItemSotienkhoanthu.setText("Số tiền: " + String.format(khoanchiArrayList.get(i).getSotien()) + " $");
        viewHolder.imgLogoKt.setImageResource(R.drawable.item_khoanchi);
        viewHolder.imgMoreKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, viewHolder.imgMoreKhoan);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.popup_Sua:
                                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                                String title = "Sửa khoản chi";
                                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLACK);
                                SpannableStringBuilder stringBuilder = new SpannableStringBuilder(title);
                                stringBuilder.setSpan(foregroundColorSpan, 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                mBuilder.setTitle(stringBuilder);
                                final View dialog = View.inflate(context, R.layout.dialog_add_khoan, null);
                                mBuilder.setView(dialog);

                                final TextInputLayout tilThemMAKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themMAKhoan);
                                final EditText edThemMaKhoan = (EditText) dialog.findViewById(R.id.ed_themMaKhoan);
                                final TextInputLayout tilThemTenKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themTenKhoan);
                                final EditText edThemTenKhoan = (EditText) dialog.findViewById(R.id.ed_themTenKhoan);
                                tilThemLoaiKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themLoaiKhoan);
                                edThemLoaiKhoan = (EditText) dialog.findViewById(R.id.ed_themLoaiKhoan);
                                final TextInputLayout tilThemSoTienKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themSoTienKhoan);
                                final EditText edThemSoTienKhoan = (EditText) dialog.findViewById(R.id.ed_themSoTienKhoan);
                                final TextInputLayout tilThemNgayKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themNgayKhoan);
                                final EditText edThemNgayKhoan = (EditText) dialog.findViewById(R.id.ed_themNgayKhoan);
                                final TextInputLayout tilThemGhiChuKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themGhiChuKhoan);
                                final EditText edThemGhiChuKhoan = (EditText) dialog.findViewById(R.id.ed_themGhiChuKhoan);
                                edThemLoaiKhoan.setHint("");
                                tilThemLoaiKhoan.setHint("Loại chi");
                                edThemLoaiKhoan.setText(khoanchiArrayList.get(i).getTenLoaiChi());
                                edThemMaKhoan.setText(khoanchiArrayList.get(i).getMaKhoanChi());
                                edThemGhiChuKhoan.setText(khoanchiArrayList.get(i).getGhichu());
                                edThemSoTienKhoan.setText(khoanchiArrayList.get(i).getSotien() + "");
                                edThemTenKhoan.setText(khoanchiArrayList.get(i).getTenKhoanChi());
                                loaiChiDao = new LoaiChi_DAO(new SqliteHelper(context));
                                final String[] loai = loaiChiDao.gettenloaichi();
                                loaichiList = loaiChiDao.getTenLoaiChi();
                                for (int i = 0; i < loaichiList.size(); i++) {
                                    loai[i] = loaichiList.get(i).getTenLoaiChi();
                                }

                                edThemLoaiKhoan.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                        builder.setTitle("Loại chi");
                                        builder.setSingleChoiceItems(loai, 1, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                edThemLoaiKhoan.setText(loai[i]);
                                                builder.setCancelable(true);
                                            }
                                        });
                                        builder.show();
                                    }
                                });

                                try {
                                    edThemNgayKhoan.setText(sdf.format(sdf.parse(khoanchiArrayList.get(i).getNgay())));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                edThemNgayKhoan.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Calendar calendar = Calendar.getInstance();
                                        int year = calendar.get(Calendar.YEAR);
                                        int month = calendar.get(Calendar.MONTH);
                                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                                        DatePickerDialog datePickerDialog = new DatePickerDialog(dialog.getContext(), new DatePickerDialog.OnDateSetListener() {
                                            @Override
                                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                                Calendar calendar1 = new GregorianCalendar(i, i1, i2);
                                                edThemNgayKhoan.setText(sdf.format(calendar1.getTime()));
                                            }
                                        }, year, month, day);
                                        datePickerDialog.show();
                                    }
                                });

                                mBuilder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        khoanChiDao = new KhoanChi_DAO(new SqliteHelper(context));
                                        try {
                                            if (khoanChiDao.updateKhoanChi(edThemMaKhoan.getText().toString(), edThemTenKhoan.getText().toString(), edThemLoaiKhoan.getText().toString(), Double.parseDouble(edThemSoTienKhoan.getText().toString()), sdf.parse(edThemNgayKhoan.getText().toString()), edThemGhiChuKhoan.getText().toString()) > 0) {
                                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                            }
                                            khoanchiArrayList.clear();
                                            khoanchiArrayList = (ArrayList<Khoanchi>) khoanChiDao.getAllKhoanChi();
                                            notifyDataSetChanged();

                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                mBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mBuilder.setCancelable(true);
                                    }
                                });
                                mBuilder.show();

                                break;
                            case R.id.popup_Xoa:
                                khoanChiDao = new KhoanChi_DAO(new SqliteHelper(context));
                                if (khoanChiDao.deleteKhoanChi(khoanchiArrayList.get(i).getMaKhoanChi()) > 0) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                }
                                khoanchiArrayList.remove(i);
                                notifyDataSetChanged();

                                break;
                            case R.id.popup_chitiet:
                                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                String title1 = "Khoản chi";
                                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLACK);
                                SpannableStringBuilder builder1 = new SpannableStringBuilder(title1);
                                builder1.setSpan(colorSpan, 0, title1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                builder.setTitle(builder1);
                                final View view1 = View.inflate(context, R.layout.showchitiet_khoan, null);
                                builder.setView(view1);

                                TextView tvShowtenKhoan = (TextView) view1.findViewById(R.id.tv_showten_khoan);
                                ImageView imgShowAvatarKhoan = (ImageView) view1.findViewById(R.id.img_show_avatar_khoan);
                                TextView tvShowIdKhoan = (TextView) view1.findViewById(R.id.tv_show_id_Khoan);
                                TextView tvShowLoaiKhoan = (TextView) view1.findViewById(R.id.tv_show_loai_Khoan);
                                TextView tvShowSotienKhoan = (TextView) view1.findViewById(R.id.tv_show_sotien_Khoan);
                                TextView tvShowNgayKhoan = (TextView) view1.findViewById(R.id.tv_show_ngay_Khoan);
                                TextView tvShowGhichuKhoan = (TextView) view1.findViewById(R.id.tv_show_ghichu_Khoan);
                                imgShowAvatarKhoan.setImageResource(R.drawable.item_khoanchi);
                                tvShowtenKhoan.setText(khoanchiArrayList.get(i).getTenKhoanChi());
                                tvShowIdKhoan.setText("ID: " + khoanchiArrayList.get(i).getMaKhoanChi());
                                tvShowLoaiKhoan.setText("Loại thu: " + khoanchiArrayList.get(i).getTenLoaiChi());
                                tvShowSotienKhoan.setText("Số tiền: " + khoanchiArrayList.get(i).getSotien() + " $");
                                tvShowNgayKhoan.setText("Ngày thu: " + khoanchiArrayList.get(i).getNgay());
                                tvShowGhichuKhoan.setText("Ghi chú: " + khoanchiArrayList.get(i).getGhichu());

                                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        builder.setCancelable(true);
                                    }
                                });
                                builder.show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return khoanchiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLogoKt;
        private TextView tvItemTenkhoanthu;
        private TextView tvItemMakhoanthu;
        private TextView tvItemSotienkhoanthu;
        private ImageView imgMoreKhoan;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemMakhoanthu = itemView.findViewById(R.id.tv_item_maloaithu_khoanthu);
            imgLogoKt = (ImageView) itemView.findViewById(R.id.img_logo_kt);
            tvItemTenkhoanthu = (TextView) itemView.findViewById(R.id.tv_item_tenkhoanthu);
            tvItemSotienkhoanthu = (TextView) itemView.findViewById(R.id.tv_item_sotienkhoanthu);
            imgMoreKhoan = (ImageView) itemView.findViewById(R.id.img_more_khoan);
        }
    }

    public void changeDataset(List<Khoanchi> items) {
        this.khoanchiArrayList = (ArrayList<Khoanchi>) items;
        notifyDataSetChanged();
    }

    private String NumFormat(Double number) {
        double compare;
        String result;
        compare = 1000000;
        if (number > compare) {
            DecimalFormat format = new DecimalFormat("0.###E0");
            result = format.format(number);
        } else {
            DecimalFormat simple = new DecimalFormat("###");
            result = simple.format(number);
        }
        return result;
    }
}
