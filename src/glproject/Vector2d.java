package glproject;

public class Vector2d {
    public float x, y;
    
    public Vector2d() {
	this.x = this.y = 0.0f;
    }
    
    public Vector2d(Vector2d copy) {
	this.x = copy.x;
	this.y = copy.y;
    }
    
    public Vector2d(float x, float y) {
	this.x =  x;
	this.y = y;
    }
}
