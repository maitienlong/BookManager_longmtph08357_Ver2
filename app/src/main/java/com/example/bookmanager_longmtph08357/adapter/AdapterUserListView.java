package com.example.bookmanager_longmtph08357.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookmanager_longmtph08357.R;
import com.example.bookmanager_longmtph08357.dao.UserDAO;
import com.example.bookmanager_longmtph08357.model.User;


import java.util.List;

public class AdapterUserListView extends BaseAdapter {

    private UserDAO userDAO;
    private Context context;
    private List<User> userList;

    public AdapterUserListView(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row_user,null,false);

            viewHolder.tvUsername = convertView.findViewById(R.id.tvUsername);
            viewHolder.tvPhone = convertView.findViewById(R.id.tvPhone);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvUsername.setText(userList.get(i).username + "");
        viewHolder.tvPhone.setText(userList.get(i).phone + "");


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //--------------------------------------------------------------------------
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_user_detail);

                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                //--------------------------------------------------------------------------


                TextView tvUsernameDetail = dialog.findViewById(R.id.tvUsernameDetail);
                TextView tvPhoneDetail = dialog.findViewById(R.id.tvPhoneDetail);
                TextView tvFullnameDetail = dialog.findViewById(R.id.tvFullnameDetail);

                tvUsernameDetail.setText(userList.get(i).getUsername() + "");
                tvPhoneDetail.setText(userList.get(i).getPhone());
                tvFullnameDetail.setText(userList.get(i).getFullname());


                Button btnDeleteUser = dialog.findViewById(R.id.btnDeleteUser);
                btnDeleteUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Bạn có muốn xóa người dùng này không").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                UserDAO userDAO = new UserDAO(context);
                                userDAO.deleteUser(userList.get(i).getUsername());
                                userList.remove(i);
                                notifyDataSetChanged();
                                dialog.cancel();

                            }
                        }).setNegativeButton("Thôi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {

                            }
                        });
                        builder.show();


                    }
                });


            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView tvUsername, tvPhone;
        Button btnEditUser;
    }
}
