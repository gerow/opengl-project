package glproject;

public class Vector4d {
    public float x, y, z, w;
    
    public Vector4d() {
	this.x = this.y = this.z = this.w = 0.0f;
    }
    
    public Vector4d(Vector4d other) {
	if (other == null) {
	    return;
	}
	this.x = other.x;
	this.y = other.y;
	this.z = other.z;
	this.w = other.w;
    }

    public Vector4d(float x, float y, float z, float w) {
	this.x = x;
	this.y = y;
	this.z = z;
	this.w = w;
    }

    public Vector4d add(Vector4d other) {
	return new Vector4d(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    public Vector4d multiply(float scalar) {
	return new Vector4d(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Vector4d subtract(Vector4d other) {
	return this.add(other.multiply(-1));
    }
    
    public Vector4d divide(float scalar) {
	return this.multiply(1.0f/scalar);
    }

    public Vector4d dot(Vector4d other) {
	return new Vector4d(x * other.x, y * other.y, z * other.z, w * other.w);
    }

    /*
    public Vector3d cross(Vector3d other) {
	return new Vector3d(y * other.z - z * other.y, z * other.x - x
		* other.z, x * other.y - y * other.x);
    }
    */

    public Vector4d normalize() {
	float dist = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)
		+ Math.pow(z, 2) + Math.pow(w,  2));
	return new Vector4d(x / dist, y / dist, z / dist, w / dist);
    }

    public Vector4d vectorTo(Vector4d other) {
	return other.subtract(this);
    }

    public float distanceTo(Vector4d other) {
	return (float) Math.sqrt(Math.pow(other.x - x, 2)
		+ Math.pow(other.y - y, 2) + Math.pow(other.z - z, 2) + Math.pow(other.w - w, 2));
    }
    
    public boolean equals(Vector4d other) {
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
