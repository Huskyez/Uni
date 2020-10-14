#include <GL/glew.h>
#include <GLFW/glfw3.h>
#include <iostream>

using namespace std;

// vertex array objects (vom discuta asta săptămâna viitoare)
#define numVAOs 1
GLuint vao[numVAOs];

// rendering program (Programul care va fi rulat pe GPU)
GLuint renderingProgram;

GLuint createVertexShader();
GLuint createFragmentShader();
GLuint createShaderProgram();

void init(GLFWwindow* window)
{	
	glfwMakeContextCurrent(window);

	if (glewInit() != GLEW_OK)
	{
		exit(EXIT_FAILURE);
	}

	glfwSwapInterval(1);

	// INIȚIALIZARE:
	renderingProgram = createShaderProgram(); // asamblează shader-ul
	glGenVertexArrays(numVAOs, vao);		  // In program OpenGL are nevoie de cel puțin un vertex array object
	glBindVertexArray(vao[0]);				  // care să fie legat la contextul OpenGL
}

void display(GLFWwindow* window, double currentTime)
{
	// COD PENTRU DESENARE
	glClearColor(0.1, 0.3, 0.6, 1.0); // selectează o culoare pentru resetarea bufferului de culoare
	glClear(GL_COLOR_BUFFER_BIT);	  // resetează bufferul de culoarea
	glUseProgram(renderingProgram);	  // încarcă programul de shading
	glPointSize(50.0f);
	glDrawArrays(GL_POINTS, 0, 1);	  // desenează orice (un punct aici) pentru a activa shader-ul

}


int main(void)
{
	if (!glfwInit())
	{
		exit(EXIT_FAILURE);
	}

	// OpenGL version 4.3
	// glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
	// glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

	GLFWwindow *window = glfwCreateWindow(1024, 768, "Laborator 1 - Hello Pixăl", NULL, NULL);
	init(window);

	while (!glfwWindowShouldClose(window))
	{
		display(window, glfwGetTime());

		glfwSwapBuffers(window); // update window buffer
		glfwPollEvents();		 // windows related events:
	}

	// clean-up
	glfwDestroyWindow(window);
	glfwTerminate();
	exit(EXIT_SUCCESS);
}

GLuint createVertexShader()
{
	const char *vshaderSource =
		"#version 430 \n"
		"void main(void) \n"
		"{ gl_Position = vec4(0.5, 0.0, 0.0, 1.0); }";
	GLuint vShader = glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vShader, 1, &vshaderSource, NULL);
	glCompileShader(vShader);
	return vShader;
}

GLuint createFragmentShader()
{
	const char *fshaderSource =
		"#version 430 \n"
		"out vec4 color; \n"
		"void main(void) \n"
		"{ color = vec4(1.0, 0.5, 1.0, 1.0); }";
	GLuint fShader = glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fShader, 1, &fshaderSource, NULL);
	glCompileShader(fShader);
	return fShader;
}

GLuint createShaderProgram()
{
	GLuint vfProgram = glCreateProgram();
	glAttachShader(vfProgram, createVertexShader());
	glAttachShader(vfProgram, createFragmentShader());
	glLinkProgram(vfProgram);

	return vfProgram;
}