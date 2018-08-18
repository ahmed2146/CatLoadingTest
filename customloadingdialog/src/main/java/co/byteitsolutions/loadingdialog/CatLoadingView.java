package co.byteitsolutions.loadingdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.byteitsolutions.loadingdialog.R;

public class CatLoadingView extends DialogFragment {

    public CatLoadingView() {
    }

    Animation carAnimation, tyreAnimation;
    Dialog mDialog;
    View carView, tyreView;
    GraduallyTextView mGraduallyTextView;
    String text;

    private boolean isClickCancelAble = true;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mDialog == null) {
            mDialog = new Dialog(getActivity(), R.style.cart_dialog);
            mDialog.setContentView(R.layout.catloading_main);
            mDialog.setCanceledOnTouchOutside(isClickCancelAble);
            mDialog.getWindow().setGravity(Gravity.CENTER);

            carAnimation = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.49f);
            carAnimation.setRepeatCount(Animation.INFINITE);
            carAnimation.setDuration(2000);

            tyreAnimation = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            tyreAnimation.setRepeatCount(Animation.INFINITE);
            tyreAnimation.setDuration(800);


            LinearInterpolator lin = new LinearInterpolator();
            carAnimation.setInterpolator(lin);
            tyreAnimation.setInterpolator(lin);

            View view = mDialog.getWindow().getDecorView();

            carView = view.findViewById(R.id.carImageView);

            tyreView = view.findViewById(R.id.tyreImageView);


            mGraduallyTextView = (GraduallyTextView) view.findViewById(R.id.graduallyTextView);

            if (!TextUtils.isEmpty(text)) {
                mGraduallyTextView.setText(text);
            }

            carAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
        return mDialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        carView.setAnimation(carAnimation);
        tyreView.setAnimation(tyreAnimation);
        mGraduallyTextView.startLoading();
    }

    @Override
    public void onPause() {
        super.onPause();

        carAnimation.reset();
        tyreAnimation.reset();

        carView.clearAnimation();
        tyreView.clearAnimation();

        mGraduallyTextView.stopLoading();
    }

    public void setText(String str) {
        text = str;
    }

    public void setClickCancelAble(boolean bo) {
        isClickCancelAble = bo;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mDialog = null;
        System.gc();
    }
}
