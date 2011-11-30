package glproject;

public class Vector4f {
    public float x, y, z, w;
    
    public Vector4f() {
	this.x = this.y = this.z = this.w = 0.0f;
    }
    
    public Vector4f(Vector4f other) {
	if (other == null) {
	    return;
	}
	this.x = other.x;
	this.y = other.y;
	this.z = other.z;
	this.w = other.w;
    }

    public Vector4f(float x, float y, float z, float w) {
	this.x = x;
	this.y = y;
	this.z = z;
	this.w = w;
    }
    
    public Vector4f(float all) {
	this.x = this.y = this.z = this.w = all;
    }

    public Vector4f add(Vector4f other) {
	return new Vector4f(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    public Vector4f multiply(float scalar) {
	return new Vector4f(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Vector4f subtract(Vector4f other) {
	return this.add(other.multiply(-1));
    }
    
    public Vector4f divide(float scalar) {
	return this.multiply(1.0f/scalar);
    }

    public Vector4f dot(Vector4f other) {
	return new Vector4f(x * other.x, y * other.y, z * other.z, w * other.w);
    }

    /*
    public Vector3d cross(Vector3d other) {
	return new Vector3d(y * other.z - z * other.y, z * other.x - x
		* other.z, x * other.y - y * other.x);
    }
    */

    public Vector4f normalize() {
	float dist = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)
		+ Math.pow(z, 2) + Math.pow(w,  2));
	return new Vector4f(x / dist, y / dist, z / dist, w / dist);
    }

    public Vector4f vectorTo(Vector4f other) {
	return other.subtract(this);
    }

    public float distanceTo(Vector4f other) {
	return (float) Math.sqrt(Math.pow(other.x - x, 2)
		+ Math.pow(other.y - y, 2) + Math.pow(other.z - z, 2) + Math.pow(other.w - w, 2));
    }
    
    public boolean equals(Vector4f other) {
	return (this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w);
    }
    
    public float magnitude() {
	return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2) + Math.pow(this.w, 2));
    }
    
    public float[] toArray() {
	float[] ret = new float[4];
	ret[0] = this.x;
	ret[1] = this.y;
	ret[2] = this.z;
	ret[3] = this.w;
	
	return ret;
    }
}
