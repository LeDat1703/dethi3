package com.example.dethi3.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dethi3.R;
import com.example.dethi3.adapter.SanPhamAdapter;
import com.example.dethi3.dao.SanPhamDAO;
import com.example.dethi3.dao.model.SanPham;
import com.example.dethi3.dao.model.SanPham;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QlspFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QlspFragment extends Fragment {
    ArrayList<SanPham> list;
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter adapter;
    View view;
    Context context;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QlspFragment() {
        // Required empty public constructor
    }

    public QlspFragment(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QlspFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QlspFragment newInstance(String param1, String param2) {
        QlspFragment fragment = new QlspFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qlsp, container, false);
        //RecyclerView
        RecyclerView rcvResult = view.findViewById(R.id.rcvResult);

        sanPhamDAO = new SanPhamDAO(context);
        list = new ArrayList<>();
        list.addAll(sanPhamDAO.getListSanPham());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rcvResult.setLayoutManager(linearLayoutManager);

        adapter = new SanPhamAdapter(list, context, sanPhamDAO);
        rcvResult.setAdapter(adapter);

        return view;
    }
}