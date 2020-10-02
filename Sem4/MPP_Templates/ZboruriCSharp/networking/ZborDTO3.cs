using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace networking
{
    [Serializable]
    public class ZborDTO3
    {
        public string destinatie{ get; set; }
        public string data { get; set; }
        public string ora { get; set; }

        public ZborDTO3(string destinatie, string data, string ora)
        {
            this.destinatie = destinatie;
            this.data = data;
            this.ora = ora;
        }
    }
}
