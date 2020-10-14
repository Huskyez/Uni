#include <GL/glew.h>
#include <GLFW/glfw3.h>
#include <iostream>
#include <utils.h>
#include <glm/glm.hpp>
#include <glm/gtc/type_ptr.hpp>
#include <glm/gtc/matrix_transform.hpp>

using namespace std;

// vertex array objects
#define numVAOs 1
GLuint vao[numVAOs];

// vertex buffer objects
#define numVBOs 1
GLuint vbo[numVBOs];

GLuint renderingProgram;

// model-view-projection matrix (vom discuta data viitoare)
GLuint mvpLoc;
glm::mat4 mvpMat;

void setupVertices(void)
{
	// 36 puncte, 12 triunghiuri, pentru un cub 2x2x2 centrat în origine
	float vertexPositions[108] = {
		-1.0f, 1.0f, -1.0f,
		-1.0f, -1.0f, -1.0f, 
		1.0f, -1.0f, -1.0f,
		1.0f, -1.0f, -1.0f, 
		1.0f, 1.0f, -1.0f, 
		-1.0f, 1.0f, -1.0f,
		1.0f, -1.0f, -1.0f, 
		1.0f, -1.0f, 1.0f, 
		1.0f, 1.0f, -1.0f,
		1.0f, -1.0f, 1.0f, 
		1.0f, 1.0f, 1.0f, 
		1.0f, 1.0f, -1.0f,
		1.0f, -1.0f, 1.0f, 
		-1.0f, -1.0f, 1.0f, 
		1.0f, 1.0f, 1.0f,
		-1.0f, -1.0f, 1.0f, 
		-1.0f, 1.0f, 1.0f, 
		1.0f, 1.0f, 1.0f,
		-1.0f, -1.0f, 1.0f, 
		-1.0f, -1.0f, -1.0f, 
		-1.0f, 1.0f, 1.0f,
		-1.0f, -1.0f, -1.0f, 
		-1.0f, 1.0f, -1.0f, 
		-1.0f, 1.0f, 1.0f,
		-1.0f, -1.0f, 1.0f, 
		1.0f, -1.0f, 1.0f, 
		1.0f, -1.0f, -1.0f,
		1.0f, -1.0f, -1.0f, 
		-1.0f, -1.0f, -1.0f, 
		-1.0f, -1.0f, 1.0f,
		-1.0f, 1.0f, -1.0f, 
		1.0f, 1.0f, -1.0f, 
		1.0f, 1.0f, 1.0f,
		1.0f, 1.0f, 1.0f, 
		-1.0f, 1.0f, 1.0f, 
		-1.0f, 1.0f, -1.0f};

	glGenVertexArrays(1, vao);
	glBindVertexArray(vao[0]);

	glGenBuffers(numVBOs, vbo);
	glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vertexPositions), vertexPositions, GL_STATIC_DRAW);
}

void init(GLFWwindow *window)
{
	renderingProgram = Utils::createShaderProgram("shaders/vertex.glsl", "shaders/fragment.glsl");
	glUseProgram(renderingProgram);
	setupVertices();
}

void display(GLFWwindow *window, double currentTime)
{
	glClearColor(0.0, 0.0, 0.0, 1.0);
	glClear(GL_COLOR_BUFFER_BIT);
	glClear(GL_DEPTH_BUFFER_BIT);

	// preia locația pentru matricea de poziționare a cubului (vom discuta asta data viitoare)
	mvpLoc = glGetUniformLocation(renderingProgram, "mvp_matrix");

	// construiește matricea de poziționare a cubului (vom discuta asta data viitoare)
	glm::mat4 pMat = glm::perspective(1.0472f, 1024.0f / 768.0f, 0.1f, 1000.0f); // 1.0472 radians = 60 degrees
	glm::mat4 vMat = glm::translate(glm::mat4(1.0f), glm::vec3(-0.0f, -0.0f, -8.0f));
	glm::mat4 mMat = glm::translate(glm::mat4(1.0f), glm::vec3(0.0f, -2.0f, 0.0f));
	mvpMat = pMat * vMat * mMat;

	// copiază matricea de poziționare în variabila uniformă corespunzătoare (vom discuta asta data viitoare)
	glUniformMatrix4fv(mvpLoc, 1, GL_FALSE, glm::value_ptr(mvpMat));

	// asociază VBO-ul cu atributul punctului din shader: position
	glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(0);

	glDrawArrays(GL_TRIANGLES, 0, 36);
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

	GLFWwindow *window = glfwCreateWindow(1024, 768, "Laborator 2 - Hello Cube", NULL, NULL);
	glfwMakeContextCurrent(window);

	if (glewInit() != GLEW_OK)
	{
		exit(EXIT_FAILURE);
	}
	// Pentru a putea reacționa la tasta ESC
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
