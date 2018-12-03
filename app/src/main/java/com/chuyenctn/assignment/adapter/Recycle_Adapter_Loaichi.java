package com.chuyenctn.assignment.adapter;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.dao.LoaiChi_DAO;
import com.chuyenctn.assignment.fragment.Fragment_Loaichi;
import com.chuyenctn.assignment.model.Loaichi;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class Recycle_Adapter_Loaichi extends RecyclerView.Adapter<Recycle_Adapter_Loaichi.ViewHolder> {
    private Context context;
    private ArrayList<Loaichi> loaichiArrayList;
    private LoaiChi_DAO loaiChi_dao;


    public Recycle_Adapter_Loaichi(Context context, ArrayList<Loaichi> loaichiArrayList) {
        this.context = context;
        this.loaichiArrayList = loaichiArrayList;
        loaiChi_dao = new LoaiChi_DAO(new SqliteHelper(context));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_loaithu, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tvItemTenloaithu.setText(loaichiArrayList.get(i).getTenLoaiChi());
        viewHolder.tvItemGhichuloaithu.setText("Ghi chú: " + loaichiArrayList.get(i).getGhichu());
        viewHolder.tvItemMaLoaiThu.setText("Mã: " + loaichiArrayList.get(i).getMaLoaiChi());
        viewHolder.imgLogoLt.setImageResource(R.drawable.item_loaichi);
        viewHolder.btnMoreLoaithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, viewHolder.btnMoreLoaithu);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.popup_Sua:
                                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                                String title = "Sửa loại chi";

                                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLACK);
                                SpannableStringBuilder stringBuilder = new SpannableStringBuilder(title);
                                stringBuilder.setSpan(foregroundColorSpan, 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                                mBuilder.setTitle(stringBuilder);

                                View dialog = View.inflate(context, R.layout.dialog_add_loai, null);
                                mBuilder.setView(dialog);

                                final TextInputLayout tilThemTenLoai = (TextInputLayout) dialog.findViewById(R.id.til_themTenLoai);
                                final EditText edThemTenLoai = (EditText) dialog.findViewById(R.id.ed_themTenLoai);
                                final TextInputLayout tilThemGhiChuLoai = (TextInputLayout) dialog.findViewById(R.id.til_themGhiChuLoai);
                                final EditText edThemGhiChuLoai = (EditText) dialog.findViewById(R.id.ed_themGhiChuLoai);
                                final EditText edThemMaLoai = (EditText) dialog.findViewById(R.id.ed_themMaLoai);
                                edThemTenLoai.setText(loaichiArrayList.get(i).getTenLoaiChi());
                                edThemMaLoai.setText(loaichiArrayList.get(i).getMaLoaiChi());
                                edThemMaLoai.setEnabled(false);
                                edThemGhiChuLoai.setText(loaichiArrayList.get(i).getGhichu());

                                mBuilder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        loaiChi_dao = new LoaiChi_DAO(new SqliteHelper(context));
                                        Loaichi loaichi=new Loaichi(edThemMaLoai.getText().toString(),edThemTenLoai.getText().toString(),edThemGhiChuLoai.getText().toString());
                                        loaiChi_dao.updateLoaiChi(loaichi);
                                        loaichiArrayList.clear();
                                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                        loaichiArrayList= (ArrayList<Loaichi>) loaiChi_dao.getAllLoaiChi();
                                        notifyDataSetChanged();
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
                                loaiChi_dao = new LoaiChi_DAO(new SqliteHelper(context));
                                loaiChi_dao.deleteLoaiChi(loaichiArrayList.get(i).getMaLoaiChi());
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                loaichiArrayList.remove(i);
                                notifyDataSetChanged();
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
        return loaichiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLogoLt;
        private TextView tvItemTenloaithu;
        private TextView tvItemGhichuloaithu, tvItemMaLoaiThu;
        private ImageView btnMoreLoaithu;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogoLt = (ImageView) itemView.findViewById(R.id.img_logo_lt);
            tvItemTenloaithu = (TextView) itemView.findViewById(R.id.tv_item_tenloaithu);
            tvItemGhichuloaithu = (TextView) itemView.findViewById(R.id.tv_item_ghichuloaithu);
            btnMoreLoaithu = (ImageView) itemView.findViewById(R.id.btn_more_loaithu);
            tvItemMaLoaiThu = (TextView) itemView.findViewById(R.id.tv_item_maloaithu);

        }
    }

    public void changeDataset(List<Loaichi> items) {
        this.loaichiArrayList = (ArrayList<Loaichi>) items;
        notifyDataSetChanged();

    }
}
