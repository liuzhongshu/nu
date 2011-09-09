package com.liuzhongshu.nu;


public enum KeyCode
{
	_0,_1,_2,_3,_4,_5,_6,_7,_8,_9,
    _a,_b,_c,_d,_e,_f,_g,_h,_i,_j,_k,_l,_m,_n,_o,_p,_q,_r,_s,_t,_u,_v,_w,_x,_y,_z,
    _shift,_ctrl,_alt,_win,_cmd,
    _esc,_f1,_f2,_f3,_f4,_f5,_f6,_f7,_f8,_f9,_f10,_f11,_f12,
    _tab,_cap,_fn,_up,_down,_left,_right,_pgup,_pgdn,_home,_end,_insert,_delete,_rbracket,_enter,_space,_backspace,
    _minus,_equals,_comma,_period,_slash,_backslash,_semicolon,_quote,
    _error;

    public static KeyCode toCode(String str)
    {
        try {
            return valueOf(str);
        } 
        catch (Exception ex) {
            return _error;
        }
    }   
}