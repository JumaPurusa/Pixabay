package com.example.pixabay.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pixabay.Models.Post;
import com.example.pixabay.R;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder>{

    private Context mContext;
    private List<Post> listOfImagesPosts;

    public PostsAdapter(Context context){
        this.mContext = context;
    }

    public void addListItems(List<Post> list){
        this.listOfImagesPosts = list;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.image_view_list_item, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Post post = listOfImagesPosts.get(position);

        Glide.with(mContext)
                .load(post.getPostURL())
                .into(holder.postImage);

        Glide.with(mContext)
                .load(post.getUserImaageURL())
                .into(holder.userImage);

        holder.postHeaderText.setText(post.getUser());

        holder.description.setText(post.getDescription());

        holder.viewsText.setText(post.getViews() + " views");

        holder.likesText.setText(post.getLikes() + " likes");

        holder.commentsText.setText(post.getComments() + " comments");

    }

    @Override
    public int getItemCount() {
        return listOfImagesPosts.size();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder{

        private ImageView postImage, userImage;
        private TextView postHeaderText, description, viewsText, likesText, commentsText;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            postImage = itemView.findViewById(R.id.imageView);
            userImage = itemView.findViewById(R.id.userImage);
            postHeaderText = itemView.findViewById(R.id.postHeaderText);
            description = itemView.findViewById(R.id.description);
            viewsText = itemView.findViewById(R.id.viewsTextView);
            likesText = itemView.findViewById(R.id.likesTextView);
            commentsText = itemView.findViewById(R.id.commentsTextView);

        }
    }
}
