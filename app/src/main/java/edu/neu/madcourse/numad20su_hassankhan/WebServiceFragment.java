package edu.neu.madcourse.numad20su_hassankhan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import static edu.neu.madcourse.numad20su_hassankhan.MainActivity.*;

public class WebServiceFragment extends Fragment {

    private EditText userInput;
    private TextView resultText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // hide link collector floating action button
        ((MainActivity) getActivity()).hideFloatingActionButton();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_service, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userInput = view.findViewById(R.id.userInputEditText);
        resultText = view.findViewById(R.id.response_textView);
        resultText.setText(getZipCodePlaceResult());

        view.findViewById(R.id.button_fetch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // loading prompt
                setZipCodePlaceResult("Fetching...");
                resultText.setText(getZipCodePlaceResult());
                // execute in a separate thread
                runnableThread webServiceFetchThread = new runnableThread();
                new Thread(webServiceFetchThread).start();
            }
        });
    }

    // runnable thread class that implements Runnable. To execute tasks in a separate thread,
    // so as to not block the main thread
    private class runnableThread implements Runnable {

        @Override
        public void run() {
            // initialize url
            URL url = null;
            try {
                // append the zip code that the user entered to the URL
                url = new URL("https://api.zippopotam.us/us/" + userInput.getText());

                // establish the connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);

                connection.connect();

                // Parse response from API.
                InputStream inStream = connection.getInputStream();
                final String response = convertStreamToString(inStream);

                JSONObject jsonObj = new JSONObject(response);

                // get array of places
                JSONArray places = jsonObj.getJSONArray("places");

                // get the first place's name
                String resultPlace = places.getJSONObject(0).getString("place name");

                // set result to the place's name
                setZipCodePlaceResult(resultPlace);
                resultText.setText(getZipCodePlaceResult());


            } catch (FileNotFoundException e) {
                // This exception is thrown if the API returns no response, meaning a valid Zip Code
                // was not entered
                System.out.println("FileNotFoundException");
                e.printStackTrace();
                setZipCodePlaceResult("Zip Code's place Not found, please enter a valid US Zip Code");
                resultText.setText(getZipCodePlaceResult());
            } catch (MalformedURLException e) {
                System.out.println("MalformedURLException");
                e.printStackTrace();
                setZipCodePlaceResult("Encountered an error while fetching");
                resultText.setText(getZipCodePlaceResult());
            } catch (ProtocolException e) {
                System.out.println("ProtocolException");
                e.printStackTrace();
                setZipCodePlaceResult("Encountered an error while fetching");
                resultText.setText(getZipCodePlaceResult());
            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
                setZipCodePlaceResult("Encountered an error while fetching");
                resultText.setText(getZipCodePlaceResult());
            } catch (JSONException e) {
                System.out.println("JSONException");
                e.printStackTrace();
                setZipCodePlaceResult("Encountered an error while fetching");
                resultText.setText(getZipCodePlaceResult());
            }
        }
    }

    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next().replace(",", ",\n") : "";
    }
}
