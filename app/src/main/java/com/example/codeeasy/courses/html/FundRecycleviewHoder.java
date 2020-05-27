package com.example.codeeasy.courses.html;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeeasy.R;

import java.util.List;

public class FundRecycleviewHoder extends RecyclerView.Adapter<FundRecycleviewHoder.ViewHoder> {

    private final Context mContext;
    private final LayoutInflater layoutInflater;
    private final List<String> HTMLLesson;
    private final List<String> HTMLTitle;

    public FundRecycleviewHoder(Context mContext, List<String> htmlLesson, List<String> htmlTitle) {
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
        HTMLLesson = htmlLesson;
        HTMLTitle = htmlTitle;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_htmlfund_lists, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.icon.setImageResource(R.drawable.ic_iconfinder_22_940979);
        holder.textLesson.setText(HTMLLesson.get(position));
        holder.textTitle.setText(HTMLTitle.get(position));
    }

    @Override
    public int getItemCount() {
        return HTMLLesson.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        public final TextView textLesson, textTitle;
        public final ImageView icon;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.imageView_htmlfund);
            textLesson = itemView.findViewById(R.id.textView_htmfund_item_lesson);
            textTitle = itemView.findViewById(R.id.textView_htmlfund_item_titile);

        }
    }
}
