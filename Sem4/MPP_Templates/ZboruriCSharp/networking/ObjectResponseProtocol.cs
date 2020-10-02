using model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace networking
{
    public interface Response
    {
    }

    public interface UpdateResponse : Response
    {
    }


    [Serializable]
    public class OkResponse : Response
    {
    }

    [Serializable]
    public class ErrorResponse : Response
    {
        private string message;

        public ErrorResponse(string message)
        {
            this.message = message;
        }

        public virtual string Message
        {
            get
            {
                return message;
            }
        }
    }

    [Serializable]
    public class GetZboruriResponse : Response
    {
        private IEnumerable<Zbor> zboruri;

        public GetZboruriResponse(IEnumerable<Zbor> zboruri)
        {
            this.zboruri = zboruri;
        }

        public virtual IEnumerable<Zbor> Zboruri
        {
            get
            {
                return zboruri;
            }
        }

    }

    [Serializable]
    public class GetZboruriCautareResponse : Response
    {
        private IEnumerable<ZborDTO> zboruri;

        public GetZboruriCautareResponse(IEnumerable<ZborDTO> zboruri)
        {
            this.zboruri = zboruri;
        }

        public virtual IEnumerable<ZborDTO> ZborDTOs
        {
            get
            {
                return zboruri;
            }
        }
    }

    [Serializable]
    public class FindClientResponse : Response
    {
        private Client client;

        public FindClientResponse(Client client)
        {
            this.client = client;
        }

        public virtual Client Client
        {
            get
            {
                return client;
            }
        }
    }

    [Serializable]
    public class FindZborResponse : Response
    {
        private Zbor zbor;

        public FindZborResponse(Zbor zbor)
        {
            this.zbor = zbor;
        }

        public virtual Zbor Zbor
        {
            get
            {
                return zbor;
            }
        }
    }
    [Serializable]
    public class FindIndexResponse : Response
    {
        private int index;
        public FindIndexResponse(int index)
        {
            this.index = index;
        }

        public virtual int Index
        {
            get
            {
                return index;
            }
        }
    }

    [Serializable]
    public class BuyTicketResponse : UpdateResponse
    {
        private IEnumerable<Zbor> zbors;

        public BuyTicketResponse(IEnumerable<Zbor> zbors)
        {
            this.zbors = zbors;
        }

        public virtual IEnumerable<Zbor> Zbors
        {
            get
            {
                return zbors;
            }
        }
    }
}
