package com.example.lab4ost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lab4ost.tasks.TaskListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements TaskFragment.OnListFragmentInteractionListener, DeleteDialog.OnDeleteDialogInteractionListener {
    public static final String taskExtra = "taskExtra";
    private int currentItemPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivity(intent);
            }
        });
    }
    private void displayTaskInFragment(TaskListContent.Task task){
        TaskInfoFragment taskInfoFragment = ((TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(taskInfoFragment != null){
            taskInfoFragment.displayTask(task);
        }
    }



    @Override
    public void onListFragmentClickInteraction(TaskListContent.Task task, int position) {
        Toast.makeText(this,getString(R.string.item_selected_msg)+ position,Toast.LENGTH_SHORT).show();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            displayTaskInFragment(task);
        } else{
            startSecondActivity(task,position);

        }
    }


    public void onDeleteClickInteraction(int position)
    {
        deleteItem();
        currentItemPosition = position;
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        Toast.makeText(this,getString(R.string.long_click_msg)+position, Toast.LENGTH_SHORT).show();
        showDeleteDialog();
        currentItemPosition = position;
    }

    private void startSecondActivity(TaskListContent.Task task, int position)
    {
        Intent intent = new Intent(this,TaskInfoActivity.class);
        intent.putExtra(taskExtra, task);
        startActivity(intent);
    }


    private void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
    }

    private void showCallDialog(){
        CallDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.call_dialog_tag));
    }
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // If the user confirmed the deletion of an item
        if(currentItemPosition != -1 && currentItemPosition < TaskListContent.ITEMS.size()){
            // If the currentItemPosition is correct. Remove the element from the TaskListContent list
            TaskListContent.removeItem(currentItemPosition);
            // Notify the TaskFragment adapter of the dataset change
            ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // If the user cancelled the deletion of the item
        View v = findViewById(R.id.deleteButton);
        if(v != null){
            // show a Snackbar displaying a message and allowing the user to retry the deletion of an item
            Snackbar.make(v, getString(R.string.delete_cancel_msg), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry_msg), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // The user pressed "Retry" button. Show the deleteDialog once again
                    showDeleteDialog();
                }
            }).show();
        }
    }

    public void deleteItem() {
        DeleteDialog.newInstance().show(getSupportFragmentManager(),"Delete");
    }

    public void deleteItem(View view) {
        DeleteDialog.newInstance().show(getSupportFragmentManager(),"Delete");
    }
}
