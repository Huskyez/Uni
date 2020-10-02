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
    public class FlightClientObjectWorker : IFlightObserver
    {
        private IFlightServer server;
        private TcpClient connection;

        private NetworkStream stream;
        private IFormatter formatter;
        private volatile bool connected;

        public FlightClientObjectWorker(IFlightServer server, TcpClient connection)
        {
            this.server = server;
            this.connection = connection;
            try
            {
                stream = connection.GetStream();
                formatter = new BinaryFormatter();
                connected = true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        public virtual void run()
        {
            while (connected)
            {
                try
                {
                    object request = formatter.Deserialize(stream);
                    object response = handleRequest((Request)request);
                    if (response != null)
                    {
                        sendResponse((Response)response);
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }

                try
                {
                    Thread.Sleep(1000);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
            }
            try
            {
                stream.Close();
                connection.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Error " + e);
            }
        }
        private void sendResponse(Response response)
        {
            Console.WriteLine("Sending response " + response);
            formatter.Serialize(stream, response);
            stream.Flush();
        }

        private object handleRequest(Request request)
        {
            Response response = null;

            if (request is LoginRequest)
            {
                Console.WriteLine("Login request ...");
                LoginRequest logReq = (LoginRequest)request;
                Angajat angajat = logReq.Angajat;
                try
                {
                    lock (server)
                    {
                        server.LogIn(angajat, this);
                    }
                    return new OkResponse();
                }
                catch (FlightException e)
                {
                    connected = false;
                    return new ErrorResponse(e.Message);
                }
            }


            if (request is LogoutRequest)
            {
                Console.WriteLine("Logout request ...");
                LogoutRequest logReq = (LogoutRequest)request;
                Angajat angajat = logReq.User;
                try
                {
                    lock (server)
                    {
                        server.LogOut(angajat, this);
                    }
                    return new OkResponse();
                }
                catch (FlightException e)
                {
                    connected = false;
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is GetZboruriRequest)
            {
                Console.WriteLine("Flights get request");
                GetZboruriRequest getReq = (GetZboruriRequest)request;
                IEnumerable<Zbor> zboruri;
                try
                {
                    lock (server)
                    {
                        zboruri = server.GetZboruri();
                    }
                    return new GetZboruriResponse(zboruri);
                }
                catch (FlightException e)
                {
                    connected = false;
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is GetZboruriCautareRequest)
            {
                Console.WriteLine("Find fligts request");
                GetZboruriCautareRequest getReq = (GetZboruriCautareRequest)request;
                ZborDTO2 zbor = getReq.zborDTO2;
                IEnumerable<ZborDTO> zborDTOs;
                try
                {
                    lock (server)
                    {
                        zborDTOs = server.GetZboruriCautare(zbor.destinatie, zbor.data);
                    }
                    return new GetZboruriCautareResponse(zborDTOs);
                }
                catch (FlightException e)
                {
                    connected = false;
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is FindClientRequest)
            {
                Console.WriteLine("Finding client");
                FindClientRequest findReq = (FindClientRequest)request;
                ClientDTO clientDTO = findReq.Client;
                Client client;
                try
                {
                    lock (server)
                    {
                        client = server.FindClient(clientDTO.nume, clientDTO.adresa);
                    }
                    return new FindClientResponse(client);
                }
                catch (FlightException e)
                {
                    connected = false;
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is FindZborRequest)
            {
                Console.WriteLine("Finding flight");
                FindZborRequest findReq = (FindZborRequest)request;
                ZborDTO3 zborDTO3 = findReq.ZborDTO3;
                Zbor zbor;
                try
                {
                    lock (server)
                    {
                        zbor = server.FindZbor(zborDTO3.destinatie, zborDTO3.data, zborDTO3.ora);
                    }
                    return new FindZborResponse(zbor);
                }
                catch (FlightException e)
                {
                    connected = false;
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is FindIndexRequest)
            {
                Console.WriteLine("Finding index");
                FindIndexRequest findReq = (FindIndexRequest)request;
                int index;
                try
                {
                    lock (server)
                    {
                        index = server.FindIndex();
                    }
                    return new FindIndexResponse(index);
                }
                catch (FlightException e)
                {
                    connected = false;
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is BuyTickeRequest)
            {
                Console.WriteLine("Ticket bought");
                BuyTickeRequest req = (BuyTickeRequest)request;
                Bilet bilet = req.Bilet;
                int id = req.Id;
                int nr = req.NrLocuri;
                try
                {
                    lock (server)
                    {
                        server.CumparaBilet(bilet, id, nr);
                    }
                    return new OkResponse();
                }catch(FlightException e)
                {
                    Console.WriteLine(e.Message);
                }
            }
            return response;
        }

        public void TicketBought(IEnumerable<Zbor> zboruri)
        {
            try
            {
                sendResponse(new BuyTicketResponse(zboruri));
            }
            catch (FlightException e)
            {
                Console.WriteLine(e.Message);
            }
        }
    }
}
