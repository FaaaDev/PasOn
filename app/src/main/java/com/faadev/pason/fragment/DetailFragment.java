package com.faadev.pason.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.faadev.pason.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DetailFragment extends BottomSheetDialogFragment {

    private Button submit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);

        _init(root);
        _prep();

        return root;
    }

    private void _init(View root){
        submit = root.findViewById(R.id.submit);
    }

    private void _prep(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessFragment processFragment = new ProcessFragment();
                processFragment.show(getFragmentManager(), processFragment.getTag());
                dismiss();
            }
        });
    }
}
