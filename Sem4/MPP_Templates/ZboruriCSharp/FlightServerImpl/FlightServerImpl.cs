using model;
using persistence;
using services;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace flightserver
{
    class FlightServerImpl : MarshalByRefObject, IFlightServer
    {
        private AngajatRepository angajatRepository;
        private IZborRepo zborRepository;
        private IClientRepo clientRepository;
        private IBiletRepo biletRepository;
        private Dictionary<String, IFlightObserver> loggedAngajati;

        public FlightServerImpl(AngajatRepository angajatRepository, IZborRepo zborRepository, IClientRepo clientRepository, IBiletRepo biletRepository)
        {
            this.angajatRepository = angajatRepository;
            this.biletRepository = biletRepository;
            this.clientRepository = clientRepository;
            this.zborRepository = zborRepository;
            loggedAngajati = new Dictionary<string, IFlightObserver>();
        }
        public void CumparaBilet(Bilet bilet, int idZbor, int locuri)
        {
            biletRepository.save(bilet);
            zborRepository.updateZbor(idZbor, locuri);
            notifyAll();
        }

        private void notifyAll()
        {
            IEnumerable<Angajat> angajati = angajatRepository.FindAll();
            foreach (Angajat a in angajati)
            {
                if (loggedAngajati.ContainsKey(a.Id))
                {
                    IFlightObserver observer = loggedAngajati[a.Id];
                    observer.TicketBought(zborRepository.findAll());
                }
            }
        }

        public int FindIndex()
        {
            return biletRepository.findCorrectIndex();
        }

        public IEnumerable<Zbor> GetZboruri()
        {
            IList<Zbor> zborDTOs = new List<Zbor>();
            foreach (Zbor z in zborRepository.findAll())
            {
                if (z.Locuri > 0)
                    zborDTOs.Add(z);
            }
            return zborDTOs;
        }

        public IEnumerable<ZborDTO> GetZboruriCautare(string destinatie, string plecare)
        {
            IList<ZborDTO> zborDTOs = new List<ZborDTO>();
            foreach (Zbor z in zborRepository.FindByDestinatieAndPlecare(destinatie, plecare))
            {
                ZborDTO zborDTO = new ZborDTO(TimeSpan.Parse(z.Plecare.Hour.ToString() + ":" + z.Plecare.Minute.ToString()), z.Locuri);
                zborDTOs.Add(zborDTO);
            }
            return zborDTOs;
        }

        public void LogIn(Angajat angajat, IFlightObserver observer)
        {
            Angajat angajat1 = angajatRepository.LogIn(angajat.Id, angajat.Password);
            if (angajat1 != null)
            {
                if (loggedAngajati.ContainsKey(angajat.Id))
                    throw new FlightException("Employee already logged in!");
                loggedAngajati[angajat.Id] = observer;

            }
            else
                throw new FlightException("Autthetication failed!");
        }

        public void LogOut(Angajat angajat, IFlightObserver observer)
        {
            IFlightObserver localClient = loggedAngajati[angajat.Id];
            if (localClient == null)
                throw new FlightException("Empployee logged out");
            loggedAngajati.Remove(angajat.Id);
        }
        
        Client IFlightServer.FindClient(string numeClient, string adresaClient)
        {
            return clientRepository.findOneByNumeAndAdress(numeClient, adresaClient);
        }

        Zbor IFlightServer.FindZbor(string destinatie, string data, string ora)
        {
            return zborRepository.FindOneByDestinatieAndDateTime(destinatie, data + "T" + ora);
        }
    }
}



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
