/*
 * Copyright 2014 Magnus Woxblom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dima.weather.screen.main;

import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dima.weather.R;
import com.woxthebox.draglistview.DragItemAdapter;

import java.util.ArrayList;

class NavigationDrawerAdapter extends DragItemAdapter<Pair<Long, String>, NavigationDrawerAdapter.ViewHolder> {

    private int mLayoutId;
    private int mGrabHandleId;
    private boolean mDragOnLongPress;
    private OnItemClickListener mOnItemClickListener;


    NavigationDrawerAdapter(ArrayList<Pair<Long, String>> list, int layoutId, int grabHandleId,
                            boolean dragOnLongPress, OnItemClickListener onItemClickListener) {
        this.mLayoutId = layoutId;
        this.mGrabHandleId = grabHandleId;
        this.mDragOnLongPress = dragOnLongPress;
        this.mOnItemClickListener = onItemClickListener;
        setItemList(list);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String text = getItemList().get(position).second;
        holder.click(text, mOnItemClickListener);
        holder.mText.setText(text);
        holder.itemView.setTag(mItemList.get(position));
    }

    public interface OnItemClickListener {
        void dragItemOnClick(String cityName);
    }

    @Override
    public long getUniqueItemId(int position) {
        return mItemList.get(position).first;
    }

    class ViewHolder extends DragItemAdapter.ViewHolder {
        TextView mText;

        ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            mText = (TextView) itemView.findViewById(R.id.text);
        }

        public void click(final String city, final OnItemClickListener listener) {
            itemView.setOnClickListener(view -> listener.dragItemOnClick(city));
        }

    }
}
