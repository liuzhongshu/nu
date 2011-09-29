package com.liuzhongshu.nu;

import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class KeyCode
{
	
    private static final Map<String, Integer> keyMap;
    static {
        Map<String, Integer> aMap = new HashMap<String,Integer>();
        
        aMap.put("0", KeyEvent.VK_0);
        aMap.put("1", KeyEvent.VK_1);
        aMap.put("2", KeyEvent.VK_2);
        aMap.put("3", KeyEvent.VK_3);
        aMap.put("4", KeyEvent.VK_4);
        aMap.put("5", KeyEvent.VK_5);
        aMap.put("6", KeyEvent.VK_6);
        aMap.put("7", KeyEvent.VK_7);
        aMap.put("8", KeyEvent.VK_8);
        aMap.put("9", KeyEvent.VK_9);
        aMap.put("a", KeyEvent.VK_A);
        aMap.put("b", KeyEvent.VK_B);
        aMap.put("c", KeyEvent.VK_C);
        aMap.put("d", KeyEvent.VK_D);
        aMap.put("e", KeyEvent.VK_E);
        aMap.put("f", KeyEvent.VK_F);
        aMap.put("g", KeyEvent.VK_G);
        aMap.put("h", KeyEvent.VK_H);
        aMap.put("i", KeyEvent.VK_I);
        aMap.put("j", KeyEvent.VK_J);
        aMap.put("k", KeyEvent.VK_K);
		aMap.put("l", KeyEvent.VK_L);
		aMap.put("m", KeyEvent.VK_M);
		aMap.put("n", KeyEvent.VK_N);
		aMap.put("o", KeyEvent.VK_O);
		aMap.put("p", KeyEvent.VK_P);
		aMap.put("q", KeyEvent.VK_Q);
		aMap.put("r", KeyEvent.VK_R);
		aMap.put("s", KeyEvent.VK_S);
		aMap.put("t", KeyEvent.VK_T);
		aMap.put("u", KeyEvent.VK_U);
		aMap.put("v", KeyEvent.VK_V);
		aMap.put("w", KeyEvent.VK_W);
		aMap.put("x", KeyEvent.VK_X);
		aMap.put("y", KeyEvent.VK_Y);
		aMap.put("z", KeyEvent.VK_Z);

        // modifiers 
        aMap.put("shift", KeyEvent.VK_SHIFT);
        aMap.put("control", KeyEvent.VK_CONTROL);
        aMap.put("alt", KeyEvent.VK_ALT);
        aMap.put("meta", KeyEvent.VK_META);
        aMap.put("altgraph", KeyEvent.VK_ALT_GRAPH);
        aMap.put("windows", KeyEvent.VK_WINDOWS);
        aMap.put("contextmenu", KeyEvent.VK_CONTEXT_MENU);
		
        aMap.put("enter", KeyEvent.VK_ENTER);
        aMap.put("backspace", KeyEvent.VK_BACK_SPACE);
        aMap.put("\t", KeyEvent.VK_TAB);
        aMap.put("tab", KeyEvent.VK_TAB);
        aMap.put("cancel", KeyEvent.VK_CANCEL);
        aMap.put("clear", KeyEvent.VK_CLEAR);
        aMap.put("compose", KeyEvent.VK_COMPOSE);
        aMap.put("pause", KeyEvent.VK_PAUSE);
        aMap.put("capslock", KeyEvent.VK_CAPS_LOCK);
        aMap.put("escape", KeyEvent.VK_ESCAPE);
        aMap.put(" ", KeyEvent.VK_SPACE);
        aMap.put("space", KeyEvent.VK_SPACE);
        aMap.put("pageup", KeyEvent.VK_PAGE_UP);
        aMap.put("pagedown", KeyEvent.VK_PAGE_DOWN);
        aMap.put("end", KeyEvent.VK_END);
        aMap.put("home", KeyEvent.VK_HOME);
        aMap.put("left", KeyEvent.VK_LEFT);
        aMap.put("up", KeyEvent.VK_UP);
        aMap.put("right", KeyEvent.VK_RIGHT);
        aMap.put("down", KeyEvent.VK_DOWN);
        aMap.put("begin", KeyEvent.VK_BEGIN);


        // punctuation
        aMap.put(",", KeyEvent.VK_COMMA);
        aMap.put("comma", KeyEvent.VK_COMMA);
        aMap.put(".", KeyEvent.VK_PERIOD);
        aMap.put("period", KeyEvent.VK_PERIOD);
        aMap.put("/", KeyEvent.VK_SLASH);
        aMap.put("slash", KeyEvent.VK_SLASH);
        aMap.put(";", KeyEvent.VK_SEMICOLON);
        aMap.put("semicolon", KeyEvent.VK_SEMICOLON);
        aMap.put("=", KeyEvent.VK_EQUALS);
        aMap.put("equals", KeyEvent.VK_EQUALS);
        aMap.put("[", KeyEvent.VK_OPEN_BRACKET);
        aMap.put("openbracket", KeyEvent.VK_OPEN_BRACKET);
        aMap.put("\\", KeyEvent.VK_BACK_SLASH);
        aMap.put("backslash", KeyEvent.VK_BACK_SLASH);
        aMap.put("]", KeyEvent.VK_CLOSE_BRACKET);
        aMap.put("closebracket", KeyEvent.VK_CLOSE_BRACKET);

        aMap.put("&", KeyEvent.VK_AMPERSAND);
        aMap.put("ampersand", KeyEvent.VK_AMPERSAND);
        aMap.put("*", KeyEvent.VK_ASTERISK);
        aMap.put("asterisk", KeyEvent.VK_ASTERISK);
        aMap.put("\"", KeyEvent.VK_QUOTEDBL);
        aMap.put("doublequote", KeyEvent.VK_QUOTEDBL);
        aMap.put("<", KeyEvent.VK_LESS);
        aMap.put("less", KeyEvent.VK_LESS);
        aMap.put(">", KeyEvent.VK_GREATER);
        aMap.put("greater", KeyEvent.VK_GREATER);
        aMap.put("{", KeyEvent.VK_BRACELEFT);
        aMap.put("leftbrace", KeyEvent.VK_BRACELEFT);
        aMap.put("}", KeyEvent.VK_BRACERIGHT);
        aMap.put("rightbrace", KeyEvent.VK_BRACERIGHT);
        aMap.put("@", KeyEvent.VK_AT);
        aMap.put("at", KeyEvent.VK_AT);
        aMap.put(":", KeyEvent.VK_COLON);
        aMap.put("colon", KeyEvent.VK_COLON);
        aMap.put("^", KeyEvent.VK_CIRCUMFLEX);
        aMap.put("circumflex", KeyEvent.VK_CIRCUMFLEX);
        aMap.put("$", KeyEvent.VK_DOLLAR);
        aMap.put("dollar", KeyEvent.VK_DOLLAR);
        aMap.put("euro", KeyEvent.VK_EURO_SIGN);
        aMap.put("!", KeyEvent.VK_EXCLAMATION_MARK);
        aMap.put("exclamationmark", KeyEvent.VK_EXCLAMATION_MARK);
        aMap.put("invertedexclamationmark", KeyEvent.VK_INVERTED_EXCLAMATION_MARK);
        aMap.put("(", KeyEvent.VK_LEFT_PARENTHESIS);
        aMap.put("leftparenthesis", KeyEvent.VK_LEFT_PARENTHESIS);
        aMap.put("#", KeyEvent.VK_NUMBER_SIGN);
        aMap.put("numbersign", KeyEvent.VK_NUMBER_SIGN);
        aMap.put("-", KeyEvent.VK_MINUS);
        aMap.put("minus", KeyEvent.VK_MINUS);
        aMap.put("+", KeyEvent.VK_PLUS);
        aMap.put("plus", KeyEvent.VK_PLUS);
        aMap.put(")", KeyEvent.VK_RIGHT_PARENTHESIS);
        aMap.put("rightparenthesis", KeyEvent.VK_RIGHT_PARENTHESIS);
        aMap.put("_", KeyEvent.VK_UNDERSCORE);        
        aMap.put("underscore", KeyEvent.VK_UNDERSCORE);        
        aMap.put("printscreen", KeyEvent.VK_PRINTSCREEN);
        aMap.put("insert", KeyEvent.VK_INSERT);
        aMap.put("help", KeyEvent.VK_HELP);
        aMap.put("`", KeyEvent.VK_BACK_QUOTE);
        aMap.put("backquote", KeyEvent.VK_BACK_QUOTE);
        aMap.put("'", KeyEvent.VK_QUOTE);
        aMap.put("quote", KeyEvent.VK_QUOTE);
			 
        aMap.put("up", KeyEvent.VK_KP_UP);
        aMap.put("down", KeyEvent.VK_KP_DOWN);
        aMap.put("left", KeyEvent.VK_KP_LEFT);
        aMap.put("right", KeyEvent.VK_KP_RIGHT);
        
        // numpad numeric keys handled below
        aMap.put("numpad*", KeyEvent.VK_MULTIPLY);
        aMap.put("numpad+", KeyEvent.VK_ADD);
        aMap.put("numpad,", KeyEvent.VK_SEPARATOR);
        aMap.put("numpad-", KeyEvent.VK_SUBTRACT);
        aMap.put("numpad.", KeyEvent.VK_DECIMAL);
        aMap.put("numpad/", KeyEvent.VK_DIVIDE);
        aMap.put("delete", KeyEvent.VK_DELETE);
        aMap.put("numlock", KeyEvent.VK_NUM_LOCK);
        aMap.put("scrolllock", KeyEvent.VK_SCROLL_LOCK);


        aMap.put("f1", KeyEvent.VK_F1);
        aMap.put("f2", KeyEvent.VK_F2);
        aMap.put("f3", KeyEvent.VK_F3);
        aMap.put("f4", KeyEvent.VK_F4);
        aMap.put("f5", KeyEvent.VK_F5);
        aMap.put("f6", KeyEvent.VK_F6);
        aMap.put("f7", KeyEvent.VK_F7);
        aMap.put("f8", KeyEvent.VK_F8);
        aMap.put("f9", KeyEvent.VK_F9);
        aMap.put("f10", KeyEvent.VK_F10);
        aMap.put("f11", KeyEvent.VK_F11);
        aMap.put("f12", KeyEvent.VK_F12);
        aMap.put("f13", KeyEvent.VK_F13);
        aMap.put("f14", KeyEvent.VK_F14);
        aMap.put("f15", KeyEvent.VK_F15);
        aMap.put("f16", KeyEvent.VK_F16);
        aMap.put("f17", KeyEvent.VK_F17);
        aMap.put("f18", KeyEvent.VK_F18);
        aMap.put("f19", KeyEvent.VK_F19);
        aMap.put("f20", KeyEvent.VK_F20);
        aMap.put("f21", KeyEvent.VK_F21);
        aMap.put("f22", KeyEvent.VK_F22);
        aMap.put("f23", KeyEvent.VK_F23);
        aMap.put("f24", KeyEvent.VK_F24);


        aMap.put("deadgrave", KeyEvent.VK_DEAD_GRAVE);
        aMap.put("deadacute", KeyEvent.VK_DEAD_ACUTE);
        aMap.put("deadcircumflex", KeyEvent.VK_DEAD_CIRCUMFLEX);
        aMap.put("deadtilde", KeyEvent.VK_DEAD_TILDE);
        aMap.put("deadmacron", KeyEvent.VK_DEAD_MACRON);
        aMap.put("deadbreve", KeyEvent.VK_DEAD_BREVE);
        aMap.put("deadabovedot", KeyEvent.VK_DEAD_ABOVEDOT);
        aMap.put("deaddiaeresis", KeyEvent.VK_DEAD_DIAERESIS);
        aMap.put("deadabovering", KeyEvent.VK_DEAD_ABOVERING);
        aMap.put("deaddoubleacute", KeyEvent.VK_DEAD_DOUBLEACUTE);
        aMap.put("deadcaron", KeyEvent.VK_DEAD_CARON);
        aMap.put("deadcedilla", KeyEvent.VK_DEAD_CEDILLA);
        aMap.put("deadogonek", KeyEvent.VK_DEAD_OGONEK);
        aMap.put("deadiota", KeyEvent.VK_DEAD_IOTA);
        aMap.put("deadvoicedsound", KeyEvent.VK_DEAD_VOICED_SOUND);
        

        aMap.put("final", KeyEvent.VK_FINAL);
        aMap.put("convert", KeyEvent.VK_CONVERT);
        aMap.put("noconvert", KeyEvent.VK_NONCONVERT);
        aMap.put("accept", KeyEvent.VK_ACCEPT);
        aMap.put("modechange", KeyEvent.VK_MODECHANGE);
		aMap.put("kana", KeyEvent.VK_KANA);
		aMap.put("kanji", KeyEvent.VK_KANJI);
        aMap.put("alphanumeric", KeyEvent.VK_ALPHANUMERIC);
        aMap.put("katakana", KeyEvent.VK_KATAKANA);
        aMap.put("hiragana", KeyEvent.VK_HIRAGANA);
        aMap.put("fullwidth", KeyEvent.VK_FULL_WIDTH);
        aMap.put("halfwidth", KeyEvent.VK_HALF_WIDTH);
        aMap.put("romancharacters", KeyEvent.VK_ROMAN_CHARACTERS);
        aMap.put("allcandidates", KeyEvent.VK_ALL_CANDIDATES);
        aMap.put("previouscandidate", KeyEvent.VK_PREVIOUS_CANDIDATE);
        aMap.put("codeinput", KeyEvent.VK_CODE_INPUT);
        aMap.put("japanesekatakana", KeyEvent.VK_JAPANESE_KATAKANA);
	  	aMap.put("japanesehiragana", KeyEvent.VK_JAPANESE_HIRAGANA);
	  	aMap.put("japaneseroman", KeyEvent.VK_JAPANESE_ROMAN);
	  	aMap.put("kanalock", KeyEvent.VK_KANA_LOCK);
	  	aMap.put("inputmethodonoff", KeyEvent.VK_INPUT_METHOD_ON_OFF);

        aMap.put("again", KeyEvent.VK_AGAIN);
        aMap.put("undo", KeyEvent.VK_UNDO);
        aMap.put("copy", KeyEvent.VK_COPY);
        aMap.put("paste", KeyEvent.VK_PASTE);
        aMap.put("cut", KeyEvent.VK_CUT);
        aMap.put("find", KeyEvent.VK_FIND);
        aMap.put("props", KeyEvent.VK_PROPS);
        aMap.put("stop", KeyEvent.VK_STOP);
		
		
        keyMap = Collections.unmodifiableMap(aMap);
    }	
	

    public static int toCode(String str)
    {
    	if (keyMap.containsKey(str)) {
    		return keyMap.get(str);
    	}
    	else {
    		return -1;
    	}
    }   
}