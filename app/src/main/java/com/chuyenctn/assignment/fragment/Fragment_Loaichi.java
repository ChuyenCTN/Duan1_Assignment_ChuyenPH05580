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
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.adapter.Recycle_Adapter_Loaichi;
import com.chuyenctn.assignment.dao.LoaiChi_DAO;
import com.chuyenctn.assignment.model.Loaichi;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.util.ArrayList;

public class Fragment_Loaichi extends Fragment {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private Recycle_Adapter_Loaichi adapterLoaichi;
    private ArrayList<Loaichi> loaichiArrayList = new ArrayList<>();
    private LoaiChi_DAO loaiChiDao;


    private TextInputLayout tilThemTenLoai;
    private EditText edThemTenLoai;
    private TextInputLayout tilThemGhiChuLoai;
    private EditText edThemGhiChuLoai;
    private EditText edThemMaLoai;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaichi, container, false);
        floatingActionButton = view.findViewById(R.id.fbt_create_Loaichi);
        recyclerView = view.findViewById(R.id.rcv_Loaichi);
        loaiChiDao = new LoaiChi_DAO(new SqliteHelper(getContext()));
        adapterLoaichi = new Recycle_Adapter_Loaichi(getContext(), loaichiArrayList);
        recyclerView.setAdapter(adapterLoaichi);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                String title = "Thêm loại chi";
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
                edThemMaLoai = (EditText) dialog.findViewById(R.id.ed_themMaLoai);

                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (checkValidateForm() > 0) {
                            loaiChiDao = new LoaiChi_DAO(new SqliteHelper(getContext()));
                            Loaichi loaichi1 = new Loaichi(edThemMaLoai.getText().toString(),edThemTenLoai.getText().toString(),edThemGhiChuLoai.getText().toString());
                            loaiChiDao.insertLoaiChi(loaichi1);
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            onResume();
                        } else {
                            Toast.makeText(getContext(), "Dữ liệu trống, thêm thất bại", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onResume() {
        super.onResume();
        loaichiArrayList.clear();
        loaichiArrayList = (ArrayList<Loaichi>) loaiChiDao.getAllLoaiChi();
        adapterLoaichi.changeDataset(loaiChiDao.getAllLoaiChi());
    }

    public int checkValidateForm() {
        if (edThemMaLoai.getText().toString().isEmpty() || edThemGhiChuLoai.getText().toString().isEmpty() || edThemTenLoai.getText().toString().isEmpty()) {
            return -1;
        } else return 1;
    }
}
