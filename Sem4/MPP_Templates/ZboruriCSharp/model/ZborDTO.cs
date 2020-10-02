using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace model
{
    [Serializable]
    public class ZborDTO
    {
        public TimeSpan ora { get; set; }
        public int locuri { get; set; }
        public ZborDTO(TimeSpan ora, int locuri)
        {
            this.ora = ora;
            this.locuri = locuri;
        }

        public override String ToString()
        {
            return "Ora:" + ora + "|Locuri:" + locuri;
        }
    }
}
