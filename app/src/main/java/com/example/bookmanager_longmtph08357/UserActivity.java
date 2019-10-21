package com.example.bookmanager_longmtph08357;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_longmtph08357.adapter.AdapterUserListView;
import com.example.bookmanager_longmtph08357.dao.UserDAO;
import com.example.bookmanager_longmtph08357.model.User;


import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private ListView lvListUser;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userDAO = new UserDAO(UserActivity.this);

        lvListUser = findViewById(R.id.lvListUser);
        userDAO = new UserDAO(UserActivity.this);
        List<User> userList = userDAO.getAll();
        AdapterUserListView adapterUserListView = new AdapterUserListView( UserActivity.this, userList);
        lvListUser.setAdapter(adapterUserListView);




    }

    public void btnAddUser(View view) {
        dialogAddUser();
    }

    public void dialogAddUser() {
// -----------------------------------
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_user_add);


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

// -------------------BUTTON CANCEL----------------

        Button btnCancelAddUser = (Button) dialog.findViewById(R.id.btnCancelAddUser);

        btnCancelAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

// -----------------------BUTTON ADD------------

        Button btnAddNewUser = (Button) dialog.findViewById(R.id.btnAddNewUser);
        btnAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText edtUsername = dialog.findViewById(R.id.edtUsername);
                EditText edtPassword = dialog.findViewById(R.id.edtPassword);
                EditText edtRePassword = dialog.findViewById(R.id.edtRePassword);
                EditText edtPhone = dialog.findViewById(R.id.edtPhone);
                EditText edtFullname = dialog.findViewById(R.id.edtFullname);


                try {

                    if (edtUsername.getText().toString().trim().equals("")) {
                        Toast.makeText(UserActivity.this, "Username đang bỏ trống", Toast.LENGTH_SHORT).show();
                        edtUsername.requestFocus();
                        return;
                    } else if (edtPassword.getText().toString().trim().equals("")) {
                        Toast.makeText(UserActivity.this, "Pssword đang bỏ trống", Toast.LENGTH_SHORT).show();
                        edtPassword.requestFocus();
                        return;

                    } else if (!edtRePassword.getText().toString().trim().equals(edtPassword.getText().toString())) {
                        Toast.makeText(UserActivity.this, "Mật khẩu nhập lại phải giống nhau", Toast.LENGTH_SHORT).show();
                        edtPassword.requestFocus();
                        return;

                    }else if (edtPhone.getText().toString().trim().equals("")) {
                        Toast.makeText(UserActivity.this, "Số điện thoại không được để trống", Toast.LENGTH_SHORT).show();
                        edtPhone.requestFocus();
                        return;
                    } else if (edtFullname.getText().toString().trim().equals("")) {
                        Toast.makeText(UserActivity.this, "Họ và Tên không được để trống", Toast.LENGTH_SHORT).show();
                        edtFullname.requestFocus();
                        return;
                    } else {
                        List<User> users = new ArrayList<>();
                        User user = new User();
                        user.username = Integer.parseInt(edtUsername.getText().toString().trim());
                        user.password = edtPassword.getText().toString().trim();
                        user.phone = edtPhone.getText().toString().trim();
                        user.fullname = edtPassword.getText().toString().trim();
                        users.add(user);

                        for (int i = 0; i < users.size(); i++) {
                            if (edtUsername.getText().toString().trim().equals(users.get(i).username)) {
                                Toast.makeText(UserActivity.this, "ID DA CO", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        long result = userDAO.insertCar(user);
                        if (result > 0) {
                            Toast.makeText(UserActivity.this, "Bạn đã thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(UserActivity.this, "Lỗi thêm người dùng", Toast.LENGTH_SHORT).show();
                }


                lvListUser = findViewById(R.id.lvListUser);
                userDAO = new UserDAO(UserActivity.this);
                List<User> userList = userDAO.getAll();
                AdapterUserListView adapterUserListView = new AdapterUserListView(UserActivity.this, userList);
                lvListUser.setAdapter(adapterUserListView);
            }
        });

    }
}
