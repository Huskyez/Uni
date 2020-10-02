using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace networking
{
    [Serializable]
    public class ClientDTO
    {
        public string nume { get; set; }
        public string adresa { get; set; }

        public ClientDTO(string nume, string adresa)
        {
            this.nume = nume;
            this.adresa = adresa;
        }
    }
}
