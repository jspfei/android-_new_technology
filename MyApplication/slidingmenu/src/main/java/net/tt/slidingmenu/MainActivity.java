package net.tt.slidingmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.tt.slidingmenu.view.SlideMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private SlideMenu slideMenu;
    private LinearLayout ll_content;
    private LayoutInflater inflater;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideMenu = (SlideMenu) findViewById(R.id.slidemenu);

        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        inflater = LayoutInflater.from(this);

        view = inflater.inflate(R.layout.frag_news,null);
        ll_content.removeAllViews();
        ll_content.addView(view);

        ImageButton backBtn = (ImageButton) findViewById(R.id.ib_back);
        backBtn.setOnClickListener(this);

        findViewById(R.id.tab_news).setOnClickListener(this);
        findViewById(R.id.tab_read).setOnClickListener(this);
        findViewById(R.id.tab_local).setOnClickListener(this);
        findViewById(R.id.tab_ties).setOnClickListener(this);
        findViewById(R.id.tab_pics).setOnClickListener(this);
        findViewById(R.id.tab_focus).setOnClickListener(this);
        findViewById(R.id.tab_vote).setOnClickListener(this);
        findViewById(R.id.tab_ugc).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                if (slideMenu.isShowMenu()) {
                    slideMenu.hideMenu();
                } else {
                    slideMenu.showMenu();
                }
                break;
            case R.id.tab_news: // 新闻
                view = inflater.inflate(R.layout.frag_news, null);
                switchView();
                break;
            case R.id.tab_read: // 订阅
                view = inflater.inflate(R.layout.frag_read, null);
                switchView();
                break;
            case R.id.tab_local: // 本地
                view = inflater.inflate(R.layout.frag_local, null);
                switchView();
                break;
            case R.id.tab_ties: // 跟帖
                view = inflater.inflate(R.layout.frag_ties, null);
                switchView();
                break;
            case R.id.tab_pics: // 图片
                view = inflater.inflate(R.layout.frag_pics, null);
                switchView();
                break;
            case R.id.tab_focus: // 话题
                view = inflater.inflate(R.layout.frag_focus, null);
                switchView();
                break;
            case R.id.tab_vote: // 投票
                view = inflater.inflate(R.layout.frag_vote, null);
                switchView();
                break;
            case R.id.tab_ugc: // 聚合阅读
                view = inflater.inflate(R.layout.frag_ugc, null);
                switchView();
                break;
            default:
                break;

        }
    }
    private void switchView() {
        if (view != null) {
            ll_content.removeAllViews();
            ll_content.addView(view);
            if (slideMenu.isShowMenu()) {
                slideMenu.hideMenu();
            } else {
                slideMenu.showMenu();
            }
        }
    }
    private void setBackground(TextView textView,int color){
        textView.setBackgroundColor(color);
    }
}
