package glproject;

public class Matrix3by3 {
    float a11, a12, a13, a21, a22, a23, a31, a32, a33;
    
    public Matrix3by3() {
	a11 = a12 = a13 = a21 = a22 = a23 = a31 = a32 = a33 = 0;
    }
    
    public Vector3f multiply(Vector3f right) {
	Vector3f out = new Vector3f();
	
	out.x = (a11 * right.x + a12 * right.y + a13 * right.z);
	out.y = (a21 * right.x + a22 * right.y + a23 * right.z);
	out.z = (a31 * right.x + a32 * right.y + a33 * right.z);
	
	return out;
    }
    
    public static Matrix3by3 getIdentity() {
	Matrix3by3 out = new Matrix3by3();
	out.a11 = out.a22 = out.a33 = 1;
	out.a12 = out.a13 = out.a21 = out.a23 = out.a31 = out.a32 = 0;
	return out;
    }
    
    public static Matrix3by3 getRotationX(float degrees) {
	float rads = (float) Math.toRadians(degrees);
	Matrix3by3 out = Matrix3by3.getIdentity();
	out.a22 = (float) Math.cos(rads);
	out.a23 = (float) -Math.sin(rads);
	out.a32 = (float) Math.sin(rads);
	out.a33 = (float) Math.cos(rads);
	
	return out;
    }
    
    public static Matrix3by3 getRotationY(float degrees) {
	float rads = (float) Math.toRadians(degrees);
	Matrix3by3 out = Matrix3by3.getIdentity();
	
	out.a11 = (float) Math.cos(rads);
	out.a13 = (float) Math.sin(rads);
	out.a31 = (float) -Math.sin(rads);
	out.a33 = (float) Math.cos(rads);
	
	return out;
    }
    
    public static Matrix3by3 getRotationZ(float degrees) {
	float rads = (float) Math.toRadians(degrees);
	Matrix3by3 out = Matrix3by3.getIdentity();
	
	out.a11 = (float) Math.cos(rads);
	out.a12 = (float) -Math.sin(rads);
	out.a21 = (float) Math.sin(rads);
	out.a22 = (float) Math.cos(rads);
	
	return out;
    }
    
    public String toString() {
	String out;
	out = "[" + a11 + ", " + a12 + ", " + a13 + "]\n";
	out += "[" + a21 + ", " + a22 + ", " + a23 + "]\n";
	out += "[" + a31 + ", " + a32 + ", " + a33 + "]\n";
	
	return out;
    }
}
