package gdg.nat.demoanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AnimationSelectScreen extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_select_screen);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener {

		/*
		 * Initiate variables
		 */
		Button buttonScaleAnimation, buttonFlipAnimation, buttonHideAndSlideAnimation;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater
					.inflate(R.layout.fragment_animation_select_screen,
							container, false);

			/*
			 * Initiate resources
			 */
			buttonScaleAnimation = (Button) rootView.findViewById(R.id.button1);
			buttonFlipAnimation = (Button) rootView.findViewById(R.id.button2);
			buttonHideAndSlideAnimation = (Button) rootView.findViewById(R.id.button3);
			
			/*
			 * Set onClickListener
			 */
			buttonScaleAnimation.setOnClickListener(this);
			buttonFlipAnimation.setOnClickListener(this);
			buttonHideAndSlideAnimation.setOnClickListener(this);
			
			return rootView;
		}

		@Override
		public void onClick(View v) {
			//Intent to switch activities
			Intent intent = null;
			
			// Implement onClick for each button
			switch (v.getId()) {
			case R.id.button1:
				intent = new Intent(getActivity(), ScaleAnimation.class);
				break;
			case R.id.button2:
				intent = new Intent(getActivity(), FlipAnimation.class);
				break;
			case R.id.button3:
					intent = new Intent(getActivity(), SlideOutDemo.class);
				break;
			default:
				break;
			
			}
			
			startActivity(intent);
		}
	}


}
