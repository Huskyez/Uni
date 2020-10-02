using CSharp;
using networking;
using services;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using Thrift.Protocol;
using Thrift.Server;
using Thrift.Transport;

namespace client
{
    static class StartFlightClient
    {
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            //BinaryServerFormatterSinkProvider serverProvider = new BinaryServerFormatterSinkProvider();
            //serverProvider.TypeFilterLevel = System.Runtime.Serialization.Formatters.TypeFilterLevel.Full;
            //BinaryClientFormatterSinkProvider clientProvider = new BinaryClientFormatterSinkProvider();
            //IDictionary dictionary = new Hashtable();
            //dictionary["port"] = 0;

            //TcpChannel channel = new TcpChannel(dictionary, clientProvider, serverProvider);
            //ChannelServices.RegisterChannel(channel, false);

            //IFlightServer server = (IFlightServer)Activator.GetObject(typeof(IFlightServer), "tcp://localhost:55555/zboruri");

            //TTransport transport = new TSocket("localhost", 55555);

            //transport.Open();
            //TProtocol protocol = new TBinaryProtocol(transport);
            //ServiceThrift.Client client = new ServiceThrift.Client(protocol);

            //ClientController ctrl = new ClientController(client);

            //LogInForm logInForm = new LogInForm(ctrl);
            //MainWindowForm mainWindowForm = new MainWindowForm(ctrl);

            //var mainWindowControllerImpl = new MainWindowControllerImpl(mainWindowForm);
            //var processor = new MainWindowController.Processor(mainWindowControllerImpl);

            //int port = 55555 + new Random().Next() % 10;
            //TServerTransport serverTransport = new TServerSocket(port);
            //TServer server = new TThreadPoolServer(processor, serverTransport);

            //logInForm.Set(mainWindowForm, port);
            //logInForm.Text = port.ToString();

            //mainWindowForm.Set(logInForm);
            //ctrl.Set(mainWindowForm);

            var socket = new TSocket("localhost", 55555);
            var protocol = new TBinaryProtocol(socket);
            var client = new ServiceThrift.Client(protocol);
            socket.Open();

            ClientController controller = new ClientController(client);

            LogInForm logonForm = new LogInForm(controller);
            MainWindowForm mainWindowForm = new MainWindowForm(controller);

            var mainWindowControllerImpl = new MainWindowControllerImpl(mainWindowForm);
            var processor = new MainWindowController.Processor(mainWindowControllerImpl);

            int port = 55555 + new Random().Next() % 10;
            TServerTransport transport = new TServerSocket(port);
            TServer server = new TThreadPoolServer(processor, transport);

            logonForm.Set(mainWindowForm, port);
            logonForm.Text = "Client on port " + port;
            mainWindowForm.Set(logonForm);
            mainWindowForm.Text = "Client on port " + port;

            Thread thread = new Thread(new ThreadStart(server.Serve));
            thread.Start();

            Application.Run(logonForm);
             
            
        }
    }
}

