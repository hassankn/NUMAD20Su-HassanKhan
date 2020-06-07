package edu.neu.madcourse.numad20su_hassankhan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static edu.neu.madcourse.numad20su_hassankhan.MainActivity.*;

public class ClickyFragment extends Fragment {

    private TextView pressedText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // hide link collector floating action button
        ((MainActivity) getActivity()).hideFloatingActionButton();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clicky, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pressedText = view.findViewById(R.id.textview_pressed);
        pressedText.setText(getClickyPressedText());

        view.findViewById(R.id.button_a).setOnClickListener(clickyOnClickListener);
        view.findViewById(R.id.button_b).setOnClickListener(clickyOnClickListener);
        view.findViewById(R.id.button_c).setOnClickListener(clickyOnClickListener);
        view.findViewById(R.id.button_d).setOnClickListener(clickyOnClickListener);
        view.findViewById(R.id.button_e).setOnClickListener(clickyOnClickListener);
        view.findViewById(R.id.button_f).setOnClickListener(clickyOnClickListener);
    }

    // make a listener in the class so we can use one onClick, instead of having to define it
    // multiple times
    private View.OnClickListener clickyOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_a:
                    setClickyPressedText("Pressed: A");
                    pressedText.setText(getClickyPressedText());
                    break;
                case R.id.button_b:
                    setClickyPressedText("Pressed: B");
                    pressedText.setText(getClickyPressedText());
                    break;
                case R.id.button_c:
                    setClickyPressedText("Pressed: C");
                    pressedText.setText(getClickyPressedText());
                    break;
                case R.id.button_d:
                    setClickyPressedText("Pressed: D");
                    pressedText.setText(getClickyPressedText());
                    break;
                case R.id.button_e:
                    setClickyPressedText("Pressed: E");
                    pressedText.setText(getClickyPressedText());
                    break;
                case R.id.button_f:
                    setClickyPressedText("Pressed: F");
                    pressedText.setText(getClickyPressedText());
                    break;
            }
        }
    };
}
