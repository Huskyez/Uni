using CSharp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace client
{
    class MainWindowControllerImpl : MainWindowController.Iface
    {
        private MainWindowForm mainWindowForm;

        public MainWindowControllerImpl(MainWindowForm mainWindowForm)
        {
            this.mainWindowForm = mainWindowForm;
        }

        public void Update(List<Zbor> result)
        {
            List<model.Zbor> zbors = new List<model.Zbor>();
            foreach (CSharp.Zbor z in result)
            {
                model.Zbor zbor = new model.Zbor(z.ID, z.Destinatie, Convert.ToDateTime(z.Plecare), z.Aeroport, z.Locuri);
                zbors.Add(zbor);
            }
            mainWindowForm.TicketBought(zbors);
        }
    }
}
