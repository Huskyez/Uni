using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace networking
{
    [Serializable]
    public class ZborDTO2
    {
        public string destinatie { get; set; }
        public string data { get; set; }
        public ZborDTO2(string destinatie, string data)
        {
            this.destinatie = destinatie;
            this.data = data;
        }
       
    }
}
