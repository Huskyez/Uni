using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using model;

namespace persistence
{
    public interface IClientRepo : ICrudRepository<int, Client>
    {
        Client findOneByNumeAndAdress(String nume, String adresa);
    }
}
