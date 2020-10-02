using System;
using System.Collections.Generic;
using log4net;
using log4net.Config;
using System.Configuration;
using System.Windows.Forms;
using ZboruriCSharp.repository;
using ZboruriCSharp.service;
using ZboruriCSharp.model;

namespace ZboruriCSharp
{
    static class Program
    {
        static string GetConnectionStringByName(string name)
        {
            string returnValue = null;
            ConnectionStringSettings settings = ConfigurationManager.ConnectionStrings[name];
            if (settings != null)
                returnValue = settings.ConnectionString;

            return returnValue;
        }
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main(string[] args)
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            XmlConfigurator.Configure(new System.IO.FileInfo(args[0]));
            Console.WriteLine("Configuration Settings for database {0}", GetConnectionStringByName("database"));
            IDictionary<String, string> props = new SortedList<String, String>();
            props.Add("ConnectionString", GetConnectionStringByName("database"));

            IClientRepo clientRepository = new ClientRepository(props);
            IZborRepo zborRepository = new ZborRepository(props);
            AngajatRepository angajatRepository = new AngajatRepository(props);
            IBiletRepo biletRepository = new BiletRepository(props);


            Service service = new Service(angajatRepository, zborRepository, clientRepository, biletRepository);

            LogInForm logInForm = new LogInForm();
            MainWindowForm mainWindowForm = new MainWindowForm();

            logInForm.SetService(service);
            logInForm.setMain(mainWindowForm);
            mainWindowForm.SetService(service);
            mainWindowForm.SetLogIn(logInForm);

            Application.Run(logInForm);

        }
    }
}
