package glproject;

public class Vector2f {
    public float x, y;
    
    public Vector2f() {
	this.x = this.y = 0.0f;
    }
    
    public Vector2f(Vector2f copy) {
	this.x = copy.x;
	this.y = copy.y;
    }
    
    public Vector2f(float x, float y) {
	this.x =  x;
	this.y = y;
    }
}
