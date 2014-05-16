package com.example.maskson;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class MyTextWatcher2 implements TextWatcher {

	public static int myNumberCount = 0;
	public static boolean changing = false;
	public EditText phoneEditText;

	public MyTextWatcher2(EditText phoneEditText) {
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
		System.out.println("myNumberss   "+myNumbers);
		
		int mostCharacters = 16;
		int dashToAdd = mostCharacters - myNumbers.length();
		
		System.out.println("dashToAdd: "+dashToAdd);
		for (int i = 0; i < dashToAdd; i++) {
			myNumbers = myNumbers + "-";
		}
		
		CharSequence firstPart = myNumbers.subSequence(0, 3);
		CharSequence secondPart = myNumbers.subSequence(3, 6);
		CharSequence thirdPart = myNumbers.subSequence(6, myNumbers.length());
		
		return firstPart+")"+secondPart+" "+thirdPart;
	
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
		if (s.length() == 18) {
			s = (Editable) s.subSequence(0, 17);
			System.out.println("s==>"+s);
		}
		
		if (!changing) {
			changing = true;
			phoneEditText.setText(returnMyNumber(s.subSequence(0, s.length())));
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
