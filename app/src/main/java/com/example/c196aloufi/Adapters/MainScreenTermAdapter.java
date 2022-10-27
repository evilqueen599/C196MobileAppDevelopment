package com.example.c196aloufi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;
import com.example.c196aloufi.UserInterface.mainScreen;

import java.util.List;

public class MainScreenTermAdapter extends RecyclerView.Adapter<MainScreenTermAdapter.MainTermViewHolder> {

    class MainTermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termTxt;


        private MainTermViewHolder(View termView) {
            super(termView);
            termTxt= termView.findViewById(R.id.termTxt);

            termView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    final Terms current = mterms.get(position);
                    Intent intent = new Intent(context, mainScreen.class);
                    intent.putExtra("termName", current.getTermName());
                }
            });
        }
    }

    private List<Terms> mterms;
    private final Context context;
    private final LayoutInflater mInflator;

    public MainScreenTermAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public MainScreenTermAdapter.MainTermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View termView = mInflator.inflate(R.layout.main_term_item,parent, false);
        return new MainScreenTermAdapter.MainTermViewHolder(termView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainScreenTermAdapter.MainTermViewHolder holder, int position) {
        if (mterms != null) {
            Terms current = mterms.get(position);
            String name = current.getTermName();
            holder.termTxt.setText(name);

        } else {
            holder.termTxt.setText("No Terms Exist");
        }
    }
    public void setTerms(List<Terms> terms) {
        mterms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mterms != null) {
            return mterms.size();
        } else return 0;
    }
}
