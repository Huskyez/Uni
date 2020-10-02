using log4net;
using model;
using System;
using System.Collections.Generic;
using System.Data;

namespace persistence
{
    public class AngajatRepository
    {
        private static readonly ILog log = LogManager.GetLogger("AngajatRepository");

        IDictionary<String, string> props;
        public AngajatRepository(IDictionary<String, string> props)
        {
            log.Info("Creating AngajatRepository ");
            this.props = props;
        }

        public Angajat LogIn(String username, String password)
        {
            log.InfoFormat("Entering LOG IN with value {0}", username);
            IDbConnection con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Angajati where Username=@username and Password=@password";

                IDbDataParameter paramUsername = comm.CreateParameter();
                paramUsername.ParameterName = "@username";
                paramUsername.Value = username;
                comm.Parameters.Add(paramUsername);

                IDbDataParameter paramPassword = comm.CreateParameter();
                paramPassword.ParameterName = "@password";
                paramPassword.Value = password;
                comm.Parameters.Add(paramPassword);


                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        String user = dataR.GetString(0);
                        String pass = dataR.GetString(1);
                        log.InfoFormat("Exiting log in with value {0}", user);
                        return new Angajat(user, pass);
                    }
                }
            }
            log.InfoFormat("Exiting LogIn with value {0}", null);
            return null;
        }

        public IEnumerable<Angajat> FindAll()
        {
            IDbConnection con = DBUtils.getConnection(props);
            IList<Angajat> angajati = new List<Angajat>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Angajati";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        String id = dataR.GetString(0);
                        String pass = dataR.GetString(1);
                        Angajat angajat = new Angajat(id, pass);
                        angajati.Add(angajat);
                    }
                }
            }
            return angajati;
        }
    }
}
