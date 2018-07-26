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
        TextView sTime;
        TextView eTime;
        TextView planType;
        TextView plan;

        public ViewHolder(View view) {
            super(view);
            sTime = (TextView) view.findViewById(R.id.sTime);
            eTime = (TextView) view.findViewById(R.id.eTime);
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
        holder.sTime.setText(plan.getSTime());
        holder.eTime.setText(plan.getETime());
        holder.planType.setText(plan.getPlanType());
        holder.plan.setText(plan.getPlan());
    }

    @Override
    public int getItemCount() {
        return mPlanList.size();
    }

}
