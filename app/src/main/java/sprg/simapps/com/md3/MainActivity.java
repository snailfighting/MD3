package sprg.simapps.com.md3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TestSurfaceView(this));
    }
    public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        float x=-100000;
        float y;
        float r=0;
        public TestSurfaceView(Context context) {
            super(context);

            getHolder().addCallback(this);

        }



        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {

            new MyThread().start();
        }

        class MyThread extends Thread{

            @Override
            public void run() {
                Paint p = new Paint();
                p.setColor(Color.YELLOW);

                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Canvas canvas = getHolder().lockCanvas();

                    if (canvas != null) {
                        canvas.drawColor(Color.BLUE);
                        canvas.drawCircle(x,y,r,p);
                        getHolder().unlockCanvasAndPost(canvas);
                    }
//x+=10;
                    r+=5;

                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            r=0;
            x=event.getX();
            y=event.getY();
            return super.onTouchEvent(event);
        }
    }}
