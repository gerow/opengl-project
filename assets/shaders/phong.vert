varying vec3 normal;
varying vec3 vectorToLight[2];
varying vec3 position;

void main()
{
	gl_Position    = gl_ModelViewProjectionMatrix * gl_Vertex;
	position = vec3(gl_Position);
	normal = gl_NormalMatrix * gl_Normal;
	vec4 inModelViewSpace = gl_ModelViewMatrix * gl_Vertex;
	for (int i = 0; i < 2; ++i) {
		vectorToLight[i] = vec3(gl_LightSource[i].position - inModelViewSpace);
	}
}