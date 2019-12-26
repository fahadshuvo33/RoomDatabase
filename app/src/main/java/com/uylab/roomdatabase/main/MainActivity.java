package com.uylab.roomdatabase.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uylab.roomdatabase.R;
import com.uylab.roomdatabase.model.Student;
import com.uylab.roomdatabase.room.AppConstraints;
import com.uylab.roomdatabase.room.database.DatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, StudentAdapter.OnItemClickListener {

    private StudentAdapter sAdapter;
    private RecyclerView recyclerView;
    private List<Student> studentList;
    private StudentAdapter.OnItemClickListener listener;
    private Button addBtn;
    private Button refBtn;
    private EditText id;
    private EditText name;
    private EditText dept;
    private EditText addr;
    private int toUpdate=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        allId ();
        studentList=new ArrayList<> ();
        sAdapter = new StudentAdapter (studentList);
        recyclerView.setAdapter ( sAdapter );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        sAdapter.setListener ( this );

        DatabaseOperator operator = new DatabaseOperator ();
        operator.execute ( "" + AppConstraints.VIEW_STUDENT );

        addBtn.setOnClickListener ( this );
        refBtn.setOnClickListener ( this );
    }

    public void allId(){
        recyclerView=findViewById ( R.id.recyclerView );
        addBtn= findViewById ( R.id.addBtn );
        refBtn= findViewById ( R.id.refreshBtn );
        id=findViewById ( R.id.stId );
        name=findViewById ( R.id.stName );
        dept=findViewById ( R.id.stDept );
        addr=findViewById ( R.id.stAddress );
    }

    @Override
    public void onClick(View view) {
        if(view.getId ()==R.id.addBtn){

            String sId = id.getText ().toString ().trim ();
            String sName = name.getText ().toString ().trim ();
            String sDept = dept.getText ().toString ().trim ();
            String sAdd = addr.getText ().toString ().trim ();

            if(!sId.isEmpty () && !sName.isEmpty () && !sDept.isEmpty () && !sAdd.isEmpty ()) {
                DatabaseOperator operator = new DatabaseOperator ();
                operator.execute ( "" + AppConstraints.INSERT_STUDENT, sId, sName, sDept, sAdd );
            }
            else {
                Toast.makeText ( MainActivity.this,"Please fillup all the fields",Toast.LENGTH_SHORT ).show ();
            }
        }
        if(view.getId()==R.id.refreshBtn){
            refreashClick ();
        }
    }

    public class DatabaseOperator extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            int flag = Integer.parseInt ( strings[0] );

            if(flag== AppConstraints.INSERT_STUDENT){
                String id = strings[1];
                String name = strings[2];
                String dept = strings[3];
                String addr = strings[4];

                Student student = new Student ();
                student.setId (Integer.parseInt ( id ));
                student.setName (name);
                student.setDept ( dept );
                student.setAddress ( addr );

                insertStudent ( student );
                clearAll ();

            }
            if(flag== AppConstraints.VIEW_STUDENT){
                viewStudent ();
            }
            if(flag==AppConstraints.DELETE_STUDENT){
                String id = strings[1];
                Student student = new Student ();
                student.setId (Integer.parseInt ( id ));
                deleteStudent (student  );
            }
            return null;
        }
    }

    public void insertStudent(Student student){
        DatabaseClient.getInstance ( MainActivity.this ).getAppDatabase ().studentDao ().InsertStudent ( student );
    }

    public void viewStudent(){
        List<Student> students = new ArrayList<> ();
        students = DatabaseClient.getInstance ( MainActivity.this ).getAppDatabase ().studentDao ().getStudent ();
        viewInRecyclerView ( students );
    }

    public void deleteStudent(Student student){
        DatabaseClient.getInstance ( MainActivity.this ).getAppDatabase ().studentDao ().DeleteStudent ( student.getId () );
    }

    public void viewInRecyclerView(final List<Student> list){
        runOnUiThread ( new Runnable () {
            @Override
            public void run() {
                studentList.clear ();
                studentList.addAll ( list );
                sAdapter.notifyDataSetChanged ();
            }
        } );
    }

    public void refreashClick (){
        DatabaseOperator operator = new DatabaseOperator ();
        operator.execute ( "" + AppConstraints.VIEW_STUDENT );
        runOnUiThread ( new Runnable () {
            @Override
            public void run() {
                Toast.makeText ( MainActivity.this,"Refreshed",Toast.LENGTH_SHORT ).show ();
            }
        } );
    }

    public void clearAll()
    {
        runOnUiThread ( new Runnable () {
            @Override
            public void run() {
                id.setText("");
                name.setText("");
                dept.setText("");
                addr.setText("");
            }
        } );

    }

    public void OnItemClick(Student item, int position){
        studentList.remove ( position );
        int id=studentList.get ( position ).getId ();

        DatabaseOperator databaseOperator=new DatabaseOperator ();
        databaseOperator.execute ( ""+AppConstraints.DELETE_STUDENT,""+id );
        //deleteStudent ( studentList.get ( position ) );
        sAdapter.notifyDataSetChanged ();
    }

}
