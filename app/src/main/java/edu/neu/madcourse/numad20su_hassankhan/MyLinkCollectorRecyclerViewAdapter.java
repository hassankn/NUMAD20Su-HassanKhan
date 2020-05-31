package edu.neu.madcourse.numad20su_hassankhan;

import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import edu.neu.madcourse.numad20su_hassankhan.LinkCollectorFragment.OnListFragmentInteractionListener;
import edu.neu.madcourse.numad20su_hassankhan.nameAndLinkURL.NameAndLinkURLContent;
import edu.neu.madcourse.numad20su_hassankhan.nameAndLinkURL.NameAndLinkURLContent.NameAndLinkURLItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link NameAndLinkURLItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyLinkCollectorRecyclerViewAdapter extends RecyclerView.Adapter<MyLinkCollectorRecyclerViewAdapter.ViewHolder> {

    private final List<NameAndLinkURLContent.NameAndLinkURLItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyLinkCollectorRecyclerViewAdapter(List<NameAndLinkURLItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_link_collector, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).linkName);
        holder.mContentView.setText(mValues.get(position).linkURL);

        holder.mView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);

                    String linkURLToGoTo = holder.mItem.linkURL;
                    Uri webPageURL = Uri.parse(linkURLToGoTo);

                    if (!linkURLToGoTo.startsWith("http://") && !linkURLToGoTo.startsWith("https://")) {
                        webPageURL = Uri.parse("https://" + linkURLToGoTo);
                    }
                    // open link in browser. Catch exception in case of error, to prevent crash
                    Intent openLinkInBrowserIntent = new Intent(Intent.ACTION_VIEW, webPageURL);
                    try {
                        view.getContext().startActivity(openLinkInBrowserIntent);
                    } catch (ActivityNotFoundException e) {
                        System.out.println(e);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public NameAndLinkURLContent.NameAndLinkURLItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
