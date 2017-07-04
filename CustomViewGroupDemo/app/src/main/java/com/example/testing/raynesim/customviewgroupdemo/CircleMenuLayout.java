package com.example.testing.raynesim.customviewgroupdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by leisure on 2017/6/22.
 */

public class CircleMenuLayout extends ViewGroup{

    //圆形直径
    private int mRadius;
    //该容器内Children的默认尺寸
    private static final float RADIO_DEFAULE_CHILE_DIMENSION = 1/4F;
    //该容器的内边距，无视padding属性，如需边距，请用该变量
    private static final float RADIO_PADDING_LAYOUT = 1/12F;
    //该容器的内边距，无视padding属性，如需边距，请用该变量
    private float mPadding;
    //布局开始时的角度
    private double mStartAngle = 0;
    //MenuItem的点击事件接口
    private AdapterView.OnItemClickListener mOnMenuItemClickListener;

    private ListAdapter mAdapter;

    public CircleMenuLayout(Context context) {
        super(context);
    }

    public CircleMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPadding(0, 0, 0, 0);//no padding
    }

    public CircleMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //设置Adapter
    public void setAdapter(ListAdapter adapter){
        this.mAdapter = adapter;
    }

    @Override
    protected void onAttachedToWindow() {
        if (mAdapter != null){
            buildMenuItem();
        }
        super.onAttachedToWindow();
    }

    //构建菜单项
    private void buildMenuItem() {
        for (int i = 0; i < mAdapter.getCount(); i++){
            //添加view到容器中
            final View itemView = mAdapter.getView(i, null, this);
            final int position = i;
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnMenuItemClickListener.onItemClick(null, v, position, position);
                }
            });
            addView(itemView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //丈量自身尺寸
        measureMyself(widthMeasureSpec, heightMeasureSpec);
        //丈量菜单项尺寸
        measureChildViews();
    }

    private void measureMyself(int widthMeasureSpec, int heightMeasureSpec) {
        int resWidth = 0;
        int resHeight = 0;

        //根据传入的参数，分别获取测量模式和测量值
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //如果宽或者高的测量模式非精确
        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY){
            //主要设置为背景图的高度
            resWidth = getSuggestedMinimumWidth();
            //如果背景图未设置，则设置为屏幕宽高的默认值
            resWidth = resWidth == 0 ? getMeasuredWidth(): resWidth;

            resHeight = getSuggestedMinimumHeight();
            //如果背景图未设置，则设置为屏幕宽高的默认值
            resHeight = resHeight == 0 ? getMeasuredHeight() : resHeight;
        }else {
            resWidth = resHeight = Math.min(width, height);
        }
        setMeasuredDimension(resWidth, resHeight);
    }

    private void measureChildViews() {
        //获得半径
        mRadius = Math.max(getMeasuredWidth(), getMeasuredHeight());
        //menu item 数量
        final int count = getChildCount();
        //menu item 尺寸
        int childSize = (int) (mRadius * RADIO_DEFAULE_CHILE_DIMENSION);
        //menu item 测量模式
        int childMode = MeasureSpec.EXACTLY;
        for (int i = 0; i < count; i++){
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE){
                continue;
            }
            //计算menu item 的尺寸，以及设置好的模式，去对item进行测量
            int makeMeasureSpec = -1;
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(childSize, childMode);
            child.measure(makeMeasureSpec, makeMeasureSpec);
        }
        mPadding = RADIO_PADDING_LAYOUT * mRadius;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        final int childCount = getChildCount();
        int left, top;
        //menu item 的尺寸
        int itemWidth = (int) (mRadius * RADIO_DEFAULE_CHILE_DIMENSION);
        //根据item个数，计算item布局占用的角度
        float angleDelay = 360 / childCount;
        //遍历所有菜单项，设置他们的位置
        for (int i=0; i<childCount; i++){
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE){
                continue;
            }
            //菜单项的起始角度
            mStartAngle %= 360;
            //计算，中心点到menu item中心的距离
            float distanceFromCenter = mRadius / 2f - itemWidth / 2 - mPadding;
            //left distanceFromCenter cos 即menu item的横坐标
            left = (int) (mRadius / 2 + Math.round(distanceFromCenter
                    * Math.cos(Math.toRadians(mStartAngle)) - 1 / 2f * itemWidth));
            //top distanceFromCenter sin 即menu item的纵坐标
            top = (int) (mRadius / 2 + Math.round(distanceFromCenter
                    * Math.sin(Math.toRadians(mStartAngle)) - 1/2f * itemWidth));
            //布局child view
            child.layout(left, top, left + itemWidth, top + itemWidth);
            //叠加尺寸
            mStartAngle += angleDelay;
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        this.mOnMenuItemClickListener = listener;
    }

}
