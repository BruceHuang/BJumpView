package hbb.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import org.hbb.bjumpviewlib.GiftBean;
import org.hbb.bjumpviewlib.JumpViewManager;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout anim_can;
    JumpViewManager jumpViewManager;

    int hitsSame = 1;
    int cc = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anim_can  = (LinearLayout) findViewById(R.id.anim_can);
        jumpViewManager = JumpViewManager.getInstance(this);
        anim_can.setLayoutTransition(jumpViewManager.createLatyoutTransition());
        jumpViewManager.setParentLinearLayout(anim_can);
        findViewById(R.id.action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiftBean giftbroadcastbean = new GiftBean();
                giftbroadcastbean.setUid("123");
                giftbroadcastbean.setGfid("1");
                giftbroadcastbean.setHits(""+(hitsSame++));
                giftbroadcastbean.setGiftname("鸡鸡");
                giftbroadcastbean.setTime(System.currentTimeMillis());
                jumpViewManager.playAnimation(giftbroadcastbean, anim_can);
            }
        });



        findViewById(R.id.action_dif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Random random = new Random();
                int rint = random.nextInt(10);
                int uid = random.nextInt(10);
                int gid = random.nextInt(100);

                GiftBean giftbroadcastbean = new GiftBean();
                giftbroadcastbean.setUid(uid+"");
                giftbroadcastbean.setGfid(gid+"");
                giftbroadcastbean.setHits(""+(rint));
                giftbroadcastbean.setTime(System.currentTimeMillis());
                giftbroadcastbean.setGiftname("顺序"+(cc++));
                jumpViewManager.playAnimation(giftbroadcastbean, anim_can);
            }
        });
    }

}

