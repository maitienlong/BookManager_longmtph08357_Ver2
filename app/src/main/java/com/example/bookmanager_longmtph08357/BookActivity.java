package com.example.bookmanager_longmtph08357;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanager_longmtph08357.adapter.AdapterTheLoaiRecycleView;
import com.example.bookmanager_longmtph08357.adapter.AdapterUserListView;
import com.example.bookmanager_longmtph08357.dao.TheLoaiDAO;
import com.example.bookmanager_longmtph08357.dao.UserDAO;
import com.example.bookmanager_longmtph08357.model.TheLoai;
import com.example.bookmanager_longmtph08357.model.User;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
    }

    public void btnThemSach(View view) {
        dialogAddBook();
    }


    public void dialogAddBook() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sach_add);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        // -------------------BUTTON CANCEL----------------

        Button btnCancelNewSach = (Button) dialog.findViewById(R.id.btnCancelNewSach);

        btnCancelNewSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        // ---------------------BUTTON ADD------------

        Button btnAddNewSach = (Button) dialog.findViewById(R.id.btnAddNewSach);
        btnAddNewSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }


}
