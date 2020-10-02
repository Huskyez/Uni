using client;
using System;
using System.Windows.Forms;
using services;
using model;
using System.Collections.Generic;

namespace client
{
    public partial class MainWindowForm : Form
    {
        private ClientController clientController;
        private LogInForm logInForm;
        public MainWindowForm(ClientController clientController)
        {
            InitializeComponent();
            this.clientController = clientController;
        }
        public void Set(LogInForm logInForm)
        {
            this.logInForm = logInForm;
            LoadData();
        }
        public void LoadData()
        {
            dataGridViewZboruri.DataSource = clientController.GetZboruri();
        }
        private void LogOut_Click(object sender, EventArgs e)
        {
            this.Hide();
            clientController.LogOut(logInForm.port);
            logInForm.Show();
        }

        private void buttonCautare_Click(object sender, EventArgs e)
        {
            String dest = textBoxDestinatie.Text;
            String data = textBoxData.Text;
            String data1 = data.Remove(10);
            String[] data2 = data1.Split('.');
            String dataCorecta = data2[2] + "-" + data2[1] + "-" + data2[0];
            dataGridViewCautari.DataSource = clientController.GetZboruriCautare(dest, dataCorecta);
        }
        
        internal void firstLoad(IEnumerable<Zbor> zboruri)
        {
            dataGridViewZboruri.DataSource = zboruri;
        }

        private void incarcareAttibute(object sender, DataGridViewCellMouseEventArgs e)
        {
            textBoxOra.Text = dataGridViewCautari.CurrentRow.Cells["ora"].Value.ToString();
        }

        private void buttonCumpara_Click(object sender, EventArgs e)
        {
            String numeClient = textBoxNumeClient.Text;
            String adresaClient = textBoxAdresa.Text;
            String turisti = textBoxTuristi.Text;
            int locuri = Int32.Parse(textBoxLocuri.Text);
            String destinatie = textBoxDestinatie.Text;

            String data = textBoxData.Text;
            String data1 = data.Remove(10);
            String[] data2 = data1.Split('.');
            String dataCorecta = data2[2] + "-" + data2[1] + "-" + data2[0];
            String ora = textBoxOra.Text;
            ora = ora.Remove(5, 3);
            Client client = clientController.FindClient(numeClient, adresaClient);
            Zbor zbor = clientController.FindZbor(destinatie, dataCorecta, ora);
            int index = clientController.FindIndex();
            Bilet bilet = new Bilet(++index, zbor.Id, client.Id, turisti, locuri);
            clientController.CumparaBilet(bilet, zbor.Id, zbor.Locuri - locuri);
            clientController.notifyServer();
        }

        private void incarcareData(object sender, DataGridViewCellMouseEventArgs e)
        {
            textBoxDestinatie.Text = dataGridViewZboruri.CurrentRow.Cells["Destinatie"].Value.ToString();
            textBoxData.Text = dataGridViewZboruri.CurrentRow.Cells["Plecare"].Value.ToString();
        }
    
        delegate void SetDataCallback(DataGridView dgv, IEnumerable<Zbor> list);

        public void TicketBought(Object list)
        {
           dataGridViewZboruri.BeginInvoke(new SetDataCallback(this.SetDataSource), new object[] { dataGridViewZboruri, (IEnumerable<Zbor>)list });
         
        }

        private void SetDataSource(DataGridView dataGridViewZboruri, IEnumerable<Zbor> list)
        {
            dataGridViewZboruri.DataSource = null;
            dataGridViewZboruri.DataSource = list;
        }
    }
}

