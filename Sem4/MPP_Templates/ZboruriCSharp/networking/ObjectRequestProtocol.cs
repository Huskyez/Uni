using model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace networking
{
    public interface Request
    {
    }

    [Serializable]
    public class LoginRequest : Request
    {
        private Angajat angajat;
        public LoginRequest(Angajat angajat)
        {
            this.angajat = angajat;
        }

        public virtual Angajat Angajat
        {
            get
            {
                return angajat;
            }
        }
    }

    [Serializable]
    public class GetZboruriRequest : Request
    {
    }

    [Serializable]
    public class LogoutRequest : Request
    {
        private Angajat angajat;

        public LogoutRequest(Angajat angajat)
        {
            this.angajat = angajat;
        }

        public virtual Angajat User
        {
            get
            {
                return angajat;
            }
        }
    }

    [Serializable]
    public class GetZboruriCautareRequest : Request
    {
        private ZborDTO2 zbor;

        public GetZboruriCautareRequest(ZborDTO2 zbor)
        {
            this.zbor = zbor;
        }

        public virtual ZborDTO2 zborDTO2
        {
            get
            {
                return zbor;
            }
        }
    }

    [Serializable]
    public class FindClientRequest : Request
    {
        private ClientDTO client;

        public FindClientRequest(ClientDTO client)
        {
            this.client = client;
        }

        public virtual ClientDTO Client
        {
            get
            {
                return client;
            }
        }
    }

    [Serializable]
    public class FindZborRequest : Request
    {
        private ZborDTO3 zbor;

        public FindZborRequest(ZborDTO3 zbor)
        {
            this.zbor = zbor;
        }

        public virtual ZborDTO3 ZborDTO3
        {
            get
            {
                return zbor;
            }
        }
    }
    [Serializable]
    public class FindIndexRequest : Request
    {
    }

    [Serializable]
    public class BuyTickeRequest : Request
    {
        private Bilet bilet;
        private int id;
        private int nrLocuri;
        public BuyTickeRequest(Bilet bilet, int id, int nrLocuri)
        {
            this.bilet = bilet;
            this.id = id;
            this.nrLocuri = nrLocuri;
        }

        public virtual Bilet Bilet
        {
            get
            {
                return bilet;
            }
        }
        public virtual int Id
        {
            get
            {
                return id;
            }
        }
        public virtual int NrLocuri
        {
            get
            {
                return nrLocuri;
            }
        }
    }

}
