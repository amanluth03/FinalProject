				


package finalproject;
 
import processing.core.PApplet;
import processing.core.PImage;
 
@SuppressWarnings("serial")
public class FinalProject extends PApplet 
{
	public boolean pause = false;
    PImage background;
    PImage paddleImage;
    PImage soapImage;
    PImage paddleImage2;
    
    public Paddle player;
    public Paddle player2;
    public Ball bubble;
    int p1s = 0;
    int p2s = 0;
    
     
    public void setup() 
    {
        size(800, 600);
        background = loadImage("soccerbackground.png");
        paddleImage = loadImage("eden-hazard2.png");
        soapImage = loadImage ("ball1.png");
        paddleImage2 = loadImage("mezutozil6.png");
        
        player = new Paddle(paddleImage, 75, 300);
        player2 = new Paddle(paddleImage2, 675, 300);
        bubble = new Ball(soapImage, 0, height/2);
    }
 
    public void draw() 
    {    
    	player.update();
    	player2.update();
		
    	if( pause == false){
    		
    		bubble.update();
    	}
        background(background);
        player.drawPaddle();
        player2.drawPaddle();
        bubble.drawBall();
        
        if (p1s >= 10){
        	pause = true;
        	System.out.println("Chelsea wins!");
        }
        else if (p2s >= 10) {
        	pause = true;
        	System.out.println("Arsenal wins!");
        }
        
        player1Score();
    	player2Score();
    }
    
    public void keyPressed() {
    	if(key == 'w'){
    		player.moveDown = true;
    	}
    	if(key == 's') {
    		player.moveUp = true;
    	}
    	
    	if(key == 'd')
    		player.moveRight = true;
    	
    	if(key == 'a'){
    		player.moveLeft = true;
    	}
    	
    	if(key == 'i'){
    		player2.moveDown = true;
    	}
    	if(key == 'k') {
    		player2.moveUp = true;
    	}
    	if(key == 'l'){
    		player2.moveRight = true;
    	}
    	
    	if(key == 'j'){
    		player2.moveLeft = true;
    	}
    	
    	if(key == 'p'){
    		pause = true;
    	}
   
    if(key == 'r'){
    	pause = false;
    }
    
    }
    public void keyReleased() {
    	if(key == 'w') {
    		player.moveDown = false;
    	}
    	if(key == 's') {
    		player.moveUp = false;
    	}
    	if(key == 'd'){
    		player.moveUp = false;
    	}
    	
    	if(key == 'a'){
    		player.moveUp = false;
    	}
    	
    	if(key == 'i') {
    		player2.moveDown = false;
    	}
    	if(key == 'k') {
    		player2.moveUp = false;
    	}
    	
    	if(key == 'l'){
    		player2.moveRight = false;
    	}
    	
    	if(key == 'j'){
    		player2.moveLeft = false;
    	}
    	
    	
    }
    
    
    public class Paddle{
        
		PImage sprite;
        float xPos;
        float yPos;
        
        
         
        int speed = 10;
        public boolean moveUp = false;
        public boolean moveDown = false;
        public boolean moveRight = false;
        public boolean moveLeft = false;
         
        public Paddle(PImage paddleSprite, float startX, float startY){
            sprite = paddleSprite;
            xPos = startX;
            yPos = startY;
        }
         
        public void drawPaddle(){
            image(sprite, xPos, yPos);
        }
        
        public void update()
    {
        if(this.moveUp)
        {
        	this.yPos += this.speed;
        	
        }
        if(this.moveDown)
        {
        	this.yPos -= this.speed;
        }
        checkBounds();
    }
    public void checkBounds()
    {
        if(this.yPos < 0){
            this.yPos = -this.yPos;
        }
        if(this.yPos < 0)
        {
            this.yPos = -this.xPos;
            this.yPos = 0;
        }
        if(this.yPos + this.sprite.height > height){
            this.yPos = -this.yPos;
            this.yPos = height - this.sprite.height;
        }

        if(this.yPos > width){
            this.xPos = player.xPos;
            this.xPos = player.xPos - this.sprite.width;

            this.xPos = -this.xPos;
        }
    }
    
    
    
    
{
    if(this.moveRight)
    {
    	this.yPos += this.speed;
    	
    }
    if(this.moveLeft)
    {
    	this.yPos -= this.speed;
    }
    checkBounds();
}

{
    if(this.yPos < 0){
        this.yPos = -this.yPos;
    }
    if(this.yPos < 0)
    {
        this.yPos = -this.xPos;
        this.yPos = 0;
    }
    if(this.yPos + this.sprite.height > height){
        this.yPos = -this.yPos;
        this.yPos = height - this.sprite.height;
    }

    if(this.yPos > width){
        this.xPos = player.xPos;
        this.xPos = player.xPos - this.sprite.width;

        this.xPos = -this.xPos;
    }
}
    
}
    
    public class Ball{
        
        PImage sprite;
        float xPos;
        float yPos;
         
        int xSpeed = 8;
        int ySpeed = 8;
         
        public Ball(PImage ballSprite, float startX, float startY){
            sprite = ballSprite;
            xPos = startX;
            yPos = startY;
        }
         
        public void drawBall(){
            image(sprite, xPos, yPos);
        }
         
        public void update(){
            this.xPos += this.xSpeed;
            this.yPos += this.ySpeed;
             
            checkBounds();
            checkCollision();
        }
         
        public void checkBounds()
        {
            if(this.yPos < 0){
                this.ySpeed = -this.ySpeed;
            }
            if(this.xPos < 0)
            {
            	p2s++;
                this.xSpeed = -this.xSpeed;
                this.xPos = 400;
                this.yPos = 10;
            }
            if(this.xPos > 800)
            {
            	p1s++;
                this.xSpeed = -this.xSpeed;
                this.xPos = 400;
                this.yPos = 10;
            }
            if(this.xPos + this.sprite.width > width){
                p1s++;
            	this.xSpeed = -this.xSpeed;
                this.xPos =  width/2;
            }
             
            if(this.yPos + this.sprite.height > height){
               this.ySpeed = -this.ySpeed;
            }
        }
         
        public void checkCollision(){
            if( this.xPos + this.sprite.width > player.xPos && this.xPos < player.xPos + player.sprite.width){
                if(this.yPos > player.yPos - player.sprite.height && this.yPos < player.yPos + player.sprite.height) {
                    this.xSpeed = -this.xSpeed;
                    this.xPos = player.xPos + player.sprite.width;
                    //this.yPos = player.yPos - this.sprite.height;
                }
            }
            if( this.xPos + this.sprite.width > player2.xPos && this.xPos < player2.xPos + player2.sprite.width){
                if(this.yPos > player2.yPos - player2.sprite.height && this.yPos < player2.yPos + player2.sprite.height) {
                    this.xSpeed = -this.xSpeed;
                    this.xPos = player2.xPos - this.sprite.width;
                    //this.yPos = player.yPos - this.sprite.height;
                }
            }
        }
        
         
    }
    public void player1Score(){
    	
    	text("Chelsea: " + p1s, 15, 10, 150, 100);
    	textSize(32);
    	fill(255,0,0);
    	
    	
    }
    
    public void player2Score(){
    	text("Arsenal: " + p2s, 625, 10, 150, 100);
    	textSize(32);
    	
    	
    	fill(0,102,204);
    }
    
    public static void main(String _args[]) {
        PApplet.main(new String[] { finalproject.FinalProject.class.getName() });
    }
    
}

