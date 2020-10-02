using persistence;
using System;
using System.Collections.Generic;
using System.Configuration;
using log4net.Config;
using services;
using System.Threading;
using System.Net.Sockets;
using networking;
using System.Runtime.Remoting.Channels;
using System.Collections;
using System.Runtime.Remoting.Channels.Tcp;
using System.Runtime.Remoting;

namespace flightserver
{
    public class StartObjectServer
    {
        static string GetConnectionStringByName(string name)
        {
            string returnValue = null;
            ConnectionStringSettings settings = ConfigurationManager.ConnectionStrings[name];
            if (settings != null)
                returnValue = settings.ConnectionString;

            return returnValue;
        }
        
        static void Main(string[] args)
        {
            XmlConfigurator.Configure(new System.IO.FileInfo(args[0]));
            Console.WriteLine("Configuration Settings for database {0}", GetConnectionStringByName("database"));
            IDictionary<String, string> props = new SortedList<String, String>();
            props.Add("ConnectionString", GetConnectionStringByName("database"));

            BinaryServerFormatterSinkProvider serverProvider = new BinaryServerFormatterSinkProvider();
            serverProvider.TypeFilterLevel = System.Runtime.Serialization.Formatters.TypeFilterLevel.Full;
            BinaryClientFormatterSinkProvider clientProvider = new BinaryClientFormatterSinkProvider();
            IDictionary dictionary = new Hashtable();
            dictionary["port"] = 55555;
            TcpChannel channel = new TcpChannel(dictionary, clientProvider, serverProvider);
            ChannelServices.RegisterChannel(channel, false);

            AngajatRepository angajatRepo = new AngajatRepository(props);
            IZborRepo zborRepo = new ZborRepository(props);
            IClientRepo clientRepo = new ClientRepository(props);
            IBiletRepo biletRepo = new BiletRepository(props);

            var server = new FlightServerImpl(angajatRepo, zborRepo, clientRepo, biletRepo);
            RemotingServices.Marshal(server, "zboruri");
            
            Console.WriteLine("Server started ...");
            Console.ReadLine();
        }
    }

    public class SerialFlightServer : ConcurrentServer
    {
        private IFlightServer server;
        private FlightClientObjectWorker worker;
        public SerialFlightServer(string host, int port, IFlightServer server) : base(host, port)
        {
            this.server = server;
            Console.WriteLine("SerialChatServer...");
        }
        protected override Thread createWorker(TcpClient client)
        {
            worker = new FlightClientObjectWorker(server, client);
            return new Thread(new ThreadStart(worker.run));
        }
    }
}
