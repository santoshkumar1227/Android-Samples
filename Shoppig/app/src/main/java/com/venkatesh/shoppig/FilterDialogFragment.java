package com.venkatesh.shoppig;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.venkatesh.shoppig.entities.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     FilterDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link FilterDialogFragment.Listener}.</p>
 */
public class FilterDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";
    private Listener mListener;
    ArrayList<Filter> filters;

    // TODO: Customize parameters
    public static FilterDialogFragment newInstance(ArrayList<Filter> filters) {
        final FilterDialogFragment fragment = new FilterDialogFragment();
        final Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_COUNT, filters);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerFilter);
        final Button button = (Button) view.findViewById(R.id.applyBtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        filters = (ArrayList<Filter>) getArguments().getSerializable(ARG_ITEM_COUNT);
        final ItemAdapter adapter = new ItemAdapter(filters);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Filter filter : filters) {
                    Log.d("Test", filter.getType() + " " + filter.isChecked());
                }
                mListener.onItemClicked(filters);
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Fragment parent = getParentFragment();
        if (parent != null) {
            mListener = (Listener) parent;
        } else {
            mListener = (Listener) context;
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public interface Listener {
        void onItemClicked(ArrayList<Filter> filters);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;
        final CheckBox checkBox;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.fragment_item_list_dialog_item, parent, false));
            text = (TextView) itemView.findViewById(R.id.text);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final ArrayList<Filter> mfilters;

        ItemAdapter(ArrayList<Filter> filters) {
            mfilters = filters;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.text.setText(mfilters.get(position).getType());
            holder.checkBox.setChecked(mfilters.get(position).isChecked());
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mfilters.get(position).setChecked(isChecked);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mfilters.size();
        }

    }

}
