package com.example.chatapp.ui.infoPage;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.chatapp.R;
import com.example.chatapp.domain.Circle;
import com.example.chatapp.domain.Modal;
import com.example.chatapp.domain.Screen;
import com.example.chatapp.model.ModalView;

public class InformationPage extends Screen{

    private ImageView imagePerson;
    public ModalView modalView = new ModalView();

    public void setImagePerson(Bitmap image){
          imagePerson.setImageBitmap(image);
    }

    public InformationPage(Context context){
        super(context);
    }
    @Override
    protected FrameLayout createContent(Context context){
        FrameLayout root = new FrameLayout(context);
        FrameLayout layout = new FrameLayout(context);
        FrameLayout upper = new FrameLayout(context);

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
        FrameLayout.LayoutParams upperParams = new FrameLayout.LayoutParams(
                200,
                200,
                Gravity.CENTER_HORIZONTAL
        );

        upper.setLayoutParams(upperParams);
        Circle circle = new Circle("stroke", Color.parseColor("#bbc2ca"), Color.TRANSPARENT, 4);
        upper.setBackground(circle);

        // ImagePerson
        FrameLayout.LayoutParams imagePersonParams = new FrameLayout.LayoutParams(
                130,
                130,
                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
        );

        imagePerson.setLayoutParams(imagePersonParams);
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.user);
        setImagePerson(image);
        imagePerson.setColorFilter(Color.parseColor("#bbc2ca"));

        // ImageCamera
        FrameLayout.LayoutParams imageCameraParams = new FrameLayout.LayoutParams(
                50,
                50,
                Gravity.END | Gravity.BOTTOM
        );
        imageCamera.setLayoutParams(imageCameraParams);
        Circle cameraCircle = new Circle("both", Color.WHITE, Color.parseColor("#0ad35b"), 5);
        imageCamera.setBackground(cameraCircle);
        imageCamera.setImageResource(R.drawable.camera);
        imageCamera.setColorFilter(Color.WHITE);
        imageCamera.setPadding(10, 10, 10, 10);

        upper.addView(imagePerson);
        upper.addView(imageCamera);

        InfoModalContent content = new InfoModalContent(context, modalView);
        Modal modal = new Modal(context, content);

        modal.setOnClickListener((mView)->{
            modalView.closeBottomToTop();
        });

        upper.setOnClickListener((upperView) ->{
            modalView.openTopToBottom(context, root, modal, content);
        });

        layout.addView(upper);
        root.addView(layout);

        return root;
    }

}