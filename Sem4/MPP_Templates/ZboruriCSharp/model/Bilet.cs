using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace model
{
    [Serializable]
    public class Bilet : HasId<int>
    {
        public int Id { get; set; }
        public int ZborId { get; set; }
        public int ClientId { get; set; }
        public String Turisti { get; set; }
        public int Locuri { get; set; }

        public Bilet(int Id, int ZborId, int ClientId, String Turisti, int Locuri)
        {
            this.Id = Id;
            this.ZborId = ZborId;
            this.ClientId = ClientId;
            this.Turisti = Turisti;
            this.Locuri = Locuri;
        }

        public override String ToString()
        {
            return "Id=" + Id + "|ZborId=" + ZborId + "|ClientId=" + ClientId + "|Turisti=" + Turisti + "|Locuri=" + Locuri;
        }
    }
}
