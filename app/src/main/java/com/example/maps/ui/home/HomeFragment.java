package com.example.maps.ui.home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.maps.R;
import com.example.maps.databinding.FragmentHomeBinding;
import com.example.maps.ui.slideshow.MiPosition;

public class HomeFragment extends Fragment implements View.OnClickListener {
    //Variables
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private Button btnUbicacion;
    private EditText txtLatitud, txtLongitud, txtAltitud;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Buscamos en el layout los componentes
        btnUbicacion = root.findViewById(R.id.btn_localizar);
        txtLatitud = root.findViewById(R.id.editTextLatitud);
        txtLongitud = root.findViewById(R.id.editTextLongitud);
        txtAltitud = root.findViewById(R.id.editTextAltitud);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    //Método onClick para darle la acción al botón Buscar Localizar
    @Override
    public void onClick(View v) {
        if (v==btnUbicacion) {
            miPosition();
            Toast.makeText(getContext(), "Click en el botón localizar", Toast.LENGTH_LONG).show();
        }
    }
//Método para hallar la posición
    public void miPosition() {

        if (ContextCompat.checkSelfPermission((Activity) getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getContext(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }

        LocationManager objLocation = null;
        LocationListener objLocListener;

        objLocation = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        objLocListener = new MiPosition();
        objLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, objLocListener);

        if (objLocation.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            txtLatitud.setText(MiPosition.latitud + "");
            txtLongitud.setText(MiPosition.longitud + "");
            txtAltitud.setText(MiPosition.altitud + "");
        }else{
            Toast.makeText(getContext(), "Gps Deshabilitado", Toast.LENGTH_LONG).show();
        }

    }

}