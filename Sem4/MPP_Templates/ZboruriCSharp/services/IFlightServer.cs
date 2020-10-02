using System;
using System.Collections.Generic;
using model;

namespace services
{
    public interface IFlightServer
    {
        void LogIn(Angajat angajat, IFlightObserver observer);

        void LogOut(Angajat angajat, IFlightObserver observer);

        Client FindClient(String numeClient, String adresaClient);

        Zbor FindZbor(String destinatie, String data, String ora);

        int FindIndex();

        void CumparaBilet(Bilet bilet, int idZbor, int locuri);

        IEnumerable<Zbor> GetZboruri();

        IEnumerable<ZborDTO> GetZboruriCautare(String destinatie, String plecare);
    }
}
