varying vec3 normal;
varying vec3 vectorToLight;
void main()
{
	gl_Position    = gl_ModelViewProjectionMatrix * gl_Vertex;
	normal = gl_NormalMatrix * gl_Normal;
	vec4 inModelViewSpace = gl_ModelViewMatrix * gl_Vertex;
	vectorToLight = vec3(gl_LightSource[0].position - inModelViewSpace);
}