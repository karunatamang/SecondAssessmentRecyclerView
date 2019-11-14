package com.example.secondassessmentrecyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondassessmentrecyclerview.R;
import com.example.secondassessmentrecyclerview.UserDetailActivity;
import com.example.secondassessmentrecyclerview.model.Designer;

import java.util.List;

public class DesignerAdapter extends RecyclerView.Adapter<DesignerAdapter.DesignerViewHolder> {
    private Context context;
    private List<Designer> designerList;

    public DesignerAdapter(Context context, List<Designer> designerList) {
        this.context = context;
        this.designerList = designerList;
    }

    @NonNull
    @Override
    public DesignerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View viewDesigner = LayoutInflater.from(context).inflate(R.layout.layout_designer_item, parent, false);
       return new DesignerViewHolder(viewDesigner);
    }

    @Override
    public void onBindViewHolder(@NonNull DesignerViewHolder holder, int position) {
        final Designer designer = designerList.get(position);
        int image=Integer.valueOf(designer.getImage());
        holder.designerImg.setImageResource(image);
        holder.tvDesignerName.setText(designer.getName());

        holder.tvDesignerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent designerDetail = new Intent(context, UserDetailActivity.class);
                designerDetail.putExtra("DesignerDetails", designer);
                context.startActivity(designerDetail);
            }
        });


    }

    @Override
    public int getItemCount() {
        return designerList.size();
    }

    public  class DesignerViewHolder extends RecyclerView.ViewHolder {
        private ImageView designerImg;
        private TextView tvDesignerName;
        public DesignerViewHolder(@NonNull View itemView) {
            super(itemView);
            designerImg=itemView.findViewById(R.id.designerimage);
            tvDesignerName=itemView.findViewById(R.id.designername);
        }
    }
}
