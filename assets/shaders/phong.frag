varying vec3 normal;
varying vec3 vectorToLight;

void main()
{
	vec3 normalizedNormal = normalize(normal);
	vec3 normalizedVectorToLight = normalize(vectorToLight);
	float DiffuseTerm = clamp(dot(normal, normalizedVectorToLight), 0.0, 1.0);
	gl_FragColor = gl_FrontMaterial.ambient + gl_FrontMaterial.diffuse * DiffuseTerm;
}