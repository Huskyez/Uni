#include <iostream>
#include <vector>
#include <thread>
#include <chrono>


// using std::cout;
// using std::vector;
// using std::thread;
// using std::chrono;
using namespace std;


void displayArray(vector<int>& array)
{
	for (vector<int>::iterator it = array.begin(); it != array.end(); ++it)
	{
		cout << *it << " ";
	}
	cout << "\n";
}

void run(int start, int end, vector<int> a, vector<int> b, vector<int> &c) 
{
	// cout << std::this_thread::get_id() << "\n";
	// cout.flush();

	for (int i=start; i<end; i++)
	{
		c[i] = a[i] + b[i];
	}
}

int main(int argc, char **argv)
{

	srand(time(NULL));

	int n = 1100000;

	int p = 20;
	int l = 5;

	vector<int> a(n), b(n), c(n), d(n);

	vector<thread> threads(p);

	int chunk = n / p;
	int rest = n % p;

	for (int i=0; i<n; i++) 
	{
		a[i] = rand() % l;
		b[i] = rand() % l;
	}

	auto startSeq = chrono::high_resolution_clock::now();
	for (int i=0; i<n; i++)
	{
		d[i] = a[i] + b[i];
	}
	auto endSeq = chrono::high_resolution_clock::now();
	cout << "Sequential: " << (endSeq - startSeq).count() << "\n";
 	

	int start = 0;
	int end = chunk;
	

	auto startTime = chrono::high_resolution_clock::now();

	for (int i=0; i<p; i++)
	{
		if (rest > 0)
		{
			end++;
			rest--;
		}
		// cout << start << " " << end << "\n";
		threads[i] = thread(run, start, end, a, b, std::ref(c));


		start = end;
		end += chunk;
 	}


 	for (int i=0; i<p; i++)
 	{
 		threads[i].join();
 	}

 	auto endTime = chrono::high_resolution_clock::now();
	cout << "Parallel: " << (endTime - startTime).count() << "\n";
 	
	// displayArray(a);
	// displayArray(b);
	// displayArray(c);

	return 0;
}