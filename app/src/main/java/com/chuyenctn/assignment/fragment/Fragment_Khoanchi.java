package com.chuyenctn.assignment.fragment;

import android.app.DatePickerDialog;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.adapter.Recycle_Adapter_Khoanchi;
import com.chuyenctn.assignment.dao.KhoanChi_DAO;
import com.chuyenctn.assignment.dao.LoaiChi_DAO;
import com.chuyenctn.assignment.model.Khoanchi;
import com.chuyenctn.assignment.model.Loaichi;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Fragment_Khoanchi extends Fragment {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private Recycle_Adapter_Khoanchi adapterKhoanchi;
    private KhoanChi_DAO khoanChiDao;
    private List<Khoanchi> dsKhoanchi = new ArrayList<>();

    private LoaiChi_DAO loaiChiDao;
    private List<Loaichi> loaichiList = new ArrayList<>();
    private String tenloaichi;

    private TextInputLayout tilThemTenKhoan;
    private EditText edThemTenKhoan;
    private TextInputLayout tilThemTenLoaiKhoan;
    private Spinner spnThemTenLoaiKhoan;
    private TextInputLayout tilThemSoTienKhoan;
    private EditText edThemSoTienKhoan;
    private TextInputLayout tilThemNgayKhoan;
    private EditText edThemNgayKhoan;
    private TextInputLayout tilThemGhiChuKhoan;
    private EditText edThemGhiChuKhoan;
    private TextInputLayout tilThemMAKhoan;
    private EditText edThemMaKhoan;
    private TextInputLayout tilThemLoaiKhoan;
    private EditText edThemLoaiKhoan;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoanchi, container, false);
        floatingActionButton = view.findViewById(R.id.fbt_create_Khoanchi);
        recyclerView = view.findViewById(R.id.rcv_Khoanchi);
        khoanChiDao = new KhoanChi_DAO(new SqliteHelper(getContext()));
        try {
            dsKhoanchi = khoanChiDao.getAllKhoanChi();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapterKhoanchi = new Recycle_Adapter_Khoanchi(getContext(), (ArrayList<Khoanchi>) dsKhoanchi);
        recyclerView.setAdapter(adapterKhoanchi);
        onResume();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                String title = "Thêm khoản chi";
                // Set Color for Title
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLACK);
                SpannableStringBuilder stringBuilder = new SpannableStringBuilder(title);
                stringBuilder.setSpan(foregroundColorSpan, 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                // Set Title
                builder.setTitle(stringBuilder);

                final View dialog = inflater.inflate(R.layout.dialog_add_khoan, null);
                builder.setView(dialog);

                tilThemMAKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themMAKhoan);
                edThemMaKhoan = (EditText) dialog.findViewById(R.id.ed_themMaKhoan);
                tilThemTenKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themTenKhoan);
                edThemTenKhoan = (EditText) dialog.findViewById(R.id.ed_themTenKhoan);

                tilThemLoaiKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themLoaiKhoan);
                edThemLoaiKhoan = (EditText) dialog.findViewById(R.id.ed_themLoaiKhoan);
                tilThemSoTienKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themSoTienKhoan);
                edThemSoTienKhoan = (EditText) dialog.findViewById(R.id.ed_themSoTienKhoan);
                tilThemNgayKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themNgayKhoan);
                edThemNgayKhoan = (EditText) dialog.findViewById(R.id.ed_themNgayKhoan);
                tilThemGhiChuKhoan = (TextInputLayout) dialog.findViewById(R.id.til_themGhiChuKhoan);
                edThemGhiChuKhoan = (EditText) dialog.findViewById(R.id.ed_themGhiChuKhoan);
                edThemTenKhoan.setHint("");
                edThemLoaiKhoan.setHint("");
                tilThemLoaiKhoan.setHint("Loại chi");
                tilThemTenKhoan.setHint("Tên khoản chi");
                edThemMaKhoan.setText("");
                tilThemMAKhoan.setHint("Mã khoản chi");

                loaiChiDao = new LoaiChi_DAO(new SqliteHelper(getContext()));
                final String[] loaichi =  loaiChiDao.gettenloaichi();
                loaichiList=loaiChiDao.getTenLoaiChi();
                for (int i=0; i<loaichiList.size();i++){
                    loaichi[i]=loaichiList.get(i).getTenLoaiChi();
                }
                edThemLoaiKhoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Loại chi");
                        builder.setSingleChoiceItems(loaichi, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                edThemLoaiKhoan.setText(loaichi[i]);
                                builder.setCancelable(true);
                            }
                        });
                        builder.show();
                    }
                });
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
                                setDate(calendar1);
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        khoanChiDao = new KhoanChi_DAO(new SqliteHelper(getContext()));
                        try {
                            Khoanchi khoanchi = new Khoanchi(edThemMaKhoan.getText().toString(), edThemTenKhoan.getText().toString(), edThemLoaiKhoan.getText().toString(), edThemNgayKhoan.getText().toString(),edThemGhiChuKhoan.getText().toString(), (edThemSoTienKhoan.getText().toString()));
                             khoanChiDao.insertKhoanChi(khoanchi)  ;
                                Toast.makeText(getContext(), "Thêm Thành công", Toast.LENGTH_SHORT).show();
                                onResume();

                        } catch (Exception e) {
                            Log.d("Error: ", e.toString());
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

    private void setDate(Calendar calendar) {
        edThemNgayKhoan.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public void onResume() {
        super.onResume();
        dsKhoanchi.clear();
        try {
            dsKhoanchi = khoanChiDao.getAllKhoanChi();
            adapterKhoanchi.changeDataset(khoanChiDao.getAllKhoanChi());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
