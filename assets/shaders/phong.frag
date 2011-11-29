varying vec3 normal;
varying vec3 vectorToLight[8];
varying vec3 position;
varying vec2 texCoords;
uniform sampler2D tex;
uniform int num_lights;

void main()
{
	vec3 normalizedNormal = normalize(normal);
	float iDiffuse = 0.0;
	float iSpecular = 0.0;
	float n = gl_FrontMaterial.shininess;
	float dotPart;
	
	for (int i = 0; i < num_lights; ++i) {
		iDiffuse = iDiffuse + clamp(max(dot(normal, vectorToLight[i]), 0.0), 0.0, 1.0);
		vec3 H = normalize(vectorToLight[i] + position);
		
		iSpecular = iSpecular + pow(max(dot(normal, H), 0.0), n);
	}
	vec4 litColor=texture2D(tex, texCoords);
	gl_FragColor = litColor*(gl_FrontMaterial.ambient + gl_FrontMaterial.diffuse * iDiffuse + gl_FrontMaterial.specular * iSpecular);
}