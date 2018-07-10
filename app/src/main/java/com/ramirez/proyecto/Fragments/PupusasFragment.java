package com.ramirez.proyecto.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramirez.proyecto.Adaptadores.BebidasAdapter;
import com.ramirez.proyecto.Adaptadores.CategoriaAdapter;
import com.ramirez.proyecto.Adaptadores.DesayunoAdapter;
import com.ramirez.proyecto.Adaptadores.PpusasAdapter;
import com.ramirez.proyecto.Categoria;
import com.ramirez.proyecto.R;
import com.ramirez.proyecto.RoomArchitecture.Entities.BebidaEntity;
import com.ramirez.proyecto.RoomArchitecture.Entities.DesayunoEntity;
import com.ramirez.proyecto.RoomArchitecture.Entities.PupusaEntity;
import com.ramirez.proyecto.RoomArchitecture.ViewModel.BebidasViewModel;
import com.ramirez.proyecto.RoomArchitecture.ViewModel.DesayunoViewModel;
import com.ramirez.proyecto.RoomArchitecture.ViewModel.PupusasViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PupusasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PupusasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PupusasFragment extends Fragment {
   /* public RecyclerView rv;
    public CategoriaAdapter adapter;
    public GridLayoutManager lManager;
    public List<Categoria> list;
    SwipeRefreshLayout swipeRefreshLayout;
    public Context contexto;*/
   public RecyclerView rv;
    public DesayunoAdapter adapter;
    public LinearLayoutManager lManager;
    public List<DesayunoEntity> list;
    SwipeRefreshLayout swipeRefreshLayout;
    public DesayunoViewModel nvmodel;
    public Context contexto;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PupusasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PupusasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PupusasFragment newInstance(String param1, String param2) {
        PupusasFragment fragment = new PupusasFragment();
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
        View v = inflater.inflate(R.layout.fragment_pupusas, container, false);

        if (mParam1.equals("Desayuno")) {

            rv = v.findViewById(R.id.recyclerpupusa);
            lManager = new LinearLayoutManager(getActivity());
            swipeRefreshLayout = v.findViewById(R.id.swipepupusa);

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                nvmodel = new DesayunoViewModel(getActivity().getApplication());
                                swipeRefreshLayout.setRefreshing(false);
                            } catch (Exception e) {

                            }
                        }
                    }, 1000);
                }
            });


            nvmodel = ViewModelProviders.of(this).get(DesayunoViewModel.class);
            nvmodel.getAllDesayunos().observe(this, new Observer<List<DesayunoEntity>>() {
                @Override
                public void onChanged(@Nullable List<DesayunoEntity> list) {
                    adapter = new DesayunoAdapter(list, getActivity());
                    lManager = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(lManager);
                    rv.setAdapter(adapter);
                }
            });

        }
        return v;

    }
   public void llenarlista(){

    }

    // TODO: Rename method, update argument and hook method into UI event


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
       // mListener = null;
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
