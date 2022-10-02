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
import com.example.c196aloufi.UserInterface.DetailedTerm;

import java.util.List;

public class mainScreenTermAdapter extends RecyclerView.Adapter<mainScreenTermAdapter.MainTermViewHolder> {

    class MainTermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termTxt;


        private MainTermViewHolder(View termView) {
            super(termView);
            termTxt= termView.findViewById(R.id.termTxt);

            termView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Terms current = mterms.get(position);
                    Intent intent = new Intent(context, DetailedTerm.class);
                    intent.putExtra("id", current.getTermId());
                    intent.putExtra("termName", current.getTermName());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                }
            });
        }
    }

    private List<Terms> mterms;
    private final Context context;
    private final LayoutInflater mInflator;

    public mainScreenTermAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public mainScreenTermAdapter.MainTermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View termView = mInflator.inflate(R.layout.term_item,parent, false);
        return new mainScreenTermAdapter.MainTermViewHolder(termView);
    }

    @Override
    public void onBindViewHolder(@NonNull mainScreenTermAdapter.MainTermViewHolder holder, int position) {
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
