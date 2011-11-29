varying vec3 normal;
varying vec3 vectorToLight[8];
varying vec3 position;
varying vec2 texCoords;
uniform int num_lights;

void main()
{
	gl_Position    = gl_ModelViewProjectionMatrix * gl_Vertex;
	position = vec3(gl_Position);
	normal = normalize(gl_NormalMatrix * gl_Normal);
	vec4 inModelViewSpace = gl_ModelViewMatrix * gl_Vertex;
	for (int i = 0; i < num_lights; ++i) {
		vectorToLight[i] = normalize(vec3(gl_LightSource[i].position - inModelViewSpace));
	}
    texCoords=gl_MultiTexCoord0.st;
}