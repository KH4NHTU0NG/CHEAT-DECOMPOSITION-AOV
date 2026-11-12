package com.android.support.ThemesData.LGL;

import android.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import com.android.support.Preferences;
import com.android.support.interfaces.IMenuComponentFactory;
import java.util.Arrays;
import java.util.LinkedList;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class LGLMenuComponents implements IMenuComponentFactory {
    LGLShared sharedData;

    public LGLMenuComponents(LGLShared lGLShared) {
        this.sharedData = lGLShared;
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Button(LinearLayout linearLayout, final int i, final String str) {
        Button button = new Button(this.sharedData.getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        button.setAllCaps(false);
        button.setText(Html.fromHtml(str));
        button.setBackgroundColor(this.sharedData.MenuStyle.getBtnColor());
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i2 = i;
                if (i2 == -100) {
                    LGLMenuComponents.this.sharedData.stopChecking = true;
                } else if (i2 == -6) {
                    LGLShared lGLShared = LGLMenuComponents.this.sharedData;
                    lGLShared.scrollView.removeView(lGLShared.mSettings);
                    LGLShared lGLShared2 = LGLMenuComponents.this.sharedData;
                    lGLShared2.scrollView.addView(lGLShared2.mods);
                }
                Preferences.changeFeatureInt(str, i, 0);
            }
        });
        linearLayout.addView(button);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void ButtonLink(LinearLayout linearLayout, String str, final String str2) {
        Button button = new Button(this.sharedData.getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setAllCaps(false);
        button.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        button.setText(Html.fromHtml(str));
        button.setBackgroundColor(this.sharedData.MenuStyle.getBtnColor());
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setFlags(268435456);
                intent.setData(Uri.parse(str2));
                LGLMenuComponents.this.sharedData.getContext.startActivity(intent);
            }
        });
        linearLayout.addView(button);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void ButtonOnOff(LinearLayout linearLayout, int i, String str, boolean z) {
        boolean z2;
        Button button = new Button(this.sharedData.getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        button.setAllCaps(false);
        String strReplace = str.replace("OnOff_", "");
        if (Preferences.loadPrefBool(str, i, z)) {
            button.setText(Html.fromHtml(strReplace + ": ON"));
            button.setBackgroundColor(this.sharedData.MenuStyle.getBtnON());
            z2 = false;
        } else {
            button.setText(Html.fromHtml(strReplace + ": OFF"));
            button.setBackgroundColor(this.sharedData.MenuStyle.getBtnOFF());
            z2 = true;
        }
        button.setOnClickListener(new View.OnClickListener(z2, strReplace, i, button) { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.5
            boolean isOn;
            final /* synthetic */ Button val$button;
            final /* synthetic */ int val$featNum;
            final /* synthetic */ boolean val$finalIsOn;
            final /* synthetic */ String val$finalfeatName;

            {
                this.val$finalIsOn = z2;
                this.val$finalfeatName = strReplace;
                this.val$featNum = i;
                this.val$button = button;
                this.isOn = z2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Preferences.changeFeatureBool(this.val$finalfeatName, this.val$featNum, this.isOn);
                if (this.isOn) {
                    this.val$button.setText(Html.fromHtml(this.val$finalfeatName + ": ON"));
                    this.val$button.setBackgroundColor(LGLMenuComponents.this.sharedData.MenuStyle.getBtnON());
                    this.isOn = false;
                    return;
                }
                this.val$button.setText(Html.fromHtml(this.val$finalfeatName + ": OFF"));
                this.val$button.setBackgroundColor(LGLMenuComponents.this.sharedData.MenuStyle.getBtnOFF());
                this.isOn = true;
            }
        });
        linearLayout.addView(button);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Category(LinearLayout linearLayout, String str) {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setBackgroundColor(this.sharedData.MenuStyle.getCategoryBG());
        textView.setText(Html.fromHtml(str));
        textView.setGravity(17);
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        textView.setTypeface(null, 1);
        textView.setPadding(0, 5, 0, 5);
        linearLayout.addView(textView);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void CheckBox(LinearLayout linearLayout, final int i, final String str, boolean z) {
        final CheckBox checkBox = new CheckBox(this.sharedData.getContext);
        checkBox.setText(str);
        checkBox.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        checkBox.setButtonTintList(ColorStateList.valueOf(this.sharedData.MenuStyle.getCheckBoxColor()));
        checkBox.setChecked(Preferences.loadPrefBool(str, i, z));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.10
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                if (checkBox.isChecked()) {
                    Preferences.changeFeatureBool(str, i, z2);
                } else {
                    Preferences.changeFeatureBool(str, i, z2);
                }
            }
        });
        linearLayout.addView(checkBox);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Collapse(LinearLayout linearLayout, String str, boolean z) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, 5, 0, 0);
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setVerticalGravity(16);
        linearLayout2.setOrientation(1);
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setVerticalGravity(16);
        linearLayout3.setPadding(0, 5, 0, 5);
        linearLayout3.setOrientation(1);
        linearLayout3.setBackgroundColor(Color.parseColor("#222D38"));
        linearLayout3.setVisibility(8);
        this.sharedData.mCollapse = linearLayout3;
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setBackgroundColor(this.sharedData.MenuStyle.getCollapseColor());
        textView.setText("▽ " + str + " ▽");
        textView.setGravity(17);
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        textView.setTypeface(null, 1);
        textView.setPadding(0, 20, 0, 20);
        if (z) {
            linearLayout3.setVisibility(0);
            textView.setText("△ " + str + " △");
        }
        textView.setOnClickListener(new View.OnClickListener(z, linearLayout3, textView, str) { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.12
            boolean isChecked;
            final /* synthetic */ LinearLayout val$collapseSub;
            final /* synthetic */ boolean val$expanded;
            final /* synthetic */ String val$text;
            final /* synthetic */ TextView val$textView;

            {
                this.val$expanded = z;
                this.val$collapseSub = linearLayout3;
                this.val$textView = textView;
                this.val$text = str;
                this.isChecked = z;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean z2 = this.isChecked;
                this.isChecked = !z2;
                if (z2) {
                    this.val$collapseSub.setVisibility(8);
                    this.val$textView.setText("▽ " + this.val$text + " ▽");
                    return;
                }
                this.val$collapseSub.setVisibility(0);
                this.val$textView.setText("△ " + this.val$text + " △");
            }
        });
        linearLayout2.addView(textView);
        linearLayout2.addView(linearLayout3);
        linearLayout.addView(linearLayout2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void InputLNum(LinearLayout linearLayout, final int i, final String str, final long j) {
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(7, 5, 7, 5);
        final Button button = new Button(this.sharedData.getContext);
        button.setText(Html.fromHtml(str + ": <font color='" + this.sharedData.MenuStyle.getNumberTxtColor() + "'>" + Preferences.loadPrefLong(str, i) + "</font>"));
        button.setAllCaps(false);
        button.setLayoutParams(layoutParams);
        button.setBackgroundColor(this.sharedData.MenuStyle.getBtnColor());
        button.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LGLMenuComponents.this.sharedData.getContext);
                final EditText editText = new EditText(LGLMenuComponents.this.sharedData.getContext);
                if (j != 0) {
                    editText.setHint("Max value: " + j);
                }
                editText.setInputType(2);
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789-"));
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.8.1
                    @Override // android.view.View.OnFocusChangeListener
                    public void onFocusChange(View view2, boolean z) {
                        InputMethodManager inputMethodManager = (InputMethodManager) LGLMenuComponents.this.sharedData.getContext.getSystemService("input_method");
                        if (z) {
                            inputMethodManager.toggleSoftInput(2, 1);
                        } else {
                            inputMethodManager.toggleSoftInput(1, 0);
                        }
                    }
                });
                editText.requestFocus();
                builder.setTitle("Input number");
                builder.setView(editText);
                LinearLayout linearLayout3 = new LinearLayout(LGLMenuComponents.this.sharedData.getContext);
                linearLayout3.setOrientation(1);
                linearLayout3.addView(editText);
                builder.setView(linearLayout3);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.8.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        long j2;
                        try {
                            String string = editText.getText().toString();
                            if (string.isEmpty()) {
                                string = "0";
                            }
                            j2 = Long.parseLong(string);
                            long j3 = j;
                            if (j3 != 0 && j2 >= j3) {
                                j2 = j3;
                            }
                        } catch (NumberFormatException unused) {
                            j2 = j;
                            if (j2 == 0) {
                                j2 = Long.MAX_VALUE;
                            }
                        }
                        button.setText(Html.fromHtml(str + ": <font color='" + LGLMenuComponents.this.sharedData.MenuStyle.getNumberTxtColor() + "'>" + j2 + "</font>"));
                        AnonymousClass8 anonymousClass8 = AnonymousClass8.this;
                        Preferences.changeFeatureLong(str, i, j2);
                        editText.setFocusable(false);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.8.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ((InputMethodManager) LGLMenuComponents.this.sharedData.getContext.getSystemService("input_method")).toggleSoftInput(1, 0);
                    }
                });
                if (!LGLMenuComponents.this.sharedData.overlayRequired) {
                    builder.show();
                    return;
                }
                AlertDialog alertDialogCreate = builder.create();
                alertDialogCreate.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                alertDialogCreate.show();
            }
        });
        linearLayout2.addView(button);
        linearLayout.addView(linearLayout2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void InputNum(LinearLayout linearLayout, final int i, final String str, final int i2) {
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(7, 5, 7, 5);
        final Button button = new Button(this.sharedData.getContext);
        button.setText(Html.fromHtml(str + ": <font color='" + this.sharedData.MenuStyle.getNumberTxtColor() + "'>" + Preferences.loadPrefInt(str, i) + "</font>"));
        button.setAllCaps(false);
        button.setLayoutParams(layoutParams);
        button.setBackgroundColor(this.sharedData.MenuStyle.getBtnColor());
        button.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LGLMenuComponents.this.sharedData.getContext);
                final EditText editText = new EditText(LGLMenuComponents.this.sharedData.getContext);
                if (i2 != 0) {
                    editText.setHint("Max value: " + i2);
                }
                editText.setInputType(2);
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789-"));
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.7.1
                    @Override // android.view.View.OnFocusChangeListener
                    public void onFocusChange(View view2, boolean z) {
                        InputMethodManager inputMethodManager = (InputMethodManager) LGLMenuComponents.this.sharedData.getContext.getSystemService("input_method");
                        if (z) {
                            inputMethodManager.toggleSoftInput(2, 1);
                        } else {
                            inputMethodManager.toggleSoftInput(1, 0);
                        }
                    }
                });
                editText.requestFocus();
                builder.setTitle("Input number");
                builder.setView(editText);
                LinearLayout linearLayout3 = new LinearLayout(LGLMenuComponents.this.sharedData.getContext);
                linearLayout3.setOrientation(1);
                linearLayout3.addView(editText);
                builder.setView(linearLayout3);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.7.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i3) {
                        int i4;
                        try {
                            String string = editText.getText().toString();
                            if (string.isEmpty()) {
                                string = "0";
                            }
                            i4 = Integer.parseInt(string);
                            int i5 = i2;
                            if (i5 != 0 && i4 >= i5) {
                                i4 = i5;
                            }
                        } catch (NumberFormatException unused) {
                            i4 = i2;
                            if (i4 == 0) {
                                i4 = Integer.MAX_VALUE;
                            }
                        }
                        button.setText(Html.fromHtml(str + ": <font color='" + LGLMenuComponents.this.sharedData.MenuStyle.getNumberTxtColor() + "'>" + i4 + "</font>"));
                        AnonymousClass7 anonymousClass7 = AnonymousClass7.this;
                        Preferences.changeFeatureInt(str, i, i4);
                        editText.setFocusable(false);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.7.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i3) {
                        ((InputMethodManager) LGLMenuComponents.this.sharedData.getContext.getSystemService("input_method")).toggleSoftInput(1, 0);
                    }
                });
                if (!LGLMenuComponents.this.sharedData.overlayRequired) {
                    builder.show();
                    return;
                }
                AlertDialog alertDialogCreate = builder.create();
                alertDialogCreate.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                alertDialogCreate.show();
            }
        });
        linearLayout2.addView(button);
        linearLayout.addView(linearLayout2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void InputText(LinearLayout linearLayout, final int i, final String str) {
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(7, 5, 7, 5);
        final Button button = new Button(this.sharedData.getContext);
        button.setText(Html.fromHtml(str + ": <font color='" + this.sharedData.MenuStyle.getNumberTxtColor() + "'>" + Preferences.loadPrefString(str, i) + "</font>"));
        button.setAllCaps(false);
        button.setLayoutParams(layoutParams);
        button.setBackgroundColor(this.sharedData.MenuStyle.getBtnColor());
        button.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LGLMenuComponents.this.sharedData.getContext);
                final EditText editText = new EditText(LGLMenuComponents.this.sharedData.getContext);
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.9.1
                    @Override // android.view.View.OnFocusChangeListener
                    public void onFocusChange(View view2, boolean z) {
                        InputMethodManager inputMethodManager = (InputMethodManager) LGLMenuComponents.this.sharedData.getContext.getSystemService("input_method");
                        if (z) {
                            inputMethodManager.toggleSoftInput(2, 1);
                        } else {
                            inputMethodManager.toggleSoftInput(1, 0);
                        }
                    }
                });
                editText.requestFocus();
                builder.setTitle("Input text");
                builder.setView(editText);
                LinearLayout linearLayout3 = new LinearLayout(LGLMenuComponents.this.sharedData.getContext);
                linearLayout3.setOrientation(1);
                linearLayout3.addView(editText);
                builder.setView(linearLayout3);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.9.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String string = editText.getText().toString();
                        button.setText(Html.fromHtml(str + ": <font color='" + LGLMenuComponents.this.sharedData.MenuStyle.getNumberTxtColor() + "'>" + string + "</font>"));
                        AnonymousClass9 anonymousClass9 = AnonymousClass9.this;
                        Preferences.changeFeatureString(str, i, string);
                        editText.setFocusable(false);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.9.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ((InputMethodManager) LGLMenuComponents.this.sharedData.getContext.getSystemService("input_method")).toggleSoftInput(1, 0);
                    }
                });
                if (!LGLMenuComponents.this.sharedData.overlayRequired) {
                    builder.show();
                    return;
                }
                AlertDialog alertDialogCreate = builder.create();
                alertDialogCreate.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                alertDialogCreate.show();
            }
        });
        linearLayout2.addView(button);
        linearLayout.addView(linearLayout2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void RadioButton(LinearLayout linearLayout, final int i, final String str, String str2) {
        LinkedList linkedList = new LinkedList(Arrays.asList(str2.split(",")));
        final TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str + ":");
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        final RadioGroup radioGroup = new RadioGroup(this.sharedData.getContext);
        radioGroup.setPadding(10, 5, 10, 5);
        radioGroup.setOrientation(1);
        radioGroup.addView(textView);
        for (int i2 = 0; i2 < linkedList.size(); i2++) {
            final RadioButton radioButton = new RadioButton(this.sharedData.getContext);
            final String str3 = (String) linkedList.get(i2);
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    textView.setText(Html.fromHtml(str + ": <font color='" + LGLMenuComponents.this.sharedData.MenuStyle.getNumberTxtColor() + "'>" + str3));
                    Preferences.changeFeatureInt(str, i, radioGroup.indexOfChild(radioButton));
                }
            };
            System.out.println((String) linkedList.get(i2));
            radioButton.setText((CharSequence) linkedList.get(i2));
            radioButton.setTextColor(-3355444);
            radioButton.setButtonTintList(ColorStateList.valueOf(this.sharedData.MenuStyle.getRadioColor()));
            radioButton.setOnClickListener(onClickListener);
            radioGroup.addView(radioButton);
        }
        int iLoadPrefInt = Preferences.loadPrefInt(str, i);
        if (iLoadPrefInt > 0) {
            textView.setText(Html.fromHtml(str + ": <font color='" + this.sharedData.MenuStyle.getNumberTxtColor() + "'>" + ((String) linkedList.get(iLoadPrefInt - 1))));
            ((RadioButton) radioGroup.getChildAt(iLoadPrefInt)).setChecked(true);
        }
        linearLayout.addView(radioGroup);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void SeekBar(LinearLayout linearLayout, final int i, final String str, final int i2, int i3) {
        int iLoadPrefInt = Preferences.loadPrefInt(str, i);
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setPadding(10, 5, 0, 5);
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        final TextView textView = new TextView(this.sharedData.getContext);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": <font color='");
        sb.append(this.sharedData.MenuStyle.getNumberTxtColor());
        sb.append("'>");
        sb.append(iLoadPrefInt == 0 ? i2 : iLoadPrefInt);
        textView.setText(Html.fromHtml(sb.toString()));
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        SeekBar seekBar = new SeekBar(this.sharedData.getContext);
        seekBar.setPadding(25, 10, 35, 10);
        seekBar.setMax(i3);
        if (Build.VERSION.SDK_INT >= 26) {
            seekBar.setMin(i2);
        }
        if (iLoadPrefInt == 0) {
            iLoadPrefInt = i2;
        }
        seekBar.setProgress(iLoadPrefInt);
        Drawable thumb = seekBar.getThumb();
        int seekBarColor = this.sharedData.MenuStyle.getSeekBarColor();
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_ATOP;
        thumb.setColorFilter(seekBarColor, mode);
        seekBar.getProgressDrawable().setColorFilter(this.sharedData.MenuStyle.getSeekBarProgressColor(), mode);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i4, boolean z) {
                int i5 = i2;
                if (i4 >= i5) {
                    i5 = i4;
                }
                seekBar2.setProgress(i5);
                String str2 = str;
                int i6 = i;
                int i7 = i2;
                if (i4 >= i7) {
                    i7 = i4;
                }
                Preferences.changeFeatureInt(str2, i6, i7);
                TextView textView2 = textView;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(": <font color='");
                sb2.append(LGLMenuComponents.this.sharedData.MenuStyle.getNumberTxtColor());
                sb2.append("'>");
                int i8 = i2;
                if (i4 < i8) {
                    i4 = i8;
                }
                sb2.append(i4);
                textView2.setText(Html.fromHtml(sb2.toString()));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
        linearLayout2.addView(textView);
        linearLayout2.addView(seekBar);
        linearLayout.addView(linearLayout2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Spinner(LinearLayout linearLayout, final int i, String str, String str2) {
        Log.d(this.sharedData.MenuStyle.getTag(), "spinner " + i + " " + str + " " + str2);
        LinkedList linkedList = new LinkedList(Arrays.asList(str2.split(",")));
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(7, 2, 7, 2);
        linearLayout2.setOrientation(1);
        linearLayout2.setBackgroundColor(this.sharedData.MenuStyle.getBtnColor());
        linearLayout2.setLayoutParams(layoutParams);
        final Spinner spinner = new Spinner(this.sharedData.getContext, 1);
        spinner.setLayoutParams(layoutParams);
        spinner.getBackground().setColorFilter(1, PorterDuff.Mode.SRC_ATOP);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.sharedData.getContext, R.layout.simple_spinner_dropdown_item, linkedList);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        spinner.setSelection(Preferences.loadPrefInt(str, i));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.6
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                Preferences.changeFeatureInt(spinner.getSelectedItem().toString(), i, i2);
                ((TextView) adapterView.getChildAt(0)).setTextColor(LGLMenuComponents.this.sharedData.MenuStyle.getTextColor2());
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        linearLayout2.addView(spinner);
        linearLayout.addView(linearLayout2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Switch(LinearLayout linearLayout, final int i, final String str, boolean z) {
        final Switch r0 = new Switch(this.sharedData.getContext);
        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{-16842910}, new int[]{R.attr.state_checked}, new int[0]}, new int[]{-16776961, this.sharedData.MenuStyle.getToggleON(), this.sharedData.MenuStyle.getToggleOFF()});
        try {
            r0.getThumbDrawable().setTintList(colorStateList);
            r0.getTrackDrawable().setTintList(colorStateList);
        } catch (NullPointerException e) {
            Log.d(this.sharedData.MenuStyle.getTag(), String.valueOf(e));
        }
        r0.setText(str);
        r0.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        r0.setPadding(10, 5, 0, 5);
        r0.setChecked(Preferences.loadPrefBool(str, i, z));
        r0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.support.ThemesData.LGL.LGLMenuComponents.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                Preferences.changeFeatureBool(str, i, z2);
                int i2 = i;
                if (i2 == -3) {
                    Preferences.isExpanded = z2;
                    LGLShared lGLShared = LGLMenuComponents.this.sharedData;
                    lGLShared.scrollView.setLayoutParams(z2 ? lGLShared.scrlLLExpanded : lGLShared.scrlLL);
                } else {
                    if (i2 != -1) {
                        return;
                    }
                    Preferences.with(r0.getContext()).writeBoolean(-1, z2);
                    if (z2) {
                        return;
                    }
                    Preferences.with(r0.getContext()).clear();
                }
            }
        });
        linearLayout.addView(r0);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void TextView(LinearLayout linearLayout, String str) {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(Html.fromHtml(str));
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        textView.setPadding(10, 5, 10, 5);
        linearLayout.addView(textView);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void WebTextView(LinearLayout linearLayout, String str) {
        WebView webView = new WebView(this.sharedData.getContext);
        webView.loadData(str, "text/html", "utf-8");
        webView.setBackgroundColor(0);
        webView.setPadding(0, 5, 0, 5);
        webView.getSettings().setCacheMode(2);
        linearLayout.addView(webView);
    }
}
