using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using model;

namespace persistence
{
    public interface IZborRepo : ICrudRepository<int, Zbor>
    {
        IEnumerable<Zbor> FindByDestinatieAndPlecare(String destinatie, String plecare);

        Zbor FindOneByDestinatieAndDateTime(String destinatie, String DataTimp);

        Zbor updateZbor(int id, int locuri);
    }
}
