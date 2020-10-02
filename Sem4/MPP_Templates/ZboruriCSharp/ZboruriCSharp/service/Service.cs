/*using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ZboruriCSharp.model;
using ZboruriCSharp.repository;

namespace ZboruriCSharp.service
{
    class Service
    {
        private AngajatRepository repoAngajat;
        private IZborRepo repoZbor;
        private IClientRepo repoClient;
        private IBiletRepo repoBilet;

        public Service(AngajatRepository repoAngajat, IZborRepo repoZbor, IClientRepo repoClient, IBiletRepo repoBilet)
        {
            this.repoAngajat = repoAngajat;
            this.repoBilet = repoBilet;
            this.repoClient = repoClient;
            this.repoZbor = repoZbor;
        }

        public bool LogIn(Angajat angajat)
        {
            return repoAngajat.LogIn(angajat.Id, angajat.Password);
        }

        public IEnumerable<Zbor> GetZboruri()
        {
            IList<Zbor> zborDTOs = new List<Zbor>();
            foreach (Zbor z in repoZbor.findAll())
            {
                if(z.Locuri > 0)
                    zborDTOs.Add(z);
            }
            return zborDTOs;
        }

        public IEnumerable<ZborDTO> GetZboruriCautate(String destinatie, String plecare)
        {
            IList<ZborDTO> zborDTOs = new List<ZborDTO>();
            foreach (Zbor z in repoZbor.FindByDestinatieAndPlecare(destinatie, plecare))
            {
                ZborDTO zborDTO = new ZborDTO(TimeSpan.Parse(z.Plecare.Hour.ToString()+":"+z.Plecare.Minute.ToString()), z.Locuri);
                zborDTOs.Add(zborDTO);
            }
            return zborDTOs;
        }

        public Bilet CumparaBilet(String numeClient, String adresaClient, String turisti, int locuri, String destinatie, String data, String ora)
        {
            Client client = repoClient.findOneByNumeAndAdress(numeClient, adresaClient);
            Zbor zbor = repoZbor.FindOneByDestinatieAndDateTime(destinatie, data + "T" + ora);
            if (zbor.Locuri - locuri > 0)
            {
                int index = repoBilet.findCorrectIndex();
                Bilet bilet = new Bilet(++index, zbor.Id, client.Id, turisti, locuri);
                repoZbor.updateZbor(zbor.Id, zbor.Locuri - locuri);
                repoBilet.save(bilet);    
            }
            return null;
        }
    }
}
*/