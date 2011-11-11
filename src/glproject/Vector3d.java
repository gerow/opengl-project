package glproject;

public class Vector3d {
    public float x, y, z;
    
    public Vector3d() {
	this.x = this.y = this.z = 0.0f;
    }
    
    public Vector3d(Vector3d other) {
	this.x = other.x;
	this.y = other.y;
	this.z = other.z;
    }

    public Vector3d(float x, float y, float z) {
	this.x = x;
	this.y = y;
	this.z = z;
    }

    public Vector3d add(Vector3d other) {
	return new Vector3d(x + other.x, y + other.y, z + other.z);
    }

    public Vector3d multiply(float scalar) {
	return new Vector3d(x * scalar, y * scalar, z * scalar);
    }

    public Vector3d subtract(Vector3d other) {
	return this.add(other.multiply(-1));
    }
    
    public Vector3d divide(float scalar) {
	return this.multiply(1.0f/scalar);
    }

    public Vector3d dot(Vector3d other) {
	return new Vector3d(x * other.x, y * other.y, z * other.z);
    }

    public Vector3d cross(Vector3d other) {
	return new Vector3d(y * other.z - z * other.y, z * other.x - x
		* other.z, x * other.y - y * other.x);
    }

    public Vector3d normalize() {
	float dist = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)
		+ Math.pow(z, 2));
	return new Vector3d(x / dist, y / dist, z / dist);
    }

    public Vector3d vectorTo(Vector3d other) {
	return other.subtract(this);
    }

    public float distanceTo(Vector3d other) {
	return (float) Math.sqrt(Math.pow(other.x - x, 2)
		+ Math.pow(other.y - y, 2) + Math.pow(other.z - z, 2));
    }
    
    public boolean equals(Vector3d other) {
	return (this.x == other.x && this.y == other.y && this.z == other.z);
    }
}
