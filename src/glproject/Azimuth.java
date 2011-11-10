package glproject;

/**
 * @author gerow
 *
 */
public class Azimuth {
    Vector3d direction = new Vector3d(0, 0, 1);
    private float heading = 0;
    private float altitude = 0;
    public float getHeading() {
	return heading;
    }
    public void setHeading(float heading) {
	float delta = heading - this.heading;
	this.direction = Matrix3by3.getRotationY(delta).multiply(this.direction);
	this.heading = heading;
    }
    public float getAltitude() {
	return altitude;
    }
    public void setAltitude(float altitude) {
	float delta = altitude - this.altitude;
	this.direction = Matrix3by3.getRotationX(delta).multiply(this.direction);
	this.altitude = altitude;
    }
}
