package com.example.kwq.oneday.db;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kwq.oneday.R;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private List<Plan> mPlanList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView startTime;
        TextView endTime;
        TextView planType;
        TextView plan;

        public ViewHolder(View view) {
            super(view);
            startTime = (TextView) view.findViewById(R.id.startTime);
            endTime = (TextView) view.findViewById(R.id.endTime);
            planType = (TextView) view.findViewById(R.id.planType);
            plan = (TextView) view.findViewById(R.id.plan);
        }
    }

    public PlanAdapter(List<Plan> planList) {
        mPlanList = planList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTyep) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
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
