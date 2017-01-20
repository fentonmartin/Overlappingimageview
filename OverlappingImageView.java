// Add your package 

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by David Harush
 * on 22/03/2016.
 * <br/>
 * <br/>
 * ImageView with options to Draw Drawable on the front or the back of the image.
 * <br/>
 * You can supply the Drawable as xml shape resources or png image resources.
 * <br/>
 * The Drawable will resize according to the ImageView size.
 */
public class OverlappingImageView extends ImageView {

    public static final String TAG = OverlappingImageView.class.getSimpleName();

    private boolean mMaskEnable = false;
    private boolean mDrawOnFront = true;
    private int mShapeID = 0;
    private String mAndroidId;

    // ================================== //
    // ========== CONSTRUCTORS ========== //
    // ================================== //

    public OverlappingImageView(Context context) {
        super(context);
        if (!isInEditMode()) {
            init();
        }
    }

    public OverlappingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init();
        }
    }

    public OverlappingImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            init();
        }
    }

    // ======================================== //
    // ========== OVERRIDDEN METHODS ========== //
    // ======================================== //

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDrawOnFront) { // Draw the mask on Front
            super.onDraw(canvas);
            drawMaskImage(canvas);
        } else { // Draw the mask on Background
            drawMaskImage(canvas);
            super.onDraw(canvas);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        toggleMask();
        return super.onTouchEvent(event);
    }

    // ================================ //
    // ==== PRIVATE CLASS METHODS ===== //
    // ================================ //

    private void init() {
        mAndroidId = getResources().getResourceEntryName(getId());
    }

    private void drawMaskImage(Canvas canvas) {
        if (mMaskEnable && mShapeID != 0) {
            Drawable drawable;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                drawable = getResources().getDrawable(mShapeID, getContext().getTheme());
            } else {
                drawable = getResources().getDrawable(mShapeID);
            }

            if (drawable != null) {
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
            }
        } else if (mShapeID == 0) {
            Log.w(TAG, "WARNING ─>> Mask not set for " + TAG + ": android:id=\"@+id/" + mAndroidId + "\" !!!");
        }

    }

    // ================================ //
    // ==== PUBLIC CLASS METHODS ====== //
    // ================================ //

    public void showMask(boolean mask) {
        if (mask == mMaskEnable) {
            return;
        }
        this.mMaskEnable = mask;
        invalidate();
    }

    public void toggleMask() {
        showMask(!mMaskEnable);
    }

    /**
     * if onFront == true -> Draw mask On front of the image
     * <br/>
     * if onBack == false -> Draw mask On back of the image
     */
    public OverlappingImageView setDrawOnFront(boolean onFront) {
        mDrawOnFront = onFront;
        return this;
    }

    /**
     * Set drawable res to draw on the imageView
     * can be XML shape or drawable
     *
     * @param id of the Res.
     */
    public OverlappingImageView setMaskShape(@DrawableRes int id) {
        mShapeID = id;
        return this;
    }

    /**
     * @return True if the mask is Visible , false otherwise.
     */
    public boolean isMaskVisible() {
        return this.mMaskEnable;
    }

    /**
     * @return True if mask is set, false otherwise.
     */
    public boolean isMaskSet() {
       return 0 != this.mShapeID;
    }


}