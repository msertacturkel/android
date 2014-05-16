package com.example.maskson;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class MyTextWatcher implements TextWatcher {

	public static int myNumberCount = 0;
	public static boolean changing = false;
	public EditText phoneEditText;

	public MyTextWatcher(EditText phoneEditText) {
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
		System.out.println("myNUmber"+myNumbers);
		myNumberCount = myNumbers.length()-2;
		int mostCharacters = 12;
		int dashToAdd = mostCharacters - myNumbers.length();

		for (int i = 0; i < dashToAdd; i++) {
			myNumbers = myNumbers + "-";
		}

		CharSequence firstPart = myNumbers.subSequence(2, 5);
		CharSequence secondPart = myNumbers.subSequence(5, 8);
		CharSequence thirdPart = myNumbers.subSequence(8, myNumbers.length());

		return "+90(" + firstPart + ")" + secondPart + " " + thirdPart;

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
		if (s.length() == 17) {
			s = (Editable) s.subSequence(0, 16);
		}
		if (!contains(s.subSequence(0, s.length()), ')') && s.length() > 3){
			s = (Editable) s.subSequence(0, 3);
		}
		if (!changing){
			changing = true;
			phoneEditText.setText(returnMyNumber(s.subSequence(0, s.length())));
			System.out.println("myCOuntNUMBER:  "+myNumberCount);
			if (myNumberCount <= 3){
				phoneEditText.setSelection(myNumberCount + 4);
			}else if((myNumberCount <= 6) && (myNumberCount > 3)){
				phoneEditText.setSelection(myNumberCount + 5);
			}else{
				phoneEditText.setSelection(myNumberCount + 6);
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
