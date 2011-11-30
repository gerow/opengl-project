uniform sampler2D texture;
uniform int num_lights;

varying vec3 normal;
varying vec3 position;

void main()
{
    vec4 ambient = vec4(0.0);
    vec4 diffuse = vec4(0.0);
    vec4 specular = vec4(0.0);
    
    vec3 norm = normalize(normal);
    vec3 cameraVector = normalize(-position.xyz);
    
    for (int i = 0; i < num_lights; ++i) {
        vec3 lightVector = gl_LightSource[i].position.xyz - position;
        lightVector = normalize(lightVector);
        float diffuseAmount = max(0.0, dot(normal, lightVector));
        diffuse = diffuse + gl_LightSource[i].diffuse * diffuseAmount;
        ambient = ambient + gl_LightSource[i].ambient;
        
        vec3 halfVector = normalize(lightVector + cameraVector);
        float halfVecDot = max(0.0, dot(norm, halfVector));
        float specularAmount = pow(halfVecDot, gl_FrontMaterial.shininess);
        
        specular = specular + gl_LightSource[i].specular * specularAmount;
    }
    
    vec4 ambientPart = ambient * gl_FrontMaterial.ambient;
    vec4 diffusePart = diffuse * gl_FrontMaterial.diffuse;
    vec4 specularPart = specular * gl_FrontMaterial.specular;
    
    gl_FragColor = (ambientPart + diffusePart + specularPart);
}