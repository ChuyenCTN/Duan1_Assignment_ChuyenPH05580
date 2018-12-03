package com.chuyenctn.assignment.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.adapter.Recycle_Adapter_Loaithu;
import com.chuyenctn.assignment.dao.LoaiThu_DAO;
import com.chuyenctn.assignment.model.Loaithu;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.util.ArrayList;

public class Fragment_Loaithu extends Fragment {
    FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private ArrayList<Loaithu> loaithuArrayList = new ArrayList<>();
    private Recycle_Adapter_Loaithu adapterLoaithu;
    private LoaiThu_DAO loaiThu_dao;

    private TextInputLayout tilThemTenLoai;
    private EditText edThemTenLoai;
    private TextInputLayout tilThemGhiChuLoai;
    private EditText edThemGhiChuLoai;
    private TextInputLayout tilThemMaLoai;
    private EditText edThemMaLoai;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaithu, container, false);
        floatingActionButton = view.findViewById(R.id.fbt_create_Loaithu);
        recyclerView = view.findViewById(R.id.rcv_Loaithu);
        loaiThu_dao = new LoaiThu_DAO(new SqliteHelper(getContext()));
        adapterLoaithu = new Recycle_Adapter_Loaithu(getContext(), loaithuArrayList);
        recyclerView.setAdapter(adapterLoaithu);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                String title = "Thêm loại thu";
                // Set Color for Title
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLACK);
                SpannableStringBuilder stringBuilder = new SpannableStringBuilder(title);
                stringBuilder.setSpan(foregroundColorSpan, 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                // Set Title
                builder.setTitle(stringBuilder);

                final View dialog = inflater.inflate(R.layout.dialog_add_loai, null);
                builder.setView(dialog);
                tilThemTenLoai = (TextInputLayout) dialog.findViewById(R.id.til_themTenLoai);
                edThemTenLoai = (EditText) dialog.findViewById(R.id.ed_themTenLoai);
                tilThemGhiChuLoai = (TextInputLayout) dialog.findViewById(R.id.til_themGhiChuLoai);
                edThemGhiChuLoai = (EditText) dialog.findViewById(R.id.ed_themGhiChuLoai);
                tilThemMaLoai = (TextInputLayout) dialog.findViewById(R.id.til_themMaLoai);
                edThemMaLoai = (EditText) dialog.findViewById(R.id.ed_themMaLoai);
                edThemMaLoai.setHint("");
                tilThemMaLoai.setHint("Mã loại thu");
                edThemTenLoai.setHint("");
                tilThemTenLoai.setHint("Tên loại thu");

                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (checkValidateForm() > 0) {
                            loaiThu_dao = new LoaiThu_DAO(new SqliteHelper(getContext()));
                            Loaithu loaithu = new Loaithu(edThemMaLoai.getText().toString(), edThemTenLoai.getText().toString(), edThemGhiChuLoai.getText().toString());
                            loaiThu_dao.insertLoaiThu(loaithu);
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            onResume();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        builder.setCancelable(true);
                    }
                });
                builder.show();
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;

    }

    public int checkValidateForm() {
        if (edThemMaLoai.getText().toString().isEmpty() || edThemTenLoai.getText().toString().isEmpty() || edThemGhiChuLoai.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }

    @Override
    public void onResume() {
        super.onResume();
        loaithuArrayList.clear();
        loaithuArrayList = (ArrayList<Loaithu>) loaiThu_dao.getAllLoaiThu();
        adapterLoaithu.changeDataset(loaiThu_dao.getAllLoaiThu());
    }
}
