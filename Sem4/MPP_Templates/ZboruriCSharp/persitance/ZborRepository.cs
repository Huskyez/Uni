using log4net;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using model;

namespace persistence
{
    public class ZborRepository : IZborRepo
    {
        private static readonly ILog log = LogManager.GetLogger("ZborRepository");

        IDictionary<String, string> props;
        public ZborRepository(IDictionary<String, string> props)
        {
            log.Info("Creating ZborRepository ");
            this.props = props;
        }

        public Zbor findOne(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Zboruri where ID=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int IdZ = dataR.GetInt32(0);
                        String Destinatie = dataR.GetString(1);
                        String PlecareS = dataR.GetString(2);
                        DateTime Plecare = DateTime.Parse(PlecareS);
                        String Aeroport = dataR.GetString(3);
                        int Locuri = dataR.GetInt32(4);
                        Zbor z = new Zbor(IdZ, Destinatie, Plecare, Aeroport, Locuri);
                        log.InfoFormat("Exiting findOne with value {0}", z);
                        return z;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            return null;
        }

        public IEnumerable<Zbor> findAll()
        {
            IDbConnection con = DBUtils.getConnection(props);
            IList<Zbor> zborR = new List<Zbor>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Zboruri";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int IdZ = dataR.GetInt32(0);
                        String Destinatie = dataR.GetString(1);
                        String PlecareS = dataR.GetString(2);
                        DateTime Plecare = DateTime.Parse(PlecareS);
                        String Aeroport = dataR.GetString(3);
                        int Locuri = dataR.GetInt32(4);
                        Zbor zbor = new Zbor(IdZ, Destinatie, Plecare, Aeroport, Locuri);
                        zborR.Add(zbor);
                    }
                }
            }

            return zborR;
        }

        public void save(Zbor entity)
        {
            var con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "insert into Zboruri values (@idZ, @destinatie, @plecare, @aeroport, @locuri)";
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@idZ";
                paramId.Value = entity.Id;
                comm.Parameters.Add(paramId);

                var paramDestinatie = comm.CreateParameter();
                paramDestinatie.ParameterName = "@destinatie";
                paramDestinatie.Value = entity.Destinatie;
                comm.Parameters.Add(paramDestinatie);

                var paramPlecare = comm.CreateParameter();
                paramPlecare.ParameterName = "@plecare";
                paramPlecare.Value = entity.Plecare;
                comm.Parameters.Add(paramPlecare);

                var paramAeroport = comm.CreateParameter();
                paramAeroport.ParameterName = "@aeroport";
                paramAeroport.Value = entity.Aeroport;
                comm.Parameters.Add(paramAeroport);

                var paramLocuri = comm.CreateParameter();
                paramLocuri.ParameterName = "@locuri";
                paramLocuri.Value = entity.Locuri;
                comm.Parameters.Add(paramLocuri);

                var result = comm.ExecuteNonQuery();
            }
        }

        public IEnumerable<Zbor> FindByDestinatieAndPlecare(string destinatie, string plecare)
        {
            IDbConnection con = DBUtils.getConnection(props);
            IList<Zbor> zborR = new List<Zbor>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Zboruri where Destinatia=@dest and DATE(Plecare) = @plecare";

                IDbDataParameter paramDestinatie = comm.CreateParameter();
                paramDestinatie.ParameterName = "@dest";
                paramDestinatie.Value = destinatie;
                comm.Parameters.Add(paramDestinatie);

                IDbDataParameter paramPlecare = comm.CreateParameter();
                paramPlecare.ParameterName = "@plecare";
                paramPlecare.Value = plecare;
                comm.Parameters.Add(paramPlecare);
                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int IdZ = dataR.GetInt32(0);
                        String Destinatie = dataR.GetString(1);
                        String PlecareS = dataR.GetString(2);
                        DateTime Plecare = DateTime.Parse(PlecareS);
                        String Aeroport = dataR.GetString(3);
                        int Locuri = dataR.GetInt32(4);
                        Zbor zbor = new Zbor(IdZ, Destinatie, Plecare, Aeroport, Locuri);
                        zborR.Add(zbor);
                    }
                }
            }
            return zborR;
        }

        public Zbor FindOneByDestinatieAndDateTime(String destinatie, String DataTimp)
        {
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Zboruri where Destinatia=@destinatia and Plecare=@plecare";

                IDbDataParameter paramDestinatie = comm.CreateParameter();
                paramDestinatie.ParameterName = "@destinatia";
                paramDestinatie.Value = destinatie;
                comm.Parameters.Add(paramDestinatie);

                IDbDataParameter paramPlecare = comm.CreateParameter();
                paramPlecare.ParameterName = "@plecare";
                paramPlecare.Value = DataTimp;
                comm.Parameters.Add(paramPlecare);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int IdZ = dataR.GetInt32(0);
                        String Destinatie = dataR.GetString(1);
                        String PlecareS = dataR.GetString(2);
                        DateTime Plecare = DateTime.Parse(PlecareS);
                        String Aeroport = dataR.GetString(3);
                        int Locuri = dataR.GetInt32(4);
                        Zbor z = new Zbor(IdZ, Destinatie, Plecare, Aeroport, Locuri);
                        log.InfoFormat("Exiting findOne with value {0}", z);
                        return z;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            return null;
        }

        public Zbor updateZbor(int id, int locuri)
        {
            
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "update Zboruri set Locuri=@locuri where ID=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                IDbDataParameter paramLocuri = comm.CreateParameter();
                paramLocuri.ParameterName = "@locuri";
                paramLocuri.Value = locuri;
                comm.Parameters.Add(paramLocuri);

                comm.ExecuteNonQuery();   
            }
            return null;
        }
    }
}
