package glproject;

public class Light {
    private Vector3d location = new Vector3d(0, 0, 0);
    private Material material = new Material();
    
    public void setAmbient(float ambient) {
	this.material.ambient = ambient;
    }
    
    public void setDiffuse(float diffuse) {
	this.material.diffuse = diffuse;
    }
    
    public void setSpecular(float specular) {
	this.material.specular = specular;
    }
    
    public void setMaterial(Material material) {
	this.material = material;
    }
    
    public float getAmbient() {
	return this.material.ambient;
    }
    
    public float getDiffuse() {
	return this.material.diffuse;
    }
    
    public float getSpecular() {
	return this.material.specular;
    }
    
    public Material getMaterial() {
	return this.material;
    }
}
