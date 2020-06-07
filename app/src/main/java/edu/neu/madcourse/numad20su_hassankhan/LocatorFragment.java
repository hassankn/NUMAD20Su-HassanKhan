package edu.neu.madcourse.numad20su_hassankhan;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.sql.SQLOutput;

public class LocatorFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationClient;
    private TextView location_coordinates;
    private String locationCoordinatesString;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // hide link collector floating action button
        ((MainActivity) getActivity()).hideFloatingActionButton();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_locator, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this.getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            locationCoordinatesString = location.getLatitude() + ", " + location.getLongitude();

                            location_coordinates = view.findViewById(R.id.location_coordinates);
                            location_coordinates.setText(locationCoordinatesString);
                        }
                    }
                });
    }
}
