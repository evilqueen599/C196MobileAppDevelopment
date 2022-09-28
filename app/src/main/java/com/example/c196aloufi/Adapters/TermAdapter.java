package com.example.c196aloufi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.autofill.AutofillValue;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;
import com.example.c196aloufi.UserInterface.DetailedTerm;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termCardViewTxt;
        private final TextView termCardViewTxt2;
        private final TextView termCardViewTxt3;

        private TermViewHolder(View termView) {
            super(termView);
            termCardViewTxt= termView.findViewById(R.id.termsCardViewTxt);
            termCardViewTxt2 = termView.findViewById(R.id.termsCardViewTxt2);
            termCardViewTxt3 = termView.findViewById(R.id.termsCardViewTxt3);
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

    public TermAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
        }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View termView = mInflator.inflate(R.layout.activity_detailed_term_list,parent, false);
        return new TermViewHolder(termView);
        }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if (mterms != null) {
            Terms current = mterms.get(position);
            String name = current.getTermName();
            holder.termCardViewTxt.setText(name);
            String start = current.getStartDate().toString();
            holder.termCardViewTxt2.setText(start);
            String end = current.getEndDate().toString();
            holder.termCardViewTxt3.setText(end);
        } else {
            holder.termCardViewTxt.setText(" ");
            holder.termCardViewTxt2.setText(" ");
            holder.termCardViewTxt3.setText(" ");
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
