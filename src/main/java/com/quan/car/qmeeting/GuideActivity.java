package com.quan.car.qmeeting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import utils.DepthPageTransformer;

public class GuideActivity extends Activity {

    private ViewPager mGuideViewPager;
    private int mImgIds[] = new int[]{R.drawable.p1,R.drawable.p2,R.drawable.p3};
    private List<ImageView> mImagesList;
    private static final String TAG = "GuideActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);

        setContentView(R.layout.guide);

        mImagesList = new ArrayList<ImageView>();

        mGuideViewPager = (ViewPager) findViewById(R.id.image_vp_guide);

        //添加切换动画效果(3.0以上)
        mGuideViewPager.setPageTransformer(true,new DepthPageTransformer());

        mGuideViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mImgIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                ImageView imageView = new ImageView(GuideActivity.this);
                imageView.setImageResource(mImgIds[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//保证图片不变形
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(GuideActivity.this,position,Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "instantiateItem: " + position);
                        if (position == mImgIds.length-1){
                            Intent i = new Intent();
                            i.setClass(GuideActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                    }
                });

                container.addView(imageView);
                mImagesList.add(imageView);

                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImagesList.get(position));
            }
        });

    }
}
