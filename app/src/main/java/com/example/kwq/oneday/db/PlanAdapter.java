package com.example.kwq.oneday.db;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kwq.oneday.NewPlanActivity;
import com.example.kwq.oneday.R;
import com.example.kwq.oneday.util.Util;

import org.litepal.crud.DataSupport;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private List<Plan> mPlanList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View planView;
        TextView startTime;
        TextView endTime;
        TextView planType;
        TextView plan;

        public ViewHolder(View view) {
            super(view);
            planView = view;
            startTime = view.findViewById(R.id.startTime);
            endTime = view.findViewById(R.id.endTime);
            planType = view.findViewById(R.id.planType);
            plan = view.findViewById(R.id.plan);
        }
    }

    public PlanAdapter(List<Plan> planList) {
        mPlanList = planList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTyep) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.planView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Plan plan = mPlanList.get(position);
                Intent intent = new Intent(mContext, NewPlanActivity.class);
                intent.putExtra("date", plan.getDate());
                intent.putExtra("id", plan.getId());
                mContext.startActivity(intent);
            }
        });

        holder.planView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("提示")
                        .setMessage("是否删除该计划")
                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int position = holder.getAdapterPosition();
                                Plan plan = mPlanList.get(position);
                                String idString = String.valueOf(plan.getId());
                                DataSupport.deleteAll(Plan.class, "id = ?", idString);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                return true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Plan plan = mPlanList.get(position);
        holder.startTime.setText(plan.getStartTime());
        holder.endTime.setText(plan.getEndTime());
        holder.planType.setText(plan.getPlanType());
        holder.plan.setText(plan.getPlan());
    }

    @Override
    public int getItemCount() {
        return mPlanList.size();
    }

}
