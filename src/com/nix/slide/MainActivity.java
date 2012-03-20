package com.nix.slide;

import java.util.Random;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	private ViewFlipper flipper;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		flipper = (ViewFlipper) findViewById(R.id.flipper);

		(new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				while(true) {
					try {
						Thread.sleep(5000);
						publishProgress();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
			}

			@Override
			protected void onProgressUpdate(Void... values) {
				super.onProgressUpdate(values);
				mySlideShow();
			}

		}).execute();
	}

	protected void mySlideShow() {
		Random generator = new Random();
		int fromX = generator.nextInt(3) - 1;
		int toX = generator.nextInt(3) - 1;
		int toY = generator.nextInt(3) - 1;
		int fromY = generator.nextInt(3) - 1;

		if (fromX == 0 && fromY == 0) {
			int tmp = generator.nextInt(3) - 1;
			if(tmp!=0){
				fromX = 0;
				fromY = tmp;
			}else{
				fromY = 0;
				fromX = generator.nextInt(3) - 1;
			}
		}
		if (toX == 0 && toY == 0) {
			int tmp = generator.nextInt(3) - 1;
			if(tmp!=0){
				toX = 0;
				toY = tmp;
			}else{
				toY = 0;
				toX = generator.nextInt(3) - 1;
			}
		}

		flipper.setInAnimation(inAnimation(fromX, fromY));

		flipper.setOutAnimation(outAnimation(toX, toY));
		flipper.showNext();
	}

//	private Animation allAnimation(int fromX, int toX, int fromY, int toY) {
//
//		Animation inFromRight = new TranslateAnimation(
//				Animation.RELATIVE_TO_PARENT, fromX,
//				Animation.RELATIVE_TO_PARENT, toX,
//				Animation.RELATIVE_TO_PARENT, fromY,
//				Animation.RELATIVE_TO_PARENT, toY);
//		return inFromRight;
//	}

	private Animation inAnimation(int fromX, int fromY) {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, fromX,
				Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
				fromY, Animation.RELATIVE_TO_PARENT, 0);
		inFromLeft.setDuration(800);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	private Animation outAnimation(int toX, int toY) {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
				toX, Animation.RELATIVE_TO_PARENT, 0,
				Animation.RELATIVE_TO_PARENT, toY);
		inFromLeft.setDuration(800);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

}