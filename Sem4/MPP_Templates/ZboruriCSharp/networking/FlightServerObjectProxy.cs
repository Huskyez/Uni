using model;
using services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace networking
{
    public class FlightServerObjectProxy : IFlightServer
    {
        private string host;
        private int port;

        private IFlightObserver client;

        private NetworkStream stream;

        private IFormatter formatter;
        private TcpClient connection;

        private Queue<Response> responses;
        private volatile bool finished;
        private EventWaitHandle _waitHandle;

        public FlightServerObjectProxy(string host, int port)
        {
            this.host = host;
            this.port = port;
            responses = new Queue<Response>();
        }
        public void CumparaBilet(Bilet bilet, int idZbor, int locuri)
        {
            sendRequest(new BuyTickeRequest(bilet, idZbor, locuri));
            Response response = readResponse();
            if (response is ErrorResponse)
            {
                closeConnection();
                throw new FlightException("Error buying ticket");

            }
        }

        public Client FindClient(string numeClient, string adresaClient)
        {
            sendRequest(new FindClientRequest(new ClientDTO(numeClient, adresaClient)));
            Response response = readResponse();

            if (response is ErrorResponse)
            {
                closeConnection();
                throw new FlightException("Error finding client");
            }
            FindClientResponse clientResponse = (FindClientResponse)response;
            Client client = clientResponse.Client;
            return client;
        }

        public int FindIndex()
        {
            sendRequest(new FindIndexRequest());
            Response response = readResponse();

            if (response is ErrorResponse)
            {
                closeConnection();
                throw new FlightException("Error finding the index");
            }
            FindIndexResponse responseR = (FindIndexResponse)response;
            int index = responseR.Index;
            return index;
        }

        public Zbor FindZbor(String destinatie, String data, String ora)
        {
            sendRequest(new FindZborRequest(new ZborDTO3(destinatie,data, ora)));
            Response response = readResponse();

            if (response is ErrorResponse)
            {
                closeConnection();
                throw new FlightException("Error finding clinet");
            }
            FindZborResponse responseR = (FindZborResponse)response;
            Zbor zbor = responseR.Zbor;
            return zbor;
        }

        public IEnumerable<Zbor> GetZboruri()
        {
            sendRequest(new GetZboruriRequest());
            Response response = readResponse();
            
            if (response is ErrorResponse)
            {
                closeConnection();
                throw new FlightException("Error loading the flights ..");
            }
            GetZboruriResponse responseGet = (GetZboruriResponse)response;
            IEnumerable<Zbor> zboruri = responseGet.Zboruri;
            return zboruri;
        }

        public IEnumerable<ZborDTO> GetZboruriCautare(string destinatie, string plecare)
        {
            sendRequest(new GetZboruriCautareRequest(new ZborDTO2(destinatie, plecare)));
            Response response = readResponse();

            if (response is ErrorResponse)
            {
                closeConnection();
                throw new FlightException("Error finding flights ..");
            }
            GetZboruriCautareResponse responseR = (GetZboruriCautareResponse)response;
            IEnumerable<ZborDTO> zborDtos = responseR.ZborDTOs;
            return zborDtos;
        }

        public virtual void LogIn(Angajat angajat, IFlightObserver observer)
        {
            initializeConnection();
            sendRequest(new LoginRequest(angajat));
            Response response = readResponse();
            if (response is OkResponse)
            {
                this.client = observer;
                return;
            }
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse)response;
                closeConnection();
                throw new FlightException(err.Message);
            }
        }
        public void LogOut(Angajat angajat, IFlightObserver observer)
        {
            sendRequest(new LogoutRequest(angajat));
            Response response = readResponse();
            closeConnection();
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse)response;
                throw new FlightException(err.Message);
            }
            if (response is OkResponse)
            {
                this.client = observer;
                return;
            }
        }

        private void sendRequest(Request request)
        {
            try
            {
                formatter.Serialize(stream, request);
                stream.Flush();
            }
            catch (Exception e)
            {
                throw new FlightException("Error sending object " + e);
            }

        }
        private void closeConnection()
        {
            finished = true;
            try
            {
                stream.Close();
                connection.Close();
                _waitHandle.Close();
                client = null;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

        }
        private Response readResponse()
        {
            Response response = null;
            try
            {
                _waitHandle.WaitOne();
                lock (responses)
                {
                    //Monitor.Wait(responses); 
                    response = responses.Dequeue();

                }


            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
            return response;
        }
        private void initializeConnection()
        {

            try
            {
                connection = new TcpClient(host, port);
                stream = connection.GetStream();
                formatter = new BinaryFormatter();
                finished = false;
                _waitHandle = new AutoResetEvent(false);
                startReader();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }
      
    
        private void startReader()
        {
            Thread tw = new Thread(run);
            tw.Start();
        }

        public virtual void run()
        {
            while (!finished)
            {
                try
                {
                    object response = formatter.Deserialize(stream);
                    Console.WriteLine("response received " + response);
                    if (response is UpdateResponse)
                    {
                        handleUpdate((UpdateResponse)response);
                    }
                    else
                    {

                        lock (responses)
                        {


                            responses.Enqueue((Response)response);

                        }
                        _waitHandle.Set();
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Reading error " + e);
                }

            }
        }

        private void handleUpdate(UpdateResponse response)
        {
            BuyTicketResponse responseR  = (BuyTicketResponse)response;
            IEnumerable<Zbor> zboruri = responseR.Zbors;
            try
            {
                client.TicketBought(zboruri);
            }
            catch (FlightException e)
            {
                Console.WriteLine(e.Message);
            }
        }
    }
}
