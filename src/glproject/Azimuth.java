package glproject;

/**
 * @author gerow
 * 
 */
public class Azimuth {
    private static final Vector3f identity = new Vector3f(0, 0, 1);
    Vector3f direction = Azimuth.identity;
    private float heading = 0;
    private float altitude = 0;

    public float getHeading() {
	return heading;
    }

    public void setHeading(float heading) {
	this.heading = heading;
	this.recalculateDirection();
    }

    public void recalculateDirection() {
	this.direction = Matrix3by3.getRotationX((float) Math.toRadians(this.altitude)).multiply(Azimuth.identity);
	this.direction = Matrix3by3.getRotationY((float) Math.toRadians(this.heading)).multiply(this.direction);
    }

    public float getAltitude() {
	return altitude;
    }

    public void setAltitude(float altitude) {
	this.altitude = altitude;
	this.recalculateDirection();
    }
}
