package com.test.wzq.androidgalleryview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzq on 16/8/9.
 */
public class LoopView extends ViewGroup {

    public static final int STATUS_ON = 1;

    public static final int STATUS_OFF = 0;

    //view
    private int mWidth, mHeight;

    //default status
    private int mStatus = STATUS_OFF;

    //move length
    private int offset;

    private int mDuration = 500;


    //repeat times
    private int mRepeats = 0;


    private List<String> mImageUrl = new ArrayList<>();
    private ImageView[] mImages = new ImageView[2];
    private View mShadowView;

    private Action action;

    public LoopView(Context context) {
        super(context, null);
    }

    public LoopView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public LoopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        offset = w;
        initViews();
    }

    private void initViews() {
        if (mImageUrl.size() == 0)
            return;
        removeAllViews();
        MarginLayoutParams params = new MarginLayoutParams(mWidth, mHeight);
        for (int i = 0; i < mImages.length; i++) {
            mImages[i] = new ImageView(getContext());
            mImages[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            addViewInLayout(mImages[i], -1, params, true);
            action.loadPicture(getImagePath(i), mImages[i]);
        }

        mShadowView = new View(getContext());
        mShadowView.setBackgroundColor(Color.parseColor("#60000000"));
        mShadowView.setAlpha(0);
        addViewInLayout(mShadowView, -1, params, true);
    }


    @Override
    protected void onLayout(boolean c, int l, int t, int r, int b) {
        int cCount = getChildCount();
        MarginLayoutParams cParams;

        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr, cb;

            if (isOddCircle()) {
                if (i == 1) {
                    ct = cParams.topMargin;
                    cl = offset - mWidth;
                } else if (i == 0) {
                    ct = cParams.topMargin;
                    cl = offset;
                }
            } else {
                if (i == 0) {
                    ct = cParams.topMargin;
                    cl = offset - mWidth;
                } else if (i == 1) {
                    ct = cParams.topMargin;
                    cl = offset;
                }
            }

            if (i == 2) {
                ct = cParams.topMargin;
                cl = offset + mWidth;
            }

            cr = cl + mWidth;
            cb = ct + mHeight;
            childView.layout(cl, ct, cr, cb);
        }

    }

    protected boolean isOddCircle() {
        return mRepeats % 2 == 1;
    }

    private String getImagePath(int position) {
        position = position % mImageUrl.size();
        return mImageUrl.get(position);
    }


    /**
     * 开启滑动
     */
    public void startSmooth() {

        if (mStatus != STATUS_OFF) {
            return;
        }

        ValueAnimator animator = ValueAnimator.ofFloat(mWidth, 0);
        animator.setDuration(mDuration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float marginTop = (float) animation.getAnimatedValue();
                offset = (int) marginTop;
                if (marginTop == 0) {

                    postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            mRepeats++;

                            offset = mWidth;

                            doAnimFinish();

                            mStatus = STATUS_OFF;

                        }
                    }, 50);

                } else {
                    doAnim();
                }
            }
        });
        animator.start();
        mStatus = STATUS_ON;
    }

    protected void doAnimFinish() {
        action.loadPicture(getImagePath(mRepeats + 1), isOddCircle()? mImages[0]: mImages[1]);
        mShadowView.setAlpha(0);
        action.onChange(mRepeats);
    }

    protected void doAnim() {
        mShadowView.setAlpha(((1 - (offset) / (float) mHeight)));
        requestLayout();
    }

    public void setImgList(List<String> imgList) {
        mImageUrl = imgList;
        //initViews();
    }

    public void setAction(Action action0){
        this.action = action0;
    }

    public interface Action{
        void loadPicture(String url, ImageView view);

        void onChange(int position);
    }
}
