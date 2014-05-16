package com.example.maskson;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class MyTextWatchereski implements TextWatcher {

	public static int myNumberCount = 0;
	public static boolean changing = false;
	public EditText phoneEditText;

	public MyTextWatchereski(EditText phoneEditText) {
		this.phoneEditText = phoneEditText;
	}

	public static String changeText(CharSequence a) {

		String myResult = "";

		for (int i = 0; i < a.length(); i++) {
			char myChar = a.charAt(i);
			if ((int) myChar >= 48 && (int) myChar <= 57) {
				myResult = myResult + myChar;
			}
		}
		return myResult;
	}

	public static String returnMyNumber(CharSequence b) {
		String myNumbers = changeText(b);

		myNumberCount = myNumbers.length();

		int mostCharacters = 10;
		int dashToAdd = mostCharacters - myNumbers.length();
		
		System.out.println("dashToAdd: "+dashToAdd);
		for (int i = 0; i < dashToAdd; i++) {
			myNumbers = myNumbers + "-";
		}

		CharSequence firstPart = myNumbers.subSequence(0, 3);
		CharSequence secondPart = myNumbers.subSequence(3, 6);
		CharSequence thirdPart = myNumbers.subSequence(6, myNumbers.length());

		System.out.println(firstPart);
		System.out.println(secondPart);

		return firstPart + " " + secondPart + " " + thirdPart;
	}

	public static boolean contains(CharSequence searchString, Character a) {

		for (int i = 0; i < searchString.length(); i++) {
			char myChar = searchString.charAt(i);
			if (myChar == a) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void afterTextChanged(Editable s) {
		Log.i("AfterTextChanged", "S : " + s.subSequence(0, s.length()));
		if (s.length() == 13) {
			s = (Editable) s.subSequence(0, 12);
		}

//		if (!contains(s.subSequence(0, s.length()), ' ') && s.length() > 3) {
//			s = (Editable) s.subSequence(0, 3);
//		}
		System.out.println("changing 1"+changing);
		if (!changing) {
			changing = true;
			System.out.println("changing2: "+changing);
			System.out.println("s.length: "+s.length());
			phoneEditText.setText(returnMyNumber(s.subSequence(0, s.length())));
			System.out.println("Mynumbercount: "+myNumberCount);

			if (myNumberCount >= 6) {
				phoneEditText.setSelection(myNumberCount + 2);
			}
			else if (myNumberCount >= 3 && myNumberCount < 6) {
				phoneEditText.setSelection(myNumberCount + 1);
			} else {
				phoneEditText.setSelection(myNumberCount + 0);
			}
			changing = false;

		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}
}
