package com.ramirez.proyecto.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ramirez.proyecto.Adaptadores.BebidasAdapter;
import com.ramirez.proyecto.Adaptadores.CategoriaAdapter;
import com.ramirez.proyecto.Adaptadores.PpusasAdapter;
import com.ramirez.proyecto.R;
import com.ramirez.proyecto.RoomArchitecture.Entities.BebidaEntity;
import com.ramirez.proyecto.RoomArchitecture.ViewModel.BebidasViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddVentafrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddVentafrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddVentafrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public RecyclerView rv;
    public BebidasAdapter adapter;
    public PpusasAdapter adapter2;
    public CategoriaAdapter adapter3;
    public LinearLayoutManager lManager;
    public List<BebidaEntity> list;
    public BebidasViewModel nvmodel;
    public Context contexto;
    private Button bc,bp,bb;

    private OnFragmentInteractionListener mListener;

    public AddVentafrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddVentafrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AddVentafrag newInstance(String param1, String param2) {
        AddVentafrag fragment = new AddVentafrag();
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
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_add_ventafrag, container, false);

        bc = v.findViewById(R.id.button_Combo);
        bp = v.findViewById(R.id.button_porcion);
        bb = v.findViewById(R.id.button_bebida);
        rv = v.findViewById(R.id.addregrecycler);
        lManager= new LinearLayoutManager(getActivity());
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nvmodel = ViewModelProviders.of(AddVentafrag.this).get(BebidasViewModel.class);
                nvmodel.getAllBebidas().observe(AddVentafrag.this, new Observer<List<BebidaEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<BebidaEntity> list) {
                        adapter = new BebidasAdapter(list, getActivity());
                        lManager= new LinearLayoutManager(getActivity());
                        rv.setLayoutManager(lManager);
                        rv.setAdapter(adapter);
                    }
                });
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
      //  mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
