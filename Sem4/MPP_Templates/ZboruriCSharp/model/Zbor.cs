using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace model
{
    [Serializable]
    public class Zbor : HasId<int>
    {
        public int Id { get; set; }
        public String Destinatie { get; set; }
        public DateTime Plecare { get; set; }
        public String Aeroport { get; set; }
        public int Locuri { get; set; }

        public Zbor(int Id, String Destinatie, DateTime Plecare, String Aeroport, int Locuri)
        {
            this.Id = Id;
            this.Destinatie = Destinatie;
            this.Plecare = Plecare;
            this.Aeroport = Aeroport;
            this.Locuri = Locuri;
        }

        public override String ToString()
        {
            return "id=" + Id + "|destinatie=" + Destinatie + "|plecare" + Plecare + "|aeroport=" + Aeroport + "|locuri=" + Locuri;
        }
    }
}
