package com.example.dethi3.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dethi3.dao.model.SanPhamDAO;
import com.example.dethi3.R;
import com.example.dethi3.dao.model.SanPhamDAO;
import com.example.finalexamination.dao.model.SanPham;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder>  {
    private ArrayList<SanPham> list;
    private final Context context;
    private SanPhamDAO sanPhamDAO;

    public SanPhamAdapter(ArrayList<SanPham> list, Context context, SanPhamDAO sanPhamDAO) {
        this.list = list;
        this.context = context;
        this.sanPhamDAO = sanPhamDAO;
    }

    @NonNull
    @Override
    public SanPhamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.ViewHolder holder, int position) {
        holder.tvGiaSP.setText(list.get(holder.getAdapterPosition()).getGiaSP() + " VND  - ");
        holder.tvSoLuong.setText("SL: " + list.get(holder.getAdapterPosition()).getSoLuong());
        holder.tvTenSP.setText(list.get(holder.getAdapterPosition()).getTenSP());

        //lấy ảnh từ drawable
        int imgId = ((Activity)context).getResources().getIdentifier(
                list.get(position).getImage(),"drawable",
                ((Activity)context).getPackageName());
        holder.ivImage.setImageResource(imgId);

        //ivDelete
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CẢNH BÁO");
                builder.setMessage("Bạn có chắc muốn xóa \"" + list.get(holder.getAdapterPosition()).getTenSP() + "\" không?");
                builder.setIcon(R.drawable.baseline_warning_24);

                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = list.get(holder.getAdapterPosition()).getId();
                        boolean check = sanPhamDAO.deleteSanPham(id);
                        if (check) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(sanPhamDAO.getListSanPham());
                            notifyItemRemoved(holder.getAdapterPosition());
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


        //ivEdit
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.layout_edit, null, false);
                builder.setView(view);
                Dialog dialog = builder.create();

                //Khai báo và ánh xạ
                TextInputEditText edtTenSP = view.findViewById(R.id.edtTenSP);
                TextInputEditText edtGiaSP = view.findViewById(R.id.edtGiaSP);
                TextInputEditText edtSoLuong = view.findViewById(R.id.edtSoLuong);
                TextInputEditText edtImage = view.findViewById(R.id.edtImage);
                Button btnEdit = view.findViewById(R.id.btnUpdate);
                Button btnCancel = view.findViewById(R.id.btnCancel);

                //Set dữ liệu lên các TextInputEditText
                edtTenSP.setText(list.get(holder.getAdapterPosition()).getTenSP());
                edtGiaSP.setText(list.get(holder.getAdapterPosition()).getGiaSP() + "");
                edtSoLuong.setText(list.get(holder.getAdapterPosition()).getSoLuong() + "");
                edtImage.setText(list.get(holder.getAdapterPosition()).getImage() + "");

                //btnEdit
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenSP = edtTenSP.getText().toString();
                        int giaSP = Integer.valueOf(edtGiaSP.getText().toString());
                        int id = list.get(holder.getAdapterPosition()).getId();
                        int soLuong = Integer.valueOf(edtSoLuong.getText().toString());
                        String image = edtImage.getText().toString();

                        SanPham sanPham = new SanPham(id, tenSP, giaSP, soLuong, image);

                        boolean check = sanPhamDAO.updateSanPham(sanPham);
                        if (check) {
                            Toast.makeText(context, "Cập nhập thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(sanPhamDAO.getListSanPham());
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Cập nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //btnCancel
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSP, tvGiaSP, tvSoLuong;
        ImageView ivDelete, ivEdit, ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGiaSP = itemView.findViewById(R.id.tvGiaSP);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            ivImage = itemView.findViewById(R.id.ivImage);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
}
