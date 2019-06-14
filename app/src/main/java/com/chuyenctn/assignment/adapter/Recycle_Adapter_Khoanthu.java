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
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.dao.KhoanThu_DAO;
import com.chuyenctn.assignment.dao.LoaiThu_DAO;
import com.chuyenctn.assignment.model.Khoanthu;
import com.chuyenctn.assignment.model.Loaithu;
import com.chuyenctn.assignment.sqlite.SqliteHelper;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Recycle_Adapter_Khoanthu extends RecyclerView.Adapter<Recycle_Adapter_Khoanthu.ViewHolder> {
    private Context context;
    private ArrayList<Khoanthu> khoanthuArrayList;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private KhoanThu_DAO khoanThuDao;
    private LoaiThu_DAO loaiThuDao;
    private List<Loaithu> loaithuList = new ArrayList<>();
    private String tenloaithu;
    private TextInputLayout tilThemLoaiKhoan;
    private EditText edThemLoaiKhoan;

    public Recycle_Adapter_Khoanthu(Context context, ArrayList<Khoanthu> khoanthuArrayList) {
        this.context = context;
        this.khoanthuArrayList = khoanthuArrayList;
        khoanThuDao = new KhoanThu_DAO(new SqliteHelper(context));
    }

    @NonNull
    @Override
    public Recycle_Adapter_Khoanthu.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_khoanthu, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Recycle_Adapter_Khoanthu.ViewHolder viewHolder, final int i) {
        viewHolder.tvItemMaloaithuKhoanthu.setText("ID: " + khoanthuArrayList.get(i).getMaKhoanThu());
        Picasso.with(context).load(R.drawable.ic_item_khoanthu).resize(50,50).into(viewHolder.imgLogoKt);
        viewHolder.tvItemTenkhoanthu.setText(khoanthuArrayList.get(i).getTenKhoanThu());
        viewHolder.tvItemSotienkhoanthu.setText("Số tiền: " + khoanthuArrayList.get(i).getSotien() + " $");
        viewHolder.imgMoreKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popupMenu = new PopupMenu(context, viewHolder.imgMoreKhoan);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.popup_Sua:
                                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                                String title = "Sửa khoản thu";
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
                                edThemLoaiKhoan.setText(khoanthuArrayList.get(i).getTenLoaiThu());
                                edThemMaKhoan.setText(khoanthuArrayList.get(i).getMaKhoanThu());
                                edThemMaKhoan.setEnabled(false);
                                edThemGhiChuKhoan.setText(khoanthuArrayList.get(i).getGhichu());
                                edThemSoTienKhoan.setText(khoanthuArrayList.get(i).getSotien() + "");
                                edThemTenKhoan.setText(khoanthuArrayList.get(i).getTenKhoanThu());
                                loaiThuDao = new LoaiThu_DAO(new SqliteHelper(context));
                                loaithuList = loaiThuDao.getTenLoaiThu();
                                final String[] loaithu = loaiThuDao.gettenloaithu();
                                for (int i = 0; i < loaithuList.size(); i++) {
                                    loaithu[i] = loaithuList.get(i).getTenLoaiThu();
                                }
                                edThemLoaiKhoan.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                        builder.setTitle("Loại thu");
                                        builder.setSingleChoiceItems(loaithu, 1, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                edThemLoaiKhoan.setText(loaithu[i]);
                                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                    builder.setCancelable(true);
                                                    }
                                                });
                                            }
                                        });
                                        builder.show();
                                    }
                                });

                                try {
                                    edThemNgayKhoan.setText(sdf.format(sdf.parse(khoanthuArrayList.get(i).getNgay())));
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
                                        khoanThuDao = new KhoanThu_DAO(new SqliteHelper(context));
                                        try {
                                            Khoanthu khoanthu = new Khoanthu(edThemMaKhoan.getText().toString(), edThemTenKhoan.getText().toString(),edThemLoaiKhoan.getText().toString(),edThemNgayKhoan.getText().toString(), edThemGhiChuKhoan.getText().toString(),  Double.parseDouble(edThemSoTienKhoan.getText().toString()));
                                            khoanThuDao.updateKhoanThu(edThemMaKhoan.getText().toString(),edThemTenKhoan.getText().toString(),edThemLoaiKhoan.getText().toString(),Double.parseDouble(edThemSoTienKhoan.getText().toString()),sdf.parse(edThemNgayKhoan.getText().toString()),edThemGhiChuKhoan.getText().toString());
                                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();

                                            khoanthuArrayList.clear();
                                            khoanthuArrayList = (ArrayList<Khoanthu>) khoanThuDao.getAllKhoanThu();
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
                                khoanThuDao = new KhoanThu_DAO(new SqliteHelper(context));
                                if (khoanThuDao.deleteKhoanThu(khoanthuArrayList.get(i).getMaKhoanThu()) > 0) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                }
                                khoanthuArrayList.remove(i);
                                notifyDataSetChanged();
                                break;
                            case R.id.popup_chitiet:
                                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                String title1 = "Khoản thu";
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

                                tvShowtenKhoan.setText(khoanthuArrayList.get(i).getTenKhoanThu());
                                tvShowIdKhoan.setText("ID: " + khoanthuArrayList.get(i).getMaKhoanThu());
                                tvShowLoaiKhoan.setText("Loại thu: " + khoanthuArrayList.get(i).getTenLoaiThu());
                                tvShowSotienKhoan.setText("Số tiền: " + khoanthuArrayList.get(i).getSotien() + " $");
                                tvShowNgayKhoan.setText("Ngày thu: " + khoanthuArrayList.get(i).getNgay());
                                tvShowGhichuKhoan.setText("Ghi chú: " + khoanthuArrayList.get(i).getGhichu());

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
        return khoanthuArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLogoKt;
        private TextView tvItemTenkhoanthu;
        private TextView tvItemMaloaithuKhoanthu;
        private TextView tvItemSotienkhoanthu;

        private ImageView imgMoreKhoan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogoKt = (ImageView) itemView.findViewById(R.id.img_logo_kt);
            tvItemTenkhoanthu = (TextView) itemView.findViewById(R.id.tv_item_tenkhoanthu);
            tvItemMaloaithuKhoanthu = (TextView) itemView.findViewById(R.id.tv_item_maloaithu_khoanthu);
            tvItemSotienkhoanthu = (TextView) itemView.findViewById(R.id.tv_item_sotienkhoanthu);
            imgMoreKhoan = (ImageView) itemView.findViewById(R.id.img_more_khoan);


        }
    }

    public void changeDataSet(List<Khoanthu> items) {
        this.khoanthuArrayList = (ArrayList<Khoanthu>) items;
        notifyDataSetChanged();
    }
}
