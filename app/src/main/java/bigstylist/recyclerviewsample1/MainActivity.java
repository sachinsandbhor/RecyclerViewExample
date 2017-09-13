package bigstylist.recyclerviewsample1;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import bigstylist.recyclerviewsample1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainPresenter.ArticleListener, NewsAdapter.ClickListener {

    private ActivityMainBinding activityMainBinding;
    private NewsAdapter mNewsAdapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        initViews();
        mainPresenter.getArticles();
    }

    private void initViews() {
        activityMainBinding.newsRecyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.scrollToPosition(0);
        activityMainBinding.newsRecyclerview.setLayoutManager(linearLayoutManager);
        mNewsAdapter = new NewsAdapter(this);
        activityMainBinding.newsRecyclerview.setAdapter(mNewsAdapter);
        mNewsAdapter.setmClickListener(this);
    }

    @Override
    public void returnArticles(List<NewsModel.Article> articles) {
        mNewsAdapter.addAll(articles);
    }

    @Override
    public void returnError(String errorMessage) {

    }

    @Override
    public void itemClicked(View view, NewsModel.Article article) {
        Toast.makeText(this, article.getTitle(), Toast.LENGTH_LONG).show();
    }
}
