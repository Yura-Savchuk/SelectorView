package com.coulcod.selectorview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.List;

public class DialogListViewAdapter extends BaseAdapter {

    private int singleChoiceLayout = R.layout.default_single_choice;
    private int singleNoDeselectLayout = R.layout.default_single_not_deselect;
    private int multipleChoiceLayout = R.layout.default_multiple_choice;

    private final List<? extends com.coulcod.selectorview.Checkable> values;
    private final SelectionMode selectionMode;
    private WeakReference<ListView> listViewWeakReference;
    private LayoutInflater layoutInflater;

    public DialogListViewAdapter(@NonNull List<? extends com.coulcod.selectorview.Checkable> values, @NonNull SelectionMode selectionMode) {
        this.values = values;
        this.selectionMode = selectionMode;
    }

    public void setSingleChoiceLayout(int singleChoiceLayout) {
        this.singleChoiceLayout = singleChoiceLayout;
    }

    public void setMultipleChoiceLayout(int multipleChoiceLayout) {
        this.multipleChoiceLayout = multipleChoiceLayout;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public com.coulcod.selectorview.Checkable getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = onCreateView(parent);
            convertView = viewHolder.itemView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.position = position;
        onBindView(viewHolder, position);
        return convertView;
    }

    @NonNull private ViewHolder onCreateView(@NonNull ViewGroup parent) {
        if (layoutInflater == null) layoutInflater = LayoutInflater.from(parent.getContext());
        if (selectionMode == SelectionMode.SingleNoDeselect) {
            return new SingleChoiceViewHolder(layoutInflater.inflate(singleNoDeselectLayout, parent, false));
        }
        if (selectionMode == SelectionMode.Single) {
            return new SingleChoiceViewHolder(layoutInflater.inflate(singleChoiceLayout, parent, false));
        }
        return new MultipleChoiceViewHolder(layoutInflater.inflate(multipleChoiceLayout, parent, false));
    }

    private void onBindView(@NonNull ViewHolder viewHolder, int position) {
        if (viewHolder instanceof SingleChoiceViewHolder) {
            SingleChoiceViewHolder SCViewHolder = (SingleChoiceViewHolder) viewHolder;
            SCViewHolder.singleChoice.setText(getItem(position).getText());
            ((Checkable)SCViewHolder.singleChoice).setChecked(getItem(position).isSelected());
        } else if (viewHolder instanceof MultipleChoiceViewHolder) {
            MultipleChoiceViewHolder MCViewHolder = (MultipleChoiceViewHolder) viewHolder;
            MCViewHolder.multipleChoice.setText(getItem(position).getText());
            ((Checkable)MCViewHolder.multipleChoice).setChecked(getItem(position).isSelected());
        }
    }

    public void setListView(@Nullable ListView listView) {
        listViewWeakReference = new WeakReference<>(listView);
    }

    private abstract class ViewHolder implements View.OnClickListener {

        protected int position;
        private View itemView;

        protected ViewHolder(View itemView) {
            this.itemView = itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (getItem(position).isSelected()) {
                if (selectionMode == SelectionMode.Single || selectionMode == SelectionMode.SingleNoDeselect) {
                    boolean changed = false;
                    for (int i=0; i<getCount(); i++) {
                        if (i != position && getItem(i).isSelected()) {
                            getItem(i).setSelected(false);
                            changed = true;
                        }
                    }
                    if (changed) notifyDataSetChanged();
                }
            }


            if (listViewWeakReference != null && listViewWeakReference.get() != null) {
                ListView listView = listViewWeakReference.get();
                if (listView.getOnItemClickListener() != null) {
                    AdapterView.OnItemClickListener onItemClickListener = listView.getOnItemClickListener();
                    onItemClickListener.onItemClick(listView, view, position, getItemId(position));
                }
            }
        }
    }

    private class SingleChoiceViewHolder extends ViewHolder {
        private final CompoundButton singleChoice;

        private SingleChoiceViewHolder(View itemView) {
            super(itemView);
            this.singleChoice = (CompoundButton) itemView.findViewById(android.R.id.button1);
        }

        @Override
        public void onClick(View view) {
            values.get(position).setSelected(singleChoice.isChecked());
            super.onClick(view);
        }
    }

    private class MultipleChoiceViewHolder extends ViewHolder {
        private final CompoundButton multipleChoice;

        private MultipleChoiceViewHolder(View itemView) {
            super(itemView);
            this.multipleChoice = (CompoundButton) itemView.findViewById(android.R.id.button2);
        }

        @Override
        public void onClick(View view) {
            values.get(position).setSelected(multipleChoice.isChecked());
            super.onClick(view);
        }
    }

}
