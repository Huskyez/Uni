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
    public class BiletRepository : IBiletRepo
    {
        private static readonly ILog log = LogManager.GetLogger("BiletRepository");

        IDictionary<String, string> props;
        public BiletRepository(IDictionary<String, string> props)
        {
            log.Info("Creating BiletRepository ");
            this.props = props;
        }
        public IEnumerable<Bilet> findAll()
        {
            IDbConnection con = DBUtils.getConnection(props);
            IList<Bilet> biletR = new List<Bilet>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Bilete";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int IdB = dataR.GetInt32(0);
                        int ZborId = dataR.GetInt32(1);
                        int ClientId = dataR.GetInt32(2);
                        String Turisti = dataR.GetString(3);
                        int Locuri = dataR.GetInt32(4);
                        Bilet bilet = new Bilet(IdB, ZborId, ClientId, Turisti, Locuri);
                        biletR.Add(bilet);
                    }
                }
            }

            return biletR;
        }

        public int findCorrectIndex()
        {
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select max(ID) as [Index] from Bilete";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int index = dataR.GetInt32(0);
                        return index;
                    }
                }
            }
            return -1;
        }

        public Bilet findOne(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Bilete where ID=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int IdB = dataR.GetInt32(0);
                        int ZborId = dataR.GetInt32(1);
                        int ClientId = dataR.GetInt32(2);
                        String Turisti = dataR.GetString(3);
                        int Locuri = dataR.GetInt32(4);
                        Bilet b = new Bilet(IdB, ZborId, ClientId, Turisti, Locuri);
                        log.InfoFormat("Exiting findOne with value {0}", b);
                        return b;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            return null;
        }

        public void save(Bilet entity)
        {
            var con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "insert into Bilete values (@idB, @idZbor, @idClient, @turisti, @locuri)";
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@idB";
                paramId.Value = entity.Id;
                comm.Parameters.Add(paramId);

                var paramZborId = comm.CreateParameter();
                paramZborId.ParameterName = "@idZbor";
                paramZborId.Value = entity.ZborId;
                comm.Parameters.Add(paramZborId);

                var paramClientId = comm.CreateParameter();
                paramClientId.ParameterName = "@idClient";
                paramClientId.Value = entity.ClientId;
                comm.Parameters.Add(paramClientId);

                var paramTuristi = comm.CreateParameter();
                paramTuristi.ParameterName = "@turisti";
                paramTuristi.Value = entity.Turisti;
                comm.Parameters.Add(paramTuristi);

                var paramLocuri = comm.CreateParameter();
                paramLocuri.ParameterName = "@locuri";
                paramLocuri.Value = entity.Locuri;
                comm.Parameters.Add(paramLocuri);

                var result = comm.ExecuteNonQuery();
            }
        }
    }
}
