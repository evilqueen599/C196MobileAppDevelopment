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

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    public Terms getTerm(int absoluteAdapterPosition) {
        return mterms.get(absoluteAdapterPosition);
    }

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termTxt;
        private final TextView termTxt2;


        private TermViewHolder(View termView) {
            super(termView);
            termTxt= termView.findViewById(R.id.termTxt);
            termTxt2 = termView.findViewById(R.id.termTxt2);

            termView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
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
        View termView = mInflator.inflate(R.layout.term_item,parent, false);
        return new TermViewHolder(termView);
        }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if (mterms != null) {
            Terms current = mterms.get(position);
            String name = current.getTermName();
            holder.termTxt.setText(name);
            String startDate = current.getStartDate();
            String endDate = current.getEndDate();
            holder.termTxt2.setText((startDate) + " to " + (endDate));

        } else {
            holder.termTxt.setText("No Terms Exist");
            holder.termTxt2.setText("No Terms Exist ");
        }
    }
    public void setTerms(List<Terms>terms) {
        mterms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mterms != null) {
            return mterms.size();
        } else return 0;
    }
    public Terms getSelectedTerm(int position) {
        return mterms.get(position);
    }

    public interface onClickEditTermListener {
        void onClickEditTermFab(int position);
    }

}
