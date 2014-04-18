package gdg.nat.demoanimation;

import gdg.nat.demoanimation.Utils.SlideOutAnimation;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.AnimatorSet;

public class SlideOutDemo extends ActionBarActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_out_animation);
		
		if(savedInstanceState == null){
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements
			OnClickListener{
		
		private TextView bombTextView;
		private SlideOutAnimation slideAnim;
		private Context mContext;
		private Handler mHandler;
		private ImageView boomImageView;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState){
			View rootView = inflater.inflate(
					R.layout.fragment_slide_out_animation, container, false);
			
			/*
			 * Initiate resource
			 */
			mContext = getActivity();
			slideAnim = new SlideOutAnimation(mContext);
			mHandler = new Handler();
			
			/*
			 * Get references to resources
			 */

			bombTextView = (TextView) rootView.findViewById(R.id.bombTextView);
			slideAnim.initSlideOutButton(bombTextView,
					SlideOutAnimation.BUTTON_ON_THE_RIGHT, 50);
			boomImageView = (ImageView) rootView
					.findViewById(R.id.boomImageView);
			
			// Set onClick
			bombTextView.setOnClickListener(this);
			return rootView;
		}
		
		@Override
		public void onClick(View v){
			switch(v.getId()){
				case R.id.bombTextView:
					long pressAtTime = System.currentTimeMillis();
					if(bombTextView.getTag(R.id.bombTextView) != null){
						if(pressAtTime
								- (Long) bombTextView.getTag(R.id.bombTextView) <= 2000){
							Toast.makeText(mContext, "Activated",
									Toast.LENGTH_LONG).show();
							bombAnimation();
							return;
						}
					}
					bombTextView.setTag(R.id.bombTextView, pressAtTime);
					mHandler.post(new Runnable(){
						
						@Override
						public void run(){
							slideAnim.slideOut(bombTextView);
							
						}
					});
					bombTextView.postDelayed(new Runnable(){
						
						@Override
						public void run(){
							slideAnim.slideIn(bombTextView);
							
						}
					}, 2000);
					break;
				
				default:
					break;
			}

		}
		
		private void bombAnimation(){
			AnimatorSet boomAni = (AnimatorSet) AnimatorInflater.loadAnimator(
					mContext, R.animator.boom_animation);
			boomAni.setTarget(boomImageView);
			boomAni.start();
			
			MediaPlayer boomSound = MediaPlayer.create(mContext,
					R.raw.bomb_exploding);
			boomSound.start();
		}
	}
	
}
