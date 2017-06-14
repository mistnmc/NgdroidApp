package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {

    private Bitmap tileset, spritesheet;
    private int spritex, spritey, speedx, speedy, speed;
    private int kareno, animasyonno;
    private int spritedirection;
    private int touchx, touchy;
    private Rect tilesrc, tiledst, spritesrc, spritedst;

    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        tileset = Utils.loadImage(root,"images/tilea2.png");
        tilesrc = new Rect();
        tiledst = new Rect();

        spritesheet = Utils.loadImage(root, "images/cowboy.png");
        spritesrc = new Rect();
        spritedst = new Rect();

        kareno = 0;
        speed = 16;
        speedx = 0;
        speedy = 0;
        spritex = 0;
        spritey = 0;
        animasyonno = 1;
        spritedirection = 3;
        touchx = 0;
        touchy = 0;
    }



    public void update() {
        Log.i(TAG, "testing_update");

    }

    public void draw(Canvas canvas) {
        tilesrc.set(32*7,0,32*8,32);
        for (int i=0; i<getWidth();i+=128){
            for (int j=0; j<getHeight(); j+=128){
                tiledst.set(i, j, i+128, j+128);
                canvas.drawBitmap(tileset,tilesrc,tiledst,null);
            }
        }
        spritex += speedx;
        spritey += speedy;

        if((spritex+256)>getWidth()){
            spritex=getWidth()-256;
            animasyonno = 0;
        }

        if(spritex<0){
            spritex = 0;
            animasyonno = 0;
        }

        if((spritey+256)>getHeight()){
            spritey=getHeight()-256;
            animasyonno = 0;
        }

        if(spritey<0){
            spritey = 0;
            animasyonno = 0;
        }

        if (animasyonno == 1){
            kareno++;
            if(kareno>8){
                kareno=1;
            }
        }
        else if (animasyonno == 0) {
            kareno = 0;
        }


        spritesrc.set(kareno*128, spritedirection * 128, (kareno+1)*128, (spritedirection+1) * 128);
        spritedst.set(spritex, spritey, spritex+256, spritey+256);
        canvas.drawBitmap(spritesheet, spritesrc, spritedst, null);

    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return true;
    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void touchDown(int x, int y) {
        touchx = x;
        touchy = y;
    }

    public void touchMove(int x, int y) {
    }

    public void touchUp(int x, int y) {
        if ((x - touchx) > 100) {
            speedx = speed;
            speedy = 0;
            animasyonno=1;
            spritedirection=3;
        }
        else if ((touchx - x) > 100) {
            speedx= -speed;
            speedy = 0;
            animasyonno=1;
            spritedirection=7;
        }
        else if ((y - touchy) > 100) {
            speedy = speed;
            speedx = 0;
            animasyonno=1;
            spritedirection=9;
        }
        else if ((touchy - y) > 100) {
            speedy = -speed;
            speedx = 0;
            animasyonno=1;
            spritedirection=5;
        }
        else {
            animasyonno = 0;
            speedx = 0;
            speedy = 0;
        }
    }


    public void pause() {

    }


    public void resume() {

    }


    public void reloadTextures() {

    }


    public void showNotify() {
    }

    public void hideNotify() {
    }

}
