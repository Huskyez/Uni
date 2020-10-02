using CSharp;
using model;
using services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace client
{
    public class ClientController
    {
        public ServiceThrift.Client server;
        public MainWindowForm mainWindowForm;
        public ClientController(ServiceThrift.Client server)
        {
            this.server = server;
        }

        public void Set(MainWindowForm mainWindowForm)
        {
            this.mainWindowForm = mainWindowForm;
        }
        public void LogIn(model.Angajat angajat)
        {

            server.logIn(new CSharp.Angajat() { ID = angajat.Id, Password = angajat.Password });
        }

        public IEnumerable<model.Zbor> GetZboruri()
        {
            IList<model.Zbor> zbors = new List<model.Zbor>();
            IEnumerable<CSharp.Zbor> zboruri =  server.getZboruri();
            foreach(CSharp.Zbor z in zboruri)
            {
                model.Zbor zbor = new model.Zbor(z.ID, z.Destinatie, Convert.ToDateTime(z.Plecare), z.Aeroport, z.Locuri);
                zbors.Add(zbor);
            }
            return zbors;
        }

        public IEnumerable<model.ZborDTO> GetZboruriCautare(string destinatie, string plecare)
        {
            IList<model.ZborDTO> zbors = new List<model.ZborDTO>();
            IEnumerable<CSharp.ZborDTO> zboruri = server.getZboruriCautare(destinatie,plecare);
            foreach (CSharp.ZborDTO z in zboruri)
            {
                model.ZborDTO zbor = new model.ZborDTO(TimeSpan.Parse(z.Ora),z.Locuri);
                zbors.Add(zbor);
            }
            return zbors;
        }

        public model.Client FindClient(string numeClient, string adresaClient)
        {
            CSharp.Clientulet client = server.findClient(numeClient, adresaClient);
            return new model.Client(client.ID, client.Nume, client.Adresa);
        }

        public model.Zbor FindZbor(string destinatie, string data, string ora)
        {
            CSharp.Zbor zbor =  server.findZbor(destinatie, data, ora);
            return new model.Zbor(zbor.ID, zbor.Destinatie, Convert.ToDateTime(zbor.Plecare), zbor.Aeroport, zbor.Locuri);
        }
        public int FindIndex()
        {
            return server.findIndex();
        }
        public void CumparaBilet(model.Bilet bilet, int idZbor, int locuri)
        {
            CSharp.Bilet biletel = new CSharp.Bilet() { ID=bilet.Id, ClientId = bilet.ClientId, ZborId = bilet.ZborId, Locuri = bilet.Locuri, Turisti = bilet.Turisti};
            server.cumparaBilet(biletel, idZbor, locuri);
        }

        public void notifyServer()
        {
            server.notifyServer();
        }

        
        public void LogOut(int port)
        {
            server.logOut(port);
        }

    }
}
