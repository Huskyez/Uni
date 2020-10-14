#include <GL/glew.h>
#include <string>

using namespace std;

class Utils
{
public:
    static string readShaderSource(const char *filePath);
    static GLuint createVertexShader(const char *vertShaderFilePath);
    static GLuint createFragmentShader(const char *fragShaderFilePath);
    static GLuint createShaderProgram(const char *vertShaderFilePath, const char *fragShaderFilePath);

    static void printShaderLog(GLuint shader);
    static void printProgramLog(int prog);
    static bool checkOpenGLError();
};
