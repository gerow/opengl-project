varying vec3 normal;
varying vec3 vectorToLight[2];
varying vec3 position;
varying vec2 texCoords;

void main()
{
	vec3 normalizedNormal = normalize(normal);
	float iDiffuse = 0.0;
	float iSpecular = 0.0;
	float n = gl_FrontMaterial.shininess;
	float dotPart;
	
	for (int i = 0; i < 2; ++i) {
		iDiffuse = iDiffuse + clamp(dot(normal, normalize(vectorToLight[i])), 0.0, 1.0);
		vec3 H = normalize(vectorToLight[i] + position);
		
		iSpecular = iSpecular + pow(dot(normal, H), n);
	}
	
	gl_FragColor = gl_FrontMaterial.ambient + gl_FrontMaterial.diffuse * iDiffuse + gl_FrontMaterial.specular * iSpecular;
}