package com.example.loginpage.modle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpage.R;

import java.util.List;


public class listAdapter  extends RecyclerView.Adapter<listAdapter.ViewHolder>{

    private Context mcontext;
    private List<LoginUser> list;

    public listAdapter(Context mcontext, List<LoginUser> list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @NonNull
    @Override
    public listAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.itemview,parent,false);

        return new listAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listAdapter.ViewHolder holder, int position) {
        LoginUser usr = list.get(position);
        holder.name.setText(usr.getUsername());
        holder.email.setText(usr.getEmail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.username2);
            email=itemView.findViewById(R.id.useremail);
        }
    }
}
