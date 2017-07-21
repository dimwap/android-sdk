package com.cloudipsp.android;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by vberegovoy on 6/20/17.
 */

class CardInputBase extends EditText {
    public CardInputBase(Context context) {
        super(context);
    }

    public CardInputBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardInputBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        if (isParentCall()) {
            super.addTextChangedListener(watcher);
        } else {
            throw new RuntimeException("unsupported operation");
        }
    }

    protected void setTextInternal(CharSequence text) {
        super.setText(text, BufferType.NORMAL);
    }

    protected CharSequence getTextInternal() {
        return super.getText();
    }

    @Override
    @Deprecated
    public Editable getText() {
        if (isParentCall()) {
            return super.getText();
        } else {
            throw new RuntimeException("unsupported operation");
        }
    }

    @Override
    @Deprecated
    public void setText(CharSequence text, BufferType type) {
        if (isParentCall()) {
            super.setText(text, type);
        } else {
            throw new RuntimeException("unsupported operation");
        }
    }

    private final boolean isParentCall() {
        final StackTraceElement stack[] = new Throwable().getStackTrace();

        return isParentClass(stack[2].getClassName()) ||
                isParentClass(stack[3].getClassName()) ||
                isParentClass(stack[4].getClassName());
    }

    private final boolean isParentClass(String className) {
        return className.equals(TextView.class.getName())
                || className.equals(EditText.class.getName())
                || className.equals("android.widget.Editor")
                || className.startsWith("android.widget.Editor$")
                || className.equals("android.support.design.widget.TextInputLayout")
                || className.equals("com.huawei.android.hwcontrol.HwEditor")
                ;
    }
}