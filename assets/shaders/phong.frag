varying vec3 normal;
varying vec3 vectorToLight[2];
varying vec3 position;

void main()
{
	vec3 normalizedNormal = normalize(normal);
	float DiffuseTerm = 0.0;
	float SpecularTerm = 0.0;
	float n = gl_FrontMaterial.shininess;
	float dotPart;
	for (int i = 0; i < 2; ++i) {
		DiffuseTerm = DiffuseTerm + clamp(dot(normal, normalize(vectorToLight[i])), 0.0, 1.0);
		dotPart = clamp(dot(-reflect(normalize(vectorToLight[i]), normal), position), 0.0, 1.0);
		SpecularTerm = SpecularTerm + pow(dotPart, n);
	}
	gl_FragColor = gl_FrontMaterial.ambient + gl_FrontMaterial.diffuse * DiffuseTerm + gl_FrontMaterial.specular * SpecularTerm;
}