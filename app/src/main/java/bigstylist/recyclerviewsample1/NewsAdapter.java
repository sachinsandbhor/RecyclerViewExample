package bigstylist.recyclerviewsample1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${BigStylist} on 13/9/17.
 */

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private ClickListener mClickListener;
    private List<NewsModel.Article> articles = new ArrayList<>();

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setmClickListener(ClickListener mClickListener){
        this.mClickListener = mClickListener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_news_cell, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsModel.Article article = articles.get(position);
        if(null != article){
            holder.bindData(article);
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void addAll(List<NewsModel.Article> articles) {
        this.articles.clear();
        this.articles = articles;
        notifyDataSetChanged();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView newsTitle;
        private NewsModel.Article article;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.newsTitle);
            itemView.setOnClickListener(this);
        }

        public void bindData(NewsModel.Article article) {
            this.article = article;
            newsTitle.setText(article.getTitle());
        }

        @Override
        public void onClick(View view) {
            if(null != mClickListener){
                mClickListener.itemClicked(view, article);
            }
        }
    }

    public interface ClickListener {
        void itemClicked(View view, NewsModel.Article article);
    }
}
