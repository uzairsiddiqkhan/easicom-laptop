// Generated by view binder compiler. Do not edit!
package com.example.easicom.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.easicom.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySettingBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView About;

  @NonNull
  public final EditText Aboutuser;

  @NonNull
  public final EditText UserName;

  @NonNull
  public final ImageView back;

  @NonNull
  public final LinearLayout infoLayout;

  @NonNull
  public final LinearLayout layout;

  @NonNull
  public final LinearLayout picLayout;

  @NonNull
  public final ImageView plus;

  @NonNull
  public final ImageView profile;

  @NonNull
  public final Button save;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final TextView textView9;

  @NonNull
  public final TextView user;

  private ActivitySettingBinding(@NonNull RelativeLayout rootView, @NonNull TextView About,
      @NonNull EditText Aboutuser, @NonNull EditText UserName, @NonNull ImageView back,
      @NonNull LinearLayout infoLayout, @NonNull LinearLayout layout,
      @NonNull LinearLayout picLayout, @NonNull ImageView plus, @NonNull ImageView profile,
      @NonNull Button save, @NonNull TextView textView5, @NonNull TextView textView6,
      @NonNull TextView textView7, @NonNull TextView textView8, @NonNull TextView textView9,
      @NonNull TextView user) {
    this.rootView = rootView;
    this.About = About;
    this.Aboutuser = Aboutuser;
    this.UserName = UserName;
    this.back = back;
    this.infoLayout = infoLayout;
    this.layout = layout;
    this.picLayout = picLayout;
    this.plus = plus;
    this.profile = profile;
    this.save = save;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.textView8 = textView8;
    this.textView9 = textView9;
    this.user = user;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySettingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_setting, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySettingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.About;
      TextView About = ViewBindings.findChildViewById(rootView, id);
      if (About == null) {
        break missingId;
      }

      id = R.id.Aboutuser;
      EditText Aboutuser = ViewBindings.findChildViewById(rootView, id);
      if (Aboutuser == null) {
        break missingId;
      }

      id = R.id.UserName;
      EditText UserName = ViewBindings.findChildViewById(rootView, id);
      if (UserName == null) {
        break missingId;
      }

      id = R.id.back;
      ImageView back = ViewBindings.findChildViewById(rootView, id);
      if (back == null) {
        break missingId;
      }

      id = R.id.infoLayout;
      LinearLayout infoLayout = ViewBindings.findChildViewById(rootView, id);
      if (infoLayout == null) {
        break missingId;
      }

      id = R.id.layout;
      LinearLayout layout = ViewBindings.findChildViewById(rootView, id);
      if (layout == null) {
        break missingId;
      }

      id = R.id.picLayout;
      LinearLayout picLayout = ViewBindings.findChildViewById(rootView, id);
      if (picLayout == null) {
        break missingId;
      }

      id = R.id.plus;
      ImageView plus = ViewBindings.findChildViewById(rootView, id);
      if (plus == null) {
        break missingId;
      }

      id = R.id.profile;
      ImageView profile = ViewBindings.findChildViewById(rootView, id);
      if (profile == null) {
        break missingId;
      }

      id = R.id.save;
      Button save = ViewBindings.findChildViewById(rootView, id);
      if (save == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      id = R.id.user;
      TextView user = ViewBindings.findChildViewById(rootView, id);
      if (user == null) {
        break missingId;
      }

      return new ActivitySettingBinding((RelativeLayout) rootView, About, Aboutuser, UserName, back,
          infoLayout, layout, picLayout, plus, profile, save, textView5, textView6, textView7,
          textView8, textView9, user);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}