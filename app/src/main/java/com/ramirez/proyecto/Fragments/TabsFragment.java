package com.ramirez.proyecto.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ramirez.proyecto.Adaptadores.SectionPagerAdapter;
import com.ramirez.proyecto.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AppBarLayout appBarLayout;

    //private OnFragmentInteractionListener mListener;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    //private ViewPagerAdapter viewPagerAdapter;
    String category;

    public TabsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabsFragment newInstance(String param1) {
        TabsFragment fragment = new TabsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabs, container, false);

        tabLayout = v.findViewById(R.id.tabs);
        SectionPagerAdapter s = new SectionPagerAdapter(getChildFragmentManager());
        viewPager = v.findViewById(R.id.vspager);
        if(mParam1.equals("Almuerzo")) {
            s.addFragment(PorcionesFragment.newInstance(mParam1, "k"), "POLLOS");
            s.addFragment(BebidasFragment.newInstance(mParam1, "k"), "BEBIDA");
        }
        if(mParam1.equals("Desayuno")){
            s.addFragment(PorcionesFragment.newInstance(mParam1,"k"),"PUPUSAS");
            s.addFragment(PupusasFragment.newInstance(mParam1,"k"),"PORCION");
            s.addFragment(BebidasFragment.newInstance(mParam1, "k"), "BEBIDA");
        }
        if(mParam1.equals("Sabados")){
            s.addFragment(PorcionesFragment.newInstance(mParam1,"k"),"PUPUSAS");
            s.addFragment(PupusasFragment.newInstance(mParam1, "k"), "PORCION");
            s.addFragment(BebidasFragment.newInstance(mParam1, "k"), "BEBIDA");
        }
        viewPager.setAdapter(s);
        tabLayout.setupWithViewPager(viewPager);
        LinearLayout layout = v.findViewById(R.id.regresar);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
                toolbar.setTitle("Menú");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentcosa, menufragment.newInstance("4","3")).commit();
            }
        });
        return v;
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
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
