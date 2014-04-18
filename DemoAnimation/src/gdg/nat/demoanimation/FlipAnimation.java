package gdg.nat.demoanimation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.AnimatorSet;

public class FlipAnimation extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flip_animation);
		
		if(savedInstanceState == null){
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment{

		private EditText mEditText;
		private Button mButton;
		private TextView mTextView;
		private Button mButtonHide;
		
		public PlaceholderFragment(){
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState){
			View rootView = inflater.inflate(R.layout.fragment_flip_animation,
					container, false);

			/*
			 * Get references to resources
			 */
			mEditText = (EditText) rootView.findViewById(R.id.mEditText);
			mButton = (Button) rootView.findViewById(R.id.mButton);
			mTextView = (TextView) rootView.findViewById(R.id.mTextView);
			mButtonHide = (Button) rootView.findViewById(R.id.mButtonHide);

			/*
			 * Set onClickListener for Button: Flip and show
			 */
			mButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v){
					AnimatorSet flipAndShowAnimationSet = (AnimatorSet) AnimatorInflater
							.loadAnimator(getActivity(),
									R.animator.flip_left_and_show);
					String text = mEditText.getText().toString();
					mTextView.setText(text);
					flipAndShowAnimationSet.setTarget(mTextView);
					flipAndShowAnimationSet.start();
				}
			});

			/*
			 * Set onClickListener for Button: Flip and hide
			 */
			mButtonHide.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v){
					AnimatorSet flipAndHideAnimationSet = (AnimatorSet) AnimatorInflater
							.loadAnimator(getActivity(),
									R.animator.flip_right_and_hide);
					flipAndHideAnimationSet.setTarget(mTextView);
					flipAndHideAnimationSet.start();
				}
			});

			return rootView;
		}

	}

}
