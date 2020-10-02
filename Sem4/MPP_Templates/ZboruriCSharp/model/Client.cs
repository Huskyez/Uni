using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace model
{
    [Serializable]
    public class Client : HasId<int>
    {
        public int Id { get; set; }

        public String Nume { get; set; }

        public String Adresa { get; set; }

        public Client(int Id, String Nume, String Adresa)
        {
            this.Id = Id;
            this.Nume = Nume;
            this.Adresa = Adresa;
        }

        public override String ToString()
        {
            return "id=" + Id + "|nume=" + Nume + "|adresa=" + Adresa;
        }
    }
}
