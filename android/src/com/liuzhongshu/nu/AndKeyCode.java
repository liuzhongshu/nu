package com.liuzhongshu.nu;

import android.view.KeyEvent;

public class AndKeyCode {

	public static String getNuKey(int keyCode) {
		
		switch (keyCode) {
			case KeyEvent.KEYCODE_0: return "0";
			case KeyEvent.KEYCODE_1: return "1";
			case KeyEvent.KEYCODE_2: return "2";
			case KeyEvent.KEYCODE_3: return "3";
			case KeyEvent.KEYCODE_4: return "4";
			case KeyEvent.KEYCODE_5: return "5";
			case KeyEvent.KEYCODE_6: return "6";
			case KeyEvent.KEYCODE_7: return "7";
			case KeyEvent.KEYCODE_8: return "8";
			case KeyEvent.KEYCODE_9: return "9";

			case KeyEvent.KEYCODE_A: return "a";
			case KeyEvent.KEYCODE_B: return "b";
			case KeyEvent.KEYCODE_C: return "e";
			case KeyEvent.KEYCODE_D: return "d";
			case KeyEvent.KEYCODE_E: return "e";
			case KeyEvent.KEYCODE_F: return "f";
			case KeyEvent.KEYCODE_G: return "g";
			case KeyEvent.KEYCODE_H: return "h";
			case KeyEvent.KEYCODE_I: return "i";
			case KeyEvent.KEYCODE_J: return "j";
			case KeyEvent.KEYCODE_K: return "k";
			case KeyEvent.KEYCODE_L: return "l";
			case KeyEvent.KEYCODE_M: return "m";
			case KeyEvent.KEYCODE_N: return "n";
			case KeyEvent.KEYCODE_O: return "o";
			case KeyEvent.KEYCODE_P: return "p";
			case KeyEvent.KEYCODE_Q: return "q";
			case KeyEvent.KEYCODE_R: return "r";
			case KeyEvent.KEYCODE_S: return "s";
			case KeyEvent.KEYCODE_T: return "t";
			case KeyEvent.KEYCODE_U: return "u";
			case KeyEvent.KEYCODE_V: return "v";
			case KeyEvent.KEYCODE_W: return "w";
			case KeyEvent.KEYCODE_X: return "x";
			case KeyEvent.KEYCODE_Y: return "y";
			case KeyEvent.KEYCODE_Z: return "z";
			
			case KeyEvent.KEYCODE_SPACE: return "space";
			case KeyEvent.KEYCODE_COMMA: return "comma";
			case KeyEvent.KEYCODE_PERIOD: return "period";
			case KeyEvent.KEYCODE_ENTER: return "enter";
			case KeyEvent.KEYCODE_DEL: return "backspace";
			case KeyEvent.KEYCODE_BACKSLASH: return "backslash";
					
			case KeyEvent.KEYCODE_DPAD_UP: return "up";
			case KeyEvent.KEYCODE_DPAD_DOWN: return "down";
			case KeyEvent.KEYCODE_DPAD_LEFT: return "left";
			case KeyEvent.KEYCODE_DPAD_RIGHT:return "right";
			case KeyEvent.KEYCODE_DPAD_CENTER:return "enter";
			
					
			
		}
		return null;
	}
}
