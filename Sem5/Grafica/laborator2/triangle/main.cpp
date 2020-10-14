#include <GL/glew.h>
#include <GLFW/glfw3.h>
#include <iostream>
//#include <utils.h>
#include <fstream>

using namespace std;

// vertex array objects
#define numVAOs 1
GLuint vao[numVAOs];

GLuint renderingProgram;


// acestea vor deveni metode statice în clasa Utils (vezi common/utils.h)
string readShaderSource(const char *filePath);
GLuint createVertexShader(const char *vertShaderFilePath);
GLuint createFragmentShader(const char *fragShaderFilePath);
GLuint createShaderProgram(const char *vertShaderFilePath, const char *fragShaderFilePath);
void printShaderLog(GLuint shader);
void printProgramLog(int prog);
bool checkOpenGLError();

void init(GLFWwindow *window)
{
	renderingProgram = createShaderProgram("shaders/vertex.glsl", "shaders/fragment.glsl");
	glGenVertexArrays(numVAOs, vao);
	glBindVertexArray(vao[0]);
	glUseProgram(renderingProgram);
}

void display(GLFWwindow *window, double currentTime)
{
	glClearColor(0.0, 0.0, 0.0, 1.0);
	glClear(GL_COLOR_BUFFER_BIT); // curăță fundalul
	glDrawArrays(GL_TRIANGLES, 0, 3);
}

int main(void)
{
	if (!glfwInit())
	{
		exit(EXIT_FAILURE);
	}

	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

	GLFWwindow *window = glfwCreateWindow(1024, 768, "Laborator 2 - Hello Triangle", NULL, NULL);
	glfwMakeContextCurrent(window);

	if (glewInit() != GLEW_OK)
	{
		exit(EXIT_FAILURE);
	}
	// Ensure we can capture the escape key being pressed below
	glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);
	glfwSwapInterval(1);

	init(window);
	while (!glfwWindowShouldClose(window) && glfwGetKey(window, GLFW_KEY_ESCAPE) != GLFW_PRESS)
	{
		display(window, glfwGetTime());
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	glfwDestroyWindow(window);
	glfwTerminate();
	exit(EXIT_SUCCESS);
}

GLuint createVertexShader(const char *vertShaderFilePath)
{
	GLint vertCompiled;
	string vertShaderStr = readShaderSource(vertShaderFilePath);
	const char *vshaderSource = vertShaderStr.c_str();
	GLuint vShader = glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vShader, 1, &vshaderSource, NULL);
	glCompileShader(vShader);
	checkOpenGLError();
	glGetShaderiv(vShader, GL_COMPILE_STATUS, &vertCompiled);
	if (vertCompiled != 1)
	{
		cout << "vertex compilation failed" << endl;
		printShaderLog(vShader);
	}
	return vShader;
}

GLuint createFragmentShader(const char *fragShaderFilePath)
{
	GLint fragCompiled;
	string fragShaderStr = readShaderSource(fragShaderFilePath);
	const char *fshaderSource = fragShaderStr.c_str();
	GLuint fShader = glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fShader, 1, &fshaderSource, NULL);
	glCompileShader(fShader);
	checkOpenGLError();
	glGetShaderiv(fShader, GL_COMPILE_STATUS, &fragCompiled);
	if (fragCompiled != 1)
	{
		cout << "fragment compilation failed" << endl;
		printShaderLog(fShader);
	}
	return fShader;
}

GLuint createShaderProgram(const char *vertShaderFilePath, const char *fragShaderFilePath)
{
	GLint linked;

	GLuint vfProgram = glCreateProgram();
	glAttachShader(vfProgram, createVertexShader(vertShaderFilePath));
	glAttachShader(vfProgram, createFragmentShader(fragShaderFilePath));
	glLinkProgram(vfProgram);

	checkOpenGLError();
	glGetProgramiv(vfProgram, GL_LINK_STATUS, &linked);
	if (linked != 1)
	{
		cout << "linking failed" << endl;
		printProgramLog(vfProgram);
	}

	return vfProgram;
}

string readShaderSource(const char *filePath)
{
	string content;
	ifstream fileStream(filePath, ios::in);
	string line = "";
	while (!fileStream.eof())
	{
		getline(fileStream, line);
		content.append(line + "\n");
	}
	fileStream.close();
	return content;
}

void printShaderLog(GLuint shader)
{
	int len = 0;
	int chWrittn = 0;
	char *log;
	glGetShaderiv(shader, GL_INFO_LOG_LENGTH, &len);
	if (len > 0)
	{
		log = (char *)malloc(len);
		glGetShaderInfoLog(shader, len, &chWrittn, log);
		cout << "Shader Info Log: " << log << endl;
		free(log);
	}
}
void printProgramLog(int prog)
{
	int len = 0;
	int chWrittn = 0;
	char *log;
	glGetProgramiv(prog, GL_INFO_LOG_LENGTH, &len);
	if (len > 0)
	{
		log = (char *)malloc(len);
		glGetProgramInfoLog(prog, len, &chWrittn, log);
		cout << "Program Info Log: " << log << endl;
		free(log);
	}
}
bool checkOpenGLError()
{
	bool foundError = false;
	int glErr = glGetError();
	while (glErr != GL_NO_ERROR)
	{
		cout << "glError: " << glErr << endl;
		foundError = true;
		glErr = glGetError();
	}
	return foundError;
}