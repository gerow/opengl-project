package glproject;

public class Light {
    private Vector3d location = new Vector3d(0, 0, 0);
    private Material material = new Material();
    
    public void setAmbient(Vector4d ambient) {
	this.material.ambient = ambient;
    }
    
    public void setDiffuse(Vector4d diffuse) {
	this.material.diffuse = diffuse;
    }
    
    public void setSpecular(Vector4d specular) {
	this.material.specular = specular;
    }
    
    public void setMaterial(Material material) {
	this.material = material;
    }
    
    public Vector4d getAmbient() {
	return this.material.ambient;
    }
    
    public Vector4d getDiffuse() {
	return this.material.diffuse;
    }
    
    public Vector4d getSpecular() {
	return this.material.specular;
    }
    
    public Material getMaterial() {
	return this.material;
    }
}
