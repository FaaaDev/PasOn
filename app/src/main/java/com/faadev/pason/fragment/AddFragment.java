package com.faadev.pason.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.faadev.pason.R;
import com.faadev.pason.adapter.DismisListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import biz.laenger.android.vpbs.ViewPagerBottomSheetBehavior;

public class AddFragment extends BottomSheetDialogFragment {

    DismisListener dismisListener;
    private ImageView exit;
    private ImageButton add, caim;
    private ImageView img;
    private Uri uri;
    private String check = "";
    private String cameraFilePath, url, message, imageurl;
    private EditText productname, price, desc;
    private Button submit;
    private Spinner spinner_category;
    ArrayList<String> list_category = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add, container, false);

        _init(root);
        _prep();

        return root;
    }

    private void _init(View root){
        exit = root.findViewById(R.id.exit);
        add = root.findViewById(R.id.add);
        caim = root.findViewById(R.id.caim);
        img = root.findViewById(R.id.img);
        productname = root.findViewById(R.id.productname);
        price = root.findViewById(R.id.price);
        desc = root.findViewById(R.id.desc);
        spinner_category = root.findViewById(R.id.spinner_kategori);
        submit = root.findViewById(R.id.submit);
    }

    private void _prep(){
        list_category.add("Pilih Kategori");
        list_category.add("Sayuran");
        list_category.add("Daging");
        list_category.add("Ikan");
        list_category.add("Buah");
        list_category.add("Bumbu");
        list_category.add("Herbal");
        list_category.add("Jajanan");

        adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_layout, R.id.txt_item, list_category);
        spinner_category.setAdapter(adapter);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        caim.setVisibility(View.GONE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(getContext(),
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(getContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA},
                            1);

                } else {
                    try {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(Objects.requireNonNull(getContext()), "com.faadev.pason", createImageFile()));
                        startActivityForResult(intent, 1);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        caim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageURI(null);
                uri = null;
                caim.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
                check = "";
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _validasi();
            }
        });
    }

    private void _validasi(){
        if (uri==null){
            Toast.makeText(getContext(), "Tambahkan Foto terlebih dahulu", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(productname.getText()) || "Pilih Kategori".equals(spinner_category.getSelectedItem())
                || TextUtils.isEmpty(price.getText()) || TextUtils.isEmpty(desc.getText())){
            if (TextUtils.isEmpty(productname.getText())){
                productname.setError("Silahkan isi nama produk");
                productname.requestFocus();
            }

            if ("Pilih Kategori".equals(spinner_category.getSelectedItem())){
                Toast.makeText(getContext(), "Pilih kategori terlebih dahulu", Toast.LENGTH_LONG).show();
            }

            if (TextUtils.isEmpty(price.getText())){
                price.setError("Silahkan isi harga produk");
                price.requestFocus();
            }

            if (TextUtils.isEmpty(desc.getText())){
                desc.setError("Silahkan isi deskripsi produk");
                desc.requestFocus();
            }
        } else {

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_DCIM);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        cameraFilePath = "file://" + image.getAbsolutePath();
        return image;
    }

    public void setDismisListener(DismisListener dismisListener) {
        this.dismisListener = dismisListener;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        dismisListener.onDismisDialog();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 1:
                    uri = Uri.parse(cameraFilePath);
                    img.setImageURI(uri);
                    caim.setVisibility(View.VISIBLE);
                    add.setVisibility(View.GONE);
                    check = "ok";
                    break;
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

