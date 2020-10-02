#include <GL/glew.h>
#include <GLFW/glfw3.h>
#include <iostream>

using namespace std;

int main(void)
{
	// Inițializează biblioteca GLFW.
	if (!glfwInit())
	{
		exit(EXIT_FAILURE);
	}

	// Crează o fereastră pentru OpenGL și un context OpenGL asociat ei.
	GLFWwindow *window = glfwCreateWindow(1024, 768, "Laborator 1 - Prima fereastră", NULL, NULL);

	// Selectează contextul OpenGL asociat ferestrei create.
	glfwMakeContextCurrent(window);

	// Inițializează biblioteca GLEW.
	if (glewInit() != GLEW_OK)
	{
		exit(EXIT_FAILURE);
	}

	// Schimbă bufferul la prima actualizare a ecranului după apelul la glfwSwapBuffers.
	glfwSwapInterval(1);

	while (!glfwWindowShouldClose(window))
	{
//		glClearColor(0.1, 0.3, 0.6, 1.0); // Setează o culoare pentru curățarea buffer-ului (RGBA = Red, Green, Blue, Alpha).
		glClearColor(0.5, 0.3, 0.5, 1.0);
		glClear(GL_COLOR_BUFFER_BIT);	  // Curăță bufferul de culoare.

		glfwSwapBuffers(window); // Actualizează bufferul ferestrei.
		glfwPollEvents();		 // Verifică dacă au fost emise alte evenimente specifice ferestrei.
	}

	// Curăză memoria.
	glfwDestroyWindow(window);
	glfwTerminate();
	exit(EXIT_SUCCESS);
}
