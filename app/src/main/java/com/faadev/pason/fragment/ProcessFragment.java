package com.faadev.pason.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.faadev.pason.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ProcessFragment extends BottomSheetDialogFragment {

    private ImageView exit;
    private EditText jumlah, alamat;
    private TextView total;
    private Button pesan;
    private int ammount = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_process, container, false);

        _init(root);
        _prep();

        return root;
    }

    private void _init(View root){
        exit = root.findViewById(R.id.exit);
        jumlah = root.findViewById(R.id.jumlah);
        alamat = root.findViewById(R.id.alamat);
        total = root.findViewById(R.id.total);
        pesan = root.findViewById(R.id.pesan);
    }

    private void _prep(){
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //ammount = Integer.parseInt(String.valueOf(dm.getHarga())) * Integer.parseInt(jumlah.getText().toString());
        total.setText("RP. "+ammount);
        jumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s!=null){
                    //ammount = Integer.parseInt(String.valueOf(dm.getHarga())) * (s.toString().equals("") ? 0 : Integer.parseInt(String.valueOf(s)));

                    //Toast.makeText(getContext(), "TOTAL : "+ammount, Toast.LENGTH_LONG).show();

                    total.setText("Rp. "+ammount);
                }
            }
        });

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ammount==0 || TextUtils.isEmpty(alamat.getText())){

                    if (ammount==0){
                        jumlah.setError("Jumlah tidak boleh kosong");
                    }
                    if (TextUtils.isEmpty(alamat.getText())){
                        alamat.setError("Alamat Harus Diisi");
                    }
                } else {

                }
            }
        });
    }

}
