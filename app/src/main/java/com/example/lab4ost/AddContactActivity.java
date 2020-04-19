package com.example.lab4ost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lab4ost.tasks.TaskListContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddContactActivity extends AppCompatActivity implements TaskFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    public void addClick(View view)
    {
        EditText taskTitleEditTxt = findViewById(R.id.name);
        EditText surnameTxt = findViewById(R.id.surname);
        EditText taskDescriptionTxt = findViewById(R.id.number);
        EditText birthdayEditTxt = findViewById(R.id.birthday);

        String taskTitle = taskTitleEditTxt.getText().toString();
        String taskDescription = taskDescriptionTxt.getText().toString();
        String surname= surnameTxt.getText().toString();
        String birthday = birthdayEditTxt.getText().toString();
        String selectedImage="4";
        if(taskTitle.isEmpty()){
            taskTitle="Default";
        }
        if(surname.isEmpty()){
            surname="Default";
        }
        if(!birthdayTest(birthday))
            if(phoneTest(taskDescription)&& birthdayTest(birthday)) {
                TaskListContent.addItem(new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() + 1,
                        taskTitle, surname, taskDescription, birthday, selectedImage));
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
            }


        //((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();

        taskTitleEditTxt.setText("");
        birthdayEditTxt.setText("");
        surnameTxt.setText("");
        taskDescriptionTxt.setText("");


    }


    private boolean phoneTest(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    public boolean birthdayTest(String date)
    {
        SimpleDateFormat test = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = null;
        try
        {
            testDate = test.parse(date);
        }
        catch (ParseException e)
        {
            return false;
        }
        if (!test.format(testDate).equals(date))
        {
            return false;
        }
        return true;
    }

    @Override
    public void onListFragmentClickInteraction(TaskListContent.Task task, int position) {

    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {

    }
}
