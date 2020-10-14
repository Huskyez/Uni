#include <iostream>
#include <fstream>
#include <string>
#include <random>
#include <vector>


using std::cout;
using std::string;
using std::vector;
using std::ofstream;
using std::ifstream;

void CreateFile(string path, size_t size, int32_t min, int32_t max)
{
	std::default_random_engine generator;
	std::uniform_int_distribution<int32_t> distribution(min, max);

	ofstream out(path);

	for (int i = 0; i < size; i++)
	{
		int x = distribution(generator);
		
		out << x << "\n";
	}

	out.close();
}


bool CompareFiles(string path1, string path2)
{
	vector<double> file1;
	vector<double> file2;

	ifstream in1(path1);
	ifstream in2(path2);
	
	string line;

	while (std::getline(in1, line))
	{
		size_t start = 0, end = 0;
		while (true)
		{
			end = line.find(' ', start);
			if (end == -1)
			{
				file1.push_back(std::stod(line.substr(start)));
				break;
			}

			file1.push_back(std::stod(line.substr(start, end - start)));
			start = end + 1;
			if (start >= line.size()) {
				break;
			}
		}
	}

	while (std::getline(in2, line))
	{
		size_t start = 0, end = 0;
		while (true)
		{
			end = line.find(' ', start);
			if (end == -1)
			{
				file2.push_back(std::stod(line.substr(start)));
				break;
			}
			file2.push_back(std::stod(line.substr(start, end - start)));
			start = end + 1;
			if (start >= line.size()) {
				break;
			}
		}
	}

	in1.close();
	in2.close();

	if (file1.size() != file2.size())
	{
		return false;
	}

	for (int i=0; i<file1.size(); i++) 
	{
		if (file1[i] != file2[i])
		{
			return false;
		}
	}

	return true;
}

int main(int argc, char **argv)
{
	CreateFile("test.txt", 10, -10, 10);

	// cout << CompareFiles("test.txt", "test2.txt");

	return 0;
}