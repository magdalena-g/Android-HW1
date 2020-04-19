package com.example.lab4ost;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallDialog extends Fragment {

    private CallDialog.OnCallDialogInteractionListener mListener;
    public CallDialog() {
        // Required empty public constructor
    }

    public static CallDialog newInstance() {
        return new CallDialog();
    }


    @NonNull

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // AlertDialog.Builder will be used to create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the message displayed in the dialog
        builder.setMessage(getString(R.string.call_question));
        // Set the text and action for the positive button click
        builder.setPositiveButton(getString(R.string.call_confirm), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Notify the OnDeleteDialogInteractionListener interface of positive button click
                mListener.onCallPositiveClick(CallDialog.this);
            }
        });
        // Set the text and action for the negative button click
        builder.setNegativeButton(getString(R.string.call_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Notify the OnDeleteDialogInteractionListener interface of negative button click
                mListener.onCallNegativeClick(CallDialog.this);
            }
        });
        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        throw new RuntimeException(context.toString()
                + " must implement OnCallDialogInteractionListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void show(FragmentManager supportFragmentManager, String string) {
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

    public class OnCallDialogInteractionListener {
        void onCallPositiveClick(CallDialog dialog) {

        }

        void onCallNegativeClick(CallDialog dialog) {

        }
    }
}
