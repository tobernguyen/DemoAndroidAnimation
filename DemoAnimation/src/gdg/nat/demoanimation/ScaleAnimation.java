package gdg.nat.demoanimation;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.AnimatorSet;

public class ScaleAnimation extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scale_animation);
		
		if(savedInstanceState == null){
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment{
		/*
		 * Initiate variables
		 */
		TextView textViewDemo;
		Button buttonDemo, buttonZoomIn, buttonZoomOut;
		ImageButton imageButtonDemo;

		Context mContext;
		Handler mHandle = new Handler();

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState){
			View rootView = inflater.inflate(R.layout.fragment_scale_animation,
					container, false);

			/*
			 * Get resources
			 */
			textViewDemo = (TextView) rootView.findViewById(R.id.textViewDemo);
			buttonDemo = (Button) rootView.findViewById(R.id.buttonDemo);
			buttonZoomIn = (Button) rootView.findViewById(R.id.buttonZoomIn);
			buttonZoomOut = (Button) rootView.findViewById(R.id.buttonZoomOut);
			imageButtonDemo = (ImageButton) rootView
					.findViewById(R.id.imageButtonDemo);
			mContext = getActivity();

			/*
			 * Inflate Animation
			 */

			/*
			 * Set OnClickListener
			 */
			buttonZoomIn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v){
					// Do Zoom In
					mHandle.post(new doZoomInHandle(textViewDemo));
					mHandle.post(new doZoomInHandle(buttonDemo));
					mHandle.post(new doZoomInHandle(imageButtonDemo));

				}
			});
			
			buttonZoomOut.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v){
					// Do Zoom In
					mHandle.post(new doZoomOutHandle(textViewDemo));
					mHandle.post(new doZoomOutHandle(buttonDemo));
					mHandle.post(new doZoomOutHandle(imageButtonDemo));
				}
			});

			return rootView;
		}
		
		class doZoomInHandle implements Runnable{

			View object;
			AnimatorSet zoomInAnimationSet;
			
			public doZoomInHandle(View object){
				this.object = object;
				zoomInAnimationSet = (AnimatorSet) AnimatorInflater
						.loadAnimator(mContext, R.animator.zoom_in);
			}
			
			private void doZoomIn(View object){
				zoomInAnimationSet.setTarget(object);
				zoomInAnimationSet.start();
			}

			@Override
			public void run(){
				doZoomIn(object);
			}

		}
		
		class doZoomOutHandle implements Runnable{

			View object;
			AnimatorSet zoomOutAnimationSet;
			
			public doZoomOutHandle(View object){
				this.object = object;
				zoomOutAnimationSet = (AnimatorSet) AnimatorInflater
						.loadAnimator(mContext, R.animator.zoom_out);
			}
			
			private void doZoomOut(View object){
				zoomOutAnimationSet.setTarget(object);
				zoomOutAnimationSet.start();
			}

			@Override
			public void run(){
				doZoomOut(object);
			}

		}

	}

}
