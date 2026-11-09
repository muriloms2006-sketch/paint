
package com.app.simplepaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.util.AttributeSet;

import java.util.ArrayList;

public class PaintView extends View {

    public static final int LIVRE = 0;
    public static final int RETANGULO = 1;
    public static final int CIRCULO = 2;

    private int corAtual = Color.BLACK;
    private int formaAtual = LIVRE;

    private ArrayList<Desenho> desenhos = new ArrayList<>();

    private Desenho desenhoAtual;

    private static class Desenho {

        Path caminho;

        int cor;
        int forma;

        float inicioX, inicioY;
        float fimX, fimY;

        Paint pincel;

        Desenho(int cor, int forma) {

            this.cor = cor;
            this.forma = forma;

            caminho = new Path();

            pincel = new Paint(Paint.ANTI_ALIAS_FLAG);

            pincel.setColor(cor);
            pincel.setStrokeWidth(10);
            pincel.setStyle(Paint.Style.STROKE);
            pincel.setStrokeCap(Paint.Cap.ROUND);
            pincel.setStrokeJoin(Paint.Join.ROUND);
        }
    }

    public PaintView(Context context) {
        super(context);
        configurar();
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        configurar();
    }

    private void configurar() {
        setBackgroundColor(Color.WHITE);
    }

    public void setCor(int cor) {
        corAtual = cor;
    }

    public void setForma(int forma) {
        formaAtual = forma;
    }

    public void limpar() {

        desenhos.clear();

        desenhoAtual = null;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        for (Desenho desenho : desenhos) {

            desenharForma(canvas, desenho);
        }

        if (desenhoAtual != null) {

            desenharForma(canvas, desenhoAtual);
        }
    }

    private void desenharForma(Canvas canvas, Desenho desenho) {

        if (desenho.forma == LIVRE) {

            canvas.drawPath(
                    desenho.caminho,
                    desenho.pincel
            );

        }

        else if (desenho.forma == RETANGULO) {

            RectF retangulo = new RectF(
                    desenho.inicioX,
                    desenho.inicioY,
                    desenho.fimX,
                    desenho.fimY
            );

            retangulo.sort();

            canvas.drawRect(
                    retangulo,
                    desenho.pincel
            );
        }

        else if (desenho.forma == CIRCULO) {

            float raio = (float) Math.sqrt(
                    Math.pow(desenho.fimX - desenho.inicioX, 2) +
                            Math.pow(desenho.fimY - desenho.inicioY, 2)
            );

            canvas.drawCircle(
                    desenho.inicioX,
                    desenho.inicioY,
                    raio,
                    desenho.pincel
            );
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                desenhoAtual = new Desenho(corAtual, formaAtual);

                desenhoAtual.inicioX = x;
                desenhoAtual.inicioY = y;

                desenhoAtual.fimX = x;
                desenhoAtual.fimY = y;

                if (formaAtual == LIVRE) {

                    desenhoAtual.caminho.moveTo(x, y);
                }

                invalidate();

                return true;

            case MotionEvent.ACTION_MOVE:

                if (desenhoAtual != null) {

                    if (formaAtual == LIVRE) {

                        desenhoAtual.caminho.lineTo(x, y);

                    } else {

                        desenhoAtual.fimX = x;
                        desenhoAtual.fimY = y;
                    }

                    invalidate();
                }

                return true;

            case MotionEvent.ACTION_UP:

                if (desenhoAtual != null) {

                    desenhoAtual.fimX = x;
                    desenhoAtual.fimY = y;

                    desenhos.add(desenhoAtual);

                    desenhoAtual = null;

                    invalidate();
                }

                return true;
        }

        return true;
    }
}
