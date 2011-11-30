uniform sampler2D texture;
uniform int num_lights;
uniform float cel_factor;

varying vec3 normal;
varying vec3 position;

void main()
{
    vec4 ambient = vec4(0.0);
    vec4 diffuse = vec4(0.0);
    vec4 specular = vec4(0.0);
    bool outline = false;
    
    vec3 norm = normalize(normal);
    vec3 cameraVector = normalize(-position.xyz);
    
    float outlineCheck = dot(norm, cameraVector);
    if (outlineCheck <= 0.2) {
        outline = true;
    }
    
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
    vec3 celColors = vec3(ivec3((gl_FragColor * cel_factor) + 0.5)) / cel_factor;
    gl_FragColor = vec4(celColors.x, celColors.y, celColors.z, gl_FragColor.w);
    
    if (outline) {
        gl_FragColor = vec4(0.0, 0.0, 0.0, 1.0);
    }
}