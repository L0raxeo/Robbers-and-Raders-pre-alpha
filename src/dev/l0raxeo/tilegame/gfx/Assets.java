package dev.l0raxeo.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 70, height = 70;
    // Menu GUI
    public static BufferedImage[] btn_start;
    // Game GUI
    public static BufferedImage reticle;
    // Game Assets
    public static BufferedImage floor, wall, VOID;
    public static BufferedImage[] player_right;
    public static BufferedImage[] player_left;
    public static BufferedImage[] thief_right;
    public static BufferedImage[] thief_left;
    public static BufferedImage[] thief_attack_right;
    public static BufferedImage[] thief_attack_left;
    public static BufferedImage[] charge_station;
    public static BufferedImage[] laser;

    public static BufferedImage hemlet_station;
    public static BufferedImage[] hemlet;

    public static void init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(558, 139, 138, height);
        btn_start[1] = sheet.crop(558, 209, 138, height - 1);

        player_right = new BufferedImage[2];
        player_left = new BufferedImage[2];

        thief_right = new BufferedImage[2];
        thief_left = new BufferedImage[2];
        thief_attack_right = new BufferedImage[2];
        thief_attack_left = new BufferedImage[2];
        hemlet = new BufferedImage[4];

        laser = new BufferedImage[2];

        charge_station = new BufferedImage[4];

        player_right[0] = sheet.crop(229, 17, 35, 37);
        player_right[1] = sheet.crop(299, 16, 35, 37);
        player_left[0] = sheet.crop(368, 16, 35, 37);
        player_left[1] = sheet.crop(438, 16, 35, 37);

        thief_right[0] = sheet.crop(489, 278, 22,39);
        thief_right[1] = sheet.crop(627, 278, 22, 39);
        thief_left[0] = sheet.crop(489, 347, 22, 39);
        thief_left[1] = sheet.crop(627, 347, 22, 39);
        thief_attack_right[0] = sheet.crop(558, 278, 25, 39);
        thief_attack_right[1] = sheet.crop(489, 278, 22, 39);
        thief_attack_left[0] = sheet.crop(558, 347, 25, 39);
        thief_attack_left[1] = sheet.crop(489, 347, 22, 39);

        hemlet_station = sheet.crop(141, 484, 68, 68);

        hemlet[0] = sheet.crop(1, 416, width - 1, height - 2);
        hemlet[2] = sheet.crop(71, 416, width - 1, height - 2);
        hemlet[3] = sheet.crop(1, 485, width - 1, height - 2);
        hemlet[1] = sheet.crop(71, 485, width - 1, height - 2);

        laser[0] = sheet.crop(281, 416, 68, 68);
        laser[1] = sheet.crop(211, 485, 68, 68);

        charge_station[0] = sheet.crop(573, 85, 50, 50);

        floor = sheet.crop(getX(0), getY(0), width, height);
        wall = sheet.crop(getX(1), getY(0), width, height);
        VOID = sheet.crop(getX(2), getY(0), width, height);

        reticle = sheet.crop(211, 416, 49, 49);
    }

    public static int getX(int x) {
        return x * width;
    }

    public static int getY(int y) {
        return y * height;
    }

}
