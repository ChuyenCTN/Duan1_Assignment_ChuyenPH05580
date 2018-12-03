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
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.dao.LoaiThu_DAO;
import com.chuyenctn.assignment.model.Loaithu;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class Recycle_Adapter_Loaithu extends RecyclerView.Adapter<Recycle_Adapter_Loaithu.ViewHolder> {
    private Context context;
    private ArrayList<Loaithu> loaithuArrayList;
    private LoaiThu_DAO loaiThu_dao;

    public Recycle_Adapter_Loaithu(Context context, ArrayList<Loaithu> loaithuArrayList) {
        this.context = context;
        this.loaithuArrayList = loaithuArrayList;
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
        viewHolder.tvItemTenloaithu.setText(loaithuArrayList.get(i).getTenLoaiThu());
        viewHolder.tvItemGhichuloaithu.setText("Ghi chú: " + loaithuArrayList.get(i).getGhichu());
        viewHolder.tvItemMaloaithu.setText("Mã: " + loaithuArrayList.get(i).getMaLoaiThu());
        viewHolder.img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, viewHolder.img_more);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.popup_Sua:
                                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                                String title = "Sửa loại thu";

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

                                edThemGhiChuLoai.setText(loaithuArrayList.get(i).getGhichu());
                                edThemMaLoai.setText(loaithuArrayList.get(i).getMaLoaiThu());
                                edThemTenLoai.setText(loaithuArrayList.get(i).getTenLoaiThu());
                                edThemMaLoai.setEnabled(false);

                                mBuilder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        loaiThu_dao = new LoaiThu_DAO(new SqliteHelper(context));
                                        Loaithu loaithu=new Loaithu(edThemMaLoai.getText().toString(),edThemTenLoai.getText().toString(),edThemGhiChuLoai.getText().toString());
                                        loaiThu_dao.updateLoaiThu(loaithu);
                                        loaithuArrayList.clear();
                                        loaithuArrayList = (ArrayList<Loaithu>) loaiThu_dao.getAllLoaiThu();
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
                                loaiThu_dao = new LoaiThu_DAO(new SqliteHelper(context));
                                loaiThu_dao.deleteLoaiThu(loaithuArrayList.get(i).getMaLoaiThu());
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                loaithuArrayList.remove(i);
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
        return loaithuArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemTenloaithu, tvItemMaloaithu;
        private TextView tvItemGhichuloaithu;
        private ImageView img_more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemMaloaithu = itemView.findViewById(R.id.tv_item_maloaithu);
            tvItemTenloaithu = (TextView) itemView.findViewById(R.id.tv_item_tenloaithu);
            tvItemGhichuloaithu = (TextView) itemView.findViewById(R.id.tv_item_ghichuloaithu);
            img_more = itemView.findViewById(R.id.btn_more_loaithu);
        }
    }
    public void changeDataset(List<Loaithu> items) {
        this.loaithuArrayList = (ArrayList<Loaithu>) items;
        notifyDataSetChanged();
    }


}
