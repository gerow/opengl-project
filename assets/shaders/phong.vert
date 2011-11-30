varying vec3 normal;
varying vec3 position;

void main()
{
    gl_Position = ftransform();
    gl_TexCoord[0] = gl_TextureMatrix[0] * gl_MultiTexCoord0;
    normal = normalize(gl_NormalMatrix * gl_Normal.xyz);
    position = vec3(gl_ModelViewMatrix * gl_Vertex);
}