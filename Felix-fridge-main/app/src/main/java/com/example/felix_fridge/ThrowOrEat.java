package com.example.felix_fridge;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;


public class ThrowOrEat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private VideoView mVideoView;

    private Button mThrowButton;

    private Button mEatButton;

    public ThrowOrEat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThrowOrEat.
     */
    // TODO: Rename and change types and number of parameters
    public static ThrowOrEat newInstance(String param1, String param2) {
        ThrowOrEat fragment = new ThrowOrEat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_throw_or_eat, container, false);

        mVideoView = view.findViewById(R.id.videoView);
        mEatButton = view.findViewById(R.id.Eat);

        mEatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("ThrowOrEat", "Eat button clicked");



                // Remove the VideoView after the video finishes playing
                mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                ViewGroup parent = (ViewGroup) mVideoView.getParent();
                                parent.removeView(mVideoView);
                            }
                        });
                    }
                });
            }
        });

        return view;
    }
}
