package rpg;



import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import rpg.entity.Entity;
import rpg.gfx.Sprite;
import rpg.gfx.SpriteSheet;
import rpg.gui.Gui;
import rpg.gui.GuiAction;
import rpg.gui.GuiButton;
import rpg.gui.GuiMap;
import rpg.gui.TextDraw;
import rpg.json.JSONDecoder;


@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {
	
	public static String[] texts = new String[100];

	public static final int WIDTH = 480;
	public static final int HEIGHT = 270;
	public static final int SCALE = 4;
	
	public static final boolean DEBUG = true;
	
	public static String map1_noroof = "res/Maps/map1_noroof.json";
	public static String map1_roof = "res/Maps/map1_roof.json";
	
	public static SpriteSheet sheet;
	public static SpriteSheet[] sheets = new SpriteSheet[20];
	
	public static Camera cam;
	
	public static KeyInput key;
	
	public static long[] data = new long[10000];
	
	public static Sprite[] player = new Sprite[36];
	
	private BufferStrategy bs;
	
	private int fps,ups;
	
	public static Sprite[] sprites = new Sprite[4000];
	public static Sprite bg,ground;
	
	private boolean running = false;
	private boolean showinv = false;
	private Thread thread;
	
	public static Color textc = new Color(138,60,34);
	
	public static Handler handler;
	
	
	
	public Image scrolltext_bg = new ImageIcon(this.getClass().getResource("/Scrolltext/background.png")).getImage();;
	public ImageObserver observer;
	Inventory inv = new Inventory();
	Gui gui = new Gui();
	TextDraw draw = new TextDraw();
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}
	public void tick() {
		handler.tick();
		key.tick();
		if(KeyInput.inventory) {
			showinv = !showinv;
		}
		for(Entity e:Handler.entity){
			if(e.getId()==Id.player) {
				cam.tick(e);
			}
		}
		draw.tick();
		gui.tick();
	}
	
	public void render() {
		bs = getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(4);
			return;
		}
			
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH*SCALE+100, HEIGHT*SCALE+100);
		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		g2d.translate(-cam.getX(), -cam.getY());
			
		draw.render(g);
		gui.render(g);
			
		g.dispose();
		bs.show();
	}

	public void init(){
		
		handler = new Handler();
		
		texts[0] = "Hallo " + Handler.g.getName();
		
     	sheet = new SpriteSheet("/Character/normal.png");
     	
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
     	
     	JSONObject map1 = JSONDecoder.loadMapData(map1_noroof);
		
     	int z = 0;
     	
		for(int a=0;a<13;a++){
			JSONObject data1 = (JSONObject) ((JSONArray)map1.get("tilesets")).get(a);
			long width = (long) data1.get("imagewidth") / 32;
			long height = (long) data1.get("imageheight") /32 ;
			
			System.out.println("Spritesheet wird ausgelesen: " + a);
			for(int b=0;b<height;b++){
				for(int c=0;c<width;c++){
					sprites[z+1] = new Sprite(sheets[a],((int)(c*32)),((int)(b*32)),32,32);
					z++;
				}
			}
		}
		
		cam = new Camera();		
		key = new KeyInput();
		addKeyListener(key);
		
		MouseInput mouse = new MouseInput();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addMouseWheelListener(mouse);
		
	
		/* GUI Elemente */
		try {
			GuiMap map = new GuiMap(230, 20, 1220, 930);
			map.setVisible(false);
			gui.addGuiElement(map);
			
			GuiButton inventory = new GuiButton(230, 20, ImageIO.read(new File("res/Inventory/inv.png")), new GuiAction() {
				public void action() {

				}
			});
			inventory.setVisible(false);
			gui.addGuiElement(inventory);
			
			GuiButton player = new GuiButton(1100, 290, ImageIO.read(new File("res/Inventory/player.png")), new GuiAction() {
				public void action() {

				}
			});
			player.setVisible(false);
			gui.addGuiElement(player);
			
			GuiButton cancel_button = new GuiButton(1750, 20, ImageIO.read(new File("res/Buttons/cancel.png")), new GuiAction() {
				public void action() {
					System.exit(0);
				}
			});
			cancel_button.setVisible(false);
			gui.addGuiElement(cancel_button);
			
			GuiButton health_button = new GuiButton(20, 950, ImageIO.read(new File("res/Buttons/health.png")), new GuiAction() {
				public void action() {
					
				}
			});
			gui.addGuiElement(health_button);
			
			
			GuiButton menu_button = new GuiButton(1770, 930, ImageIO.read(new File("res/Buttons/menu.png")), new GuiAction() {
				public void action() {
					
				}
			});
			gui.addGuiElement(menu_button);
			
			GuiButton inventory_button = new GuiButton(1620, 930, ImageIO.read(new File("res/Buttons/inventory.png")), new GuiAction() {
				public void action() {
					inventory.setVisible(!inventory.isVisible());
					player.setVisible(!player.isVisible());
				}
			});
			gui.addGuiElement(inventory_button);
			
			GuiButton quest_button = new GuiButton(1460, 935, ImageIO.read(new File("res/Buttons/quest.png")), new GuiAction() {
				public void action() {
					
				}
			});
			gui.addGuiElement(quest_button);
			
			GuiButton map_button = new GuiButton(30, 840, ImageIO.read(new File("res/Buttons/map_button.png")), new GuiAction() {
				public void action() {
					map.setVisible(!map.isVisible());
					KeyInput.key_enable = !KeyInput.key_enable;
				}
			});
			gui.addGuiElement(map_button);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* GUI Elemente */
		
		
		int p = 0;
     	
    	for(int i=0;i<player.length/4;i++){
    		player[i] = new Sprite(sheet, p+17, 523, 30,52);
    		p+=64;
    	}
    	p=0;
    	for(int i=9;i<player.length/4+9;i++){
    		player[i] = new Sprite(sheet, p+17, 587, 30,52);
    		p+=64;
    	}
    	p=0;
    	for(int i=18;i<player.length/4+18;i++){
    		player[i] = new Sprite(sheet, p+17, 650, 30,52);
    		p+=64;
    	}
    	p=0;
    	for(int i=27;i<player.length/4+27;i++){
    		player[i] = new Sprite(sheet, p+17, 714, 30,52);
    		p+=64;
    	}
    	
    	handler.ChangeMusic(1,1,false);
    	handler.ChangeLevel("res/Maps/map1_roof.json");
		
		
		
	
	}
	
	@Override
	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		double ns = 1000000000.0/60.0;
		int ticks = 0;
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta+=(now-lastTime)/ns; 
			lastTime = now;
			while(delta>1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000) {
				
				timer+=1000;
				fps = frames;
				ups = ticks;
				frames = 0;
				ticks = 0;
				System.out.println("FPS: " + fps);
				System.out.println("Ticks: " + ups);
			}
		}
		stop();
	}
	
	public Game() {
		Dimension size = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);

	}
	public static int getFrameWidth() {
		return WIDTH*SCALE;
	}
	
	public static int getFrameHeight() {
		return HEIGHT*SCALE;
	}
	
	public static Rectangle getVisisbleArea() {
        for(int i=0;i<handler.entity.size();i++) {
            Entity e = handler.entity.get(i);   
            if(e.getId()==Id.player)
            {
                return new Rectangle(e.getX()-960, e.getY()-540, 1920, 1080);
            }
        }
        return null;
    }
	
	public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
	    BufferedImage dbi = null;
	    if(sbi != null) {
	        dbi = new BufferedImage(dWidth, dHeight, imageType);
	        Graphics2D g = dbi.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
	        g.drawRenderedImage(sbi, at);
	    }
	    return dbi;
	}
	

}
