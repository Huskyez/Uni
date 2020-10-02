using Newtonsoft.Json;
using System;
using System.Net.Http;
using System.Threading.Tasks;

namespace TestRest
{
    class Program
    {
        static HttpClient client = new HttpClient();

        static void Main(string[] args)
        {
            RunAsync().Wait();
        }

        static async Task RunAsync()
        {
            Console.WriteLine("Caut Zbor cu ID-ul 1");
            Zbor zbor1 = await FindOne("http://localhost:8080/flight/zboruri/1");
            Console.WriteLine(zbor1.ToString());
            Console.WriteLine();

            Console.WriteLine("Afisez toate zborurile");
            foreach (Zbor zbor2 in await FindAll("http://localhost:8080/flight/zboruri"))
            {
                Console.WriteLine(zbor2.ToString());
            }
            Console.WriteLine();

            Console.WriteLine("Adaug un zbor");
            Zbor Zbor2 = new Zbor();
            Zbor2.ID = 17; Zbor2.aeroport = "GVN"; Zbor2.destinatie = "Paris";Zbor2.plecare = "1986-04-08 12:30"; Zbor2.locuri = 10;
            Zbor Zbor3 = await Save("http://localhost:8080/flight/zboruri", Zbor2);
            Console.WriteLine(Zbor3.ToString());
            Console.WriteLine();

            Console.WriteLine("Il actualizez");
            Zbor Zbor4 = new Zbor();
            Zbor4.ID = 17; Zbor4.aeroport = "HTR"; Zbor4.destinatie = "Paris"; Zbor4.plecare = "1986-04-08 12:30"; Zbor4.locuri = 20;
            Zbor Zbor5 = await Update("http://localhost:8080/flight/zboruri", Zbor4);
            Console.WriteLine("Am actualizat! Zbor este: " + Zbor5.ToString());
            Console.WriteLine();
            
            Console.WriteLine("Il sterg");
            await client.DeleteAsync("http://localhost:8080/flight/zboruri/17");
            Console.WriteLine("Am sters!");
            Console.WriteLine();
            
            Console.WriteLine("Probele sunt");
            foreach (Zbor Zbor in await FindAll("http://localhost:8080/flight/zboruri"))
            {
                Console.WriteLine(Zbor.ToString());
            }

            Console.Read();
        }

        static async Task<Zbor> FindOne(string path)
        {
            Zbor Zbor = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                Zbor = await response.Content.ReadAsAsync<Zbor>();
            }
            return Zbor;
        }

        static async Task<Zbor[]> FindAll(string path)
        {
            Zbor[] probe = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                probe = await response.Content.ReadAsAsync<Zbor[]>();
            }
            return probe;
        }

        static async Task<Zbor> Save(string path, Zbor Zbor)
        {
            Zbor result = null;
            HttpResponseMessage response = await client.PostAsJsonAsync(path, Zbor);
            if (response.IsSuccessStatusCode)
            {
                result = await response.Content.ReadAsAsync<Zbor>();
            }
            return result;
        }

        static async Task<Zbor> Update(string path, Zbor Zbor)
        {
            Zbor result = null;
            HttpResponseMessage response = await client.PutAsJsonAsync<Zbor>(path+"/"+Zbor.ID, Zbor);
            if (response.IsSuccessStatusCode)
            {
                result = await FindOne("http://localhost:8080/flight/zboruri/17");
            }
            return result;
        }
    }

    public class Zbor
    {
        public int ID { get; set; }
        public String destinatie { get; set; }
        public String plecare { get; set; }
        public String aeroport { get; set; }
        public int locuri { get; set; }


        public override String ToString()
        {
            return "ID=" + ID + "|destinatie=" + destinatie + "|plecare" + plecare + "|aeroport=" + aeroport + "|locuri=" + locuri;
        }
    }

}