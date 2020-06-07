package edu.neu.madcourse.numad20su_hassankhan;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import static edu.neu.madcourse.numad20su_hassankhan.MainActivity.*;

public class FirstFragment extends Fragment {

    private final int locationPermissionRequestCode = 1;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // hide link collector floating action button
        ((MainActivity) getActivity()).hideFloatingActionButton();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.button_clicky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_ClickyFragment);
            }
        });

        view.findViewById(R.id.button_link_collector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_LinkCollectorFragment);
            }
        });

        view.findViewById(R.id.button_locator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // on click to Locator, check for location permission. Ask if not already given
                // we move to locator fragment downstream from this function
                this.getLocationPermission();
            }

            /*
             * location permission request handler. Result is managed by a callback function
             * onRequestPermissionsResult (called automatically by Android)
             */
            private void getLocationPermission() {
                if (ContextCompat.checkSelfPermission(view.getContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    // navigate to locator fragment since location permission was already given
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_LocatorFragment);

                } else {
                    // result of this is handled by onRequestPermissionsResult
                    requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, locationPermissionRequestCode);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case locationPermissionRequestCode:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.

                    // navigate to locator fragment since location permission was given
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_LocatorFragment);

                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                    Snackbar.make(this.getView(), "Locator requires location permissions, please allow", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }
}
