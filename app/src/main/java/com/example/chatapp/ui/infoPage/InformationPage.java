package com.example.chatapp.ui.infoPage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chatapp.R;
import com.example.chatapp.domain.Circle;
import com.example.chatapp.domain.Input;
import com.example.chatapp.domain.Loader;
import com.example.chatapp.domain.Modal;
import com.example.chatapp.domain.RippleButton;
import com.example.chatapp.domain.Screen;
import com.example.chatapp.domain.Text;
import com.example.chatapp.model.ButtonView;
import com.example.chatapp.model.CircleImageView;
import com.example.chatapp.model.InputView;
import com.example.chatapp.model.ModalView;

public class InformationPage extends Screen{

    private ImageView imagePerson;
    private final InputView inputWatcher = new InputView();
    private final ButtonView buttonWatcher = new ButtonView();

    private boolean isLoading = false;

    public void setImagePerson(Bitmap image){
          FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                  200, 200
          );

          imagePerson.setLayoutParams(params);
          imagePerson.setColorFilter(Color.TRANSPARENT);
          imagePerson.setImageBitmap(CircleImageView.circleImage(image, 200));
    }

    public InformationPage(Context context){
        super(context);
    }
    @Override
    protected FrameLayout createContent(Context context){
        FrameLayout root = new FrameLayout(context);
        LinearLayout layout = new LinearLayout(context);
        FrameLayout upper = new FrameLayout(context);
        Input input = new Input(context, Color.TRANSPARENT, 20, Color.parseColor("#bbc2ca"), 4);
        Text next = new Text(context, "Next", 16, Color.WHITE);
        RippleButton nextBtn = new RippleButton(context, next, Color.parseColor("#0ad35b"));

        // Line
        GradientDrawable bg = new GradientDrawable();
        bg.setSize(0, 50);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setDividerDrawable(bg);
        layout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        imagePerson = new ImageView(context);
        ImageView imageCamera = new ImageView(context);

        // Root Params
        FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        root.setLayoutParams(rootParams);

        // layoutParams
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                (int) (context.getResources().getDisplayMetrics().widthPixels * 0.80),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_HORIZONTAL
        );

        layoutParams.setMargins(0, 100, 0, 0);
        layout.setForegroundGravity(Gravity.CENTER_HORIZONTAL);
        layout.setLayoutParams(layoutParams);

        // upperParams
        LinearLayout.LayoutParams upperParams = new LinearLayout.LayoutParams(
                200,
                200
        );

        upperParams.gravity = Gravity.CENTER_HORIZONTAL;
        upper.setLayoutParams(upperParams);
        Circle circle = new Circle("stroke", Color.parseColor("#bbc2ca"), Color.TRANSPARENT, 4);
        upper.setBackground(circle);

        // ImagePerson
        FrameLayout.LayoutParams imagePersonParams = new FrameLayout.LayoutParams(
                130,
                130,
                Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL
        );

        imagePerson.setLayoutParams(imagePersonParams);
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.user);
        imagePerson.setImageBitmap(image);
        imagePerson.setColorFilter(Color.parseColor("#bbc2ca"));

        // ImageCamera
        FrameLayout.LayoutParams imageCameraParams = new FrameLayout.LayoutParams(
                50,
                50,
                Gravity.BOTTOM | Gravity.END
        );

        imageCamera.setLayoutParams(imageCameraParams);
        Circle cameraCircle = new Circle("both", Color.WHITE, Color.parseColor("#0ad35b"), 5);
        imageCamera.setBackground(cameraCircle);
        imageCamera.setImageResource(R.drawable.camera);
        imageCamera.setColorFilter(Color.WHITE);
        imageCamera.setPadding(10, 10, 10, 10);

        upper.addView(imagePerson);
        upper.addView(imageCamera);

        InfoModalContent content = new InfoModalContent(context);
        Modal modal = new Modal(context, content);

        modal.setOnClickListener((mView)->{
            ModalView.closeBottomToTop();
        });

        upper.setOnClickListener((upperView) ->{
            ModalView.openTopToBottom(context, root, modal, content);
        });

        // input styles
        input.setPadding(30, 20, 30, 20);
        input.setOnFocusChangeListener((inputView, focus)->{
            inputWatcher.focusStroke(input.getBg(), Color.parseColor("#bbc2ca"), 4, focus, Color.parseColor("#0ad35b"));
        });

        // Ripple Params
        LinearLayout.LayoutParams rippleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        rippleParams.gravity = Gravity.CENTER_HORIZONTAL;
        nextBtn.setLayoutParams(rippleParams);
        nextBtn.setPadding(40, 15, 40, 15);

        nextBtn.setOnClickListener((nextView)->{
            buttonWatcher.onNext(nextView);
        });

        layout.addView(upper);
        layout.addView(input);
        layout.addView(nextBtn);

        root.addView(layout);
        return root;
    }

}