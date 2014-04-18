package gdg.nat.demoanimation.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class SlideOutAnimation{
	
	public static final int BUTTON_ON_THE_LEFT = -1;
	public static final int BUTTON_ON_THE_RIGHT = 1;
	private static final int ANIMATION_DURATION = 500;
	
	Context mContext;
	AnimatorSet mAnimatorSet;
	
	public SlideOutAnimation(Context context){
		this.mContext = context;
		
	}
	
	public void initSlideOutButton(View target, int buttonPosition,
			float howManyDip){
		float translation_px = convertDpToPixel(howManyDip);
		Bundle buttonInfo = new Bundle();
		
		buttonInfo.putInt("BUTTON_POSITION", buttonPosition);
		buttonInfo.putFloat("AMOUNT_PX", translation_px);
		
		mAnimatorSet = new AnimatorSet();
		
		if(buttonPosition == BUTTON_ON_THE_RIGHT){
			// Translation button to the left 150px
			mAnimatorSet.playTogether(ObjectAnimator.ofFloat(target,
					"TranslationX", translation_px, translation_px));
			
		}else if(buttonPosition == BUTTON_ON_THE_LEFT){
			mAnimatorSet.playTogether(ObjectAnimator.ofFloat(target,
					"TranslationX", -translation_px, -translation_px));
		}else
			return;
		
		target.setTag(buttonInfo);
		mAnimatorSet.setDuration(0);
		mAnimatorSet.start();
	}
	
	public void slideOut(View target){
		if(target.getTag() != null){
			Bundle buttonInfo = (Bundle) target.getTag();
			mAnimatorSet = new AnimatorSet();
			int buttonPosition = buttonInfo.getInt("BUTTON_POSITION");
			float translation_px = buttonInfo.getFloat("AMOUNT_PX");
			if(buttonPosition == BUTTON_ON_THE_RIGHT){
				mAnimatorSet.playTogether(ObjectAnimator.ofFloat(target,
						"TranslationX", translation_px, 0));
			}else if(buttonPosition == BUTTON_ON_THE_LEFT){
				mAnimatorSet.playTogether(ObjectAnimator.ofFloat(target,
						"TranslationX", 0, translation_px));
			}else
				return;
		}else
			return;
		mAnimatorSet.setDuration(ANIMATION_DURATION);
		mAnimatorSet.start();
	}
	
	public void slideIn(View target){
		if(target.getTag() != null){
			Bundle buttonInfo = (Bundle) target.getTag();
			mAnimatorSet = new AnimatorSet();
			int buttonPosition = buttonInfo.getInt("BUTTON_POSITION");
			float translation_px = buttonInfo.getFloat("AMOUNT_PX");
			if(buttonPosition == BUTTON_ON_THE_RIGHT){
				mAnimatorSet.playTogether(ObjectAnimator.ofFloat(target,
						"TranslationX", 0, translation_px));
			}else if(buttonPosition == BUTTON_ON_THE_LEFT){
				mAnimatorSet.playTogether(ObjectAnimator.ofFloat(target,
						"TranslationX", translation_px, 0));
			}else
				return;
		}else
			return;
		mAnimatorSet.setDuration(ANIMATION_DURATION);
		mAnimatorSet.start();
	}

	// Convert DpToPixel
	private float convertDpToPixel(float dp){
		Resources resources = mContext.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

}
