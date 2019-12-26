package com.uylab.roomdatabase.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uylab.roomdatabase.R;
import com.uylab.roomdatabase.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    List<Student> studentList = new ArrayList<> ();
    private OnItemClickListener listener;

    public StudentAdapter(List<Student> studentList) {
        this.studentList=studentList;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from ( parent.getContext () );
        View view=inflater.inflate ( R.layout.student_recycler,parent,false );
        ViewHolder viewHolder = new ViewHolder ( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.idEdit.setText ( "Id : "+ studentList.get ( position ).getId () );
        holder.nameEdit.setText ( ""+ studentList.get ( position ).getName () );
        holder.deptEdit.setText ( "Department : "+ studentList.get ( position ).getDept () );
        holder.addressEdit.setText ( "Address"+ studentList.get ( position ).getAddress () );
        holder.imageView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.OnItemClick ( studentList.get ( position ),position );
                }
            }
        } );

    }

    @Override
    public int getItemCount() {
        return studentList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView idEdit;
        private TextView nameEdit;
        private TextView deptEdit;
        private TextView addressEdit;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            idEdit=itemView.findViewById ( R.id.itemId );
            nameEdit=itemView.findViewById ( R.id.itemName );
            deptEdit=itemView.findViewById ( R.id.itemDept );
            addressEdit=itemView.findViewById ( R.id.itemAddress );
            imageView=itemView.findViewById ( R.id.imageView );
        }
    }
    public interface OnItemClickListener{
         void OnItemClick (Student info, int position);
    }
}
