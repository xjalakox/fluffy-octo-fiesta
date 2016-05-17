package rpg;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import rpg.entity.Entity;
import rpg.gfx.Sprite;
import rpg.gfx.SpriteSheet;
import rpg.gui.Gui;
import rpg.gui.TextDraw;
import rpg.json.JSONDecoder;
import rpg.json.Texts;
import rpg.level.Level;
import rpg.quest.Quest;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

	public static String[] texts = new String[100];

	public static final int WIDTH = 480;
	public static final int HEIGHT = 270;
	public static final int SCALE = 4;

	public static final boolean DEBUG = true;

	public static String map1_noroof = "res/Maps/map1_noroof.json";
	public static String map1_roof = "res/Maps/map1_roof.json";

	public static SpriteSheet sheet, items;
	public static SpriteSheet[] sheets = new SpriteSheet[20];

	public static Camera cam;

	public static KeyInput key;
	public static Handler handler;

	public static long[] data = new long[100000];

	public static Sprite[] player = new Sprite[36];

	private BufferStrategy bs;

	private int fps, ups;

	public static Sprite[] sprites = new Sprite[8000];
	public static Sprite[] inv_items = new Sprite[200];
	public static Sprite bg, ground;

	private boolean running = false;
	private Thread thread;

	public static Color textc = new Color(138, 60, 34);

	public static Level level;

	public Image scrolltext_bg;
	public ImageObserver observer;
	
	public static Quest quests = new Quest();
	
	public static Texts texte = new Texts();

	Gui gui = new Gui();
	TextDraw draw = new TextDraw();

	public Game() {
		try {
			scrolltext_bg = ImageIO.read(Game.class.getResourceAsStream("/Scrolltext/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);

	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}

	public void tick() {
		level.update();
		key.tick();
		for (Entity e : level.entities) {
			if (e.getId() == Id.player) {
				cam.tick(e);
			}
		}
		draw.tick();
		gui.tick();
	}

	public void render() {
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH * SCALE + 100, HEIGHT * SCALE + 100);
		g2d.translate(cam.getX(), cam.getY());
		level.render(g);
		g2d.translate(-cam.getX(), -cam.getY());

		draw.render(g);
		gui.render(g);
		String f = String.valueOf(fps);
		String u = String.valueOf(ups);
		g.setFont(new Font("Arial", Font.BOLD, 32));
		g.drawString("FPS: " + f, 50, 50);
		g.drawString("UPS: " + u, 50, 100);

		g.dispose();
		bs.show();
	}

	public void init() {
		key = new KeyInput();
		handler = new Handler();
		level = Level.map1;

		cam = new Camera();
		addKeyListener(key);

		initSprite();

		MouseInput mouse = new MouseInput();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addMouseWheelListener(mouse);

		gui.init();

		handler.ChangeMusic(1,1,false);
		handler.manager.setVolume(1, -20);
		
	}

	private void initSprite() {
		sheet = new SpriteSheet("/Character/normal.png");
		items = new SpriteSheet("/Inventory/items.png");

		for (int i = 0; i < 10; i++) {
			inv_items[i] = new Sprite(items, 0 + i * 34, 0, 34, 34);
		}

		sheets[0] = new SpriteSheet("/Tiles/nature.png");
		sheets[1] = new SpriteSheet("/Tiles/rpg set.png");
		sheets[2] = new SpriteSheet("/Tiles/floor wood.png");
		sheets[3] = new SpriteSheet("/Tiles/windows.png");
		sheets[4] = new SpriteSheet("/Tiles/doors.png");
		sheets[5] = new SpriteSheet("/Tiles/flowerpots.png");
		sheets[6] = new SpriteSheet("/Tiles/kitchen.png");
		sheets[7] = new SpriteSheet("/Tiles/furniture.png");
		sheets[8] = new SpriteSheet("/Tiles/furniture2.png");
		sheets[9] = new SpriteSheet("/Tiles/trees2.png");
		sheets[10] = new SpriteSheet("/Tiles/paintings.png");
		sheets[11] = new SpriteSheet("/Tiles/statues.png");
		sheets[12] = new SpriteSheet("/Tiles/trees.png");

		JSONObject map1 = JSONDecoder.loadData(map1_noroof);

		int z = 0;

		for (int a = 0; a < 13; a++) {
			JSONObject data1 = (JSONObject) ((JSONArray) map1.get("tilesets")).get(a);
			long width = (long) data1.get("imagewidth") / 32;
			long height = (long) data1.get("imageheight") / 32;

			System.out.println("Spritesheet wird ausgelesen: " + a);
			for (int b = 0; b < height; b++) {
				for (int c = 0; c < width; c++) {
					sprites[z + 1] = new Sprite(sheets[a], ((int) (c * 32)), ((int) (b * 32)), 32, 32);
					z++;
				}
			}
		}

		int p = 0;

		for (int i = 0; i < player.length / 4; i++) {
			player[i] = new Sprite(sheet, p + 17, 523, 30, 52);
			p += 64;
		}
		p = 0;
		for (int i = 9; i < player.length / 4 + 9; i++) {
			player[i] = new Sprite(sheet, p + 17, 587, 30, 52);
			p += 64;
		}
		p = 0;
		for (int i = 18; i < player.length / 4 + 18; i++) {
			player[i] = new Sprite(sheet, p + 17, 650, 30, 52);
			p += 64;
		}
		p = 0;
		for (int i = 27; i < player.length / 4 + 27; i++) {
			player[i] = new Sprite(sheet, p + 17, 714, 30, 52);
			p += 64;
		}
	}

	@Override
	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		double ns = 1000000000.0 / 60.0;
		int ticks = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta > 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {

				timer += 1000;
				fps = frames;
				ups = ticks;
				frames = 0;
				ticks = 0;
				//System.out.println("FPS: " + fps);
				//System.out.println("Ticks: " + ups);
			}
		}
		stop();
	}

	public static int getFrameWidth() {
		return WIDTH * SCALE;
	}

	public static int getFrameHeight() {
		return HEIGHT * SCALE;
	}

	public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth,
			double fHeight) {
		BufferedImage dbi = null;
		if (sbi != null) {
			dbi = new BufferedImage(dWidth, dHeight, imageType);
			Graphics2D g = dbi.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
			g.drawRenderedImage(sbi, at);
		}
		return dbi;
	}

	public static void changeLevel(int map) {
		
		String a = null;
		switch (map) {
		case Level.MAP:
			level = Level.map1;
			a = "res/Maps/map1_roof.json";
			break;
		case Level.MAP_NOROOF:
			level = Level.map2;
			a = "res/Maps/map1_noroof.json";
			break;
		}
		
		int x = level.getPlayer().getX();
		int y = level.getPlayer().getY() - 250;
		
		level.getPlayer().setX(x);
		level.getPlayer().setY(y);
		
		Handler.g.setX(x);
		Handler.g.setY(y);
		
		level.addEntities(a);
		
		
		
		level.getPlayer().changeLevel = false;

	}

}
